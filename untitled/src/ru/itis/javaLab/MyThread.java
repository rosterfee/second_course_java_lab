package ru.itis.javaLab;

import java.util.List;

public class MyThread extends Thread {

    private List<String> list;
    private List<String> resultList;

    public MyThread(List<String> list, List<String> resultList) {
        this.list = list;
        this.resultList = resultList;
    }

    @Override
    public void run() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
            resultList.add(list.get(i));
            try {
                sleep(10);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
