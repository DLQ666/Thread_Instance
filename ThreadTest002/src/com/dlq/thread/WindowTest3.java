package com.dlq.thread;

/**
 * @program: Thread
 * @description: 使用多线程买票3
 * @author: Hasee
 * @create: 2020-07-19 16:30
 * <p>
 *
 * 使用同步代码块解决继承Thread类的方式的线程安全问题
 *
 * 例子：创建三个窗口买票，总票数为100张，使用继承Thread类的方式
 *
 * 说明：在继承Thread类创建多线程的方式中，慎用this充当同步监视器，考虑使用当前类充当同步监视器。
 */

class Window3 extends Thread {

    private static int ticket = 100;
    private static Object obj = new Object();

    @Override
    public void run() {

        while (true) {
            //正确的
//            synchronized (obj) {
            synchronized (Window3.class) {//Class clazz = Window3.class, Window3.class只会加载一次
              //错误的方式：this代表着 t1,t2,t3三个对象
//            synchronized(this){
                if (ticket > 0) {

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + ":卖票，票号为： " + ticket);
                    ticket--;
                } else {
                    break;
                }
            }
        }
    }
}

public class WindowTest3 {
    public static void main(String[] args) {
        Window3 t1 = new Window3();
        Window3 t2 = new Window3();
        Window3 t3 = new Window3();

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}
