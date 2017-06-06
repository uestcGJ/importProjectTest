package learn;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;


public class CalssForLearn {
	//利用Predicate方法统计列表中符合条件的元素的个数
   public static int getTimes(List<?> items,Predicate<Object> p){
	   int times=0;
	   for(Object obj:items){
		   //test()方法为函数式方法
		   if(p.test(obj)){
			   times++;
		   }
	   }
	   return times;
   } 
   /***
    * 非静态内部类,不能包含静态成员和静态方法、也不能包含静态代码块
    * **/
   public  class HashSetDemo{
	  private final HashSet<Integer> hs=new HashSet<Integer>();
	  {
		   hs.add(5);
		   hs.add(10);
	   }
	   public  void iniHs(){
		  /* hs.add(5);
		   hs.add(10);*/
	   }
       public void printHs(){
    	   Iterator<Integer> it=hs.iterator();
    	   while(it.hasNext()){
    		   System.out.println(it.next());
    	   }
		  
	   }
	   
   }
   /***
    * static inner class can't contain any none static function nor parameter,but it can contain 
    * static code blocks
    * **/
   public static class InnerStaticCalss{
	   static{
		   
	   }
	   static int count(int a,int b){
		   int count=0;
	        for(int i=0;i<32;i++){
	        	if((a&0x01)!=(b&0x01)){
	        		count++;
	        	}
	        	a=a>>>1;
	        	b=b>>>1;
	        }
			return count;
	   }
	   public static int calculateMax(int[] prices) {
		    int firstBuy=Integer.MIN_VALUE;
	        int firstSell=0;
	        int secondBuy=Integer.MIN_VALUE;
	        int secondSell=0;
	        for(int i=0;i<prices.length;i++){
	            int price=prices[i];
	            firstBuy=Math.max(firstBuy,-price);//第一次购入价
	            firstSell=Math.max(firstSell,price+firstBuy);//第一次交易的收益
	            secondBuy=Math.max(secondBuy,firstSell-price);
	            secondSell=Math.max(secondSell,secondBuy+price);
	        	System.out.println("firstBuy:"+firstBuy+"  firstSell:"+firstSell+" secondBuy:"+secondBuy+" secondSell:"+secondSell);

	        }
			return secondSell;
	   }
	   public static void main(String[] args) throws InterruptedException{}
	  
	   
	   
   }

   public static int meanAge(int W,int Y,double x,int N){
       double  total=W*Y;
       for(int i=0;i<N;i++){
           double  newT=(double)(total-W*x*Y+W*x*21);
           total=newT;
       }
       return (int) Math.ceil(total/W);
   }
   public  static void main(String[] args){
	   System.out.println(meanAge(5,5,0.2,3));
	//   CalssForLearn cl=  new CalssForLearn();

	   /*
	   String mailReg="\\w{3,20}+@\\w+\\.(com|cn|org|net|gov)";
	   String email="sdust@163.com";
	   System.out.println("is match:"+email.matches(mailReg));
	   String str="我的邮箱是sdugusu@163.com,小明的是xiaoMing@uestc.cn,李四的是lisi@qq.com";
	   Pattern p=Pattern.compile(mailReg);//生成pattern对象
	   Matcher m=p.matcher(str);
	   while(m.find()){
		   System.out.println(m.group());
	   }
	   Date date=new Date();
	   SimpleDateFormat sf=new SimpleDateFormat("Gyyyy年中第D天");
	   String format=sf.format(date);
	   System.out.println(format); 
	   *//**利用Iterator接口实现遍历，Iterator接口主要用于遍历(迭代访问)
	    * Iterator接口必须依赖于Collection而存在，否则没有意义
	    * 利用Iterator迭代访问Collection时，修改迭代值并不会修改集合中的值
	    * 迭代访问Collection过程中不能修改Collection，只能用Iterator自带的remove()方法
	    * 移除上一次next()方法返回的对象
	    * *//*	   
	   List<Integer> co=new  ArrayList<Integer>();
	   co.add(4);
	   co.add(1);
	   co.add(2);
	   List<Integer> c=new ArrayList<Integer>();
	   c.add(6);
	   c.add(4);
	   co.addAll(c);
	   for(int i=0;i<100000;i++){
		   co.add(i%5);
	   }
	   System.out.println("times:"+getTimes(co,item->((int)item)==4));
	   //利用Predicate操作，传入的Lambda为条件
	   co.removeIf(item->(item==4));
	   //尝试从集合中移除值为4的对象
	   Iterator<Integer> it=co.iterator();
	   //way 1  可取，能正常移除元素，利用Iterator接口自带的移除对象的方法
	   Long start= System.currentTimeMillis();
	   while(it.hasNext()){
		   int item=it.next();
		   if(item==4){
			   it.remove();
		   }
	   }
	   Long end= System.currentTimeMillis();
       System.out.println("way 1 cost time:"+(end-start));
	   //way 2  不可取，会报错，不允许在迭代访问过程中修改集合
	   while(it.hasNext()){
		   int item=it.next();
		   if(item==4){
			 co.remove(item);
		   }
	   }
	   //way 3  不可取，会报错，此种方式本质上也是迭代访问，不允许在迭代访问过程中修改集合
	   for(Integer item :co){
		   if(item==4){
			   co.remove(item);
		   }
	   }
       start= System.currentTimeMillis();
	   //way 4   可取，采用索引值的方式遍历循环移除对象
	  for(int i=0;i<co.size();i++){
		   if(co.get(i)==4){
			   co.remove(i--);
		   }
	   }
	  //way 5 不可取，会报错，此种方式本质上也是迭代访问，不允许在迭代访问过程中修改集合
	  co.forEach(item->{
		  if(item==4){
			  co.remove(item);
		  }
	  });
	  //way 6 不可取，会报错，此种方式本质上也是迭代访问，不允许在迭代访问过程中修改集合
	  it=co.iterator();
	  it.forEachRemaining(item->{
		  if(item==4){
			  co.remove(item);
		  }
	  });
	  end= System.currentTimeMillis();
      System.out.println("way 2 cost time:"+(end-start));
       it=co.iterator();
	   while(it.hasNext()){
		  
	   }
	   System.out.println("=======forEach======");
	   //利用函数式接口forEach()来进行遍历，传入Lambda表达式
	   co.forEach( o->{
		                o+=1;
	                    System.out.println("this:"+o);
	                   });
	          
	   Scanner sc=new Scanner(System.in);
	   while(sc.hasNext()){
		   System.out.println("in:"+sc.nextLine()+"\r");
		   if(sc.nextLine().equals("close")){
			   System.out.println("=====system exit======"+sc.nextLine()+"\r");
			   sc.close();
			   System.exit(0);
		   }
	   }
    */}
}
