package forOffer;

import java.util.ArrayList;
import java.util.List;
/****二叉排序树***/
public class BitSortTree {
  private int value;//当前节点的数据
  private BitSortTree right;//右子树
  private BitSortTree left;//左子树
  public  BitSortTree (int value){
	  this.value=value;
  }
 /***以中根序列的方式输出结果，利用递归的思想，中根序列顺序为左子树、根、右子树，对于每个子树也按同样的顺序遍历
  * @param sorted List<Integer>:用于放置结果的容器
  * @return List<Integer>:中根顺序输出的结果，也即为排序后的数组
  * ***/
  public List<Integer> travle(List<Integer> sorted){
	  if(left!=null){
		  left.travle(sorted);
	  }
	  sorted.add(value);
	  if(right!=null){
		  right.travle(sorted);
	  }
	  return sorted;
  }
  /***增加节点，如果增加节点的值小于当前节点的值，将新增节点作为当前节点的左子树，否则作为当前节点的右子树***/
  public void add(BitSortTree tree){
	  if(tree.value<this.value){
		  if(left==null){
			  left=tree;
		  }
		  else{//当前树已经具有右子树，递归增加
			  left.add(tree);
		  }  
	  }else{ 
		  if(right==null){
			  right=tree;
		  }
		  else{
			  right.add(tree);
		  }   
	  }
  }
  public static void main(String[] args){
	  int []nums=new int[]{8,2,5,12,7,9,12,3};
	  BitSortTree root=new BitSortTree(nums[0]);
	  for(int i=1;i<nums.length;i++){
		  root.add(new BitSortTree(nums[i]));
	  }
	  List<Integer> sorted=new ArrayList<>();
	  sorted= root.travle(sorted);
 	  System.out.println(sorted);
  }
}
