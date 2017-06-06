package learn;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Meal{
	private final int order;
	public Meal(int order){
		this.order=order;
	}
	public String toString(){
		return "Meal "+order;
	}
}
class Waiter extends Thread{
	private Restaurant restaurant;
    public Waiter(Restaurant restaurant){
    	this.restaurant=restaurant;
    }
	public void run() {
		try{
			while(!Thread.interrupted()){
				synchronized (this){
					while(restaurant.meal==null){//wait for  the meal ready
						wait();
					}
				}
				System.out.println("waiter got "+restaurant.meal);
				synchronized(restaurant.busBoy){
					restaurant.needClean=true;
					restaurant.busBoy.notifyAll();
				}
				//TimeUnit.MILLISECONDS.sleep(1);
				synchronized (restaurant.chief){
					restaurant.meal=null;
					restaurant.chief.notifyAll();
				}
				
			}
		}catch(Exception e){
			System.out.println("Waiter interrupted!");
		}	
	}
}
class BusBoy extends Thread{
	private Restaurant restaurant;
    public BusBoy(Restaurant restaurant){
    	this.restaurant=restaurant;
    }
	public void run() {
		try{
			while(!Thread.interrupted()){
				synchronized (this){
					while(!restaurant.needClean){//wait for  the meal ready
						wait();
					}
				}
				System.out.println("BusBoy clean "+restaurant.meal);
				synchronized (restaurant.waiter){
					restaurant.needClean=false;
					restaurant.waiter.notifyAll();
				}
			}
		}catch(Exception e){
			System.out.println("BusBoy interrupted!");
		}
	}
}
class Chief implements Runnable{
	private Restaurant restaurant;
	private int count=0;
    public Chief(Restaurant restaurant){
    	this.restaurant=restaurant;
    }
	public void run() {
		try{
			while(!Thread.interrupted()){
				synchronized (this){
					while(restaurant.meal!=null){//wait for  the meal to token
						wait();
					}
				}
				if(++count==10){
					System.out.println("out of food,closing");
					restaurant.exec.shutdownNow();
				}
				System.out.println("order up! ");
				synchronized (restaurant.waiter){
					restaurant.meal=new Meal(count);
					restaurant.waiter.notifyAll();
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}
		}catch(Exception e){
			System.out.println("Chief interrupted!");
		}
	}	
}
 public class Restaurant {
	  boolean needClean=false;
	  Meal meal;
	  BusBoy busBoy=new BusBoy(this);
	  Chief chief=new Chief(this);
	  Waiter waiter=new Waiter(this);
	  ExecutorService exec=Executors.newCachedThreadPool();
	  public Restaurant(){
		  exec.submit(chief);
		  exec.submit(waiter);
		  exec.submit(busBoy);
		 
	  }
	  public static void main(String[]args){
		  new Restaurant();
	  }
 }
