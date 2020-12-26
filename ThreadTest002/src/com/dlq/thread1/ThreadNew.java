package com.dlq.thread1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @program: Thread
 * @description: 创建多线程方式三
 * @author: Hasee
 * @create: 2020-07-21 11:07
 *
 *
 * 创建多线程的方式三：实现Callable接口。 ---JDK5.0新增
 *
 * 如何理解实现Callable接口的方式创建多线程比实现Runnable接口创建多线程方式强大？
 * 1、call()可以有返回值的
 * 2、call()可以抛出异常，被外面的操作捕获，获取异常的信息
 * 3、Callable是支持泛型的
 *
 */

//1、创建一个实现Callable的实现类
class NumThread implements Callable<Integer>{
    //2、实现call方法，将此线程需要执行的操作声明在call()中
    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName()+":"+i);
                sum += i;
            }
        }
        return sum;
    }
}

public class ThreadNew {
    public static void main(String[] args) {
        //3、创建Callable接口实现类的对象
        NumThread numThread = new NumThread();
        //4、将此Callable接口实现类的对象作为参数传递到FutureTask构造器中，创建FutureTask对象。
        FutureTask<Integer> futureTask = new FutureTask<>(numThread);

        //5、将FutureTask的对象作为参数传递到Thread类的构造器中，创建Thread对象，并调用start()
        new Thread(futureTask,"A").start();

        try {
            //6、如果对call()方法的返回值感兴趣，就可以获取Callable接口实现类中call()的返回值
            //get()返回值即为FutureTask构造器参数 Callable接口实现类 重写的call()的返回值
            Integer sum = futureTask.get();
            System.out.println("总和为： " + sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
