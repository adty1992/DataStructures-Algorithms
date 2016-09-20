{
	动态规划(Dynamic Programming, DP)求解最长增序子序列问题(Longest Increasing Subsequence, LIS)

	比如序列： 5，3，4，8，6，7

	解析：
	定义d(i)，表示前i个数中以a[i]结尾的最长非降子序列的长度

	前1个数的LIS长度d(1) = 1(序列：5)
	前2个数的LIS长度d(2) = 1(序列：3；3前面没有比3小的)
	前3个数的LIS长度d(3) = 2(序列：3，4；4前面有个比它小的3，所以d(3) = d(2) + 1)
	前4个数的LIS长度d(4) = 3(序列：3，4，8；8前面比它小的有3个数，所以 d(4) = max {d(1), d(2), d(3)} +1 = 3)

	OK，分析到这，我觉得状态转移方程已经很明显了，如果我们已经求出了d(1)到d(i - 1)， 那么d(i)可以用下面的状态转移方程得到：

	d(i) = max {1, d(j) + 1}, 其中j < i, a[j] <= a[i]

	用大白话解释就是，想要求d(i)，就把i前面的各个子序列中， 最后一个数不大于a[i]的序列长度加1，然后取出最大的长度即为d(i)。
	当然了，有可能i前面的各个子序列中最后一个数都大于A[i]，那么d(i) = 1， 即它自身成为一个长度为1的子序列。
}

public class LIS {

	public static void main(String[] args) {
		int[] a = {5, 3, 4, 8, 6, 7};
		System.out.println(Lis(a, 6) + "");
	}

	// 求一个序列arr前n个数的LIS，以数组形式返回，数组中的数值即最长增序子序列LIS
	private static int Lis(int[] a, int n) {
		if (n > a.length)  return 0;

		int lis = 1;
		int[] d = new int[n];//盒子里存储着前j + 1个数的LIS

		for (int i = 0; i < n; i++) {//d(i) = max{1, d(j)+1},其中j<i,A[j]<=A[i]
			d[i] = 1;//初始化前i个数的LIS为1

			for (int j = 0; j < i; j++) {//考察前i - 1个数
				if (a[j] <= a[i]) {
					if (d[j] + 1 > d[i]) d[i] = d[j] + 1; //更新box[i]为最大，d(i) = max{1, d(j)+1}
				}
			}

			if (d[i] > lis) lis = d[i]; //更新lis为最大
		}

		return lis;
	}

}