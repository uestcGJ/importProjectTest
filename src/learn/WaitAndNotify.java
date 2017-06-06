package learn;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Car{
	private volatile  boolean isReady=false;
	 public synchronized  void setReady(){
    	 isReady=true;
  	     notifyAll();
    }
    public synchronized  void setSpace(){
   	     isReady=false;
 	     notifyAll();
    }
    public synchronized  void waitForReady( ){
	   while(!isReady){
		try {
			  wait();		
			} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }
	  
   }
    public synchronized  void waitForSpace( ){
 	   while(isReady){
 		try {
 			wait();
 		} catch (InterruptedException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
 	   }
    } 
}
 class DoJob extends Thread{
	 private Car car;
	 public DoJob(Car car){
		 this.car=car;
	 }
	 public void run(){
		try {
				while(!Thread.interrupted()){
					System.out.println("===== working=====");
					TimeUnit.MILLISECONDS.sleep(200);
					car.setReady();
					car.waitForSpace();
			}
			} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	 }
}
 class DoWait extends Thread{
	 private Car car;
	 public DoWait(Car car){
		 this.car=car;
	 }
	 public void run(){
		try {
			 while(!Thread.interrupted()){
				 car.waitForReady();
				 System.out.println("===== waiting=====");
				 TimeUnit.MILLISECONDS.sleep(150);
				 car.setSpace();
		 }
				
		} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}		
	 }
}
 class StringWait{
	 private String syncStrA;
	 private String syncStrB;
	 public StringWait(String syncStrA,String syncStrB){
		 this.syncStrA=syncStrA;
		 this.syncStrB=syncStrB;
	 }
	 public void fun1(){
		 synchronized(syncStrA) {
			for(int i=0;i<10;i++){
				 System.out.println(Thread.currentThread().getName()+" fun 1");
			}
			
		 }
	 } 
	 public void fun2(){
		 synchronized(syncStrB) {
			 for(int i=0;i<10;i++){
				 System.out.println(Thread.currentThread().getName()+" fun 2");
			}
		 }
	 } 
 }
public class WaitAndNotify {
	public static void  main(String[] args) throws InterruptedException{
		Car car=new Car();
		ExecutorService exec= Executors.newCachedThreadPool();
//		exec.execute(new DoJob(car));
//		exec.execute(new DoWait(car));
		String strA="12";
		String strB="12";
		StringWait wait=new StringWait(strA,strB);
		Thread A=new Thread(new Runnable(){
			public void run() {
				// TODO Auto-generated method stub
				wait.fun1();
			}
			
		});
		Thread B=new Thread(new Runnable(){
			public void run() {
				// TODO Auto-generated method stub
				wait.fun2();
			}
			
		});
		exec.execute(A);
		exec.execute(B);
		//TimeUnit.MILLISECONDS.sleep(1000);
		//exec.shutdown();
	}
}
