//二分查找：在有序数组中查找某一特定元素的搜索算法
public class BinarySearch {
	public static void main(String[] args) {
		int[] data = {1, 4, 6, 14, 56, 233, 1234};
		int target = 233;
		int index = binarySearch2(data, 0, data.length - 1, target);
		if (index > -1) {
			System.out.println("found");
		}else {
			System.out.println("not found");
		}
	}

	/** 
 	* 递归方法实现二分查找
 	* @param data   已排序数组(这里假设是从小到大排序) 
 	* @param from   起始位置 
 	* @param to     终止位置 
 	* @param target 要查找的值
 	* @return 要查找的值在数组中的位置，如果没找到则返回-1
 	*/  
	private static int binarySearch1(int[] data, int from, int to, int target) {
		if (from <= to) {
			int mid = from + (to - from) / 2;//中间位置，为了防止溢出使用这种方式求中间位置
			if (data[mid] < target) {//中间的值比目标值小，则在左半边继续查找
				return binarySearch1(data, mid + 1, to, target);
			}else if(data[mid] > target){//中间的值比目标值大，则在右半边继续查找
				return binarySearch1(data, from, mid - 1, target);	
			}else {//找到了，把找到的情况放在最后是因为多数情况下中间值不是大于就是小于，这样做可以节省操作
				return mid;
			}
		}
		return -1;
	}

	/** 
 	* 非递归方法实现二分查找
 	* @param data   已排序数组(这里假设是从小到大排序) 
 	* @param from   起始位置 
 	* @param to     终止位置 
 	* @param target 要查找的值
 	* @return 要查找的值在数组中的位置，如果没找到则返回-1
 	*/  
	private static int binarySearch2(int[] data, int from, int to, int target) {
		while(from <= to) {
			int mid = from + (to - from) / 2;
			if (data[mid] < target) {
				from = mid + 1;				
			}else if(data[mid] > target) {
				to = mid - 1;
			}else {//找到了，把找到的情况放在最后是因为多数情况下中间值不是大于就是小于，这样做可以节省操作
				return mid;
			}
		}
		return -1;
	}
}