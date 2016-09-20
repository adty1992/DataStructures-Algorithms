

import java.util.*;
/*
	最大堆实现

	二叉堆一般用数组实现，层遍历顺序存储
	比如，[90,80,70,60,40,30,20,10,50]
			    90
		        /            \
		      80           70
		     /    \        /    \
 		   60    40   30    20
 		  /    \
 		10    50
*/
public class MaxHeap<T extends Comparable<T>> {
	private List<T> mHeap;

	public MaxHeap() {
		this.mHeap = new ArrayList<T>();
	}

	//添加节点方法
	private void add(T data) {
		if (data == null) return;

		//step1：将节点添加到数组尾部
		mHeap.add(data);

		//step2：对添加进来的节点进行向上浮动操作
		filterUp(mHeap.indexOf(data),  (mHeap.indexOf(data) - 1) / 2) ;

	}

	/*
	比较孩子节点和父亲节点是否符合最大堆要求，即如果孩子节点大于父亲节点，则交换孩子节点和父亲节点的位置
	这一动作类似孩子节点向上浮动，因此取名filterUp
	*/
	private void filterUp(int childIndex, int parentIndex) {
		if (childIndex < parentIndex || childIndex < 0 || childIndex >= mHeap.size()
		      || parentIndex < 0 || parentIndex >= mHeap.size()) {
			return;
		}

		T child = mHeap.get(childIndex);
		T parent = mHeap.get(parentIndex);

		if (child.compareTo(parent) > 0) {//子节点比父节点大，交换子父节点位置，递归与父节点比较寻找合适位置
			//交换孩子节点和父亲节点的位置
			mHeap.set(parentIndex, child);
			mHeap.set(childIndex, parent);
			//继续向上浮动
			filterUp(mHeap.indexOf(child),  (mHeap.indexOf(child) - 1) / 2 );
		} else {//子节点与父节点符合最大堆的要求，退出
			return;
		}
	}

	//删除节点方法
	private void delete(T data) {
		if (data == null || !mHeap.contains(data)) {
			return;
		}
		//用末尾数据替换掉待删除数据
		int index = mHeap.indexOf(data);

		mHeap.set(index, mHeap.get(mHeap.size() - 1));
		mHeap.remove(mHeap.size() - 1);
		//向下浮动
		filterDown(index);
	}

	private void filterDown(int parentIndex) {
		int LChildIndex = parentIndex * 2 + 1;
		int RChildIndex = parentIndex * 2 + 2;
		if (LChildIndex < 0 || LChildIndex >= mHeap.size() || RChildIndex < 0 || RChildIndex >= mHeap.size()) {
			return;
		}

		T parent = mHeap.get(parentIndex);
		T LChild = mHeap.get(LChildIndex);
		T RChild = mHeap.get(RChildIndex);

		if (parent.compareTo(LChild) < 0 || parent.compareTo(RChild) < 0) {
			T bigChild = LChild.compareTo(RChild) > 0 ? LChild : RChild;
			int bigChildIndex = mHeap.indexOf(bigChild);
			mHeap.set(parentIndex, bigChild);
			mHeap.set(bigChildIndex, parent);
			filterDown(mHeap.indexOf(parent));
		} else {
			return;
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (T t : mHeap) {
			sb.append(t + " ");
		}
		return sb.toString();
	}

	public static void main(String[] args) {

		int a[] = {90, 80, 70, 60, 40, 30, 20, 10, 50};
		MaxHeap<Integer> heap = new MaxHeap<Integer>();

		System.out.printf("依次添加: ");
		for (int i = 0; i < a.length; i++) {
			System.out.printf("%d ", a[i]);
			heap.add(a[i]);
		}
		System.out.println("添加完毕： ");
		System.out.println(heap.toString());

		int newAdd = 85;
		System.out.println("新增节点： " + newAdd);
		heap.add(newAdd);
		System.out.println("添加完毕： " + heap.toString());

		int delete = 90;
		System.out.println("删除节点： " + delete);
		heap.delete(delete);
		System.out.println("删除完毕： " + heap.toString());

	}


}