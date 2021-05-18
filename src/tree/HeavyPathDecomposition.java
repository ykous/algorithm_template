package tree;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 树链剖分，给节点定义一个权重，如何定义有很多种方法，例如最普遍的以节点的值作为权重，被称作轻重剖分
 * <p>
 * 树链剖分本质目的就是方便的寻找到任意两点的最近公共祖先，也就是 LCA 问题
 * <p>
 * 而其实现可以再借助其他的数据结构实现更复杂的功能
 * <p>
 * =================================
 * <p>
 * 核心原理就是，根据权重把节点划分成轻节点和重节点，重节点是指它是其父节点的直接子节点中最重的
 * 同时我们把重节点与父节点的连边称作重边，并定义完全由重边构成的到达叶子节点的链称作重链
 * <p>
 * 显然，对于树种任意一点，其要么是重节点，要么是某一个重链的头节点
 * <p>
 * 剖分的目的，就是可以查询任意节点所在重链的头节点，当然，还可以实现动态的更新节点时的轻重变化
 */
public class HeavyPathDecomposition {

    private final Map<TreeNode, TreeNode> pathHead;
    private final Map<TreeNode, Integer> depth;

    public HeavyPathDecomposition(TreeNode root) {
        pathHead = new HashMap<>();
        depth = new HashMap<>();

        pathHead.put(root, root); // 根首先是轻节点

        // 构造重链
        // 同时记录每个节点的深度
        dfs(root, 0);

    }

    private void dfs(TreeNode root, int depth) {
        if (root == null){
            return;
        }
        this.depth.put(root,depth);
        int max = Integer.MIN_VALUE;
        TreeNode temp = null;
        // 找出子节点中最重的
        for (TreeNode child : root.children) {
            if (child.val > max) {
                temp = child;
                max = child.val;
            }
        }
        // 再次遍历，如果是轻节点则设置 pathHead 为自己，否则为当前节点的 pathHead
        // 递归执行
        for (TreeNode child : root.children) {
            if (child != temp){
                pathHead.put(child,child);
            }else {
                pathHead.put(child,pathHead.get(root));
            }
            dfs(child, depth+1);
        }

    }

    // 查找任意两对节点的最近公共祖先
    public TreeNode find(TreeNode node1,TreeNode node2){
        TreeNode p1 = pathHead.get(node1);
        TreeNode p2 = pathHead.get(node2);
        while (p1 != p2){
            if (depth.get(p1) > depth.get(p2)){
                node1 = p1.parent;
                p1 = pathHead.get(node1);
            }else {
                node2 = p2.parent;
                p2 = pathHead.get(node2);
            }
        }
        return depth.get(node1) > depth.get(node2)? node2 : node1;
    }

    // 更新节点的值
    public void update(TreeNode node,int val){
        TreeNode parent = node.parent;
        if (parent == null){
            return;
        }
        node.val = val;
        dfs(parent,depth.get(parent));
    }

}

class TreeNode {
    int val;
    TreeNode parent;
    List<TreeNode> children;
}
