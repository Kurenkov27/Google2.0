import java.math.BigInteger;

public class Easy {
    public static void main(String[] args) {

        int n = 10;
        //System.out.println(factorial(n));
        System.out.println(trailingZeroes(n));
    }


    public static int trailingZeroes(int n) {
        BigInteger m = factorial(n);
        int count = 0;
        int k = 1;
        BigInteger d = BigInteger.valueOf(10);
        BigInteger d2 = d.pow(k);
        while(m.remainder(d2) == BigInteger.valueOf(0)){
            count++;
            k++;
            d2 = d.pow(k);
        }
        return count;
    }

    public static BigInteger factorial(int n) {
        BigInteger fact = BigInteger.valueOf(1);
        BigInteger num = BigInteger.valueOf(n);
        while(n>1) {
            fact = fact.multiply(num);
            num = num.subtract(BigInteger.valueOf(1));
            n = n - 1;
        }
        return fact;
    }
}

