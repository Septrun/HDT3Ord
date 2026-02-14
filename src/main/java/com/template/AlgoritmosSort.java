package com.template;

import java.util.Arrays;

/**
 * Clase que contiene las implementaciones de 5 algoritmos tipo Sort.
 * Se incluyen: Gnome Sort, Merge Sort, Quick Sort, Radix Sort y Shell Sort.
 */
public class AlgoritmosSort {

    /**
     * Ordena un arreglo utilizando el algoritmo Gnome Sort.
     * Complejidad: O(n^2).
     * * @param <T> Tipo de elementos en el arreglo.
     * @param arr El arreglo a ordenar.
     */

    public static <T extends Comparable<T>> void gnomeSort(T[] arr) {
        int index = 0;
        while (index < arr.length) {
            if (index == 0) {
                index++;
            }
            if (arr[index].compareTo(arr[index - 1]) >= 0) {
                index++;
            } else {
                T temp = arr[index];
                arr[index] = arr[index - 1];
                arr[index - 1] = temp;
                index--;
            }
        }
    }

    /**
     * Ordena un arreglo utilizando el algoritmo Merge Sort.
     * Complejidad: O(n log n).
     * * @param <T> Tipo de elementos en el arreglo.
     * @param arr El arreglo a ordenar.
     */

    public static <T extends Comparable<T>> void mergeSort(T[] arr) {
        if (arr.length < 2) return;
        
        int mid = arr.length / 2;
        T[] left = Arrays.copyOfRange(arr, 0, mid);
        T[] right = Arrays.copyOfRange(arr, mid, arr.length);

        mergeSort(left);
        mergeSort(right);

        merge(arr, left, right);
    }

    /**
     * Se incluye un método auxiliar para combinar dos sub-arreglos en orden.
     */

    private static <T extends Comparable<T>> void merge(T[] arr, T[] left, T[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i].compareTo(right[j]) <= 0) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }
        while (i < left.length) arr[k++] = left[i++];
        while (j < right.length) arr[k++] = right[j++];
    }

    /**
     * Ordena un arreglo utilizando el algoritmo Quick Sort.
     * Complejidad promedio: O(n log n).
     * @param <T> Tipo de elementos en el arreglo.
     * @param arr El arreglo a ordenar.
     * @param low Índice inicial.
     * @param high Índice final.
     */

    public static <T extends Comparable<T>> void quickSort(T[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    /**
     * Se incluye un método auxiliar para particionar el arreglo en Quick Sort utilizando un pivote.
     */
    
    private static <T extends Comparable<T>> int partition(T[] arr, int low, int high) {
        T pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j].compareTo(pivot) <= 0) {
                i++;
                T temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        T temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    /**
     * Ordena un arreglo de enteros utilizando el algoritmo Radix Sort.
     * Complejidad: O(nk).
     * * @param arr El arreglo de enteros primitivos que se va a ordenar.
     */
    public static void radixSort(int[] arr) {
        if (arr.length == 0) return;
        int max = Arrays.stream(arr).max().getAsInt();
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countSort(arr, exp);
        }
    }

    /**
     * Se incluye un método auxiliar para Radix Sort que ordena por dígitos específicos.
     */
    private static void countSort(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];
        for (int i = 0; i < n; i++) count[(arr[i] / exp) % 10]++;
        for (int i = 1; i < 10; i++) count[i] += count[i - 1];
        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }
        System.arraycopy(output, 0, arr, 0, n);
    }

    /**
     * Ordena un arreglo utilizando el algoritmo Shell Sort.
     * Complejidad: Depende de la secuencia de brechas.
     * @param <T> Tipo de elementos en el arreglo.
     * @param arr El arreglo a ordenar.
     */

    public static <T extends Comparable<T>> void shellSort(T[] arr) {
        int n = arr.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                T key = arr[i];
                int j = i;
                while (j >= gap && arr[j - gap].compareTo(key) > 0) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = key;
            }
        }
    }
}