public class MaxHeap{
	List<Integer> mHeap;

	public MaxHeap(){
		mHeap = new ArrayList<Integer>();
	}


	// 添加元素
	// 先将数据加入到最大堆的最后，然后尽可能把这个元素往上挪，直到挪不动为止
	// 注：数组实现的堆中，第N个节点的左孩子的索引值是(2N+1)，右孩子的索引是(2N+2)
	private void add(int i){
		mHeap.add(i);
		if (mHeap.size() != 0) {
			int focus = i;
			int parent = (i-1)/2;
			while(mHeap.get(focus) > mHeap.get(parent)){
				swap(mHeap, focus, parent);
				foucs = parent;
				parent = (focus-1)/2;
			}
					
		}
	}

	private static void swap(List<Integer> list, int indx1, int indx2){
		int tmp = list.get(indx1);
		list.set(indx1, list.get(indx2));
		list.set(indx2, tmp);
	}


	public static void main(String[] args) {
		MaxHeap heap = new MaxHeap();
		// 90,80,70,60,40,30,20,10,50
		heap.add(90);
		heap.add(80);
		heap.add(70);
		heap.add(60);
		heap.add(40);
		heap.add(30);
		heap.add(20);
		heap.add(10);
		heap.add(50);
		heap.add(85);

		System.out.println(mHeap.get(mHeap.size() - 1));

	}
}