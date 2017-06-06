package forOffer;
/***链表***/
public class ListNode {
    public int value;
    public ListNode next;
    public ListNode(int value){
    	this.value=value;
    }
    /***
     * 链表的插入：增加一个节点
     * **/
    public void add(ListNode x){
    	x.next=this.next;//将新增节点的下一个节点设置为新增之前节点的下一个节点
    	this.next=x;//将原来节点的下一个节点设置为新增的节点
    }
    /***
     * 尾部追加
     * 在末尾追加一个节点
     * ***/
    public void append(ListNode node){
    	ListNode last=this;
    	while(last.next!=null){
    		last=last.next;
    	}
    	last.next=node;
    }
    /***
     * 从头到尾打印链表
     * ***/
    public void show(){
    	ListNode node=this;
    	while(node!=null){
    		System.out.println(node.value);
    		node=node.next;
    	}
    }
    public static void main(String[] args){
    	ListNode node=new ListNode(10);
    	node.add(new ListNode(20));
    	node.add(new ListNode(30));
    	node.add(new ListNode(60));
    	node.add(new ListNode(50));
    	node.append(new ListNode(40));
    	node.show();
    }
}
