package learn;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
  /***
   * Implements  Runnable interface
   * 
   * ***/
 class Adaemons implements Runnable{
	@Override
	public void run() {
			// TODO Auto-generated method stub	
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public void start(){
		this.start();
	}
 }
 /***
  * Extends  Thread class
  * 
  * ***/
 class AdaemonThread extends Thread{

	public void run() {
			// TODO Auto-generated method stub	
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public void start(){
		super.start();
	}
 }
 /***
  * Implements  Callable interface
  * 
  * ***/
  class Callables implements Callable<String>{
		@Override
		public String call() throws Exception {
			// TODO Auto-generated method stub
			TimeUnit.SECONDS.sleep(1);
			return Thread.currentThread().getName();
		} 
		public void start(){
			this.start();
		}
  }
  /***
   * Implements  Callable interface
   * 
   * ***/
   class Sleeper extends Thread{
 		@Override
 		public void run()  {
 			System.out.println("Sleeper");
 			// TODO Auto-generated method stub
 			try {
				TimeUnit.SECONDS.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("awake");
			}
 		} 
 		public void start(){
 			super.start();
 		}
   }
 class ResponsiveUI extends Thread{
	 private static volatile double d=1;
	 public static double getD(){
		 return d;
	 } 
	 public ResponsiveUI(){
		 this.setDaemon(true);
		 this.start();
	 }
	 public void run(){
		 while(true){
			 d=d=(Math.PI+Math.E)/d;
		 }
	 }
 }
 /***throw a RuntimeException***/
 class ExceptionThread extends Thread{	
	 public void run(){
		 try{		
			 throw new RuntimeException();
           }catch(Exception e){
        	   
           }
        throw new RuntimeException();
           
	 }
 }
 /****
  * 未捕获的异常处理器
  * ***/
 class UncaughtExceptionHandler implements Thread.UncaughtExceptionHandler{
	@Override
	public void uncaughtException(Thread t, Throwable e) {
		// TODO Auto-generated method stub
		System.out.println("catch "+e);
	} 
 }
 /****
  * 线程池
  * *****/
 class HandlerThreadFactory implements ThreadFactory{
	@Override
	public Thread newThread(Runnable r) {
		// TODO Auto-generated method stub
		Thread thread=new Thread(r);
		thread.setUncaughtExceptionHandler(new UncaughtExceptionHandler());
		System.out.println("eh= "+thread.getUncaughtExceptionHandler());
		return thread;
	} 
 }
 public class Adaemon{
	public static void main(String[] args) throws InterruptedException, Exception{
		 List<Future<String>> results=new ArrayList<Future<String>>();
		 ExecutorService exc=Executors.newCachedThreadPool();
		 Long startTime=System.currentTimeMillis();
		 for(int i=0;i<10;i++){
			  results.add(exc.submit(new Callables()));
		  }
		 for(Future<String>fc:results){
			 try {
				System.out.println(fc.get());
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				exc.shutdown(); 
			}
		 }
		System.out.println("Implements Callable and Executors cost time:"+(System.currentTimeMillis()-startTime)+"ms");
		startTime=System.currentTimeMillis();
		List<Thread> adaemons=new ArrayList<Thread>();
		for(int i=0;i<10;i++){
			Thread adaemon=new Thread(new Adaemons());
			adaemon.start();
			adaemons.add(adaemon);
		}
		for(Thread adaemon: adaemons){
			adaemon.join();
		}
		System.out.println("Implements Runable and list cost time:"+(System.currentTimeMillis()-startTime)+"ms"); 
		startTime=System.currentTimeMillis();
		adaemons.clear();
		for(int i=0;i<10;i++){
			Thread adaemon=new AdaemonThread();
			adaemon.start();
			adaemons.add(adaemon);
		}
		for(Thread adaemon: adaemons){
			adaemon.join();
		}
		/***将自定义的未捕获异常处理器设置为默认的未捕获处理器***/
		Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler());
		exc=Executors.newCachedThreadPool();
		//exc=Executors.newCachedThreadPool(new HandlerThreadFactory());
		System.out.println("Extends thread and list cost time:"+(System.currentTimeMillis()-startTime)+"ms");
		try{
			exc.execute(new ExceptionThread());
		}catch(Exception e){
			
		}
	}
}