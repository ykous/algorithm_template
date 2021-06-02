package string;

import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

// 中缀表达式计算模板
// 表达式没有空格，只包含单字符运算符（没有 >> 之类的），只包含二元运算符
// 模板可以自定义二元运算符及其优先级
public class InfixExpression {
    static Map<Character, Integer> priority =
            Map.of('(', 0, '+', 1, '-', 1, '*', 2, '/', 2);
    static Stack<Integer> nums = new Stack<>();
    static Stack<Character> opts = new Stack<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String exp = in.next();
        System.out.println(match(exp));
    }

    static int match(String express) {
        char[] chars = express.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (Character.isDigit(c)) {
                int j = i, num = 0;
                while (j < chars.length && Character.isDigit(chars[j]))
                    num = num * 10 + chars[j++] - '0';
                i = j - 1;
                nums.push(num);
            } else if (c == '(') {
                opts.push(c);
            } else if (c == ')') {
                while (opts.peek() != '(') calc();
                opts.pop();
            } else {
                while (!opts.empty() && priority.get(opts.peek()) >= priority.get(c))
                    calc();
                opts.push(c);
            }
        }
        while (!opts.empty()) calc();
        return nums.pop();
    }

    private static void calc() {
        int num2 = nums.pop();
        int num1 = nums.pop();
        char opt = opts.pop();
        int res;
        if (opt == '+') res = num1 + num2;
        else if (opt == '-') res = num1 - num2;
        else if (opt == '*') res = num1 * num2;
        else res = num1 / num2;
        nums.push(res);
    }
}
