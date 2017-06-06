package sdpack;

import java.nio.CharBuffer;
import java.util.Scanner;

public class AdaptedCharSerail extends CharSerial implements Readable{
    private int count;
    public AdaptedCharSerail(int count){
    	this.count=count;
    }
	@Override
	public int read(CharBuffer cb)  {
		// TODO Auto-generated method stub
		if(count--==0){
			return -1;
		}
		String result=Character.toString(this.next())+" ";
		cb.append(result);
		return result.length();
	}
   public static void main(String[] args){
	   Scanner sc=new Scanner(new AdaptedCharSerail(50));
	   while(sc.hasNext()){
		   System.out.println(sc.next()+" ");
	   }
	   sc.close();
   } 
}
