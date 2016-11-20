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
         * 运行本段代码可能会出现 Exception in thread "pool-1-thread-1" java.lang.ArrayIndexOutOfBoundsException: 9369错误
         * 将上面改为List<Double> list = new CopyOnWriteArrayList<>();即可避免这种情况产生
         * 
         * CopyOnWriteArrayList是ArrayList在并发环境下的替代品。CopyOnWriteArrayList通过增加写时复制语义来避免并发访问引起的问题，
         * 
         * 也就是说任何修改操作都会在底层创建一个列表的副本，也就意味着之前已有的迭代器不会碰到意料之外的修改。这种方式对于不要严格读写同步的场景非常有用，
         * 
         * 因为它提供了更好的性能。
         */
        ExecutorService es = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        
        es.execute(new AddThread(list));
        es.execute(new AddThread(list));
        es.shutdown();
    }
}
