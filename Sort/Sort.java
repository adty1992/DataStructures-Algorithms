import java.util.*;
//各种排序算法
public class Sort{
	
	public static void main(String[] args) {
		int[] data = {22, 14, 32, 523, 3212, 1, 9, 34};
		Utils.printArray(data);
		cocktailSort(data);
		System.out.println(" ");
		Utils.printArray(data);
	}

	/*
	1. 插入排序（英语：Insertion Sort）是一种简单直观的排序算法。(稳定)
	它的工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
	插入排序在实现上，通常采用in-place排序（即只需用到O(1)的额外空间的排序），
	因而在从后向前扫描过程中，需要反复把已排序元素逐步向后挪位，为最新元素提供插入空间。

	平均来说插入排序算法复杂度为O(n^2)。因而，插入排序不适合对于数据量比较大的排序应用。
	*/
	private static void insertionSort(int[] data) {
		for (int i = 0; i < data.length - 1; i++) {
			int foucus = i + 1;//已排序序列的下一个元素索引，即待插入元素索引
			for (int j = i; j >= 0; j--) {//将待插入元素与已排序序列中的元素比较，如果比待插入元素大则交换，直到找到比待插入元素小的元素停止
				if (data[j] <= data[foucus]) {
					break;
				}
				Utils.swap(data, j, foucus);
				foucus--;
			}
		}
	}
	//二分法插入排序的思想和直接插入一样，只是找合适的插入位置的方式不同，
	//这里是按二分法找到合适的位置，可以减少比较的次数。
	//最佳情况O(n log n)，最差和平均情况O(n^2)
	private static void binaryInsertionSort(int[] data) {
		
		for (int i = 1; i < data.length; i++) {
			int key = data[i];
			int from = 0;
			int to = i - 1;
			int mid = 0;

			while(from <= to) {
				mid = from + (to - from) / 2;
				if (data[mid] > key) {
					to = mid - 1;
				}else {
					from = mid + 1;
				}
			}//from最后所指的就是要插入的位置

			//从插入点到i之间元素正题往后移一位
			for (int j = i - 1; j >= from; j--) {
				data[j + 1] = data[j];
			}

			//将要插入的数据插入到插入点
			data[from] = key;
		}
	}

	/*
	2. 选择排序（不稳定）

	首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，
	然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
	以此类推，直到所有元素均排序完毕。

	选择排序的主要优点与数据移动有关。
	如果某个元素位于正确的最终位置上，则它不会被移动。
	选择排序每次交换一对元素，它们当中至少有一个将被移到其最终位置上，因此对n个元素的表进行排序总共进行至多n-1次交换。
	在所有的完全依靠交换去移动元素的排序方法中，选择排序属于非常好的一种。

	最坏时间复杂度：O(n^2)
	最优时间复杂度：O(n^2)
	平均时间复杂度：O(n^2)
	最差空间复杂度：总共O(n) 需要辅助空间O(1)
	*/
	private static void selectionSort(int[] data) {
		int min = 0;//初始化最小元素索引
		for (int i = 0; i < data.length; i++) {
			for (int j = i + 1; j < data.length; j++) {
				if (data[j] < data[min]) {
					min = j;//更新最小值索引
				}
			}
			//交换最小值和当前未排序元素的头部元素
			Utils.swap(data, i, min);
		}

	}

	/*
	3. 冒泡排序

	最坏时间复杂度：O(n^2)
	最优时间复杂度：O(n)
	平均时间复杂度：O(n^2)
	最差空间复杂度：总共O(n) 需要辅助空间O(1)
	*/
	private static void bubbleSort(int[] data) {
		for (int i = 0; i < data.length - 1; i++) {//第一个循环指定了第二层循环的次数，即进行多少次冒泡，显然n长的数组要进行n-1次冒泡
			for (int j = 0; j < data.length - i - 1; j++) {//第二层循环每循环一次确定一个最大（小）值
				if (data[j] > data[j + 1]) {
					Utils.swap(data, j, j + 1);
				}
			}
		}
	}

	/*
	4. 鸡尾酒排序
	鸡尾酒排序等于是冒泡排序的轻微变形。不同的地方在于从低到高然后从高到低，而冒泡排序则仅从低到高去比较序列里的每个元素。
	
	最坏时间复杂度：O(n^2)
	最优时间复杂度：O(n)
	平均时间复杂度：O(n^2)
	最差空间复杂度：总共O(n) 需要辅助空间O(1)
	*/
	private static void cocktailSort(int[] data) {
		int left = 0;
		int right = data.length - 1;
		while(left < right) {
			//先从低往高排序
			for (int i = left; i < right; i++) {
				if (data[i] > data[i + 1]) {
					Utils.swap(data, i, i + 1);
				}
			}
			right--;

			//再从高往低
			for (int j = right; j > left; j--) {
				if (data[j] < data[j - 1]) {
					Utils.swap(data, j, j - 1);
				}
			}
			left++;
		}
	}

	/*
	5. 快速排序

		
	*/
	private static void quickSort(int[] data) {
		
	}

	/*
	4. 希尔排序，也称递减增量排序算法，因DL．Shell于1959年提出而得名，是插入排序的一种高速而稳定的改进版本。
		算法描述：
		1、先取一个小于n的整数d1作为第一个增量，把文件的全部记录分成d1个组。
	    2、所有距离为d1的倍数的记录放在同一个组中，在各组内进行直接插入排序。
	    3、取第二个增量d2<d1重复上述的分组和排序，
	    4、直至所取的增量dt=1(dt<dt-l<…<d2<d1)，即所有记录放在同一组中进行直接插入排序为止。

	原始的算法实现在最坏的情况下需要进行O(n2)的比较和交换。
	步长的选择是希尔排序的重要部分。只要最终步长为1任何步长序列都可以工作。
	*/
	private static void shellSort(int[] data) {

	}


	

	//工具类
	static class Utils {
		public static void printArray(int[] data) {
			for (int i = 0; i < data.length; i++) {
				System.out.print(data[i] + " ");
			}
		}

		public static void swap(int[] data, int x, int y) {
			if (x < data.length && y < data.length) {
				int tmp = data[x];
				data[x] = data[y];
				data[y] = tmp;
			}
		}

		public static int binarySearch(int[] data, int from, int to, int target) {
			while(from <= to) {
				if (from == to) {//如果要查找的序列长度为1，即from = to，则表示找到了插入位置
					if (data[from] > target) {
						return from;
					}else {
						return from + 1;
					}
				}
				if (to == from + 1) {//要查找的序列长度为2
					if (data[from] > target) {
						return from;
					}else {
						return to;
					}
				}
				int mid = from + (to - from) / 2;// 不要这样写 int mid = (from + to) / 2; 可能导致溢出
				if (data[mid] >= target) {
					to = mid - 1;
				}else {
					from = mid + 1;
				}
			}
			return -1;
		}

	}

}


   