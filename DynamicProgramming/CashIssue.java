{
	动态规划求解硬币问题
}

import java.util.*;
public class CashIssue{
	public static void main(String[] args) {
		int[] cashs = {5, 1, 2};
		int money = 12;
		int[] dp = new int[money+1];//从第1个位置开始存储，舍弃第0个位置不用，所以要加1
		makeChange(cashs, cashs.length, money, dp);
	}

	/*
	cashs: 所有的可用找零的硬币
	cashsCount: 可用找零的硬币种类数
	money: 待找零的零钱
	dp: 用来存储子问题解得数组， dp[i]表示找零i元需要的最少硬币数
	*/
	private static void makeChange(int[] cashs, int cashsCount, int money, int[] dp){
		//先对可用找零的硬币升序排序
		Arrays.sort(cashs);

		//填表，每一分钱都计算其所需最少硬币数
		for (int eachMoney = 1; eachMoney <= money; eachMoney++) {
			//当前找零所需的最少硬币数，初始化为最大即其本身大小，即用一块钱找零
			int minCoins = eachMoney;
			List<Integer> coins = new ArrayList<>();

			// 遍历每一种面值的硬币，看是否可作为找零的其中之一
			for (int j = 0; j < cashsCount; j++) {
				//零钱要小于待找零钱数，大于没意义，找3块你拿个5块的？
				if (cashs[j] < eachMoney) {
					int temp = dp[eachMoney-cashs[j]] + 1;
					coins.add(cashs[j]);
					if(temp < minCoins) minCoins = temp;
				}
			}

			dp[eachMoney] = minCoins;
			System.out.println("找零" + eachMoney + "所需的最少硬币数为：" + dp[eachMoney] + "找零：" + coins.toString());
		}
	}
}