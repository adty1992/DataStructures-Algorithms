public class Main{
    public static void main(String[] args) {
        int a = 6;
        int b = 12;
        System.out.println("最大公约数是：" + gcd_enumeration(a, b));
    }

    // 穷举法
    public static int gcd_enumeration(int a, int b){
        // 先找出a,b中小的那个
        int smaller = a;
        if (b < a) {
            smaller = b;
        }

        int gcd = 1;
        for (int i = 2; i <= smaller; i++) {
            // 如果能同时被a和b除尽，则更新GCD
            if ((a % i == 0) && (b % i == 0)) {
                gcd = i;
            }
        }
        return gcd;
    }

    // 辗转相除法（递归写法）
    public static int gcd_division_recursive(int a, int b){
        if (b == 0) {
            return a;
        }
        return gcd_division_recursive(b, a % b);
    }

    // 辗转相除法（迭代写法）
    public static int gcd_division_iteration(int a, int b){
        while(b != 0){// 为什么用b判断呢？因为b就是用来存a%b的，即上面算法步骤里的r的
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    // 辗转相减法（递归写法）
    public static int gcd_substract_recursive(int a, int b){
        if(a == b)
            return a;
        return a > b ? gcd_substract_recursive(a - b, b) : gcd_substract_recursive(a, b - a);
    }

    // 辗转相减法（迭代写法）
    public static int gcd_substract_iteration(int a, int b){
        // 如果a,b不相等，则用大的数减去小的数，直到相等为止
        while(a != b){
            if(a > b)
                a = a - b;
            else
                b = b - a;
        }
        return a;
    }
}