import java.io.*;

public class QuickSort {
	
	public static void main(String[] args) {
		int[] arr = {9, 2, 2, 10, 3, 27, 1};//待排序的数组
		quickSort(arr, 0, arr.length - 1);
		for(int i = 0; i < arr.length; i++) {
			System.out.println(arr[i] + "  ");
		}
	}
	
	/*
		arr:   待排序数组
		left:  排序起始位置
		right: 排序终止位置
	*/
	private static void quickSort(int[] arr, int left, int right) {
		if(left > right || arr == null || arr.length == 0) {
			return;
		}
		int pivot = arr[left];//以左边界的值为基准
		int i = left;
		int j = right;//使用局部变量接收参数
		
		while(i != j) {//当i = j时表示已经排序好（即基准点左侧都小于基准点，基准点右侧都大于基准点）
			while(arr[j] >= pivot && i < j) {//从右往左找小于基准点的数
				j--;
			}
			while(arr[i] <= pivot && i < j) {//从左往右找大于基准点的数
				i++;
			}
			if(i < j) {//寻找到的大于/小于基准点的下标还没有相遇(相遇了就本轮排序结束跳出循环)，此时交换两个的位置
				int tmp = arr[i];
				arr[i] = arr[j];
				arr[j] = tmp;
			}
		}
		
		//将基准数归位
		arr[left] = arr[i];
		arr[i] = pivot;
		
		quickSort(arr, left, i-1);
		quickSort(arr, i+1, right);
		
	}
}
