package com.company.model.functions;

import com.company.model.Function;

import java.util.Arrays;
import java.util.Random;

public class HardFunction extends Function {

    public HardFunction(int topValue, int bottomValue, int aParam)
    {
        super(topValue, 2, aParam);
    }

    public int[] merge_sort(int[] a)
    {
        int size = a.length;
        int[] result = new int[size];
        int middle = size/2;
        int[] left = new int[middle];
        int[] right = new int[size - middle];
        if (result.length <= 1){
            return a;
        } else {

            for (int i = 0; i < middle; i++) {
                left[i] = a[i];
            }
            for (int i = middle; i < size; i++) {
                right[i-middle] = a[i];
            }
            left = merge_sort(left);
            right = merge_sort(right);
            result = merge(left, right);

            return result;
        }
    }


    public int[] merge(int[] left, int[] right)
    {
        int[] result = new int[left.length + right.length];
        int count = 0;
        while (left.length > 0 && right.length > 0){
            if (left[0] <= right[0]) {
                result[count] = left[0];
                count++;
                left = Arrays.copyOfRange(left, 1, left.length);
            } else {
                result[count] = right[0];
                count++;
                right = Arrays.copyOfRange(right, 1, right.length);
            }
        }
        while (left.length > 0){
            result[count] = left[0];
            count++;
            left = Arrays.copyOfRange(left, 1, left.length);
        }
        while (right.length > 0){
            result[count] = right[0];
            count++;
            right = Arrays.copyOfRange(right, 1, right.length);
        }
        return result;
    }


    @Override
    public double calculateY(double x) {
        Random rd = new Random();
        int[] arr = new int[(int)x];
        for (int i = 0; i < (int)x; i++){
            arr[i] = (int)(Math.random()*10+1);
        }

        if (x == 6){
            int b = 5;
        }

        long start = System.nanoTime();
        arr = merge_sort(arr);
        long finish = System.nanoTime();

        return (double)(finish-start)/1000;
    }
}
