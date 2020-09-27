package edu.neu.coe.info6205.sort.simple;

import edu.neu.coe.info6205.sort.GenericSort;
import edu.neu.coe.info6205.util.Benchmark;
import edu.neu.coe.info6205.util.Benchmark_Timer;
import edu.neu.coe.info6205.util.CSVExport;
import io.cucumber.java.en_pirate.Aye;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * @Author: Zixiao Wang
 * @Version: 1.0.0
 * @Description:
 **/

public class hw2Part3 {

    @Test
    public void part3() {

        final int N = 100;

        final int T = 5;
        List<Double> randomSortedCost = new ArrayList<>();
        List<Double> orderedSortedCost = new ArrayList<>();
        List<Double> partiallyOrderedSortedCost = new ArrayList<>();
        List<Double> reverseOrderedSortedCost = new ArrayList<>();
        for (int n = 100; n <= N * Math.pow(2, T); n = n * 2) {

            // Four array's situations: random, ordered, partially-ordered and reverse-ordered.
            // random
            List<Integer> randomList = new ArrayList<>();
            Random random = new Random();
            for (int i = 0; i < n; i++) {
                randomList.add(random.nextInt(n));
            }
//            System.out.println("Random list");
//            System.out.println(randomList.toString());

            // ordered
            List<Integer> orderedList = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                orderedList.add(i);
            }
//            System.out.println("Ordered list");
//            System.out.println(orderedList.toString());

            // partially-ordered
            List<Integer> partiallyOrderedList = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (random.nextInt(100) < 70) {
                    partiallyOrderedList.add(i);
                } else {
                    partiallyOrderedList.add((random.nextInt(n)));
                }
            }
//            System.out.println("Partially-ordered list");
//            System.out.println(partiallyOrderedList.toString());

            // reverse-ordered
            List<Integer> reverseOrderedList = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                reverseOrderedList.add(n - i);
            }
//            System.out.println("Reverse ordered list");
//            System.out.println(reverseOrderedList);


            // ************************************************************************
            // sort random list
            GenericSort insertionSort = new InsertionSort();

            Benchmark<Integer> benchmark = new Benchmark_Timer<>("hw2 part3 test start...",
                    blank -> {
                        insertionSort.sort(randomList);
                    });
            // warm up
            benchmark.run(0, 10);
            // get average run time
            double res = 0;
            for (int i = 0; i < 10; i++) {
                res += benchmark.run(0, 10);
            }
            randomSortedCost.add(res/10);

            // ************************************************************************
            // sort ordered list
            benchmark = new Benchmark_Timer<>("hw2 part3 test start...",
                    blank -> {
                        insertionSort.sort(orderedList);
                    });
            // warm up
            benchmark.run(0, 10);
            // get average run time
            res = 0;
            for (int i = 0; i < 10; i++) {
                res += benchmark.run(0, 10);
            }
            orderedSortedCost.add(res/10);

            // ************************************************************************
            // sort partically-ordered list
            benchmark = new Benchmark_Timer<>("hw2 part3 test start...",
                    blank -> {
                        insertionSort.sort(partiallyOrderedList);
                    });
            // warm up
            benchmark.run(0, 10);
            // get average run time
            res = 0;
            for (int i = 0; i < 10; i++) {
                res += benchmark.run(0, 10);
            }
            partiallyOrderedSortedCost.add(res/10);

            // ************************************************************************
            // sort reverse order list
            benchmark = new Benchmark_Timer<>("hw2 part3 test start...",
                    blank -> {
                        insertionSort.sort(reverseOrderedList);
                    });
            // warm up
            benchmark.run(0, 10);
            // get average run time
            res = 0;
            for (int i = 0; i < 10; i++) {
                res += benchmark.run(0, 10);
            }
            reverseOrderedSortedCost.add(res/10);
        }

        List<Object> head = new ArrayList<>();
        head.add("Random List");
        head.add("Ordered List");
        head.add("Partially Ordered List");
        head.add("Reversed List");

        List<List<Object>> data =new ArrayList<>();
        for(int i=0;i<T;i++){
            List<Object> temp = new ArrayList<>();
            temp.add(randomSortedCost.get(i));
            temp.add(orderedSortedCost.get(i));
            temp.add(partiallyOrderedSortedCost.get(i));
            temp.add(reverseOrderedSortedCost.get(i));

            data.add(temp);
        }

        CSVExport.createCSVFile(head,data,"G:\\2020 Fall\\INFO 6205 Program Structure & Algorithms\\Assignments\\Assignment 2","hw2");

        System.out.println(randomSortedCost.toString());
        System.out.println(orderedSortedCost.toString());
        System.out.println(partiallyOrderedSortedCost.toString());
        System.out.println(reverseOrderedSortedCost.toString());
    }
}
