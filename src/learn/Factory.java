package learn;
 interface  Service{
	 void method1();
	 void method2();
 } 
 interface ServiceFactory{
	 Service getService();
 }
 class Implemtentation1 implements Service{
    Implemtentation1(){};
	@Override
	public void method1() {
		System.out.println("Implemtentation1 method1");
	}
	@Override
	public void method2() {
		// TODO Auto-generated method stub
		
	}
	public static ServiceFactory factory=new ServiceFactory(){
		public Service getService(){
			return new Implemtentation1();
		}
	}; 
	 class  T{
		private void f(){}
		void method1(){
			System.out.println("class t");
		}
		class U{
		  	void s(){
		  		f();
		  		Implemtentation1.this.method1();
			}
		}
		
	}
	
 }
 class Implemtentation2 implements Service{
	    private Implemtentation2(){};
		@Override
		public void method1() {
			System.out.println("Implemtentation2 method1");
		}
		@Override
		public void method2() {
			// TODO Auto-generated method stub
			
		}
		/***利用匿名内部类实现工厂模式**/
		public static ServiceFactory factory=new ServiceFactory(){
			public Service getService(){
				return new Implemtentation2();
			}
		};		 
 }

 public  class Factory {
	 public static void serviceConsumer(ServiceFactory factory){
		 factory.getService().method1();
	 }
	 public static void main(String[] args){
			Implemtentation1 im=new Implemtentation1();
			Implemtentation1.T t= im.new T();
			Implemtentation1.T.U u=t.new U();
			u.s();
		} 
}
