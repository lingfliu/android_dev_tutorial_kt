package io.issc.kotlin_basics;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DemoAcitivity extends AppCompatActivity {

    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {

//        Button btn = findViewById(R.id.btn);
//        btn.setOnClickListener(v -> System.out.println("Hello World"));
        return super.onCreateView(name, context, attrs);
    }
}
