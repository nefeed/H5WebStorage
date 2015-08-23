package com.gavin.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 * 测试Java5提供的锁Lock
 * User: Gavin
 * Mail: gavinchangcn@163.com
 * Date: 2015/8/23 0023
 * Time: 15:46
 */
public class TestLock {
    public static void main(String[] args) {
        // 创建并发访问的丈夫
        MyCount myCount = new MyCount("95599200901215522", 10000) ;
        // 创建一个 锁 对象
        Lock lock = new ReentrantLock() ;
        // 创建一个线程池
        ExecutorService pool = Executors.newCachedThreadPool() ;
        // 创建一些并发访问的用户，一个信用卡，存归存，取归取
        User u1 = new User("张三", myCount, -4000, lock) ;
        User u2 = new User("张三他爹", myCount, 6000, lock);
        User u3 = new User("张三他弟", myCount, -8000, lock);
        User u4 = new User("张三", myCount, 800, lock);
        // 在线程池中执行各个用户的操作
        pool.execute(u1);
        pool.execute(u2);
        pool.execute(u3);
        pool.execute(u4);
        // 关闭线程池
        pool.shutdown();
    }

    private static class MyCount {
        private String oid ;
        private int cash ;

        public MyCount(String oid, int cash) {
            this.oid = oid;
            this.cash = cash;
        }

        public String getOid() {
            return oid;
        }

        public void setOid(String oid) {
            this.oid = oid;
        }

        public int getCash() {
            return cash;
        }

        public void setCash(int cash) {
            this.cash = cash;
        }

        @Override
        public String toString() {
            return "MyCount{" +
                    "oid='" + oid + '\'' +
                    ", cash=" + cash +
                    '}';
        }
    }

    private static class User implements Runnable {
        private String name ;
        private MyCount myCount ;
        private int iocash ;
        private Lock myLock ;

        public User(String name, MyCount myCount, int iocash, Lock lock) {
            this.name = name ;
            this.myCount = myCount ;
            this.iocash = iocash ;
            this.myLock = lock;
        }

        @Override
        public void run() {
            // 获取锁
            myLock.lock();
            // 执行现金业务
            System.out.println(name + "正在操作" + myCount + "账户，金额为" + iocash + ",当前金额为" + myCount.getCash() );
            myCount.setCash(myCount.getCash() + iocash);
            System.out.println(name + "操作" + myCount + "账户成功，金额为" + iocash + "，当前金额为" + myCount.getCash() );
            // 释放锁，后者别的线程没有机会执行
            myLock.unlock();
        }
    }
}
