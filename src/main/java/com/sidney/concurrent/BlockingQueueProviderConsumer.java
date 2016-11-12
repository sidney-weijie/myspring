package com.sidney.concurrent;




import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 公共常量
 * @author 骆昊
 *
 */
class Constants1 {
    public static final int MAX_BUFFER_SIZE = 10;
    public static final int NUM_OF_PRODUCER = 2;
    public static final int NUM_OF_CONSUMER = 3;
}

/**
 * 工作任务
 * @author 骆昊
 *
 */
class Task1 {
    private String id;  // 任务的编号

    public Task1() {
        id = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "Task[" + id + "]";
    }
}

/**
 * 消费者
 * @author 骆昊
 *
 */
class Consumer1 implements Runnable {
    private BlockingQueue<Task1> buffer;

    public Consumer1(BlockingQueue<Task1> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Task1 task = buffer.take();
                System.out.println("Consumer[" + Thread.currentThread().getName() + "] got " + task);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * 生产者
 * @author 骆昊
 *
 */
class Producer1 implements Runnable {
    private BlockingQueue<Task1> buffer;

    public Producer1(BlockingQueue<Task1> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Task1 task = new Task1();
                buffer.put(task);
                System.out.println("Producer[" + Thread.currentThread().getName() + "] put " + task);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}

public class BlockingQueueProviderConsumer {

    public static void main(String[] args) {
        BlockingQueue<Task1> buffer = new LinkedBlockingQueue<>(Constants1.MAX_BUFFER_SIZE);
        ExecutorService es = Executors.newFixedThreadPool(Constants1.NUM_OF_CONSUMER + Constants1.NUM_OF_PRODUCER);
        for(int i = 1; i <= Constants1.NUM_OF_PRODUCER; ++i) {
            es.execute(new Producer1(buffer));
        }
        for(int i = 1; i <= Constants1.NUM_OF_CONSUMER; ++i) {
            es.execute(new Consumer1(buffer));
        }
    }
}