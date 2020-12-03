package me.zqt.code.dynamic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: zqtao
 * @description: 动态规划之找零钱
 * <p>
 * 动态规划的基本理解
 * 1、动态规划问题，一般问题形式，求解最值
 * 2、动态规划问题，核心是穷举
 * 3、动态规划问题，解决穷举带来的重叠子问题,子问题相互独立
 * 4、动态规划问题，一定具有最优子结构
 */
public class CoinChange {


    public static void main(String[] args) {
        List<Integer> coins = new ArrayList<>();
        coins.add(1);
        coins.add(2);
        coins.add(5);

        int amount = 11;

//        System.out.println(process2(coins, amount, new HashMap<>()));
        System.out.println(process3(coins, amount));

    }

    private static int process3(List<Integer> coins, int amount) {

        // dp[] dp数组的含义：当目标金额为i时，至少需要dp[i]枚金币
        int[] dp = new int[amount + 1];

        // 初始化数组
        for (int i = 0; i < amount + 1; i++) {
            dp[i] = i;
        }

        // base case
        dp[0] = 0;

        // 外层 for 循环在遍历所有状态的所有取值
        for (int i = 1; i <= amount; i++) {
            // 内层 for 循环在求所有选择的最小值
            for (Integer coin : coins) {
                // 子问题无解，跳过
                if (i - coin < 0) {
                    continue;
                }
                // 取 10 枚 1元大，还是取一枚 价值为 coin 的数量大
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }

        return dp[amount];
    }

    /**
     * 具有重复子问题，解决重复子问题计算，使用备忘录
     *
     * @param coins  可提供的零钱值
     * @param amount 目标找零
     * @param memory 备忘录
     * @return 需要找零的最小张数
     */
    private static int process2(List<Integer> coins, int amount, Map<Integer, Integer> memory) {
        // 查询备忘录，避免重复计算
        if (memory.containsKey(amount)) {
            return memory.get(amount);
        }
        // base case
        if (amount == 0) return 0;
        if (amount < 0) return -1;

        int res = Integer.MAX_VALUE;
        for (Integer coin : coins) {
            // 子问题
            int subProblems = process1(coins, amount - coin);
            if (subProblems == -1) {
                continue;
            }
            res = Math.min(1 + subProblems, res);
        }

        res = res == Integer.MAX_VALUE ? -1 : res;
        memory.put(amount, res);

        return res;
    }


    /**
     * 暴力枚举
     *
     * @param coins  可提供的零钱值
     * @param amount 目标找零
     * @return 需要找零的最小张数
     */
    private static int process1(List<Integer> coins, int amount) {

        // base case
        if (amount == 0) return 0;
        if (amount < 0) return -1;

        int res = Integer.MAX_VALUE;
        for (Integer coin : coins) {
            // 子问题
            int subProblems = process1(coins, amount - coin);
            if (subProblems == -1) {
                continue;
            }
            res = Math.min(1 + subProblems, res);
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }

}
