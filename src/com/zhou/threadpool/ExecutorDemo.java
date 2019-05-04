package com.zhou.threadpool;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ExecutorDemo {
	
	/**
	 * 使用系统定义好的线程池有下面4种方式
	 */
	 private static Executor executors = Executors.newCachedThreadPool();//缓存线程池
     private static Executor executor2 = Executors.newFixedThreadPool(5);//固定线程个数的线程池
     private static Executor executor3 = Executors.newScheduledThreadPool(5);//计划任务线程池
     private static Executor executor4 = Executors.newSingleThreadExecutor();//单个线程的线程池
     
     /**
      * 使用的时候直接 executor2.execute(实现Runnable)
      * @param args
      */
     public static void main(String[] args) {
          for (int i = 0; i < 100; i++) {
              executor2.execute(new Runnable() {
                   
                   @Override
                   public void run() {
                        method();
                   }
              });
          }
     }
     
     public static void method() {
          System.out.println("ThreadName =" + Thread.currentThread().getName()+"过来了");
          try {
              Thread.sleep(2000);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
          System.out.println("ThreadName =" + Thread.currentThread().getName()+"出去了");
     }
}
