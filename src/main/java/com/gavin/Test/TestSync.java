package com.gavin.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Gavin
 * Mail: gavinchangcn@163.com
 * Date: 2015/8/23 0023
 * Time: 14:46
 */
public class TestSync implements Runnable{
    private Foo foo = new Foo() ;

    public static void main(String[] args) {
        TestSync r = new TestSync() ;
        Thread ta = new Thread(r, "Thread-A") ;
        Thread tb = new Thread(r, "Thread-B") ;
        ta.start();
        tb.start();
    }
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            this.fix(30) ;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":当前foo对象的x值= " + foo.getX());
        }
    }

    public int fix (int y) {
        return foo.fix(y) ;
    }
    public class Foo {
        private int x = 100 ;

        public synchronized int getX() {
            return x ;
        }

        public synchronized int fix( int y ) {
            x -= y ;
            return x ;
        }
    }
}
