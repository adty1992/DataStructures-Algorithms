
{
	平面上有 N * M 个格子，每个格子中放着一定数量的苹果。
	你从左上角的格子开始， 每一步只能向下走或是向右走，每次走到一个格子上就把格子里的苹果收集起来， 这样下去，你最多能收集到多少个苹果。

	解析：

	首先，我们要找到这个问题中的“状态”是什么？
	我们必须注意到的一点是， 到达一个格子的方式最多只有两种：从左边来的(除了第一列)和从上边来的(除了第一行)。
	因此为了求出到达当前格子后最多能收集到多少个苹果， 我们就要先去考察那些能到达当前这个格子的格子，到达它们最多能收集到多少个苹果。
	(是不是有点绕，但这句话的本质其实是DP的关键：欲求问题的解，先要去求子问题的解)

	定义状态 S[i][j] 表示我们走到 (i, j) 这个格子时，最多能收集到多少个苹果。

	则状态转移方程为：
	S[i][j] = A[i][j] + max(S[i - 1][j], if i > 0 ; S[i][j - 1], if j > 0)
			其中i代表行，j代表列，下标均从0开始；A[i][j]代表格子(i, j)处的苹果数量。
		}

import java.util.*;
public class Main {
	public static void main(String[] args) {
		int[][] A = {{3, 5, 1, 10}, {2, 9, 6, 3}, {7, 3, 4, 1}, {8, 9, 7, 6}};

		System.out.println("最多可以收集 " + getMaxApple(A)  + " 个苹果");
	}

	private static int getMaxApple(int[][] A) {

		int[][] S = new int[A.length][A[0].length];//创建一个与A数组同维的数组，用来存储最多苹果数
		int max = 0;//用来保存可收集到的最多苹果数量，如果需要知道最多能收集多少苹果，则可以返回此值
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[0].length; j++) {
				int append = 0;
				if (i > 0 && j > 0) {//既可能从上面来，也可能从左边来，这时要取两者的最大值
					append = Math.max(S[i - 1][j], S[i][j - 1]);
				} else if (i > 0) {//当前在第1列，即下标为0的列，这是只能从上向下
					append = S[i - 1][j];
				} else if (j > 0) { //当前在第一行，即下标为0的行，这时只可能从左边来
					append = S[i][j - 1];
				}
				S[i][j] = A[i][j] + append;
				//刷新收集的最多苹果数，其实最大值一定在最右下角的位置
				if (S[i][j] > max) max = S[i][j];
			}
		}
		// 至此，S填充完毕
		//S中即包含了走到 (i, j) 这个格子时，最多能收集到多少个苹果

		//这里直接返回最右下角位置的值，也即最多可收集苹果数
		return S[A.length - 1][A[0].length - 1];
	}
}