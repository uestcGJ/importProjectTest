package learn;

import java.util.ArrayList;
import java.util.Stack;

class SimpleException extends Exception{
	private static final long serialVersionUID = 1L;
	public SimpleException(){
		super();
	}
	public SimpleException(String info){
		super(info);
	}
	
}

public class StringRelated {
	 public String replaceSpace(StringBuffer str) {
	        String tempStr=str.toString();
	    	return  tempStr.replaceAll(" ", "%20");
	    }
	 class ListNode{
		    int val;
		    ListNode next;
		    ListNode(int val){
		        this.val=val;
		        this.next=null;
		    }
		}
	 public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
	        Stack<Integer> stack= new Stack<Integer>();
	        while(listNode!=null){
	        	stack.push(listNode.val);
	        	listNode=listNode.next;
	        }
	        ArrayList<Integer> valus=new ArrayList<>();
	        while(!stack.isEmpty()){
	        	valus.add(stack.pop());
	        }
			return valus;
	    }
	 public static void throwSimpleException() throws SimpleException{
		System.out.println("throw SimpleException from throwSimpleException()");
		throw new SimpleException("error in throwSimpleException()");
	 }
	 public boolean Find(int target, int [][] array) {
		 int col=array.length;
		 int row=array[0].length;
		 if(target<array[0][0]||target>array[col-1][row-1]){
			 
		 }
		 for(int i=0;i<array.length-1;i++){
			 
		 }
		return false;
	 }
	 public static void main(String[] args){
		 StringRelated strRelated= new StringRelated();
		 System.out.println(strRelated.replaceSpace(new StringBuffer("We are Happy")));
	 }
		
	   /***
	    * 在字符串的每个字符间做逆序调整
	    * **/
	   private static String switchStr(String str){
		   char[] serial=str.toCharArray();
		   int index=serial.length/2;
		   for(int i=0;i<index;i++){
			   char temp=serial[i];
			   serial[i]=serial[serial.length-i-1];
			   serial[serial.length-i-1]=temp;
		   }
		   return String.valueOf(serial);
	   }
	   /***
	    * 在字符串串间做单词逆序调整
	    * @param str 要调整的字符串
	    * @return String 调整后的字符串
	    * ***/
	   public static String switchString(String str){
		   str=switchStr(str);
		   String[] strs=str.split(" ");
		   str="";
		   for(String st:strs){
			   str+=switchStr(st)+" ";
		   }
		   return str;
	   }
	   /***
	    * 将字符串数组中的各字符串按照字典顺序最小的方式组合并返回
	    * 方案：如果str1+str2<str2+str1则str1放在前，反之，str2在前
	    * **/
	   public static String sortString(String[] strs){
		   String sorted="";
		   for(int i=0;i<strs.length-1;i++){
			   for(int j=i+1;j<strs.length;j++){
				   if((strs[i]+strs[j]).compareTo((strs[j]+strs[i]))>0){
					   String temp=strs[j];
					   strs[j]=strs[i];
					   strs[i]=temp;
				   }
			   }
		   }
		   for(String str:strs){
			   sorted+=str;
		   }
		   return sorted;
	   }
	   /**
	    * 
	    * 如果对于一个字符串A，将A的前面任意一部分挪到后边去形成的字符串称为A的旋转词。比如A="12345",
	    * A的旋转词有"12345","23451","34512","45123"和"51234"。对于两个字符串A和B，请判断A和B是否互为旋转词。
                给定两个字符串A和B及他们的长度lena，lenb，请返回一个bool值，代表他们是否互为旋转词。
     *
     *方案：1、判断两个字符串长度是否相同，不相同则返回false<br/>
     *     2、大字符串A=A+A，判断A中是否含有B，若含有则为true，反之为false           
	    * ***/
	   public boolean chkRotation(String A, int lena, String B, int lenb) {
	        // write code here
	        if(lena!=lenb){
	            return false;
	        }
	        A+=A;
	        return A.contains(B);
	    }
}
