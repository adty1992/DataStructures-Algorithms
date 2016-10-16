import java.util.*;
import java.lang.Math;
public class Main{
	public static void main(String[] args) {
// 		Scanner scan = new Scanner(System.in);
// 		while(scan.hasNext()){
// 			System.out.println(isPrime_v1(scan.nextInt()));
// 		}

// 		System.out.println(isPrime_Aratosterny(6));
		//打印前10个质数
		preNPrime(10);
	}

	//试除法（儿童级别）
	private static boolean isPrime_v1(int num){
		for (int i = 2; i < Math.sqrt(num); i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}

	//筛法确定num是否是素数
	private static boolean isPrime_Aratosterny(int n){
		if (n < 2) {//没有小于2的质数，负数更不用说
			return false;
		}
		boolean[] flag = new boolean[n + 1];//标志数组，为true即素数，使用boolean节省内存且不用频繁释放内存，n+1可以让第i个元素索引就是i
		Arrays.fill(flag, true);//初始化为true
		for (int i = 2; i <= n; i++) {
			if (flag[i] == true) {
			    //凡是i的j倍的数都为合数，flag[i * j]要设为false
				for (int j = 2; j * i <= n; j++) {
					flag[i * j] = false;
				}
			}
		}
		//至此，flag中为true的即是质数
		return flag[n];
	}

	//筛法确定前n个质数，和isPrime_Aratosterny方法类似，从flag数组中即可确定哪些是质数
	private static void preNPrime(int n){
		if (n < 0) {
			return;
		}
		//根据n确定质数范围，根据质数定理应为x/lnx，误差最大为15%，因此范围多加15%
		int range = (int)(n / Math.log(n))(1 + 0.15);
		boolean[] flag = new boolean[range + 1];
		Arrays.fill(flag, true);
		for (int i = 2; i <= range; i++) {
			if (flag[i] == true) {
			    //凡是i的j倍的数都为合数，flag[i * j]要设为false
				for (int j = 2; j * i <= range; j++) {
					flag[i * j] = false;
				}
			}
		}
		int count = 0;//增加计数器，只输出前N个质数
		for (int j = 2; j < flag.length; j++) {
			if (flag[j]) {
				if (++count > n) {
					return;
				}
				System.out.print(j+ ", ");
			}
		}
	}

}