package com.zhou.threadpool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolExecutorDemo {
	 public static void method() {
         System.out.println("ThreadName=" + Thread.currentThread().getName()+"过来了");
         try {
             Thread.sleep(2000);
         } catch (InterruptedException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }
         System.out.println("ThreadName=" + Thread.currentThread().getName()+"出去了");
    }
    
    public static void main(String[] args) {
         LinkedBlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<>(10);//10是自己设定的该容器的最大上限
         ThreadFactory threadFactory = new ThreadFactory() {
             //线程安全的int的包装类
             AtomicInteger atomicInteger =  new AtomicInteger(0);
             @Override
             public Thread newThread(Runnable r) {
                  //创建一个线程，然后把r赋值给该线程
                  Thread thread = new Thread(r);
                  thread.setName("MyThread="+ atomicInteger.getAndIncrement());
                  return thread;
             }
         };
         
         /**
          * 参数1：核心池个数  如果设置为0，那么最后控制台真的会执行完毕，只要有一个线程还在，那么控制台就不会执行完毕
          * 参数2：最大线程池上限个数  
          * 参数3：任务执行完之后，要裁员的延时
          * 参数4：时间单位
          * 参数5：用于存储任务的工作队列
          * 参数6：线程工厂，用于创建线程
          */
         ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(3, 5, 1, TimeUnit.SECONDS, blockingQueue, threadFactory);
         
         //循环能接受的最大的就是10+5=15个，超过15就会报异常
         for (int i = 0; i < 15; i++) {
             poolExecutor.execute(new Runnable() {
                  
                  @Override
                  public void run() {
                       method();
                  }
             });
         }
    }
}
