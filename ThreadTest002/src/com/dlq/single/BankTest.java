package com.dlq.single;

/**
 * @program: Thread
 * @description: 使用同步机制将单例模式中懒汉式改写为线程安全的
 * @author: Hasee
 * @create: 2020-07-19 21:28
 *
 *
 * 使用同步机制将单例模式中懒汉式改写为线程安全的
 *
 */
public class BankTest {

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            new Thread(()->{
                System.out.println(Bank.getInstance().hashCode());
            }).start();
        }
    }
}

class Bank{

    private Bank(){}

    private static volatile Bank instance = null;

    public static /*synchronized*/ Bank getInstance(){
        //方式一：效率稍差
//        synchronized (Bank.class) {
//            if (instance == null){
//                instance = new Bank();
//            }
//            return instance;
//        }
        //方式二：效率更高
        if (instance == null){ //Double Check Lock  DCL双重检查
            synchronized (Bank.class) {
                if (instance == null){
                    instance = new Bank();
                }
            }
        }
        return instance;
    }
}
