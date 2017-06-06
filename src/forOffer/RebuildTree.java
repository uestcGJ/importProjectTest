package forOffer;

/****重建二叉树
 * 已知先根序列和中根序列，还原二叉树
 * ***/
public class RebuildTree {
	 public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
		 if(pre==null||pre.length<1){
			 return null;
		 }
		 TreeNode root=rebuild(pre,0,pre.length-1,in,0,in.length-1);
		 return root;
	 }
	 /***
	  * 前序序列的第一个个元素为根节点的值，对应的中序序列中，如在跟节点之前的节点属于左子树，在之后的属于右子树
	  * 对于每个子树，同样满足该规律，从而采用递归的方式
	  * @param pre int[] 先根序列
	  * @param preStart int 当前子树先根序列的起点位置
	  * @param preEnd int 当前子树先根序列的结束位置
	  * @param in int[] 当前子树中根序列
	  * @param inStart int当前子树的中根序列开始位置
	  * @param inEnd int 当前子树中根序列结束位置
	  * ***/
	 private TreeNode reConstructBinaryTree(int[] pre,int preStart,int preEnd,int[] in,int inStart,int inEnd){
		 if(pre==null||preStart>preEnd||pre.length<preStart){
			return null;
		 }
		 TreeNode root=new TreeNode(pre[preStart]);
		 for(int i=inStart;i<=inEnd;i++){
			 if(in[i]==pre[preStart]){
				 /*
				  中根序列起点为inStart 当在位置i处找到根，说明左子树的长度为i-inStart，从而左子树的范围为
				      先根：preStart+1~preStart+i-inStart
				      中根：inStart~i-1
				   右子树范围为：
				      先根  preStart+i-inStart+1~preEnd
				      中根：i+1~inEnd
				 */
				 root.left=reConstructBinaryTree(pre,preStart+1,preStart+i-inStart,in,inStart,i-1);
				 root.right=reConstructBinaryTree(pre,preStart+i-inStart+1,preEnd,in,i+1,inEnd);
				 break;
			 }
		 }
		 return root;
	 }
	 private TreeNode rebuild(int[] pre,int preStart,int preEnd,int[] in,int inStart,int inEnd){
	        if(pre==null||preStart>preEnd){
	            return null;
	        }
	        TreeNode root=new  TreeNode(pre[preStart]);
	        for(int i=inStart;i<=inEnd;i++){
	            if(in[i]==pre[preStart]){
	                 root.left=rebuild(pre,preStart+1,i-inStart+preStart,in,inStart,i-1);//左子树
	                 root.right=rebuild(pre,i-inStart+preStart+1,preEnd,in,i+1,inEnd);//右子树
	                 break;
	            }
	           
	        }
	        return root;
	    }
	 public static void main(String[] args){
		 RebuildTree tree=new RebuildTree();
		 System.out.println(tree.reConstructBinaryTree(new int[]{1,2,3,4,5,6,7}, new int[]{3,2,4,1,6,5,7}));
	 }
}
class TreeNode{
	int value;//当前节点的值
	TreeNode left;//左节点
	TreeNode right;//右节点
	public TreeNode(int value){
		this.value=value;
	}
	
}