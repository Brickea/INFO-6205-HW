package edu.neu.coe.info6205.union_find;

import edu.neu.coe.info6205.util.Benchmark;
import edu.neu.coe.info6205.util.Benchmark_Timer;

import java.util.Random;

/**
 * @Author: Zixiao Wang
 * @Version: 1.0.0
 * @Description:
 **/

public class WeigthedUnionFindPCAlterAndNonAlterBenchmarking {

    /**
     * @author: Zixiao Wang
     * @date: 10/13/2020
     * @param: N
     * @return: void
     * @description: Union N with randomly generated pair until there is only component
     * The Union-find store size
     **/
    public static void UF_HWQUPC_RUN(final int N) {
        UF_HWQUPC uf_hwqupc = new UF_HWQUPC(N);
        Random random = new Random();


        // loop until all sites are connected
        while (uf_hwqupc.components() != 1) {
            // generate two points
            int p = random.nextInt(N);
            int q = random.nextInt(N);

            if (!uf_hwqupc.connected(p, q)) {
                // these two points are not connected
                uf_hwqupc.union(p, q);
            }
        }

    }

    /**
     * @author: Zixiao Wang
     * @date: 10/13/2020
     * @param: N
     * @return: void
     * @description: Union N with randomly generated pair until there is only component
     * The Union-find store depth
     **/
    public static void UF_HWQUPC_ALTER_Run(final int N) {
        UF_HWQUPC_ALTER uf_hwqupc_alter = new UF_HWQUPC_ALTER(N);
        Random random = new Random();


        // loop until all sites are connected
        while (uf_hwqupc_alter.components() != 1) {
            // generate two points
            int p = random.nextInt(N);
            int q = random.nextInt(N);

            if (!uf_hwqupc_alter.connected(p, q)) {
                // these two points are not connected
                uf_hwqupc_alter.union(p, q);
            }
        }

    }

    public static void main(String[] args) {
        Benchmark<Integer> benchmark_UF_WQUPC_RUN = new Benchmark_Timer<>("WeightedUnionFindRun 10000", black -> {
            UF_HWQUPC_RUN(10000);
        });

        System.out.printf("Weigthed UnionFind to union 10000 sites takes %.2f milliseconds\n",benchmark_UF_WQUPC_RUN.run(0,1000));

        Benchmark<Integer> benchmark_WQUPC_ALTER_RUN = new Benchmark_Timer<>("WeightedUnionFindAlterRun 10000", black -> {
            UF_HWQUPC_ALTER_Run(10000);
        });

        System.out.printf("Weigthed UnionFind to union 10000 sites takes %.2f milliseconds\n",benchmark_WQUPC_ALTER_RUN.run(0,1000));

    }
}
