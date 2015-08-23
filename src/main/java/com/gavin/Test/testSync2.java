package com.gavin.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Gavin
 * Mail: gavinchangcn@163.com
 * Date: 2015/8/23 0023
 * Time: 15:16
 */
public class testSync2 {

    public static void main(String[] args) {
        User u = new User("张三", 100) ;
        MyThread t1 = new MyThread("线程A", u, 20) ;
        MyThread t2 = new MyThread("线程B", u, -60) ;
        MyThread t3 = new MyThread("线程C", u, -80) ;
        MyThread t4 = new MyThread("线程D", u, -30) ;
        MyThread t5 = new MyThread("线程E", u, 32) ;
        MyThread t6 = new MyThread("线程F", u, 21) ;

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
    }
    // 模拟用户的编码和现今额
    static class User {
        private String code;
        private int cash;

        User(String code, int cash) {
            this.code = code;
            this.cash = cash;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getCash() {
            return cash;
        }

        public void setCash(int cash) {
            this.cash = cash;
        }

        /**
         * 业务方法
         * @param x 添加x万元
         */
        public synchronized void oper(int x) {
            try {
                Thread.sleep(10L);
                this.cash += x;
                System.out.println(Thread.currentThread().getName() + "运行结束，增加“" + x
                    + "”，当前用户约为：" + cash);
                Thread.sleep(10L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "User{" +
                    "code='" + code + '\'' +
                    ", cash=" + cash +
                    '}';
        }
    }

    static class MyThread extends Thread {
        private User u ;
        private int y = 0 ;

        MyThread(String name, User u, int y) {
            super(name);
            this.u = u;
            this.y = y;
        }

        @Override
        public void run() {
            u.oper(y) ;
        }
    }
}
