package learn;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


class EvenGenerator extends IntGenerator{
    private  int  value=0;
	@Override
	synchronized  public int next() {
		// TODO Auto-generated method stub
	    value++;
	    Thread.yield();
	    value++;
		return value;
	}
	
}
public class EvenChecker implements Runnable {
    private IntGenerator generator;
    private final long id;
    public long getId() {
		return id;
	}
    public EvenChecker(IntGenerator g,long ident){
    	id=ident;
    	generator=g;
    }
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(!generator.isCanceled()){
			int val=generator.next();
			if(val%2!=0){
				System.out.println(val+" is not even!");
				generator.cancel();
			}
		}
	}
	public static void test(IntGenerator g,int count){
		System.out.println("Press Control-C to exit");
		ExecutorService exec=Executors.newCachedThreadPool();
		for(int i=0;i<count;i++){
			exec.execute(new EvenChecker(g,i));
		}
		exec.shutdown();
	}
	public static void test(IntGenerator g){
		test(g,10);
	}
	public static  void main(String[] args){ 
		SerialNumChecker.main(new String[]{"5"});
	}
	
}
