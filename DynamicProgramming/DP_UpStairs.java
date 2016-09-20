
{
	有一楼梯共m级，刚开始时你在第一级，若每次只能跨上一级或二级，要走上第m级，共有多少走法？
	注：规定从一级到一级有0种走法。

	输入:
	输入数据首先包含一个整数n(1 <= n <= 100)，表示测试实例的个数，然后是n行数据，每行包含一个整数m，（1 <= m <= 40), 表示楼梯的级数。

	输出
	对于每个测试实例，请输出不同走法的数量。

	样例输入
	2
	2
	3

	样例输出
	1
	2
}

import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);


		int m = scan.nextInt();
		int[] Box = new int[m + 1];
		Box[1] = 1;//第0个位置舍弃不用，从第一个位置开始存
		Box[2] = 2;
		for (int j = 2; j <= m; j++) {
			Box[j] = Box[j - 1] + Box[j - 2];
		}
		System.out.println(Box[m]);

	}
}