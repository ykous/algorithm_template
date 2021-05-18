package math;

// 最小公倍数
public class LCM {

    static int gcd(int i, int j) {
        return j > 0 ? gcd(j, i % j) : i;
    }

    static int lcm(int i, int j) {
        return i * j / gcd(i, j);
    }

    public static void main(String[] args) {
        System.out.println(lcm(13231, 124541));
    }
}
