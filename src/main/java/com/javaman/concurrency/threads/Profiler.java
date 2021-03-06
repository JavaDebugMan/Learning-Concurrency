package com.javaman.concurrency.threads;

import java.util.concurrent.TimeUnit;

/**
 * @author:彭哲
 * @Date:2017/12/4
 * ThreadLocal:线程变量,是一个以ThreadLocal对象为键,任意对象为值得存储结构
 * 这个结构被附带在线程上,
 * 也就是说一个线程可以根据一个ThreadLocal对象查询到绑定到这个线程上的一个值
 * 通过set(T)方法来设置一个值,在当前线程下在通过get()方法获取到原先设置的值
 */
public class Profiler {
    /**
     * 第一次get()方法调用时会进行初始化(如果set方法没有调用),每个线程会调用一次
     */
    private static final ThreadLocal<Long> TIME_THREADLOCAL = new ThreadLocal<Long>() {
        @Override
        protected Long initialValue() {
            return System.currentTimeMillis();
        }
    };

    public static final void begin() {
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }

    public static final long end() {
        return System.currentTimeMillis() - TIME_THREADLOCAL.get();
    }

    public static void main(String[] args) throws InterruptedException {
        Profiler.begin();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Cost:" + Profiler.end() + "mills");
    }


}
