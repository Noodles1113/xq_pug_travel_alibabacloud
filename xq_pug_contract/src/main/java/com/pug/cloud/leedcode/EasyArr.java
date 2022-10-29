package com.pug.cloud.leedcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 */
public class EasyArr {

    // 暴力破解法
    // 双重for循环,时间复杂度：n*(n-1)/2
    public static int[] twoSum1(int[] nums, int target) {
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    res[0] = i;
                    res[1] = j;
                    break;
                }
            }
        }
        return res;
    }

    // 哈希
    public static int[] twoSum2(int[] nums, int target) {
        int[] res = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int temp = target - nums[i];
            if (map.containsKey(temp)) {
                res[1] = i;
                res[0] = map.get(temp);
            }
            map.put(nums[i], i);
        }
        return res;
    }

    // 哈希优化
    public static int[] twoSum3(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            }
            map.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }

    // 冒泡排序
    public static int[] sort(int[] nums) {
        int min = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    min = nums[j];
                    nums[j] = nums[i];
                    nums[i] = min;
                }
            }
        }
        return nums;
    }

    // 最大和（连续的子数组）
    public int maxSubArray(int[] nums) {
        int max = 0, temp = nums[0];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                temp += nums[j];
                if (temp > max) max = temp;
            }

        }
        return max;
    }


    public static void main(String[] args) {
//        int[] arr = {2, 3, 7, 3, 15};
//        int[] result = twoSum2(arr, 10);
//        System.out.println("[" + result[0] + "," + result[1] + "]");
        int[] arr = {1, 2, 0, 4, 9, 0, -1, 3, 7, 3, 15};
        for (int i : sort(arr)) {
            System.out.println(i);
        }
    }


}
