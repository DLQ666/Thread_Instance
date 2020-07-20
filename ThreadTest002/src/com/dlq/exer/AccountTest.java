package com.dlq.exer;

/**
 * @program: Thread
 * @description:
 * @author: Hasee
 * @create: 2020-07-20 17:54
 *
 * 银行有一个账户。
 *  有两个储户分别向同一账户存3000元，每次存1000，存3次。每次存完打印账户余额。
 *
 *      分析：
 *      1、是否多线程？ 是，两个储户
 *      2、是否有共享数据？ 有，账户（或账户余额）
 *      3、是否有线程安全问题？ 有
 *      4、需要考虑如何解决线程安全问题？ 同步机制：有三种方式
 *
 */

class Account{
    private double balance;

    public Account(double balance) {
        this.balance = balance;
    }

    //存钱方法
    public synchronized void deposit(double amt) {
        if (amt > 0) {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            balance += amt;
            System.out.println(Thread.currentThread().getName() + ":存钱成功。余额为：" + balance);
        }
    }
}

class Customer implements Runnable {

    private Account acct;

    public Customer(Account acct) {
        this.acct = acct;
    }

    @Override
    public void run() {

        for (int i = 0; i < 3; i++) {
            acct.deposit(1000);
        }
    }
}

public class AccountTest {
    public static void main(String[] args) {
        Account acct = new Account(0);

        Customer customer = new Customer(acct);

        Thread t1 = new Thread(customer);
        Thread t2 = new Thread(customer);

        t1.setName("甲");
        t2.setName("乙");

        t1.start();
        t2.start();
    }
}
