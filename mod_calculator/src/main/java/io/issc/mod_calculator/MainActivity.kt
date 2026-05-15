package io.issc.mod_calculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    private lateinit var tvExpression: TextView
    private lateinit var tvResult: TextView
    private val expression = StringBuilder()
    private var resultShown = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvExpression = findViewById(R.id.tvExpression)
        tvResult = findViewById(R.id.tvResult)

        setupNumberButtons()
        setupOperatorButtons()
        setupActionButtons()
    }

    private fun setupNumberButtons() {
        val numberIds = intArrayOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        )
        for (id in numberIds) {
            findViewById<Button>(id).setOnClickListener { view ->
                onNumberClick((view as Button).text.toString())
            }
        }
        findViewById<Button>(R.id.btnDot).setOnClickListener { onDotClick() }
    }

    private fun setupOperatorButtons() {
        findViewById<Button>(R.id.btnAdd).setOnClickListener { onOperatorClick("+") }
        findViewById<Button>(R.id.btnSub).setOnClickListener { onOperatorClick("-") }
        findViewById<Button>(R.id.btnMul).setOnClickListener { onOperatorClick("*") }
        findViewById<Button>(R.id.btnDiv).setOnClickListener { onOperatorClick("/") }
    }

    private fun setupActionButtons() {
        findViewById<Button>(R.id.btnEquals).setOnClickListener { onEqualsClick() }
        findViewById<Button>(R.id.btnClear).setOnClickListener { onClearClick() }
        findViewById<Button>(R.id.btnBack).setOnClickListener { onBackClick() }
    }

    private fun onNumberClick(number: String) {
        if (resultShown) {
            expression.clear()
            resultShown = false
        }
        expression.append(number)
        updateDisplay()
    }

    private fun onDotClick() {
        if (resultShown) {
            expression.clear()
            expression.append("0.")
            resultShown = false
            updateDisplay()
            return
        }
        val currentNumber = getCurrentNumber()
        if (currentNumber.contains(".")) return
        if (currentNumber.isEmpty()) {
            expression.append("0.")
        } else {
            expression.append(".")
        }
        updateDisplay()
    }

    private fun onOperatorClick(operator: String) {
        resultShown = false
        if (expression.isEmpty()) {
            if (operator == "-") {
                expression.append("-")
                updateDisplay()
            }
            return
        }
        val last = expression.last()
        if (last in "+-*/") {
            if (operator == "-" && last in "*/") {
                expression.append(operator)
            } else {
                expression.deleteCharAt(expression.length - 1)
                expression.append(operator)
            }
        } else {
            expression.append(operator)
        }
        updateDisplay()
    }

    private fun onEqualsClick() {
        if (expression.isEmpty()) return

        var expr = expression.toString()
        // Remove trailing operators and dots
        while (expr.isNotEmpty() && expr.last() in "+-*/.") {
            expr = expr.dropLast(1)
        }
        if (expr.isEmpty()) return

        val result = evaluate(expr)
        if (result.isNaN()) {
            Toast.makeText(this, R.string.error_divide_by_zero, Toast.LENGTH_SHORT).show()
            return
        }

        tvExpression.text = formatDisplayExpr(expr) + " ="
        tvResult.text = formatNumber(result)

        expression.clear()
        expression.append(formatNumber(result))
        resultShown = true
    }

    private fun onClearClick() {
        expression.clear()
        resultShown = false
        tvExpression.text = ""
        tvResult.text = "0"
    }

    private fun onBackClick() {
        if (resultShown) {
            onClearClick()
            return
        }
        if (expression.isEmpty()) return
        expression.deleteCharAt(expression.length - 1)
        updateDisplay()
    }

    private fun updateDisplay() {
        val expr = expression.toString()
        tvExpression.text = formatDisplayExpr(expr)
        val current = getCurrentNumber()
        tvResult.text = if (current.isNotEmpty()) current else "0"
    }

    private fun getCurrentNumber(): String {
        val expr = expression.toString()
        val lastOpIndex = expr.indexOfLast { it in "+-*/" }
        return if (lastOpIndex >= 0) expr.substring(lastOpIndex + 1) else expr
    }

    private fun formatDisplayExpr(expr: String): String {
        return expr.replace("*", "×").replace("/", "÷")
    }

    private fun evaluate(expression: String): Double {
        val tokens = tokenize(expression)
        if (tokens.isEmpty()) return Double.NaN

        // First pass: multiply and divide
        val pass1 = mutableListOf<Any>()
        var i = 0
        while (i < tokens.size) {
            val token = tokens[i]
            if (token is Char && (token == '*' || token == '/')) {
                val left = pass1.removeAt(pass1.lastIndex) as Double
                val right = tokens[++i] as Double
                if (token == '/') {
                    if (right == 0.0) return Double.NaN
                    pass1.add(left / right)
                } else {
                    pass1.add(left * right)
                }
            } else {
                pass1.add(token)
            }
            i++
        }

        // Second pass: add and subtract
        var result = pass1[0] as Double
        i = 1
        while (i < pass1.size) {
            val op = pass1[i] as Char
            val num = pass1[i + 1] as Double
            result = if (op == '+') result + num else result - num
            i += 2
        }
        return result
    }

    private fun tokenize(expression: String): List<Any> {
        val tokens = mutableListOf<Any>()
        var i = 0
        while (i < expression.length) {
            val c = expression[i]
            if (c in "+-*/") {
                // Handle leading minus or minus after operator
                if (c == '-' && (tokens.isEmpty() || tokens.last() is Char)) {
                    // Start of a negative number
                    i++
                    val start = i
                    if (i < expression.length && expression[i] == '.') i++
                    while (i < expression.length && expression[i].isDigit()) i++
                    if (i < expression.length && expression[i] == '.') {
                        i++
                        while (i < expression.length && expression[i].isDigit()) i++
                    }
                    if (i == start) {
                        // Just a standalone minus, treat as operator
                        tokens.add(c)
                    } else {
                        tokens.add(-parseDouble(expression.substring(start, i)))
                    }
                } else {
                    tokens.add(c)
                    i++
                }
            } else {
                val start = i
                if (c == '.') i++
                while (i < expression.length && expression[i].isDigit()) i++
                if (i < expression.length && expression[i] == '.' &&
                    (i + 1 < expression.length && expression[i + 1].isDigit())
                ) {
                    i++
                    while (i < expression.length && expression[i].isDigit()) i++
                }
                tokens.add(parseDouble(expression.substring(start, i)))
            }
        }
        return tokens
    }

    private fun parseDouble(s: String): Double {
        return if (s == ".") 0.0 else s.toDouble()
    }

    private fun formatNumber(value: Double): String {
        if (value.isNaN()) return getString(R.string.error_expression)
        if (value.isInfinite()) return getString(R.string.error_divide_by_zero)
        return if (value == value.toLong().toDouble()) {
            value.toLong().toString()
        } else {
            // Limit decimal places to avoid display overflow
            val formatted = "%.10f".format(value).trimEnd('0').trimEnd('.')
            formatted
        }
    }
}
