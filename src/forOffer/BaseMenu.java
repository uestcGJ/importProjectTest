package forOffer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/***控制台通用菜单类，其数据结构类似于一般树的结构**/
public class BaseMenu {
	class NodeTree{
		private String value;
		private String parent;
		private List<NodeTree> nodes=new ArrayList<>();
		public NodeTree(String value,String parent){
			this.value=value;
			this.parent=parent;
		}
		public NodeTree(){
		}
		public void add(String parent,String nod){
			NodeTree node=new NodeTree();
			node.value=nod;
			node.parent=parent;
			if(!nodes.contains(node)){
				nodes.add(node);
			}	
		}
		/***找某一节点的父节点**/
		public String getParent(String value){
			for(NodeTree node:nodes){
				if(node.value.equals(value)){
					return node.parent;
				}
			}
			return null;	
		}
		/***找某一节点的所有子节点的值***/
		public List<String> getChild(String value){
			List<String> child=new ArrayList<>();
			for(NodeTree node:nodes){
				if(node.parent.equals(value)){
					child.add(node.value);
				}
			}
			return child;	
		}
		
	}
	private NodeTree nodeTree=new NodeTree();
	
	public void add(String parent,String child){
		nodeTree.add(parent, child);
	}
	public String goToMenu(String value){
		Scanner in=new Scanner(System.in);
		while(true){
			List<String> child=nodeTree.getChild(value);
			if(child.isEmpty()){
				System.out.println("close");
				break;
			}
			System.out.println("---------------");
			for(int i=0;i<child.size();i++){
				System.out.println(i+". "+child.get(i));
			}
			System.out.println("---------------");
			System.out.print("请选择：");
			String select=in.nextLine();
			if(Integer.parseInt(select)<0||Integer.parseInt(select)>child.size()){
				System.out.println("");
				System.out.println("选项无效，请重新选择.");
			}
			else{
				value=child.get(Integer.parseInt(select));
				goToMenu(value);
			}
		}
		//in.close();
		return value;
	}
	public static void main(String[] args){
		BaseMenu menu=new BaseMenu();
		menu.add("水果","苹果");
		menu.add("水果","香蕉");
		menu.add("水果","葡萄");
		menu.add("苹果","红富士");
		menu.add("苹果","山东苹果");
		menu.add("苹果","美国苹果");
		menu.add("红富士","精品红富士");
		menu.add("红富士","水晶红富士");
		menu.add("红富士","普通红富士");
		menu.add("美国苹果","加州大苹果");
		menu.add("美国苹果","美国蛇果");
		String select=menu.goToMenu("水果");
		System.out.print("您选择了："+select);
	}
	
}
