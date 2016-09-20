//杨氏矩阵查找算法实现
//杨氏矩阵：在一个m行n列二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
public class YoungSearch {

	public static void main(String[] args) {
		int[][] arr = {{1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}};
		int height = 4;
		int width = 4;
		int target = 55;//待查找的数
		boolean have = youngSearch(arr, height, width, target);
		if (have) {
			System.out.println("have");
		} else {
			System.out.println("do not have");
		}
	}

	/*
	a[][]:  杨氏矩阵
	height: 矩阵的行数
	width:  矩阵的列数
	target: 待查找的数
	*/
	public static boolean youngSearch(int a[][], final int height, final int width, final int target) {
		int i = 0;
		int j = width - 1;
		int foucus = a[i][width - 1];//从第一行最后一列开始，即右上角的位置开始查找

		while (true) {
			if (foucus == target) {
				return true;
			} else if (foucus < target && i < height - 1) {//如果当前值小于目标值，则向下（下一行）寻找
				foucus = a[++i][j];
			} else if (foucus > target && j > 0) {//如果当前值大于目标值，则向左（上一列）寻找
				foucus = a[i][--j];
			} else {
				return false;
			}
		}
	}

	//Another approach
	public static  boolean youngSearch2(int [][] array, int target) {
		if (array == null || array.length == 0 || array[0].length == 0) {
			return false;
		}
		int hang = 0;
		int lie = array[0].length - 1;
		int focus = array[hang][lie];//从最右上角开始查找

		while (hang >= 0 && hang < array.length && lie >= 0 && lie < array[0].length) {
			focus = array[hang][lie];
			if (focus > target) {
				lie--;
			} else if (focus < target) {
				hang++;
			} else {
				return true;
			}
		}

		return false;
	}
}