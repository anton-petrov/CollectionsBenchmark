package edu.petrov.benchmark;

import de.vandermeer.asciitable.v2.RenderedTable;
import de.vandermeer.asciitable.v2.V2_AsciiTable;
import de.vandermeer.asciitable.v2.render.V2_AsciiTableRenderer;
import de.vandermeer.asciitable.v2.render.WidthAbsoluteEven;
import de.vandermeer.asciitable.v2.render.WidthLongestWord;
import de.vandermeer.asciitable.v2.themes.V2_E_TableThemes;

import java.util.LinkedList;
import java.util.List;

public class Main {

    private static double nano2ms(long nano) {
        return nano / (1000.0 * 1000);
    }

    private static void printBenchmarkResult(String benchmarkName, double nanoSeconds) {
        System.out.format("%s = %.4f ms\n", benchmarkName, nanoSeconds);
    }

    private static String renderTable(List<double[]> benchmarkResults, String collectionsSize) {
        V2_AsciiTable at = new V2_AsciiTable();

        at.addStrongRule();
        at.addRow(null, null, null, null, null, null, null, "Java Collections Benchmark - "
                + collectionsSize + ", (results in ms)")
                .setAlignment(new char[]{'l', 'l', 'l', 'l', 'l', 'l', 'l', 'c'});
        at.addStrongRule();
        at.addRow("", "add", "get", "remove", "contains", "populate", "iterator.add", "iterator.remove")
                .setAlignment(new char[]{'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c'});
        at.addStrongRule();
        at.addRow("ArrayList",
                benchmarkResults.get(0)[0],
                benchmarkResults.get(0)[1],
                benchmarkResults.get(0)[2],
                benchmarkResults.get(0)[3],
                benchmarkResults.get(0)[4],
                benchmarkResults.get(0)[5],
                benchmarkResults.get(0)[6])
                .setPadding(new int[]{2, 2, 2, 2, 2, 2, 2, 2});
        at.addRule();
        at.addRow("LinkedList",
                benchmarkResults.get(1)[0],
                benchmarkResults.get(1)[1],
                benchmarkResults.get(1)[2],
                benchmarkResults.get(1)[3],
                benchmarkResults.get(1)[4],
                benchmarkResults.get(1)[5],
                benchmarkResults.get(1)[6])
                .setPadding(new int[]{2, 2, 2, 2, 2, 2, 2, 2});
        at.addRule();
        at.addRow("HashSet",
                benchmarkResults.get(2)[0],
                "x",
                benchmarkResults.get(2)[1],
                benchmarkResults.get(2)[2],
                benchmarkResults.get(2)[3],
                "x",
                "x")
                .setPadding(new int[]{2, 2, 2, 2, 2, 2, 2, 2});
        at.addRule();
        at.addRow("TreeSet",
                benchmarkResults.get(3)[0],
                "x",
                benchmarkResults.get(3)[1],
                benchmarkResults.get(3)[2],
                benchmarkResults.get(3)[3],
                "x",
                "x")
                .setPadding(new int[]{2, 2, 2, 2, 2, 2, 2, 2});
        at.addStrongRule();

        V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
        rend.setTheme(V2_E_TableThemes.UTF_LIGHT.get());
        rend.setWidth(new WidthAbsoluteEven(150));
        rend.setWidth(new WidthLongestWord());
        rend.setTheme(V2_E_TableThemes.ASC7_LATEX_STYLE_STRONG.get());
        RenderedTable rt = rend.render(at);
        System.out.println(rt);

        return rt.toString();
    }

    public static void main(String[] args) {
        System.out.println("Preparing collections for benchmarking, please, wait...");
        CollectionsBenchmark[] benchmark = new CollectionsBenchmark[] {
                new CollectionsBenchmark(10_000, 100),
                new CollectionsBenchmark(100_000, 100),
                new CollectionsBenchmark(1_000_000, 100)
        };

        String benchmarkTablesResult = "";
        for (int i = 0, k = 10; i < benchmark.length; i++, k *= 10) {
            System.out.println("Starting benchmark " + k + "K \n");

            List<double[]> benchmarkResults = new LinkedList<>();

            benchmarkResults.add(new double[8]);
            printBenchmarkResult(k + "K.benchmarkArrayListAdd",
                    benchmarkResults.get(0)[0] = nano2ms(benchmark[i].benchmarkArrayListAdd()));
            printBenchmarkResult(k + "K.benchmarkArrayListGet",
                    benchmarkResults.get(0)[1] = nano2ms(benchmark[i].benchmarkArrayListGet()));
            printBenchmarkResult(k + "K.benchmarkArrayListRemove",
                    benchmarkResults.get(0)[2] = nano2ms(benchmark[i].benchmarkArrayListRemove()));
            printBenchmarkResult(k + "K.benchmarkArrayListContains",
                    benchmarkResults.get(0)[3] = nano2ms(benchmark[i].benchmarkArrayListContains()));
            printBenchmarkResult(k + "K.benchmarkArrayListContains",
                    benchmarkResults.get(0)[4] = nano2ms(benchmark[i].benchmarkArrayListContains()));
            printBenchmarkResult(k + "K.benchmarkArrayListIteratorAdd",
                    benchmarkResults.get(0)[5] = nano2ms(benchmark[i].benchmarkArrayListIteratorAdd()));
            printBenchmarkResult(k + "K.benchmarkArrayListIteratorRemove",
                    benchmarkResults.get(0)[6] = nano2ms(benchmark[i].benchmarkArrayListIteratorRemove()));

            benchmarkResults.add(new double[8]);
            printBenchmarkResult(k + "K.benchmarkLinkedListAdd",
                    benchmarkResults.get(1)[0] = nano2ms(benchmark[i].benchmarkLinkedListAdd()));
            printBenchmarkResult(k + "K.benchmarkLinkedListGet",
                    benchmarkResults.get(1)[1] = nano2ms(benchmark[i].benchmarkLinkedListGet()));
            printBenchmarkResult(k + "K.benchmarkLinkedListRemove",
                    benchmarkResults.get(1)[2] = nano2ms(benchmark[i].benchmarkLinkedListRemove()));
            printBenchmarkResult(k + "K.benchmarkLinkedListContains",
                    benchmarkResults.get(1)[3] = nano2ms(benchmark[i].benchmarkLinkedListContains()));
            printBenchmarkResult(k + "K.benchmarkLinkedListContains",
                    benchmarkResults.get(1)[4] = nano2ms(benchmark[i].benchmarkLinkedListContains()));
            printBenchmarkResult(k + "K.benchmarkLinkedListIteratorAdd",
                    benchmarkResults.get(1)[5] = nano2ms(benchmark[i].benchmarkLinkedListIteratorAdd()));
            printBenchmarkResult(k + "K.benchmarkLinkedListIteratorRemove",
                    benchmarkResults.get(1)[6] = nano2ms(benchmark[i].benchmarkLinkedListIteratorRemove()));

            benchmarkResults.add(new double[8]);
            printBenchmarkResult(k + "K.benchmarkHashSetAdd",
                    benchmarkResults.get(2)[0] = nano2ms(benchmark[i].benchmarkHashSetAdd()));
            printBenchmarkResult(k + "K.benchmarkHashSetRemove",
                    benchmarkResults.get(2)[1] = nano2ms(benchmark[i].benchmarkHashSetRemove()));
            printBenchmarkResult(k + "K.benchmarkHashSetContains",
                    benchmarkResults.get(2)[2] = nano2ms(benchmark[i].benchmarkHashSetContains()));
            printBenchmarkResult(k + "K.benchmarkHashSetPopulate",
                    benchmarkResults.get(2)[3] = nano2ms(benchmark[i].benchmarkHashSetPopulate()));

            benchmarkResults.add(new double[8]);
            printBenchmarkResult(k + "K.benchmarkTreeSetAdd",
                    benchmarkResults.get(3)[0] = nano2ms(benchmark[i].benchmarkTreeSetAdd()));
            printBenchmarkResult(k + "K.benchmarkTreeSetRemove",
                    benchmarkResults.get(3)[1] = nano2ms(benchmark[i].benchmarkTreeSetRemove()));
            printBenchmarkResult(k + "K.benchmarkTreeSetContains",
                    benchmarkResults.get(3)[2] = nano2ms(benchmark[i].benchmarkTreeSetContains()));
            printBenchmarkResult(k + "K.benchmarkTreeSetPopulate",
                    benchmarkResults.get(3)[3] = nano2ms(benchmark[i].benchmarkTreeSetPopulate()));

            System.out.println("\n");

            benchmarkTablesResult += renderTable(benchmarkResults, k + "K") + "\n";

            System.out.println("\n");
        }

    }
}
