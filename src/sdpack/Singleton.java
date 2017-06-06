package sdpack;

/**
 * 双重校验锁单例模式
 * **/
 public class Singleton {
  
  private Singleton(){
  }
  private  static volatile  Singleton singleton=new Singleton();
  public static Singleton getSingleton(){
	  if(singleton==null){
		  synchronized (Singleton.class) {  
		        if (singleton == null) {  
		            singleton = new Singleton();  
		        }  
		  }  
	  }
	  return singleton;
  }
  public static void main(String[] args){
  }
}
/***
 * 饿汉式单例模式,利用了class loader机制
 * ***/
  class SingletonLoader {
	  private SingletonLoader(){
	  }
	  private  static  SingletonLoader instance=new SingletonLoader();
	  public static SingletonLoader getInstance(){
		  return instance;
	  }
}
 /***
  * 静态内部类形式的单例模式
  * ***/
/** public class Singleton {  
	  private static class SingletonHolder {  
	    private static final Singleton INSTANCE = new Singleton();  
	  }  
	  private Singleton (){}  
	  public static final Singleton getInstance() {  
	    return SingletonHolder.INSTANCE;  
	  }  
}*/  