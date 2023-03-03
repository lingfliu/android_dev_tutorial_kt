package io.issc.kotlin_basics;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class Container {

    BigDecimal val;

    public BigDecimal add(double y) {
        return val.add(BigDecimal.valueOf(y));
    }

    public void closureDemo() {
        final int x = 10;
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(x);
            }
        };

        r.run();

    }

    public void demo(Object val) {

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello");
            }
        });
        t.start();

        byte[] aaa = new byte[128];
        List<String> s;
        Set<String> ss = new HashSet<>();
        Map<String, String> mm = new HashMap<>();
        Queue<String> qq = new LinkedList<>();
        Deque<String> dd = new LinkedList<>();

        List<Integer> numList = new ArrayList<>();
        for (int m = 0; m < 10; m ++) {
            numList.add(m);
        }
        List<Integer> filteredList = new ArrayList<>();
        for (int m = 0; m < 10; m ++) {
            Integer v = numList.get(m);
            if (v > 5) {
                filteredList.add(v);
            }
        }
        numList.stream().filter(v -> v > 5).forEach(v -> filteredList.add(v));
        SortedMap<String, String> smm = new TreeMap<>();

        List<Object> info = new LinkedList<>();
        info.add("String");
        info.add(1);
        info.add(new BaseType(1, "aa"));

        for (Object i : info) {
            String className = i.getClass().getName();
        }

        String className = val.getClass().getName();

        if (className.equals("BigDecimal")) {
            //针对BigDecimal操作
        }
        else if (className.equals("String")) {
            //针对字符串的操作
        }

        List<String> strList = new ArrayList<>();
    }
}
