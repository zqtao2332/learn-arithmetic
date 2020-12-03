package me.zqt.code.traceback;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 全排列
 * 回溯算法问题基本理解
 * 1、回溯问题，一般问题形式，罗列出决策树的过程
 * 2、回溯问题，核心是循环里面进行递归，递归前做选择，递归后撤销选择
 * 3、回溯问题，思考的问题
 *      a、路径：已经做出的选择
 *      b、选择列表：当前可以做出的选择
 *      c、结束条件：到达决策树的底层，无法做出选择，可以理解选择列表空
 *
 */
public class PermuteAll {

    // 暴力枚举
    public static List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            list.add(nums[i]);
        }
        backtrack(new ArrayList<>(), list, res);
        return res;
    }


    private static void backtrack(List<Integer> choose, List<Integer> list, List<List<Integer>> res) {
        if (choose.size() == list.size()) {
            res.add(new LinkedList<>(choose));
            return;
        }

        for (Integer i : list) {
            // 查看当前值是否已经选择,已经选择跳过
            if (choose.contains(i)) {
                continue;
            }
            // 做选择
            choose.add(i);
            backtrack(choose, list, res);
            // 撤销选择
            choose.remove(i);
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        List<List<Integer>> permute = permute(arr);
        for (List<Integer> list : permute) {
            for (Integer i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }

    }


}
