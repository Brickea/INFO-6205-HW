package edu.neu.coe.info6205.union_find;

import edu.neu.coe.info6205.util.CSVExport;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author: Zixiao Wang
 * @Version: 1.0.0
 * @Description:
 **/

public class UF_HWQUPC_Client {

    /**
    * @author: Zixiao Wang
    * @date: 10/5/2020
     * @param: N
    * @return: int[]
    * @description:
     * takes n as the argument and returns the (int[0]) number of connections after judging if "connected"
     * takes n as the argument and returns the (int[1]) number of connections of the randomly generated pairs.
    **/
    public static int[] count(final int N){
        UF_HWQUPC uf_hwqupc = new UF_HWQUPC(N,false);
        Random random = new Random();

        int[] res = new int[2];
        int m = 0; // record the number of random connections
        int randomPair = 0; // record the number of randomly generated pairs.

        // loop until all sites are connected
        while(uf_hwqupc.components()!=1){
            // generate two points
            int p = random.nextInt(N);
            int q = random.nextInt(N);
            randomPair++;

            if(!uf_hwqupc.connected(p,q)){
                // these two points are not connected
                uf_hwqupc.connect(p,q);
                m++;
            }
        }

        // return the number of connections
        res[0] = m;
        res[1] = randomPair;

        return res;
    }

    /**
    * @author: Zixiao Wang
    * @date: 10/5/2020
     * @param: args
     * The first arg should be integer, which indicates the number of "site"
    * @return: void
    * @description:
     * The main method will call static method "count()", which takes n as the argument and returns the number of connections
    **/
    public static void main(String[] args) {
        final int N = Integer.valueOf(args[0]); // Take N from commend line
        int[] res = UF_HWQUPC_Client.count(N);
        System.out.printf("In order to connect %d (n) sites, the number of randomly generated connections is %d (m)\n " +
                "The number of randomly generated pairs %d (randomly pair)\n",N,res[0],res[1]);

        // Export into csc
        List<Object> head = new ArrayList<>();
        List<List<Object>> dataList = new ArrayList<>();
        head.add("n (number of sites)");
        head.add(("m (number of connections)"));
        head.add("rp (number of randomly generated pairs)");
        for(int i=N;i>0;i--){
            res = UF_HWQUPC_Client.count(i);
            List<Object> row = new ArrayList<>();
            row.add(i);
            row.add(res[0]);
            row.add(res[1]);
            dataList.add(row);
        }

        CSVExport.createCSVFile(head,dataList,"G:\\2020 Fall\\INFO 6205 Program Structure & Algorithms\\Assignments\\Assignment 3","data");
    }
}
