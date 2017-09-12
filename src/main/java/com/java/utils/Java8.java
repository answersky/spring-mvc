package com.java.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liufeng
 * @version [1.0 , 2017/9/9]
 */
public class Java8 {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> list1 = list.stream().filter(num -> num > 3).collect(Collectors.toList());
        System.out.println(list1);
    }
}
