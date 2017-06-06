package learn;

public class Sort {
	/***
	 * 冒泡排序法
	 * 时间复杂度O(n2)，思路：<br/>
	 * 先是第一个数二第二个数比较，如果第一个数大于第二个数，则交换两个数；然后第二个数和第三个数比较，如果第二个数大于第三个数，交换两个数的位置
	 * 以此类推，第一次外循环后将最大的数移到左边，然后开始第二次循环，将次大的数移到从左往右的第二个位置。
	 * 最终经过N-1次外循环，将大的值移到左侧
	 * 从而一共经过N-1次外循环，每次外循环的内循环次数为N-1-i
	 * 
	 * ***/
	 public int[] bubble(int[] sort){
		   for(int i=0;i<sort.length-1;i++){
			   for(int j=0;j<sort.length-i-1;j++){
				   if(sort[j]>sort[j+1]){
					   int temp=sort[j];
					   sort[j]=sort[j+1];
					   sort[j+1]=temp;
				   }
			   }
		   }
		   return sort;
	   }
	/***
	 * 选择排序
	 * 时间复杂度O(n2)
	 * 选定一个位置，将对应最值放到该位置
	 * 两层循环，外层为0~N-1，依次判断每一项，进行对比判断，交换位置
	 * 内层为i+1~N，使当前外循环的项和后面所有的元素对比交换位置
	 * ***/
   public int[] select(int[] sort){
	   for(int i=0;i<sort.length-1;i++){
		   for(int j=i+1;j<sort.length;j++){
			   if(sort[i]>sort[j]){
				   int temp=sort[j];
				   sort[j]=sort[i];
				   sort[i]=temp;
			   }
		   }
	   }
	   return sort;
   }
	/***
	 * 插入排序
	 * 时间复杂度O(n2)
	 * 每一步都将一个待排数据按其大小插入到已经排序的数据中的适当位置，直到全部插入完毕。 
	 * 每次对当前位置的下一个位置的数进行排序，如果下一个位置的数大于当前位置，则交换当前位置和下一个位置的值
	 * ***/
  public int[] insert(int[] sort){
	   for(int i=0;i<sort.length-1;i++){
		  if(sort[i]>sort[i+1]){//当当前位置的值大于下一个位置的值时才对下一个位置进行排序
			   for(int j=i+1;j>1;j--){//从当前位置的前一个位置一直判断到第一个位置
				   if(sort[j-1]>sort[j]){
					   int temp=sort[j];
					   sort[j]=sort[j-1];
					   sort[j-1]=temp;
				   }
			   }
		   }
		}
	   return sort;
  }
  private  void mergeSort(int[] sort,int[]temp,int left,int right){
	  if(left<right){
		  int center=(left+right)/2;
		  mergeSort(sort,temp,left,center);
		  mergeSort(sort,temp,center+1,right);
		  this.merge(sort, temp, left, center+1,right);
	  }
  }
  private void merge(int[] sort,int[] temp,int left,int right,int rightEnd){
	  
  }
  /***
   * 归并排序：时间复杂度nlogn
   * 
   * ***/
  public int[] merge(int[] sort){
	  int []temp=new int[sort.length];
	  this.mergeSort(sort,temp,0,sort.length-1);
	  return temp;
  }
  public int getValue(int[] gifts, int n) {
      // write code here
	  int item=0;
	  int maxCount=0;
      for(int i=0;i<n;i++ ){
    	 int count=1;
    	 for(int j=i+1;j<n;j++){
        	  if(gifts[i]==gifts[j]){
        		  count++;
        	  }
          }
    	 if(count>n/2){
			 return gifts[i];
		 }
    	 if(count>maxCount){
    		 item=gifts[i];
    		 maxCount=count;
    	 }
      }
      if(maxCount<=n/2)
    	  return 0;
      return item;
  }
  public int[] arrayPrint(int[][] arr, int n) {
      // write code here
	  int[] print=new int[n*n];
	  int index=0;
	//  int count=2*n-1;
//	  int row=0;
//	  for(int i=0;i<count;i++){
//		  if(i>=n){
//			  row++;
//		  }
//		  for(int j=row;j<i+1-row;j++){
//			  print[index]=arr[j][n-i-1+j];
//			  index++;
//		  }
//	  }
	  for(int i=0;i<n;i++){
		  for(int j=0;j<i+1;j++){
			  print[index]=arr[j][n-i-1+j];
			  index++;
		  }
	  }
	  for(int i=1;i<n;i++){
		  for(int j=0;j<n-i;j++){
			  print[index]=arr[i+j][j];
			  index++;
		  }
	  }
	  return print;
  }
  public String formatString(String A, int n, char[] arg, int m) {
      // write code here
	  int i=0;
//	  while(A.contains("%s")){
//		  A=A.replaceFirst("%s", String.valueOf(arg[i]));
//		  i++;
//	  }
//	  for(int j=i;j<arg.length;j++){
//		  A+=String.valueOf(arg[i]);
//	  }
	  StringBuilder result=new StringBuilder();
	  for(int j=0;j<n;j++){
		  if(A.charAt(j)!='%'){
			  result.append(A.charAt(j));
		  }else{
			  result.append(arg[i++]);
			  j++;
		  }
	  }
	  while(i<m){
		  result.append(arg[i++]);
	  }
	  return result.toString();
  }
  public int getDis(int[] A, int n) {
      // write code here
	  int max=0;
	  for(int i=0;i<n;i++){
		  for(int j=i;j>=0;j--){
			  System.out.println("Ai:"+A[i]+" Aj:"+A[j]);
			  max=(A[i]-A[j]>max)?(A[i]-A[j]):max;
		  }
	  }
	  return max;
  }
  public int num;
  public static int a;
  static{
	  a=1;
  }
   public static void main(String[] args){}
}
