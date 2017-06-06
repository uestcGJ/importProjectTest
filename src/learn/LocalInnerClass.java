package learn;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;


interface Local{
	 void PrintS();
}
public class LocalInnerClass {
  private int s=0;
  public  Local getLocalClass(){
	  class LocalClass implements Local{
		  public LocalClass(){
			  System.out.println("LocalClass");
		  }
		  public void PrintS(){
			  System.out.println(s);
		  }
	  }
	  return new LocalClass();
  }
  public static void main(String[] args){
	  String strs="+U+n+c---+e+r+t---+a-+i-+n+t+y---+-+r+u--+l+e+s---";
	  Stack<String> stack=new Stack<String>();
      for(int i=0;i<strs.length();i++){
    	  if(strs.charAt(i)=='+'){
    		  stack.push(String.valueOf(strs.charAt(++i)));
    	  }
    	  else if(strs.charAt(i)=='-'){
    		  System.out.print(stack.pop());
    	  }
    		  
      }
	 System.out.println("");
     int[] unsorted={21,1,4,6,32,7,8,34,23,4,24,78,454};
     SortedSet<Integer> set=new TreeSet<>();
     for(int i:unsorted){
    	 set.add(i);
     }
     SortedSet<String> words=new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
     words.addAll(Arrays.asList("hh","a","b","A","ff","ee","AA","AB","aB","ab","Ab"));
     System.out.println(words);
     SortedSet<String> word=new TreeSet<>(Arrays.asList("hh","a","b","A","ff","ee","AA","AB","aB","ab","Ab"));
     System.out.println(word);
     
    
     System.out.println("main end:"+System.currentTimeMillis());
     /***统计输入句子中个各单词含有的元音字母个数和所有元音字母之和***/
     @SuppressWarnings("resource")
	 Scanner in=new Scanner(System.in);
     Set<String> vowel=new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
	 vowel.addAll(Arrays.asList("A","E","I","O","U"));
     while(in.hasNext()){
    	 String line=in.nextLine();
    	 String[] inStrs=line.split(" ");
    	 int total=0;
    	 for(String inStr:inStrs){
    		  int count=0;
    		  for(int i=0;i<inStr.length();i++){
        		 if(vowel.contains(inStr.substring(i,i+1))){
        			 count++;
        		 }
        	 }
        	 total+=count;
        	 System.out.println(inStr+" contains "+count +" vowel letters"); 
    	 }
    	 System.out.println(line+" contains "+total +" vowel letters"); 
     }
  }
}
