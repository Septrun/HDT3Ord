package com.template;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class BenchmarkMain {

    private static final String INPUT_FILE = "numeros.txt";
    private static final String OUTPUT_CSV = "resultados.csv";

    // intervalos 
    private static final int MIN_N = 10;
    private static final int MAX_N = 3000;
    private static final int STEP = 100;

    public static void main(String[] args) throws IOException {
        // Lee 3000 números 
        Integer[] all = FileHandler.readNumbers(INPUT_FILE);
        if (all.length < MAX_N) {
            throw new IllegalStateException("numeros.txt tiene menos de " + MAX_N + " números.");
        }

        try (BufferedWriter w = new BufferedWriter(new FileWriter(OUTPUT_CSV))) {
            // Encabezado CSV
            w.write("n," +
                    "gnome_rand_ms,gnome_sorted_ms," +
                    "merge_rand_ms,merge_sorted_ms," +
                    "quick_rand_ms,quick_sorted_ms," +
                    "radix_rand_ms,radix_sorted_ms," +
                    "shell_rand_ms,shell_sorted_ms," +
                    "teo_n2,teo_nlogn,teo_n\n");

            for (int n = MIN_N; n <= MAX_N; n += STEP) {
                Integer[] base = Arrays.copyOfRange(all, 0, n);

                // Escenario 1: desordenado 
                double gnomeRand = timeGnome(base);
                double mergeRand = timeMerge(base);
                double quickRand = timeQuick(base);
                double radixRand = timeRadix(base);
                double shellRand = timeShell(base);

                // Escenario 2: ya ordenado 
                Integer[] sortedBase = base.clone();
                Arrays.sort(sortedBase);

                double gnomeSorted = timeGnome(sortedBase);
                double mergeSorted = timeMerge(sortedBase);
                double quickSorted = timeQuick(sortedBase);
                double radixSorted = timeRadix(sortedBase);
                double shellSorted = timeShell(sortedBase);

                // Curvas teóricas 
                double teoN2 = (double) n * (double) n;
                double teoNlogn = n * (Math.log(n) / Math.log(2)); // log2
                double teoN = n;

                w.write(n + "," +
                        gnomeRand + "," + gnomeSorted + "," +
                        mergeRand + "," + mergeSorted + "," +
                        quickRand + "," + quickSorted + "," +
                        radixRand + "," + radixSorted + "," +
                        shellRand + "," + shellSorted + "," +
                        teoN2 + "," + teoNlogn + "," + teoN + "\n");

                System.out.println("n=" + n + " listo");
            }
        }

        System.out.println("CSV generado: " + OUTPUT_CSV);
    }

    //Helpers de timing (miden SOLO el sort)
    private static double timeGnome(Integer[] input) {
        Integer[] a = input.clone();
        long t0 = System.nanoTime();
        SortingAlgorithms.gnomeSort(a);
        long t1 = System.nanoTime();
        return (t1 - t0) / 1_000_000.0; // ms
    }

    private static double timeMerge(Integer[] input) {
        Integer[] a = input.clone();
        long t0 = System.nanoTime();
        SortingAlgorithms.mergeSort(a);
        long t1 = System.nanoTime();
        return (t1 - t0) / 1_000_000.0;
    }

    private static double timeQuick(Integer[] input) {
        Integer[] a = input.clone();
        long t0 = System.nanoTime();
        SortingAlgorithms.quickSort(a, 0, a.length - 1);
        long t1 = System.nanoTime();
        return (t1 - t0) / 1_000_000.0;
    }

    private static double timeRadix(Integer[] input) {
        int[] a = FileHandler.toPrimitive(input.clone());
        long t0 = System.nanoTime();
        SortingAlgorithms.radixSort(a);
        long t1 = System.nanoTime();
        return (t1 - t0) / 1_000_000.0;
    }

    private static double timeShell(Integer[] input) {
        Integer[] a = input.clone();
        long t0 = System.nanoTime();
        SortingAlgorithms.shellSort(a);
        long t1 = System.nanoTime();
        return (t1 - t0) / 1_000_000.0;
    }
}
