package io.issc.kotlin_basics

import android.util.Log
import kotlinx.coroutines.*

/**
 * 协程演示：结构化并发、async/await、取消、超时
 *
 * 调用方式：在 MainActivity.onCreate() 中取消注释：
 *   CoroutineDemo.runAll()
 */
class CoroutineDemo {
    companion object {
        const val TAG = "coroutine_demo"

        fun runAll() {
            Log.d(TAG, "=== coroutine demo start ===")

            // 1. 结构化并发 -- coroutineScope 会等待所有子协程完成
            CoroutineScope(Dispatchers.Main).launch {
                structuredConcurrency()
            }

            // 2. async/await -- 并行分解计算
            CoroutineScope(Dispatchers.Default).launch {
                asyncAwaitDemo()
            }

            // 3. Job 取消与 join
            CoroutineScope(Dispatchers.Default).launch {
                jobCancelDemo()
            }

            // 4. withTimeoutOrNull -- 超时处理
            CoroutineScope(Dispatchers.Default).launch {
                timeoutDemo()
            }

            // 5. supervisorScope -- 子协程异常隔离
            CoroutineScope(Dispatchers.Default).launch {
                supervisorDemo()
            }
        }

        /** 结构化并发：coroutineScope 内并发执行多个子协程，全部完成后才返回 */
        suspend fun structuredConcurrency() = coroutineScope {
            Log.d(TAG, "structuredConcurrency start")
            val start = System.currentTimeMillis()

            val job1 = launch {
                delay(300)
                Log.d(TAG, "  job1 done")
            }
            val job2 = launch {
                delay(200)
                Log.d(TAG, "  job2 done")
            }
            // 无需 join()，coroutineScope 会自动等待所有子协程

            val elapsed = System.currentTimeMillis() - start
            Log.d(TAG, "structuredConcurrency end (elapsed: ${elapsed}ms, expected ~300ms)")
        }

        /** async/await：两个计算并行执行，结果汇总 */
        suspend fun asyncAwaitDemo() = coroutineScope {
            Log.d(TAG, "asyncAwaitDemo start")

            val deferred1 = async {
                delay(500)
                (1..100).sum() // 计算 1+2+...+100
            }
            val deferred2 = async {
                delay(500)
                (1..100).fold(1) { acc, i -> acc * i } // 计算 100! (简化为溢出示意)
            }

            val sum = deferred1.await()
            Log.d(TAG, "  sum(1..100) = $sum")
            Log.d(TAG, "  both computations ran in parallel (~500ms total, not ~1000ms)")
        }

        /** Job 生命周期：launch → join → cancel */
        suspend fun jobCancelDemo() = coroutineScope {
            Log.d(TAG, "jobCancelDemo start")

            val job = launch {
                repeat(10) { i ->
                    delay(100)
                    Log.d(TAG, "  job working... iteration $i")
                }
            }

            delay(350) // 让 job 跑一会儿
            Log.d(TAG, "  cancelling job...")
            job.cancel()
            job.join()
            Log.d(TAG, "  job cancelled: ${job.isCancelled}")
        }

        /** withTimeoutOrNull：超过时限自动取消 */
        suspend fun timeoutDemo() = coroutineScope {
            Log.d(TAG, "timeoutDemo start")

            val result = withTimeoutOrNull(300) {
                delay(500) // 模拟耗时操作
                "finished"
            }
            Log.d(TAG, "  result: $result (expected null, because 500ms > 300ms timeout)")

            val result2 = withTimeoutOrNull(500) {
                delay(200)
                "done"
            }
            Log.d(TAG, "  result2: $result2 (expected 'done', 200ms < 500ms timeout)")
        }

        /** supervisorScope：一个子协程失败不影响其他子协程 */
        suspend fun supervisorDemo() = supervisorScope {
            Log.d(TAG, "supervisorDemo start")

            launch {
                delay(100)
                Log.d(TAG, "  child1 done")
            }

            launch {
                delay(200)
                throw RuntimeException("child2 failed!")
            }

            launch {
                delay(300)
                Log.d(TAG, "  child3 done (unaffected by child2 failure)")
            }

            delay(500)
            Log.d(TAG, "supervisorDemo end")
        }
    }
}
