package forOffer;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/***单例模式
 * 将构造器隐藏，只生成一个类的实例
 * **/

/**
 * 饿汉模式，一加载便产生实例对象
 * 线程安全,只有在类加载时才会新建实例对象，不存在线程竞争问题
 * ***/
class HungarySingleton{
	private  static HungarySingleton instace=new HungarySingleton();
	public static HungarySingleton getInstance(){
		return instace;
	}
	private HungarySingleton(){
		
	}
}
/**
 * 懒汉模式，初次调用时产生实例对象
 * 线程不安全，可能多个线程同时调用获取实例的方法，此时会新建多个实例
 * 
 * ***/
class LazySingleton{
	private  static LazySingleton instace=null;
	public static LazySingleton getInstance(){
		if(instace==null){
			instace=new LazySingleton();
		}
		return instace;
	}
	private LazySingleton(){
		
	}
}
/**
 * 懒汉模式，初次调用时产生实例对象
 * 线程安全，通过同步锁的方式对新建实例的方法加锁
 * 
 * ***/
class SynchronizedLazySingleton{
	private  static SynchronizedLazySingleton instace=null;
	public static synchronized SynchronizedLazySingleton getInstance(){
		if(instace==null){
			instace=new SynchronizedLazySingleton();
		}
		return instace;		
	}
	private SynchronizedLazySingleton(){
		
	}
}
/**
 * 推荐
 * 
 *  双重校验锁懒汉模式，初次调用时产生实例对象
 * 线程安全，通过同步锁的方式对新建实例的方法加锁,通过优化判断，减少加锁的时间
 * 
 * ***/
class SynchronizedPartLazySingleton{
	private  static SynchronizedPartLazySingleton instace=null;
	public static  SynchronizedPartLazySingleton getInstance(){
		if(instace==null){
			synchronized (SynchronizedPartLazySingleton.class){
				if(instace==null){
					instace=new SynchronizedPartLazySingleton();
				}
			}
		}
		return instace;		
	}
	private SynchronizedPartLazySingleton(){
		
	}
}
/**
 * 推荐
 * 
 * 静态内部类懒汉模式，初次调用时产生实例对象,通过静态内部类的方式实现线程安全 
 * 由于实例为静态内部类的静态成员，通过java 的类加载机制可知，静态成员变量只会加载一次，而且只会在加载类的时候进行初始化，所以
 * INSTANCE只会在第一次调用InnerStaticClassLazySingleton.getInstance()时加载和初始化
 * ***/
class InnerStaticClassLazySingleton{
	private static class InstanceCreater{//私有的静态内部类
		private  static final InnerStaticClassLazySingleton INSTANCE=new InnerStaticClassLazySingleton();
	}
	public static  final InnerStaticClassLazySingleton getInstance(){
		return InstanceCreater.INSTANCE;		
	}
	private InnerStaticClassLazySingleton(){
		
	}
}
/**
 * 推荐
 * 
 * 懒汉模式，初次调用时产生实例对象
 * 线程安全，通过显式锁的方式对新建实例的方法加锁，同时先进行非空判断，优化了加锁的时间
 * 
 * ***/
class LockLazySingleton{
	private  static LockLazySingleton instace=null;
	public static LockLazySingleton getInstance(){
		if(instace==null){
			Lock lock=new ReentrantLock();
			try{
				lock.lock();
				if(instace==null){
					instace=new LockLazySingleton();
				}
			}catch(Exception e){
				
			}finally{
				lock.unlock();	
			}
		}
		return instace;		
	}
	private LockLazySingleton(){
		
	}
}
class LazyInstaceCreater implements Runnable{
	public static Set<LazySingleton> instances=new HashSet<LazySingleton>();
	public void run() { 
		instances.add(LazySingleton.getInstance());	
	}
	
}
class HungaryInstaceCreater implements Runnable{
	public static Set<HungarySingleton> instances=new HashSet<HungarySingleton>();
	public void run() { 
		instances.add(HungarySingleton.getInstance());	
	}
	
}
class SynchronizedLazySingletonCreater implements Runnable{
	public static Set<SynchronizedLazySingleton> instances=new HashSet<SynchronizedLazySingleton>();
	public void run() { 
		instances.add(SynchronizedLazySingleton.getInstance());	
	}
	
}
public class Singleton {
	public static void main(String[] srgs){
		 ExecutorService exec=Executors.newCachedThreadPool();
		 for(int i=0;i<10;i++){
			exec.execute(new LazyInstaceCreater());  
			//exec.execute(new HungaryInstaceCreater());  
			//exec.execute(new SynchronizedLazySingletonCreater());   
		 }
		 exec.shutdown();
		 System.out.println("SynchronizedLazySingleton size:"+SynchronizedLazySingletonCreater.instances.size());
		 for(SynchronizedLazySingleton single:SynchronizedLazySingletonCreater.instances){
			 System.out.println(single.toString());
		 }
		 System.out.println("HungarySingleton size:"+HungaryInstaceCreater.instances.size());
		 for(HungarySingleton single:HungaryInstaceCreater.instances){
			 System.out.println(single.toString());
		 }
		 System.out.println("LazySingleton size:"+LazyInstaceCreater.instances.size());
		 for(LazySingleton single:LazyInstaceCreater.instances){
			 System.out.println(single.toString());
		 }
	}
  
}
