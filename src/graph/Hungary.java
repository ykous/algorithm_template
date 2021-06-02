package graph;

import java.util.Arrays;

// 匈牙利算法，用于计算二分图中的最大配对数量，稍加修改也可以求出具体的配对情况
// 复杂度 O(n*m)
public class Hungary {
    // 匈牙利算法只需要记录一半部分的边的情况就可以，虽然是无向图
    static final int N = 510, M = 100010, NULL = 0;
    static final int[] head = new int[N], e = new int[M], ne = new int[M];
    static int idx = 1;

    // n1 是左半部分节点的数量，n2 是右半部分
    // 我们把 [0,n1) 的点作为左半部分点的编号 [0,n2) 作为右半部分的编号
    // 请注意虽然此时编号冲突但是并不影响算法计算
    // 如果算法来自与一个真实的二分图，那么我们可以使用染色法区分出左右部分的节点
    // 然后得出左部分节点和右部分节点的序列，请注意右部分到底怎么排布都无所谓
    static int n1, n2;

    static int[] match = new int[N];
    static boolean[] affiliation = new boolean[N];

    // 算法的模型可以看作是男女生配对，具有连边的则表示二者互有好感，而我们要求出在不违背好感的情况下的最大配对数量
    static int hungary() {
        // match[i] 表示 i 号女生配对的男生，初始状态下，所有女生都没有配对，为 -1
        Arrays.fill(match,-1);
        int res = 0;
        // 为所有男生配对，由于我们的序列就是 [0,n1) ，所以写成 fori 即可，必要时可替换成其他序列
        // 大多数时候只有这里需要改
        for (int i = 0; i < n1; i++) {
            // occupy[i] 表达 i 号女生在这一轮配对中是否确定了归属
            // 在每一次为一个男生配对的最初，都不确定所有女生的归属是否确定
            Arrays.fill(affiliation, false);
            if (find(i)) res ++;
        }
        return res;
        // 执行结束后 match 中记录了所有的配对情况
    }

    // find 函数的含义是为 boy 配对一个女生，并返回是否配对成功
    static boolean find(int boy) {
        // 遍历所有有好感的女生，尝试配对
        for (int p = head[boy] ; p != NULL; p = ne[p]) {
            int girl = e[p];
            // 如果女生的归属已经确定，不管属不属于自己，都不考虑，此时我们的目的是找到一个新的
            if (affiliation[girl]) continue;
            // 此时，女生的归属已经确定，不是 boy 就是 match[girl]
            // 因为要不然女生没有配对，则配对成功，归属为 boy
            // 要不然配对失败，归属为 match[girl]
            // 其他女生的配对也不可能占据 girl ，因为 match[girl] 已经确定了其没有办法找到另一个
            affiliation[girl] = true;
            // 我们看看其是否有配对的人，就算有我们看看能不能让这个配对者换一个
            if(match[girl] == -1 || find(match[girl])) {
                match[girl] = boy;
                return true;
            }
        }
        return false; // 尝试了所有女生都不可能，则放弃
    }
}
