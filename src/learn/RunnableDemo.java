package learn;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

class TaskWithResult implements Callable<String>{
	private int id;
    public TaskWithResult(int i){
    	id=i;
    }
	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("this is a message from TaskWithResult "+id);
		Thread.sleep(200);
		return "return result from thread "+id;
	}
	
}
 class RunnableDemos implements Runnable {
	protected static long count=0;
    private final  long id=count++;
	synchronized void print() throws InterruptedException{
		System.out.println("this is a message from RunnableDemo "+id);
		TimeUnit.MILLISECONDS.sleep(100);
	}
	@Override
	public void run() {
		try {
			print();
			while(true){
				//Thread.yield();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			System.out.println("exec finally");
		}
	}
   
    }
 
    public class RunnableDemo{
    	 public static void main(String[] args){
    	    	//利用执行器管理线程
    	    	ExecutorService exec=Executors.newCachedThreadPool();
    	    	ArrayList<Future<String>> results=new ArrayList<Future<String>>();
    	    	System.out.println("=========start time:"+System.currentTimeMillis());
    	    	ArrayList<Thread> list=new ArrayList<Thread>();
    	    	for(int i=0;i<10;i++){
    	    	//	results.add(exec.submit( new TaskWithResult(i)));
    	    		Thread run=new Thread(new RunnableDemos());
    	    	//	exec.execute(run);
    	   		    list.add(run);
    	   		    run.setDaemon(true);//设置为后台进程
    				run.start();
    	    	}
//    	    	for(Thread run:list){
//    	    		try {
//    					run.join();
//    				} catch (InterruptedException e) {
//    					// TODO Auto-generated catch block
//    					e.printStackTrace();
//    				}
//    	    	}
    	    	exec.shutdown();
    	    	for(Future<String> fs:results){
    	    		try {
    					System.out.println(fs.get());
    				} catch (InterruptedException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				} catch (ExecutionException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}finally{
    		    		exec.shutdown();
    		    	}
    	    	}
    	    	
    	    	System.out.println("=========end time:"+System.currentTimeMillis());
    }
    }

