package com.dlq.thread;

/**
 * @program: Thread
 * @description: 使用同步方法解决实现Runnable接口的线程安全问题
 * @author: Hasee
 * @create: 2020-07-19 21:05
 *
 * 使用同步方法解决实现Runnable接口的线程安全问题
 *
 *
 * 关于同步方法的总结：
 * 1、同步方法仍然涉及到同步监视器，只是不需要我们显示的声明。
 * 2、非静态的同步方法，同步监视器是：this
 *    静态的同步方法，同步监视器是：当前类本身
 *
 */

class Window4 implements Runnable {

    private int ticket = 100;

    @Override
    public void run() {
        while (true) {
            show();
        }
    }

    private synchronized void show(){//同步监视器：this
//        synchronized(this){

            if (ticket > 0) {

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + ":卖票，票号为：" + ticket);
                ticket--;
            }
//        }
    }
}

public class WindowTest4 {
    public static void main(String[] args) {
        Window4 w = new Window4();

        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}
