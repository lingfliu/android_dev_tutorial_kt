package io.issc.kotlin_basics;

/**
 * Java 类，用于示范如何用kotlin调用
 */
public class ObjJava extends BaseObj implements ListenerJ {
    public String name;
    public double val;

    public ObjJava(String name, double val) {
        this.name = name;
        this.val = val;
    }

    public ObjJava(String name) {
        this.name = name;
        this.val = 0.0;
    }

    public int test() {
        System.out.println("Hello from Java");
        return 0;
    }

    public void loop() {
        for (int m = 0; m < 100; m ++) {
            System.out.println("Looping from Java");
        }
    }

    @Override
    public void onEvent(ObjJava obj) {
    }
}
