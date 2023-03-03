package io.issc.kotlin_basics;

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

    public void test() {
        System.out.println("Hello from Java");
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
