package learn;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class SerialCreater{
	private volatile static  int serial=0;
	public synchronized static int newSerial(){
		return  serial++;
	}
}
class CircSet{
	private int[] array;
	private int len;
	private int index=0;
	public CircSet(int size){
		array=new int[size];
		len=size;
		for(int i=0;i<len;i++){
			array[i]=-1;
		}
	}
	public synchronized void add(int i){
		array[index]=i;
		index=++index%2;
	}
	public  synchronized boolean contians(int i){
		for(int value:array){
			if(value==i){
				return true;
			}
		}
		return false;
	}
}
public class SerialNumChecker {
	private static final int SIZE=10;
	private static CircSet circSet=new CircSet(1000);
	private static ExecutorService exec=Executors.newCachedThreadPool();
	static class SerialChecker implements Runnable{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true){
				int serial=SerialCreater.newSerial();
				if (circSet.contians(serial)){
					System.out.println("Duplicate:"+serial);
					System.exit(0);
				}
				circSet.add(serial);
			}
		}	
	}
	public static void main(String[]args){
		for(int i=0;i<SIZE;i++){
			exec.execute(new SerialChecker());
		}
		int sleep=1;
	    if(args.length>0){
			sleep=new Integer(args[0]);	
		}
		try {
			System.out.println("sleep "+sleep+" s");
			TimeUnit.SECONDS.sleep(sleep);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		} 
		finally{
			System.out.println("No Duplicate was found in "+sleep+" s");
			System.exit(0);
		}
	}		
}
