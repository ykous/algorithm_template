package string;

import java.util.*;

/**
 * 以数据结构的方式构造，编码指定字符集，这里以小写字母为例
 */
public class ACMachine {
    private final Node root;

    public ACMachine(String[] words) {
        Node god = new Node();
        root = new Node();
        root.failed = god;
        Arrays.fill(god.children, root);
        for (String word : words) {
            insertWord(word);
        }
        updateFailed();
    }

    public static void main(String[] args) {
        ACMachine acMachine = new ACMachine(new String[]{"aeiou"});
        System.out.println(acMachine.match("asjdlaaeiousadqd"));
    }

    public Map<String,List<Integer>> match(String s){
        char[] chars = s.toCharArray();
        Map<String,List<Integer>> result = new HashMap<>();
        Node now = root;
        for (int i = 0, charsLength = chars.length; i < charsLength; i++) {
            char c = chars[i];
            Node child = now.children[parseIndex(c)];
            if (child == null) {
                now = now.failed;
                i -- ;
            } else {
                now = child;
                for (Integer integer : now.wordsLength) {
                    int t = i - integer+1;
                    String substring = s.substring(t, i + 1);
                    List<Integer> integers = result.computeIfAbsent(substring, k -> new ArrayList<>());
                    integers.add(t);
                }
            }
        }
        return result;
    }

    private int parseIndex(char c) {
        return c - 'a';
    }

    private void updateFailed() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            for (int i = 0; i < poll.children.length; i++) {
                Node child = poll.children[i];
                if (child == null) {
                    continue;
                }
                while (poll.failed.children[i] == null){
                    poll = poll.failed;
                }
                child.failed = poll.failed.children[i];
                child.wordsLength.addAll(child.failed.wordsLength);
                queue.add(child);
            }
        }
    }

    private void insertWord(String word) {
        char[] chars = word.toCharArray();
        Node now = root;
        for (char c : chars) {
            int t = parseIndex(c);
            Node child = now.children[t];
            if (child == null) {
                child = new Node();
                now.children[t] = child;
            }
            now = child;
        }
        now.wordsLength.add(chars.length);
    }

    static class Node {
        private final Node[] children = new Node[26];
        private Node failed = null;
        private final List<Integer> wordsLength = new ArrayList<>();

    }
}
