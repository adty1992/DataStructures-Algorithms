/**
 * λ����ʵ�ּӡ������ˡ�������
 */
public class Test {
    public static void main(String[] args){
        int a = -7;
        int b = -4;
        System.out.println(divide_v2(a, b));
    }

    // �ӷ�����
    static int add(int num1, int num2){
        int sum = num1 ^ num2;
        int carry = (num1 & num2) << 1;
        while(carry != 0){
            int a = sum;
            int b = carry;
            sum = a ^ b;
            carry = (a & b) << 1;
        }
        return sum;
    }
    
    // ��������
    static int substract(int num1, int num2){
        int subtractor = add(~num2, 1);// ��������Ĳ��루ȡ����һ��
        int result = add(num1, subtractor); // add()�������ӷ����㡡��
        return result ;
    }

    // �򵥰�˷�����
    static int multiply(int a, int b){
        // ȡ����ֵ����
        int multiplicand = a < 0 ? add(~a, 1) : a;
        int multiplier = b < 0 ? add(~b , 1) : b;// ���Ϊ����ȡ����һ���䲹�룬����������

        // �������ֵ�ĳ˻�����
        int product = 0;
        int count = 0;
        while(count < multiplier) {
            product = add(product, multiplicand);
            count = add(count, 1);// ����ɱ���count++����˵��������λ����ʵ�ּӷ�����
        }
        // ȷ���˻��ķ��š���
        if((a ^ b) < 0) {// ֻ�������λ�����a,b��ţ����������λΪ1�����ͬ�ţ����������λΪ0����������
            product = add(~product, 1);
        }
        return product;
    }

    // ��ǿ��˷�����
    public static int multiply_v2(int a, int b) {
        //�������ͱ�������ȡ����ֵ��
        int multiplicand = a < 0 ? add(~a, 1) : a;
        int multiplier = b < 0 ? add(~b , 1) : b;
        //�������ֵ�ĳ˻�����
        int product = 0;
        while(multiplier > 0) {
            if((multiplier & 0x1) > 0) {// ÿ�ο�����������һλ��������
                product = add(product, multiplicand);
            }
            multiplicand = multiplicand << 1;// ÿ����һ�Σ�������Ҫ����һλ��������
            multiplier = multiplier >> 1;// ÿ����һ�Σ�����Ҫ����һλ���ɶ�����ͼ��⣩����
        }
        //����˻��ķ��š���
        if((a ^ b) < 0) {
            product = add(~product, 1);
        }
        return product;
    }
    
    // �򵥰��������
    static int divide(int a, int b){
        // ��ȡ�������ͳ����ľ���ֵ
        int dividend = a > 0 ? a : add(~a, 1);
        int divisor = b > 0 ? a : add(~b, 1);

        int quotient = 0;// ��
        int remainder = 0;// ����
        // �����ó���ȥ����������ֱ��������С�ڱ����������������ˣ�
        while(dividend >= divisor){// ֱ����С�ڱ�����
            quotient = add(quotient, 1);
            dividend = substract(dividend, divisor);
        }
        // ȷ���̵ķ���
        if((a ^ b) < 0){// ��������ͱ�������ţ�����Ϊ����
            quotient = add(~quotient, 1);
        }
        // ȷ����������
        remainder = b > 0 ? dividend : add(~dividend, 1);
        return remainder;// ������
    }

    // ��ǿ���������
    static int divide_v2(int a,int b) {
        // ��ȡ�������ͳ����ľ���ֵ    
        int dividend = a > 0 ? a : add(~a, 1);
        int divisor = b > 0 ? a : add(~b, 1);
        int quotient = 0;// ��    
        int remainder = 0;// ����    
        for(int i = 31; i >= 0; i--) {
            //�Ƚ�dividend�Ƿ����divisor��(1<<i)�η�����Ҫ��dividend��(divisor<<i)�Ƚϣ�������(dividend>>i)��divisor�Ƚϣ�
            //Ч��һ�������ǿ��Ա�����(divisor<<i)�������ܵ��µ������������������dividend����С��divisor�������������dividend����divisor       
            if((dividend >> i) >= divisor) {
                quotient = add(quotient, 1 << i);
                dividend = substract(dividend, divisor << i);
            }
        }
        // ȷ���̵ķ���    
        if((a ^ b) < 0){
            // ��������ͱ�������ţ�����Ϊ����        
            quotient = add(~quotient, 1);
        }
        // ȷ����������    
        remainder = b > 0 ? dividend : add(~dividend, 1);
        return quotient;// ������
    }
}
