package com.mentpeak.website.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 排列组合工具类
 */
public class PermutationUtil {


    /**
     * 排列选择（从列表中选择n个排列）
     * @param dataList 待选列表
     * @param n 选择个数
     */
    public static void arrangementSelect(String[] dataList, int n, List<String[]> result) {
        arrangementSelect(dataList, new String[n], 0,result);
    }

    /**
     * 排列选择
     * @param dataList 待选列表
     * @param results 前面（resultIndex-1）个的排列结果
     * @param resultIndex 选择索引，从0开始
     */
    private static void arrangementSelect(String[] dataList, String[] results, int resultIndex, List<String[]> result) {
        int resultLen = results.length;
        // 全部选择完时，输出排列结果
        if (resultIndex >= resultLen) {
            String [] temps = new String[resultLen];
            System.arraycopy(results, 0, temps, 0, results.length);
            result.add(temps);
            System.out.println(Arrays.asList(temps));
            return;
        }
        // 递归选择下一个
        for (String aDataList : dataList) {
            // 判断待选项是否存在于排列结果中
            boolean exists = false;
            for (int j = 0; j < resultIndex; j++) {
                if (aDataList.equals(results[j])) {
                    exists = true;
                    break;
                }
            }
            // 排列结果不存在该项，才可选择
            if (!exists) {
                results[resultIndex] = aDataList;
                arrangementSelect(dataList, results, resultIndex + 1, result);
            }
        }
    }


    public static void main(String[] args) {

        String[] arr = {"A","B","C","D","E","F"};

        List<String[]> result =  new ArrayList();

        arrangementSelect(arr,3,result);

        result.forEach(s -> System.out.println(Arrays.asList(s)));

    }

}
