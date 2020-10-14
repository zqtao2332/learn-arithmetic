package me.zqt.code.dynamic;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: zqtao
 * @description: 动态规划之斐波那契数列
 * <p>
 * 关于动态规划的基本理解
 * 1、动态规划求解的问题一般形式：求解最值
 * 2、动态规划的核心问题是穷举，穷举带来重复子问题
 * 3、动态规划的穷举问题，存在重叠子问题，所以需要「备忘录」或者「DP table」来优化穷举过程，避免不必要的重复计算
 * 4、动态规划的问题，一定具有最优子结构，可以通过子问题的最值得到原问题的最值
 * 5、动态规划的问题，需要找出base case
 * 6、动态规划的问题，需要找出初始状态条件
 * 7、动态规划的问题，需要找出状态转移方程
 * <p>
 * 动态规划之备忘录，使用备忘录记录已经计算过的状态，避免重复计算
 * 斐波那契数列，基本状态转移方程 f(n) = f(n - 1) + f(n - 2)
 * 通过画出演进树发现，存在大量的重复计算
 */
public class Fib {

    public static void main(String[] args) {
        System.out.println(fibByRecursion(7));
    }

    /**
     * 递归备忘录计算斐波那契数列第 N 个数
     * @param N 第 n 个斐波那契数
     */
    private static int fibByRecursion(int N) {
        if (N < 1) {
            return 0;
        }

        // 初始化备忘录
        Map<Integer, Integer> memory = new HashMap<>();
        // 初始状态
        memory.put(1, 1);
        memory.put(2, 1);

        return function(memory, N);

    }

    /**
     * 递归计算 f(i)
     *
     * @param memory 备忘录：存储已经计算过的 f(i)
     * @param i 第 i 个
     * @return 返回 f(i) 的值
     */
    private static int function(Map<Integer, Integer> memory, int i) {

        if (i == 1 || i == 2) {
            return 1;
        }

        // 计算过的子问题，无需重复计算
        if (memory.containsKey(i)) {
            return memory.get(i);
        }
        // f(n) = f(n - 1) + f(n - 2)
        memory.put(i, function(memory, i - 1) + function(memory, i - 2));
        return memory.get(i);
    }


    /**
     * 动态规划, 使用 dp[] 表进行正向推导
     * @return 返回 f(N) 的值
     */
    private int fibByDynamic(int N){

        // dp
        int[] dp = new int[N + 1];

        // base case
        dp[1] = 1;
        dp[2] = 1;

        for (int i = 3; i <= N; i++) {
            // 动态转移方程
            dp[i] = dp[i - 1] + dp[i -2];
        }

        return dp[N];

    }

    /**
     * 状态压缩，空间压缩，不使用 dp ,根据状态转移方程，发现状态转移只和当前状态 c, 之前的两个状态 a b 有关
     * 这里可以进行空间压缩
     */
    private int fibByDynamic2(int N) {

        // dp , 替换为 cur
        int cur = 0;
        // 上一个状态
        int pre = 1;
        // 上上一个状态
        int prePre = 1;
        for (int i = 0; i <= N; i++) {

            // 状态转移
            cur = pre + prePre;
            // 更新其他两个状态
            prePre = pre;
            pre = cur;
        }
        return cur;
    }
}
