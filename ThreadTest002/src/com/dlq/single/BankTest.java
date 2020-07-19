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
}

class Bank{

    private Bank(){}

    private static Bank instance = null;

    public static Bank getInstance(){
        //方式一：效率稍差
//        synchronized (Bank.class) {
//            if (instance == null){
//                instance = new Bank();
//            }
//            return instance;
//        }
        //方式二：效率更高
        if (instance == null){
            synchronized (Bank.class) {
                if (instance == null){
                    instance = new Bank();
                }
            }
        }
        return instance;
    }
}
