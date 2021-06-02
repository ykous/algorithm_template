package string;

import java.util.Random;

// 前缀树
// 同样通过数组模拟内存
// 模板支持自定义字符集，自定义额外属性
public class Tire {
    static final int N = 100010;
    static final int[][] children = new int[N][26];
    static int index = 1;
    static final int root = 0;
    static final int NULL = 0;

    // 除了插入，还返回对应的下标，可以当作查询使用
    static int insert(char[] s){
        int now = root;
        for (char c : s) {
            int idx = parse(c);
            // 生成新节点，在 tire 树的应用中通常新的节点不需要什么初始化操作，默认值 0 就足够了
            // 例如 ac 自动机的 fault 指针就是在之后生成的
            if (children[now][idx] == NULL) children[now][idx] = index++;
            now = children[now][idx];
        }
        return now;
    }

    // 绝大多数情况下，tire 树题型是不需要查询不存在的字符串的，但以防万一
    // 没查到返回 NULL = 0
    static int query(char[] s){
        int now = root;
        for (char c : s) {
            int idx = parse(c);
            if (idx == NULL) return NULL;
            now = idx;
        }
        return now;
    }

    // 自定义字符集到下标的映射关系
    private static int parse(char c) {
        return c-'a';
    }
}
