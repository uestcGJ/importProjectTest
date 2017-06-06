package sdpack;

import java.util.Random;

public class CharSerial {
  private static Random random=new Random(47);
  private static final char[] captial="ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
  public char next(){
	  return  captial[random.nextInt(25)];
  }
  public static void main(String[] args){
	  CharSerial cs=new CharSerial();
	  for(int i=0;i<5;i++){
		  System.out.println(cs.next()+" ");
	  }
  }
}
