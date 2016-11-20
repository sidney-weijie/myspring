package com.sidney.concurrent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class AddThread implements Runnable {
    private List<Double> list;

    public AddThread(List<Double> list) {
        this.list = list;
    }

    public void run() {
        for(int i = 0; i < 10000; ++i) {
            list.add(Math.random());
        }
    }
}

public class TestCopyOnWriteList {
    private static final int THREAD_POOL_SIZE = 2;

    public static void main(String[] args) {
        List<Double> list = new ArrayList<Double>();
        /**
         * ���б��δ�����ܻ���� Exception in thread "pool-1-thread-1" java.lang.ArrayIndexOutOfBoundsException: 9369����
         * �������ΪList<Double> list = new CopyOnWriteArrayList<>();���ɱ��������������
         * 
         * CopyOnWriteArrayList��ArrayList�ڲ��������µ����Ʒ��CopyOnWriteArrayListͨ������дʱ�������������Ⲣ��������������⣬
         * 
         * Ҳ����˵�κ��޸Ĳ��������ڵײ㴴��һ���б�ĸ�����Ҳ����ζ��֮ǰ���еĵ�����������������֮����޸ġ����ַ�ʽ���ڲ�Ҫ�ϸ��дͬ���ĳ����ǳ����ã�
         * 
         * ��Ϊ���ṩ�˸��õ����ܡ�
         */
        ExecutorService es = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        
        es.execute(new AddThread(list));
        es.execute(new AddThread(list));
        es.shutdown();
    }
}
