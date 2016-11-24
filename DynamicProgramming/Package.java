{
	0/1 背包问题
}
import java.util.*;
public class Package{
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int[] weight = {5, 4, 3, 5, 7, 1, 9, 4};//物品体积
		int[] value = {20, 10, 12, 34, 25, 36, 13, 42};//物品价值
		int c = 10;//背包容量

		int[][] dp = new int[weight.length + 1][c + 1];//dp[i][j]表示前i个物品装入j体积背包中的最大价值
													   //舍弃索引0不用

		for (int i = 0; i <= weight.length + 1; i++) {
			for (int j = 0; j <= c; j++) {
				if(i == 0 || j == 0) {
					d[i][j] = 0;
					continue;
				}
				d[i][j] = Math.max(d[i-1][j], d[i-1][j-weight[j-1]] + value[i-1]);
			}
		}
		System.out.println(d[2][10] + "");
	}
}