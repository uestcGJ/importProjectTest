package forOffer;

import java.util.Formatter;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/***StringBuffer线程安全，相对StringBuilder会稍微增加开销**/
class CollectThread extends Thread{
	private StringBuffer buffer;
	private StringBuilder builder;/***StringBuilder线程不安全**/
	public  CollectThread(StringBuffer buffer,StringBuilder builder){
		this.buffer=buffer;
		this.builder=builder;
	}
	public void run(){
		for(int i=0;i<500;i++){
			Thread.yield();
			buffer.append("1");
			builder.append("1");
		}
		System.out.println("buffer length:"+buffer.length()+"/builder length："+builder.length());
	}
}
public class StringDemo {

	
	public static Random rand=new Random(47);
	public String toString(){
		return "The address of this if:"+this;/**此时会引起循环递归调用，因为返回语句是用+连接一个字符串和this，
		此时编译器会自动将this转换为字符串，其转换的方式正是调用toString()方法，从而会引起递归循环调用。
		此时的正确做法是调用Object.toString()，也就是super.toString()*/
	}
	public static void main(String[] args){
		StringBuilder builder=new StringBuilder();
		StringBuffer buffer=new StringBuffer();
		ExecutorService exec=Executors.newCachedThreadPool();
		for(int i=0;i<10;i++){
			exec.execute(new CollectThread(buffer, builder));
		}
		exec.shutdown();
		float x=0.0f;
		Formatter format=new Formatter(System.err);
		int y=23;
		format.format("Row 1: [%d %f]\n",y,x);
		format.flush();
		format.close();
	} 
}
