package com.qianjin.support;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class OrderQueue {

    private Consumer c = new Consumer();
    private static OrderQueue instance = new OrderQueue();

    private OrderQueue() {
    }

    public static OrderQueue getQueue() {
        return instance;
    }

    private BlockingQueue<Order> queue = new LinkedBlockingQueue<Order>();

    public void put(Order order) {
        try {
            queue.put(order);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(c.getState().equals(Thread.State.NEW)){
            c.start();
        }
    }

    public Order get() {
        try {
            return queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void stop() {
        c.stopRuning();
    }

    private static class Consumer extends Thread {

        private boolean run = true;

        public void stopRuning() {
            run = false;
        }

        @Override
        public void run() {
            while (run) {
                Order order = OrderQueue.getQueue().get();
                if (order == null) {
                    continue;
                }
                MessageSender.send(order);
            }
        }
    }
}
