package string;


public class KMP {
    private static int[] next;
    private static char[] t;

    // 设置模式串
    public static void set(char[] chars) {
        t = chars;
        next = new int[t.length + 1];
        next[0] = -1;
        for (int i = 0; i < t.length; i++)
            next[i + 1] = match(i, t[i], i) + 1;
    }

    public static void main(String[] args) {
        set("abc".toCharArray());
        match("abcababcdababc".toCharArray());
    }
    // 返回所有匹配到的位置
    public static void match(char[] s) {
        int now = 0;
        for (int i = 0; i < s.length; i++) {
            now = match(now, s[i], t.length) + 1;
            if (now == t.length) exec(i - now + 1);
        }
    }

    private static void exec(int i) {
        System.out.println(i);
    }


    // 模式串构造的本质也是KMP，特殊之处在于模式串是原串的一个前缀
    // 显然匹配仅失败在越过边界的时候，因此此函数当在模式串构造时调用总是以，match(当前构造位置,当前位置字符,当前构造位置)，的形式出现
    // 代码也可以共用于普通匹配，此时的 boundary 永远等于模式串的长度
    private static int match(int now, char target, int boundary) {
        // 把越过边界视为一种失配
        while (now != -1 && (now >= boundary || t[now] != target))
            now = next[now];
        return now;
    }
}
