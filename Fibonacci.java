import java.util.*;
public class Main{
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while(scan.hasNext()){
			getFibonacci(scan.nextInt());
		}
	}

	private static int getFibonacci(int num){
		if (num == 1 || num == 2) {
			return 1;
		}
		return(getFibonacci(num - 1) + getFibonacci(num - 2));
	}
}