package learn;

class  Inteface implements InterfaceDemo,Interface1,Interface2{

	@Override
	public int f(int i) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void f() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fun1() {
		// TODO Auto-generated method stub
		
	}

	
}
interface Interface1{
	void f();
}
interface Interface2{
	int f(int i);
}
interface Interface3{
	int f();
}
class C{
	public int f(){
		System.out.println("==C==");
		return 1;
	}
}
class C2 implements Interface1,Interface2{
    @Override
	public int f(int i) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void f() {
		// TODO Auto-generated method stub
		
	}
}
class C3 extends C  implements Interface2{
    @Override
	public int f(int i) {
		// TODO Auto-generated method stub
		return 0;
	}	
}
class C4 extends C implements Interface3{
    public int f() {
		// TODO Auto-generated method stub
		return 0;
	}	
}
/*class C5 extends C implements Interface1{
	
}*/
public interface InterfaceDemo {
	   public static void main(String args[]){
		   C4 c4=new C4();
		   c4.f();
	   }

  void fun1();
  static void printl(){
	  
  }
  default void ptints(){
	  
  }
}
