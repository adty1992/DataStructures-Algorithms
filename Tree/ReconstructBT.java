{
	已知二叉树前序、中序遍历结果，重建二叉树
}

public class ReconstructBT {
	static int INDEX_IN_PRE_ORDER = 0;//在前序遍历中的索引
	public static void main(String[] args) {
		int[] pre = {7,10,4,3,1,2,8,11};  
        int[] mid = {4,10,3,1,7,11,8,2}; 

        Node root = reconstructTree(pre, mid, 0, mid.length - 1);  
        afterOrder(root);

	}

	//递归重建二叉树
	//返回重建后的二叉树的根节点
    public static Node reconstructTree(int[] pre, int[] mid, int midStart, int midEnd) {
		if (INDEX_IN_PRE_ORDER >= pre.length) {
			return null;
		}
		Node node = new Node(pre[INDEX_IN_PRE_ORDER]);
		int indexInMidOrder = findIndexInArray(mid, pre[INDEX_IN_PRE_ORDER], midStart, midEnd);
		INDEX_IN_PRE_ORDER++;
		
		if (midStart < indexInMidOrder)
			node.leftChild = reconstructTree(pre, mid, midStart, indexInMidOrder - 1);
		if (indexInMidOrder < midEnd)
			node.rightChild = reconstructTree(pre, mid, indexInMidOrder + 1, midEnd);
		
		return node;
	}

    public static int findIndexInArray(int[] a,int x,int begin,int end){  
            for(int i=begin;i<=end;i++){  
                if(a[i]==x)return i;  
            }  
            return -1;  
    }  

	public static void afterOrder(Node node) {
		if (node != null) {
			afterOrder(node.leftChild);
			afterOrder(node.rightChild);
			System.out.print(node.data +" ");
		}
	}

	public static boolean zhishu(int n){
		int i;
		for(i=2;i<=n/2;i++){
			if(n%i==0){
				return false;
			}
		}
		return true;
	}

    static class Node {

    	Node leftChild;  
        Node rightChild;  
        int data;  

        public Node(int data){  
            this.data=data;  
        }  
    }  
}