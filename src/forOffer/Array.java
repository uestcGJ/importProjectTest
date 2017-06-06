package forOffer;

import java.util.HashSet;
import java.util.Set;

/***剑指offer面试题3，数组查重及其去重问题**/
public class Array {
  /***
   * 通过Hash表的方式寻找数组中第一个重复的数字
   * 空间复杂度O(n) 时间复杂度O(n)
   * **/
  public static int findFirstRepeatNumInHash(int arr[]){
	  Set<Integer> temp=new HashSet<Integer>();
	  for(int item:arr){
		  if(temp.contains(item)){
			  return item;
		  }
		  temp.add(item);
	  }
	  return 0; 
  }
  /***
   * 题干：长度为n的数组中所有数字的值都在0~n-1之间，判断是否存在重复的数字
   * 时间复杂度O(n)  空间复杂度O(1)
   * **/
  public static boolean checkIfcontainsRepeat(int n,int[] arr ){
	  if(arr==null||n<=0){
		  return false;
	  }
	  //如果存在大于n或小于0的值，说明不存在重复值
	  for(int i=0;i<arr.length;i++){
		  if(arr[i]<0||arr[i]>arr.length-1){
			  return false;
		  }
	  }
	  //如果第i个位置的值（设其为m）等于i，检查下一个数字；
	  //否则将它和第m个数字进行比较，如果它和第m个数字相等，则找到了一个重复的数字；
	  //否则将第i个数和第m个数交换，将m放到它应该在的位置上
	  for(int i=0;i<arr.length;i++){
		  while(arr[i]!=i){
			  if(arr[i]==arr[arr[i]]){//第i个位置和第m个位置有相同的值m
				  return true;
			  } 
			  int temp=arr[i];
			  arr[i]=arr[temp];
			  arr[temp]=temp;//
		  }
	  }
	  return false;
  }
  
  /***
   * 题干：长度为n+1的数组中所有数字的值都在1~n之间，至少存在一个重复的数字，在不修改传入数组的前提下找出任意一个重复的数字
   * 
   * **/
  /***
   * way1：引入辅助数组，遍历数组，令第i个位置的值为m，如果辅助数组的第m个数的值等于m，说明存在相同值；否则将其放到第m个位置上
   *   空间复杂度为O(n),时间复杂度O(n)
   * 
   * ***/

  public static int findRepeatWay1(int n,int[] arr ){
	  if(arr==null||n<=0){
		  return -1;
	  }
	  //如果存在大于n或小于0的值，说明不存在重复值
	  for(int i=0;i<arr.length;i++){
		  if(arr[i]<1||arr[i]>arr.length){
			  return -1;
		  }
	  }
	  //引入辅助数组
	  int[] arrCopy=new int[n]; 
	  for(int i=0;i<arr.length;i++){
		  if(arr[i]==arrCopy[arr[i]-1]){//第i个位置和第m个位置有相同的值m
				  return arr[i];
			  } 
		  arrCopy[arr[i]-1]=arr[i];//  
	  }
	  return -1;
  }
  
  /**way2 根据二分法的思路，将1~n的数字分为两部分，前一半为1~m，后一半为m+1~n，如果1~m范围内的数字数目超过m，所以包含了重复的数字，否则在后半部分一定存在重复数字
   * 空间复杂度为O(1),时间复杂度O(nlogn)
   * ***/
  public static int getRepeatNum(int length,int[] arr){
	  if(length<0||arr==null){
		  return -1;
	  }
	  int start=1;
	  int end=length-1;
	  while(end>=start){
		  int middle=((end-start)>>1)+start;
		  int cout=countNum(start,length,middle,arr);//统计位于前半段数值范围内的元素的个数
		  if(end==start){//统计到n时
			  if(cout>1){//为n的值不止一个，表明其为重复值
				  return start;
			  }
			  else{//循环完成，未找到重复值
				  break;
			  }
		  }
		  //数值落在前半段的数据的个数超过m，说明这一段存在重复数据，接下来只统计全半段数据
		  if(cout>(middle-start+1)){
			  end=middle;
		  }
		  else{//统计后半段数据
			  start=middle+1;
		  }
	  }
	  return -1;
  }
  private static int countNum(int start,int len,int end,int[]arr){
	  if(arr==null){
		  return 0;
	  }
	  int count=0;
	  for(int i=0;i<len;i++){
		  if(arr[i]<=end&&arr[i]>=start){
			  count++;
		  }
	  }
	  System.out.println("range:"+start+"~"+end+"  count:"+count);
	  return count;
  }
  public static void main(String[] args){
	  int[] arr={4,2,3,1,4};
	  System.out.println("the first repeat:"+(3>>1));
	  System.out.println("exists repeat:"+getRepeatNum(5,arr));
  }
}
