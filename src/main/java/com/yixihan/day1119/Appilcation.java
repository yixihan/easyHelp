package com.yixihan.day1119;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author Yixin Cao (November 1, 2022)
 * <p>
 * A binary search tree for Polyu students.
 * <p>
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * Update on November 8: fix a bug in the main method.
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * <p>
 * Since we only store students, the class doesn't use generics.
 */
public class Appilcation { // Please change!


    /**
     * No modification to the class {@code Student} is allowed.
     * If you change anything in this class, your work will not be graded.
     */
    static class Student {
        String id;
        String name;

        public Student(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String toString() {
            return id + ", " + name;
        }
    }

    /**
     * No modification to the class {@code Node} is allowed.
     * If you change anything in this class, your work will not be graded.
     */
    private class Node {
        Student e;
        public Node lc, rc; // left child; right child

        @SuppressWarnings("unused")
        public Node(Student data) {
            this.e = data;
        }

        public String toString() {
            return e.toString ();
        }
    }

    Node root;

    /**
     * Insert a new student into the tree.
     * <p>
     * VERY IMPORTANT.
     * <p>
     * I've discussed this question with the following students
     * <p>
     * 1.
     * 2.
     * 3.
     * ...
     * <p>
     * I've sought help from the following Internet resources and books:
     * <p>
     * 1. <a href="https://www.cnblogs.com/yahuian/p/java-binary-search-tree.html">二叉搜索树详解（Java实现）</a><br>
     * 2.
     * 3.
     * ...
     * <p>
     * Running time: O( ). A function of d and n.
     */
    public void insert(Student s) {
        //待插入的节点
        Node p = new Node (s);

        if (root == null) {
            root = new Node (s);
            return;
        }
        Node parent;
        Node current = root;
        while (true) {
            parent = current;
            if (compareTo (current.e.name, p.e.name, false) < 0) {
                // 左子树
                current = current.lc;
                if (current == null) {
                    parent.lc = p;
                    return;
                }
            } else {
                // 右子树
                current = current.rc;
                if (current == null) {
                    parent.rc = p;
                    return;
                }
            }
        }

    }

    /**
     * Calculate the largest difference between the depths of the two subtrees of a node.
     * <p>
     * VERY IMPORTANT.
     * <p>
     * I've discussed this question with the following students:
     * 1.
     * 2.
     * 3.
     * ...
     * <p>
     * I've sought help from the following Internet resources and books:
     * 1.
     * 2.
     * 3.
     * ...
     * <p>
     * Running time: O( ). A function of d and n.
     */
    public int maxDepthDiff() {
        return maxDepthDiff (root, 0);
    }

    private int maxDepthDiff(Node node, int maxDiff) {
        if (node == null) {
            return maxDiff;
        }
        maxDiff = Math.max(maxDiff, getDepthDiff (node));
        return Math.max (maxDepthDiff (node.lc, maxDiff), maxDepthDiff (node.rc, maxDiff));
    }

    private int getDepthDiff (Node node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = getHeight (node.lc, 0);
        int rightHeight = getHeight (node.rc, 0);
        // TODO for test
//        System.out.println (node.e.id + ", leftHeight = " + leftHeight + ", rightHeight = " + rightHeight);
        return Math.abs (leftHeight - rightHeight);
    }

    private int getHeight(Node node, int depth) {
        if (node == null) {
            return depth;
        }
        depth += 1;
        return Math.max (getHeight (node.lc, depth), getHeight (node.rc, depth));
    }

    /**
     * Calculate the largest difference between the sizes of the two subtrees of a node.
     * <p>
     * VERY IMPORTANT.
     * <p>
     * I've discussed this question with the following students:
     * 1.
     * 2.
     * 3.
     * ...
     * <p>
     * I've sought help from the following Internet resources and books:
     * 1.
     * 2.
     * 3.
     * ...
     * <p>
     * Running time: O( ). A function of d and n.
     */
    public int maxSizeDiff() {
        return maxSizeDiff (root, 0);
    }

    private int maxSizeDiff(Node node, int maxDiff) {
        if (node == null) {
            return maxDiff;
        }
        maxDiff = Math.max(maxDiff, getSizeDiff (node));
        return Math.max (maxSizeDiff (node.lc, maxDiff), maxSizeDiff (node.rc, maxDiff));
    }

    private int getSizeDiff (Node node) {
        if (node == null) {
            return 0;
        }
        int leftSize = getSize (node.lc, 0);
        int rightSize = getSize (node.rc, 0);
        // TODO for test
//        System.out.println (node.e.id + ", leftSize = " + leftSize + ", rightSize = " + rightSize);
        return Math.abs (leftSize - rightSize);
    }

    private int getSize(Node node, int size) {
        if (node == null) {
            return size;
        }
        size += 1;
        size = getSize (node.lc, size);
        size = getSize (node.rc, size);
        return size;
    }

    /**
     * Calculate the number of nodes that have only one child.
     * <p>
     * VERY IMPORTANT.
     * <p>
     * I've discussed this question with the following students:
     * 1.
     * 2.
     * 3.
     * ...
     * <p>
     * I've sought help from the following Internet resources and books:
     * 1.
     * 2.
     * 3.
     * ...
     * <p>
     * Running time: O( ). A function of d and n.
     */
    public int nodesWithOneChild() {
        if (root == null) {
            return 0;
        }
        return nodesWithOneChild (root, 0);
    }

    private int nodesWithOneChild(Node node, int cnt) {
        cnt += verifyOneChild (node) ? 1 : 0;
        if (node.lc != null) {
            return nodesWithOneChild (node.lc, cnt);
        }
        if (node.rc != null) {
            return nodesWithOneChild (node.rc, cnt);
        }
        return cnt;
    }

    private boolean verifyOneChild(Node node) {
        return (node.lc == null && node.rc != null) || (node.lc != null && node.rc == null);
    }

    /**
     * Find a student with the specified name.
     * You may return any of them if there are multiple students of this name.
     * <p>
     * VERY IMPORTANT.
     * <p>
     * I've discussed this question with the following students:
     * 1.
     * 2.
     * 3.
     * ...
     * <p>
     * I've sought help from the following Internet resources and books:
     * 1.
     * 2.
     * 3.
     * ...
     * <p>
     * Running time: O( ). A function of d and n.
     */
    public Student searchFullname(String name) {
        return searchFullname (root, name);
    }

    private Student searchFullname(Node node, String name) {
        if (node == null) {
            return null;
        }
        if (node.e.name.equals (name)) {
            return node.e;
        }
        if (compareTo (node.e.name, name, false) < 0) {
            return searchFullname (node.lc, name);
        } else {
            return searchFullname (node.rc, name);
        }
    }


    /**
     * Find all students with the specified surname.
     * Consider the first word as the surname.
     * Warning: Make sure "Liu Dennis" does not show when you search "Li."
     * <p>
     * VERY IMPORTANT.
     * <p>
     * I've discussed this question with the following students:
     * 1.
     * 2.
     * 3.
     * ...
     * <p>
     * I've sought help from the following Internet resources and books:
     * 1.
     * 2.
     * 3.
     * ...
     * <p>
     * Running time: O( ). A function of d and n.
     */
    public Student[] searchSurname(String surname) {
        List<Student> list = new ArrayList<> ();
        searchSurname (root, surname, list);
        return list.toArray (new Student[0]);
    }

    private void searchSurname(Node node, String surname, List<Student> list) {
        if (node == null) {
            return;
        }
        if (compareTo (node.e.name, surname, true) == 0) {
            list.add (node.e);
        }
        if (compareTo (node.e.name, surname, true) < 0) {
            searchSurname (node.lc, surname, list);
        } else {
            searchSurname (node.rc, surname, list);
        }
    }

    /**
     * Find all students who are taking a certain class.
     * The input is an array of student names.
     * <p>
     * VERY IMPORTANT.
     * <p>
     * I've discussed this question with the following students:
     * 1.
     * 2.
     * 3.
     * ...
     * <p>
     * I've sought help from the following Internet resources and books:
     * 1.
     * 2.
     * 3.
     * ...
     * <p>
     * Running time: O( ). A function of d, n, and c.
     */
    public Student[] searchClass(String[] roster) {
        Student[] ans = new Student[roster.length];
        for (int i = 0; i < roster.length; i++) {
            ans[i] = searchFullname (roster[i]);
            if (ans[i] == null) {
                System.out.println ("not found student [" + roster[i] + "]!");
            }
        }
        return ans;
    }

    /**
     * You are free to make any change to this method.
     * You can even remove it if you want to test your code with other means.
     */
    public static void main(String[] args) {
        Appilcation tree = new Appilcation ();
        /*
         * Here are 192 names you can use for testing.
         *
         * You should test your code with more names (>= 1 million). One way is to generate random names.
         * Tips: Given names can be random strings. You can assign a random surname from this list
         * https://surnam.es/hong-kong.
         */
        String[] names = {"Chang Chi Fung", "Lee Cheuk Kwan", "Liu Tsz Ki", "Vallo David Jonathan Delos Reyes", "Jiang Han", "Park Taejoon", "Shin Hyuk", "Jung Junyoung", "Lam Wun Yiu", "Kwok Shan Shan", "Chui Cheuk Wai", "Lam Yik Chun", "Luo Yuehan", "Wang Hao", "Mansoor Haris", "Liang Wendi", "Meng Guanlin", "Wang Zhiyu", "Mak Ho Lun", "Liu Zixian", "Geng Qiyang", "Fong Chun Ming", "Cheung Chun Hei", "Lau Tsun Hang Ryan", "Cheung Cheuk Hang", "Liu Chi Hang", "Wong Yiu Nam", "Cheng Kok Yin", "Lam Wai Kit", "Liu Valerie", "Tam Chung Man", "Yan Tin Chun", "Lok Yin Chun", "Ng Ming Hei", "Lo Chun Hin", "Lam Pui Wa", "Lo Cho Hung", "Chu Tsz Fui", "Chow Ho Man", "Gao Ivan", "Ng Man Chau", "Iu Lam Ah", "Hung Wai Hin", "Tong Ka Yan", "Lo Ching Sau", "Lee Lee Ling", "Lam Ho Yin", "Sze Kin Ho", "Ng Siu Chi", "Wong Cheuk Laam", "Chan Yat Chun", "Lee Lap Shun", "Deng Chun Yung", "Fung Ki Ho", "Yeung Ting Kit", "Shiu Chi Yeung", "Kwan Yat Ming", "Chan Kin Kwan", "Leung Man Yi", "Yau Minki", "Hong Yuling", "Yung Wing Kiu", "Yuen Marco Siu Tung", "Lo Yung Sum", "Cheung Tsz Ho", "Chu Ka Hang", "Chan Man Yi", "Ng Yuet Kwan", "Lui Cheuk Lam Lily", "Tai Cheuk Hin", "Ong Chun Wa", "Yiu Pun Cham", "Cheng Ho Wing", "Wong Tsz Wai Desmond", "Lai Ho Sum", "Lee Siu Wai", "Lai Ming Hin", "Leung Hoi Ming", "To Ka Hei", "Tang Tsz Yeung", "Au Yeung Chun Yi", "Lau Ue Tin", "Yau Sin Yan", "Lam Ho Yan", "Tong Mei Chun", "Cheung Tsz Kwan", "Chiang Tin Hang", "Lai Kit Lun", "Cheung Sum Yin", "Wang Matthew Moyin", "Jiang Guanlin", "Edgar Christopher", "Liang Zhihong", "Bai Ruiyuan", "Chen Ru Bing", "Hu Wenqing", "Zhou Siyu", "Wang Yukai", "Lam Hei Yung", "Zhang Wanyu", "Wei Xianger", "Conte Chan Gabriel Alejandro", "Pratento Dylan Jefferson", "Lam Wan Yuet", "Chen Ziyang", "Jiang Zheng", "Xu Le", "He Boyan", "Liu Minghao", "Zhang Zhiyuan", "Chen Yuxuan", "Jin Cheng", "Liu Chenxi", "Qiu Siyi", "Han Wenyu", "Chan Cheuk Hei", "Ho Tsz Kan", "Du Haoxun", "Zheng Shouwen", "Ye Feng", "Yu Kaijing", "Lee Jer Tao", "Shen Ziqi", "Wang Yihe", "Liu Yanqi", "Zhang Wenxuan", "Huang Tianji", "Lu Zhoudao", "Zhang Tianyi", "Yuan Yunchen", "Liu Chengju", "Wei Siqi", "Liu Yuzhou", "Zhao Letao", "Huo Shuaining", "Li Kin Lung Anson", "Qin Qipeng", "Li Jiale", "He Rong", "Hiu Jason Kenneth", "Lam Ka Hang", "Li Tong", "Lau Choi Yu Elise", "Liu Dong", "Li Shuhang", "Zeng Yuejia", "Cai Zhenyu", "Lau Siu Hin", "Szeto Siu Chun", "Leung Cheuk Kit", "Cai Haoyu", "Ye Chenwei", "Huang Yidan", "Lee Kam Hung", "Wang Zhengyang", "Bao Yucheng", "Niyitegeka Berwa Aime Noel", "Lyateva Paulina Veselinova", "Zhang Boyu", "Chen Junru", "Fang Yuji", "Lin Qinfeng", "Tang Haichuan", "Hu Yuhang", "Zhou Taiqi", "Fang Anshu", "Wu Chao", "Zhang Haolin", "Ivanichev Mikhail", "Luo Yi", "Ieong Mei Leng", "Lee Wang Ho", "Jian Junxi", "Tam Tin", "Kjoelbro Niklas August", "Lee Hau Laam", "Pak Yi Ching", "Pang Kin Hei", "Xue Bingxin", "Lau Sin Yee", "Kwok Sze Ming", "Chan Lok Hin", "Chan Ho Yin Francis", "Chung Wai Ching", "Hu Hongjian", "Yiu Chi Chiu", "Tso Yuet Yan", "Chow Chun Wang", "Li Wun Wun", "Chen Junyu", "Kan Wai Yi", "Fong Chun Ho"};
//                String[] names = {"Tse Kay", "Ho Denise", "Yung Joey", "Tang Gloria", "Chan Eason", "Lau Andy", "Cheung Jacky"};
        SecureRandom random = new SecureRandom ();
        int id = 22222222;
        for (String name : names) {
            id += random.nextInt (100);
            tree.insert (new Student (String.valueOf (id), name));
        }

        // for test
//        NodeShow.show (tree.root);

        // Statistics
        System.out.println ("The largest depth difference of a node is: " + tree.maxDepthDiff ());
        System.out.println ("The largest size difference of a node is: " + tree.maxSizeDiff ());
        System.out.println (tree.nodesWithOneChild () + " nodes have a single child.");

        String[] comp2011 = {"Chang Chi Fung", "Lee Cheuk Kwan", "Liu Tsz Ki", "Vallo David Jonathan Delos Reyes", "Jiang Han", "Park Taejoon", "Shin Hyuk", "Jung Junyoung", "Lam Wun Yiu", "Kwok Shan Shan", "Chui Cheuk Wai", "Lam Yik Chun", "Luo Yuehan", "Wang Hao", "Mansoor Haris", "Liang Wendi", "Meng Guanlin", "Wang Zhiyu", "Mak Ho Lun", "Liu Zixian", "Geng Qiyang", "Fong Chun Ming", "Cheung Chun Hei", "Lau Tsun Hang Ryan", "Cheung Cheuk Hang", "Liu Chi Hang", "Wong Yiu Nam", "Cheng Kok Yin", "Lam Wai Kit", "Liu Valerie", "Tam Chung Man", "Yan Tin Chun", "Lok Yin Chun", "Ng Ming Hei", "Lo Chun Hin", "Lam Pui Wa", "Lo Cho Hung", "Chu Tsz Fui", "Chow Ho Man", "Gao Ivan", "Ng Man Chau", "Iu Lam Ah", "Hung Wai Hin", "Tong Ka Yan", "Lo Ching Sau", "Lee Lee Ling", "Lam Ho Yin", "Sze Kin Ho", "Ng Siu Chi", "Wong Cheuk Laam", "Chan Yat Chun", "Lee Lap Shun", "Deng Chun Yung", "Fung Ki Ho", "Yeung Ting Kit", "Shiu Chi Yeung", "Kwan Yat Ming", "Chan Kin Kwan", "Leung Man Yi", "Yau Minki", "Hong Yuling", "Yung Wing Kiu", "Yuen Marco Siu Tung", "Lo Yung Sum", "Cheung Tsz Ho", "Chu Ka Hang", "Chan Man Yi", "Ng Yuet Kwan", "Lui Cheuk Lam Lily", "Tai Cheuk Hin", "Ong Chun Wa", "Yiu Pun Cham", "Cheng Ho Wing", "Wong Tsz Wai Desmond", "Lai Ho Sum", "Lee Siu Wai", "Lai Ming Hin", "Leung Hoi Ming", "To Ka Hei", "Tang Tsz Yeung", "Au Yeung Chun Yi", "Lau Ue Tin", "Yau Sin Yan", "Lam Ho Yan", "Tong Mei Chun", "Cheung Tsz Kwan", "Chiang Tin Hang", "Lai Kit Lun", "Cheung Sum Yin", "Wang Matthew Moyin", "Jiang Guanlin", "Edgar Christopher", "Liang Zhihong", "Bai Ruiyuan", "Chen Ru Bing", "Hu Wenqing", "Zhou Siyu", "Wang Yukai", "Lam Hei Yung", "Zhang Wanyu", "Wei Xianger", "Conte Chan Gabriel Alejandro", "Pratento Dylan Jefferson", "Lam Wan Yuet", "Chen Ziyang", "Jiang Zheng", "Xu Le", "He Boyan", "Liu Minghao", "Zhang Zhiyuan", "Chen Yuxuan", "Jin Cheng", "Liu Chenxi", "Qiu Siyi", "Han Wenyu", "Chan Cheuk Hei", "Ho Tsz Kan", "Du Haoxun", "Zheng Shouwen", "Ye Feng", "Yu Kaijing", "Lee Jer Tao", "Shen Ziqi", "Wang Yihe", "Liu Yanqi", "Zhang Wenxuan", "Huang Tianji", "Lu Zhoudao", "Zhang Tianyi", "Yuan Yunchen", "Liu Chengju", "Wei Siqi", "Liu Yuzhou", "Zhao Letao", "Huo Shuaining", "Li Kin Lung Anson", "Qin Qipeng", "Li Jiale", "He Rong", "Hiu Jason Kenneth", "Lam Ka Hang", "Li Tong", "Lau Choi Yu Elise", "Liu Dong", "Li Shuhang", "Zeng Yuejia", "Cai Zhenyu", "Lau Siu Hin", "Szeto Siu Chun", "Leung Cheuk Kit", "Cai Haoyu", "Ye Chenwei", "Huang Yidan", "Lee Kam Hung", "Wang Zhengyang", "Bao Yucheng", "Niyitegeka Berwa Aime Noel", "Lyateva Paulina Veselinova", "Zhang Boyu", "Chen Junru", "Fang Yuji", "Lin Qinfeng", "Tang Haichuan", "Hu Yuhang", "Zhou Taiqi", "Fang Anshu", "Wu Chao", "Zhang Haolin", "Ivanichev Mikhail", "Luo Yi", "Ieong Mei Leng", "Lee Wang Ho", "Jian Junxi", "Tam Tin", "Kjoelbro Niklas August", "Lee Hau Laam", "Pak Yi Ching", "Pang Kin Hei", "Xue Bingxin", "Lau Sin Yee", "Kwok Sze Ming", "Chan Lok Hin", "Chan Ho Yin Francis", "Chung Wai Ching", "Hu Hongjian", "Yiu Chi Chiu", "Tso Yuet Yan", "Chow Chun Wang", "Li Wun Wun", "Chen Junyu", "Kan Wai Yi", "Fong Chun Ho"};
        String[] comp9999 = {"Tang Gloria", "Chan Eason"};
        Student[] ss = tree.searchClass (comp2011);
        System.out.println ((comp2011.length - ss.length) + " Not Found\n" + ((ss != null) ? Arrays.toString (ss) : ""));

        @SuppressWarnings("resource") Scanner input = new Scanner (System.in);
        System.out.println ("Enter a name for search, end in a '*' for surname search." + " q to quit");
        while (input.hasNext ()) {
            String search = input.nextLine ().trim ();
            if (search.equals ("q")) break;
            if (search.indexOf ('*') > 0) {
                // call surname search
                search = search.substring (0, search.length () - 1);
                Student[] list = tree.searchSurname (search);
                if (list == null) System.out.println ("Not Found");
                else
                    System.out.println (list.length + " students with surname \"" + search + "\" found:\n" + Arrays.toString (list));
            } else {
                // call full name search
                Student student = tree.searchFullname (search);
                if (student == null) System.out.println ("Not Found");
                else System.out.println (student);
            }
        }
    }

    /**
     * compareTo student surname
     *
     * @param name1 name1 (in tree)
     * @param name2 name2 (compare name)
     * @param type  surname : true | fullname : false
     */
    private int compareTo(String name1, String name2, boolean type) {
        if (type) {
            return name1.split (" ")[0].compareTo (name2);
        } else {
            return name1.split (" ")[0].compareTo (name2.split (" ")[0]);
        }
    }

    // ***************** for testing **************** //
    static class NodeShow {

        // 用于获得树的层数
        private static int getTreeDepth(Node root) {
            return root == null ? 0 : (1 + Math.max(getTreeDepth(root.lc), getTreeDepth(root.rc)));
        }

        private static void writeArray(Node currNode, int rowIndex, int columnIndex, String[][] res, int treeDepth) {
            // 保证输入的树不为空
            if (currNode == null) return;
            // 先将当前节点保存到二维数组中
            res[rowIndex][columnIndex] = String.valueOf(currNode.e.id);

            // 计算当前位于树的第几层
            int currLevel = ((rowIndex + 1) / 2);
            // 若到了最后一层，则返回
            if (currLevel == treeDepth) return;
            // 计算当前行到下一行，每个元素之间的间隔（下一行的列索引与当前元素的列索引之间的间隔）
            int gap = treeDepth - currLevel - 1;

            // 对左儿子进行判断，若有左儿子，则记录相应的"/"与左儿子的值
            if (currNode.lc != null) {
                res[rowIndex + 1][columnIndex - gap] = "/";
                writeArray(currNode.lc, rowIndex + 2, columnIndex - gap * 2, res, treeDepth);
            }

            // 对右儿子进行判断，若有右儿子，则记录相应的"\"与右儿子的值
            if (currNode.rc != null) {
                res[rowIndex + 1][columnIndex + gap] = "\\";
                writeArray(currNode.rc, rowIndex + 2, columnIndex + gap * 2, res, treeDepth);
            }
        }


        public static void show(Node root) {
            if (root == null){
                System.out.println("EMPTY!");
                return;
            }
            // 得到树的深度
            int treeDepth = getTreeDepth(root);

            // 最后一行的宽度为2的（n - 1）次方乘3，再加1
            // 作为整个二维数组的宽度
            int arrayHeight = treeDepth * 2 - 1;
            int arrayWidth = (2 << (treeDepth - 2)) * 3 + 1;
            // 用一个字符串数组来存储每个位置应显示的元素
            String[][] res = new String[arrayHeight][arrayWidth];
            // 对数组进行初始化，默认为一个空格
            for (int i = 0; i < arrayHeight; i++) {
                for (int j = 0; j < arrayWidth; j++) {
                    res[i][j] = " ";
                }
            }

            // 从根节点开始，递归处理整个树
            // res[0][(arrayWidth + 1)/ 2] = (char)(root.val + '0');
            writeArray(root, 0, arrayWidth / 2, res, treeDepth);

            // 此时，已经将所有需要显示的元素储存到了二维数组中，将其拼接并打印即可
            for (String[] line : res) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < line.length; i++) {
                    sb.append(line[i]);
                    if (line[i].length() > 1 && i <= line.length - 1) {
                        i += line[i].length() > 4 ? 2 : line[i].length() - 1;
                    }
                }
                System.out.println(sb.toString());
            }
        }
    }

}



