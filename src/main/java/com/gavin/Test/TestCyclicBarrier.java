package com.gavin.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created with IntelliJ IDEA.
 * 测试线程阻碍器
 * User: Gavin
 * Mail: gavinchangcn@163.com
 * Date: 2015/8/23 0023
 * Time: 16:08
 */
public class TestCyclicBarrier {
    public static void main(String[] args) {
        // 创建障碍器，并设置MainTask为所有定数量的线程都达到障碍点时候所要执行的任务(Runnable)
        CyclicBarrier cb = new CyclicBarrier(7, new MainTask()) ;
        new SubTask("A", cb).start();
        new SubTask("B", cb).start();
        new SubTask("C", cb).start();
        new SubTask("D", cb).start();
        new SubTask("E", cb).start();
        new SubTask("F", cb).start();
        new SubTask("G", cb).start();
    }

    /**
     * 主任务
     */
    private static class MainTask implements Runnable {
        @Override
        public void run() {
            System.out.println(">>>>主任务执行了！<<<<");
        }
    }

    private static class SubTask extends Thread {
        private String name;
        private CyclicBarrier cb;

        SubTask(String name, CyclicBarrier cb) {
            this.name = name;
            this.cb = cb;
        }

        public void run() {
            System.out.println("[子任务" + name +"]开始执行了！");
            for (int i = 0; i < 999999; i++) ;    //模拟耗时的任务
            System.out.println("[子任务" + name +"]开始执行完成了，并通知障碍器已经完成！");
            try {
                //通知障碍器已经完成
                cb.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
