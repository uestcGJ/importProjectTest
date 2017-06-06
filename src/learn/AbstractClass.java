package learn;
abstract class Abstract{
	abstract void fun1();
	abstract Abstract fun2();
	void fun3(){
		System.out.println("I am fun3");
	}
	static void fun4(){
		
	}
}
 class Implements extends Abstract{

	public Implements() {
	}
	@Override
	public void fun1() {
		// TODO Auto-generated method stub
		System.out.println("fun1");
	}
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	Abstract fun2() {
		// TODO Auto-generated method stub
		return null;
	}
	
}

public class AbstractClass {
	public Implements implement(String nam){
		return new Implements(){
			private String name;
			//name=nam;
			{
				name=nam;
			}
			public String getName(){
				return name;
			}
		};
	}
	public Implements implement1(float s){
		return new Implements(){
			private int cost;
			{
				cost=Math.round(s);
				if(cost>100){
					System.out.println("over");
				}
			}
			public String getName(){
				return String.valueOf(cost);
			}
		};
	}
	public void fun(){
		AbstractClass ab=new AbstractClass();
		Implements im=ab.implement("xiaoming");
		im.getName();
	}
	public static void main(String[] args){
		AbstractClass ab=new AbstractClass();
		Implements im=ab.implement1(new Float(3.24));
		System.out.println(im.getName());
		Implements im1=ab.implement1(new Float(300.24));
		System.out.println(im1.getName());
	}
}
