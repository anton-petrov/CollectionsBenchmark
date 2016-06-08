package edu.goit.petrov.module01;

public class Main {

    private static double nano2ms(long nano) {
        return nano / (1000.0 * 1000);
    }

    private static void printBenchmarkResult(String benchmarkName, long nanoSeconds) {
        System.out.format("%s = %.4f ms\n", benchmarkName, nano2ms(nanoSeconds));
    }

    public static void main(String[] args) {
        System.out.println("Preparing collections for benchmarking, please, wait...");
        CollectionsBenchmark[] benchmark = new CollectionsBenchmark[] {
                new CollectionsBenchmark(10_000, 100),
                new CollectionsBenchmark(100_000, 100),
                new CollectionsBenchmark(1_000_000, 100)
        };

        for (int b = 0, k = 10; b < benchmark.length; b++, k *= 10) {
            System.out.println("Starting benchmark " + k + "K");
            System.out.println("begin ==========================================================================");

            printBenchmarkResult(k + "K.benchmarkArrayListAdd", benchmark[b].benchmarkArrayListAdd());
            printBenchmarkResult(k + "K.benchmarkArrayListGet", benchmark[b].benchmarkArrayListGet());
            printBenchmarkResult(k + "K.benchmarkArrayListContains", benchmark[b].benchmarkArrayListContains());
            printBenchmarkResult(k + "K.benchmarkArrayListIteratorAdd", benchmark[b].benchmarkArrayListIteratorAdd());
            printBenchmarkResult(k + "K.benchmarkArrayListIteratorRemove", benchmark[b].benchmarkArrayListIteratorRemove());
            printBenchmarkResult(k + "K.benchmarkArrayListPopulate", benchmark[b].benchmarkArrayListPopulate());
            printBenchmarkResult(k + "K.benchmarkArrayListRemove", benchmark[b].benchmarkArrayListRemove());

            printBenchmarkResult(k + "K.benchmarkLinkedListAdd", benchmark[b].benchmarkLinkedListAdd());
            printBenchmarkResult(k + "K.benchmarkLinkedListGet", benchmark[b].benchmarkLinkedListGet());
            printBenchmarkResult(k + "K.benchmarkLinkedListContains", benchmark[b].benchmarkLinkedListContains());
            printBenchmarkResult(k + "K.benchmarkLinkedListIteratorAdd", benchmark[b].benchmarkLinkedListIteratorAdd());
            printBenchmarkResult(k + "K.benchmarkLinkedListIteratorRemove", benchmark[b].benchmarkLinkedListIteratorRemove());
            printBenchmarkResult(k + "K.benchmarkLinkedListPopulate", benchmark[b].benchmarkLinkedListPopulate());
            printBenchmarkResult(k + "K.benchmarkLinkedListRemove", benchmark[b].benchmarkLinkedListRemove());

            printBenchmarkResult(k + "K.benchmarkHashSetAdd", benchmark[b].benchmarkHashSetAdd());
            printBenchmarkResult(k + "K.benchmarkHashSetContains", benchmark[b].benchmarkHashSetContains());
            printBenchmarkResult(k + "K.benchmarkHashSetPopulate", benchmark[b].benchmarkHashSetPopulate());
            printBenchmarkResult(k + "K.benchmarkHashSetRemove", benchmark[b].benchmarkHashSetRemove());

            printBenchmarkResult(k + "K.benchmarkTreeSetAdd", benchmark[b].benchmarkTreeSetAdd());
            printBenchmarkResult(k + "K.benchmarkTreeSetContains", benchmark[b].benchmarkTreeSetContains());
            printBenchmarkResult(k + "K.benchmarkTreeSetPopulate", benchmark[b].benchmarkTreeSetPopulate());
            printBenchmarkResult(k + "K.benchmarkTreeSetRemove", benchmark[b].benchmarkTreeSetRemove());

            System.out.println("end ============================================================================\n");
        }



    }
}
