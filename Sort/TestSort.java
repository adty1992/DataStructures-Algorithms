public class TestSort {
	public static void main(String[] args) {
		int[] arr = {12333, 34, 2, 1, 3, 5, 64, 2, 9, 12, 4, 8};
		quickSort(arr, 0, arr.length - 1);
		for (int i : arr) {
			System.out.println(i);
		}
	}

	private static void swap(int[] arr, int from, int to) {
		if (from < 0 || from >= arr.length || to < 0 || to >= arr.length) return;
		int tmp = arr[from];
		arr[from] = arr[to];
		arr[to] = tmp;
	}

	//冒泡排序(升序)
	//O(n^2) 稳定（假设在数列中存在a[i]=a[j]，若在排序之前，a[i]在a[j]前面；并且排序之后，a[i]仍然在a[j]前面。则这个排序算法是稳定的）
	private static  void bubbleSort(int[] arr) {
		for (int i = arr.length - 1; i >= 0; i--) {//最多需要n - 1趟
			int flag = 0;//增加一个标记，如果某一趟没有发生数据交换则说明数据已经排序好了，不需要下一趟了

			for (int j = 0; j < i ; j++) {
				if (arr[j] > arr[j + 1]) {
					swap(arr, j, j + 1);
					flag = 1;
				}
			}
			if (flag == 0) break; //当前这一趟没有发生交换，已经排序好了，退出
		}
	}

	/*
	     arr:   待排序数组
	     left:  排序起始位置
	     right: 排序终止位置
	 */
	private static void quickSort(int[] arr, int left, int right) {
		if (left > right || arr == null || arr.length == 0) {
			return;
		}
		int pivot = arr[left];//以左边界的值为基准
		int i = left;
		int j = right;//使用局部变量接收参数

		while (i != j) { //当i = j时表示已经排序好（即基准点左侧都小于基准点，基准点右侧都大于基准点）
			while (arr[j] >= pivot && i < j) { //从右往左找小于基准点的数
				j--;
			}
			while (arr[i] <= pivot && i < j) { //从左往右找大于基准点的数
				i++;
			}
			if (i < j) { //寻找到的大于/小于基准点的下标还没有相遇(相遇了就本轮排序结束跳出循环)，此时交换两个的位置
				swap(arr, i, j);
			}
		}

		//将基准数归位
		swap(arr, left, i);
		// arr[left] = arr[i];
		// arr[i] = pivot;

		quickSort(arr, left, i - 1);
		quickSort(arr, i + 1, right);

	}

	private static void quickSort1(int[] arr, int left, int right) {
		if (left >= right) {
			return;
		}
		int i = left;
		int j = right;
		int pivot = arr[left];
		boolean fromRight = true;

		while (i < j) {//while left == right, we are done
			if (fromRight) {
				fromRight = !fromRight;
				while (i < j && arr[j] > pivot) {
					j--;
				}
				if (i < j) arr[i] = arr[j];

			} else {
				fromRight = !fromRight;
				while (i < j && arr[i] < pivot) {
					i++;
				}
				if (i < j) arr[j] = arr[i];
			}
		}
		//to this, left == right

		arr[i] = pivot;
		quickSort(arr, left, i - 1);
		quickSort(arr, j + 1, right);
	}


}