package ru.itis.javaLab;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	    List<String> list1 = new ArrayList<>();
	    for (int i = 0; i < 10; i++) {
	        list1.add("1");
        }
        List<String> list2 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list1.add("2");
        }
        List<String> resultList = new ArrayList<>();
        MyThread thread1 = new MyThread(list1, resultList);
        MyThread thread2 = new MyThread(list2, resultList);
        synchronized (resultList) {
            thread1.start();
            thread2.start();
        }

    }
}
