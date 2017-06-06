package learn;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
class Atomicity implements Runnable{
    private  AtomicInteger i=new AtomicInteger(0);
    public  int getValue(){
    	return i.get();
    }
    private synchronized  void increment(){
    	i.addAndGet(2);
    }
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			increment();
		}
	}
	
}
public class MutexEvenGenerator extends IntGenerator{
    private  int count=0;
    private Lock lock=new ReentrantLock();//利用显式子 的锁
	@Override
	public int next() {
       lock.lock();
        try{
			count++;
			Thread.yield();
			count++;
			return count;
       }finally{
    	   lock.unlock();
       }
	}
    public static void main(String[] args){
        new Timer().schedule(new TimerTask(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("exit");
				System.exit(0);
			}   
       }, 5000); 
       ExecutorService exec=Executors.newCachedThreadPool();
       Atomicity at=new Atomicity();
       exec.execute(at);
       while(true){
    	   int value=at.getValue();
    	   if(value%2!=0){
    		   System.out.println(value);
    		   System.exit(0);
    	   }
       }
    }
}
