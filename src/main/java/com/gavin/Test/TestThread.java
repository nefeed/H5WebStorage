package com.gavin.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Gavin
 * Mail: gavinchangcn@163.com
 * Date: 2015/8/23 0023
 * Time: 14:11
 */
public class TestThread {

    public static class DoSomething implements Runnable {
        private String name ;

        public DoSomething() {

        }
        public DoSomething( String name ) {
            this.name = name ;
        }

        @Override
        public void run() {
            for ( int i = 0 ; i < 5 ; i ++ ) {
                System.out.println( name + ":" + i ) ;
            }
        }
    }


    public static class OutThread extends Thread {
        public OutThread( String name ) {
            super(name);
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println(this.getName() +" :" +i);
            }
        }
    }

    public static class SleepThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i <100; i++) {
                if ( i % 10 == 0 ) {
                    System.out.println("------" + i);
                }
                System.out.print(i);
                try {
                    Thread.sleep(100);
                    System.out.print("        线程睡眠100毫秒！\n");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) {
//        DoSomething ds1 = new DoSomething("张三") ;
//        DoSomething ds2 = new DoSomething("李四") ;
//
//
//        Thread t1 = new Thread(ds1) ;
//        Thread t2 = new Thread(ds2) ;
        Thread t3 = new OutThread("王五") ;
//        Thread t4 = new OutThread("赵六") ;
//
//        t1.start() ;
//        t2.start() ;
//        t3.start();
//        t4.start();
        Thread sleepT1 = new SleepThread() ;
        sleepT1.setPriority(Thread.MAX_PRIORITY);
        t3.setPriority(Thread.MIN_PRIORITY);
        sleepT1.start();
        t3.start();
        t3.yield();

    }
}
