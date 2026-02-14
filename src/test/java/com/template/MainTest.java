package com.template;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Random;

/**
 * Funciona como la clase de pruebas unitarias para validar la exactitud de los algoritmos de ordenamiento.
 * Utiliza JUnit 5 para comparar los resultados de los algoritmos implementados contra el método de ordenamiento estándar de Java.
 */

public class MainTest {

    /**
     * Genera un arreglo de objetos Integer con valores aleatorios.
     * * @param size El tamaño del arreglo a generar.
     * @return Un arreglo de Integers con valores entre 0 y 999.
     */

    private Integer[] getRandomArray(int size) {
        Random rand = new Random();
        Integer[] arr = new Integer[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(1000);
        }
        return arr;
    }

    /**
     * Genera un arreglo de tipos primitivos int con valores aleatorios.
     * @param size El tamaño del arreglo a generar.
     * @return Un arreglo de ints con valores entre 0 y 999.
     */

    private int[] getRandomPrimitiveArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(1000);
        }
        return arr;
    }

    /**
     * Prueba la implementación de Gnome Sort.
     * Verifica que un arreglo de 100 elementos sea ordenado correctamente.
     */

    @Test
    @DisplayName("Test Gnome Sort")
    public void testGnomeSort() {
        Integer[] arr = getRandomArray(100);
        Integer[] sorted = arr.clone();
        Arrays.sort(sorted);
        
        AlgoritmosSort.gnomeSort(arr);
        assertArrayEquals(sorted, arr, "Gnome Sort debería ordenar el arreglo");
    }

    /**
     * Prueba la implementación de Merge Sort.
     * Verifica que un arreglo de 1000 elementos sea ordenado correctamente.
     */

    @Test
    @DisplayName("Test Merge Sort")
    public void testMergeSort() {
        Integer[] arr = getRandomArray(1000);
        Integer[] sorted = arr.clone();
        Arrays.sort(sorted);
        
        AlgoritmosSort.mergeSort(arr);
        assertArrayEquals(sorted, arr, "Merge Sort debería ordenar el arreglo");
    }

    /**
     * Prueba la implementación de Quick Sort.
     * Verifica que un arreglo de 1000 elementos sea ordenado correctamente.
     */

    @Test
    @DisplayName("Test Quick Sort")
    public void testQuickSort() {
        Integer[] arr = getRandomArray(1000);
        Integer[] sorted = arr.clone();
        Arrays.sort(sorted);
        
        AlgoritmosSort.quickSort(arr, 0, arr.length - 1);
        assertArrayEquals(sorted, arr, "Quick Sort debería ordenar el arreglo");
    }

    /**
     * Prueba la implementación de Radix Sort.
     * Utiliza un arreglo primitivo de 1000 elementos.
     */

    @Test
    @DisplayName("Test Radix Sort")
    public void testRadixSort() {
        int[] arr = getRandomPrimitiveArray(1000);
        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        
        AlgoritmosSort.radixSort(arr);
        assertArrayEquals(sorted, arr, "Radix Sort debería ordenar el arreglo");
    }

    /**
     * Prueba la implementación de Shell Sort.
     * Verifica que un arreglo de 500 elementos sea ordenado correctamente.
     */

    @Test
    @DisplayName("Test Shell Sort")
    public void testShellSort() {
        Integer[] arr = getRandomArray(500);
        Integer[] sorted = arr.clone();
        Arrays.sort(sorted);

        AlgoritmosSort.shellSort(arr);
        assertArrayEquals(sorted, arr, "Shell Sort debería ordenar el arreglo");
    }

    /**
     * Prueba el comportamiento de los algoritmos ante un arreglo vacío.
     * Asegura que el programa no lance excepciones y mantenga la integridad del arreglo.
     */
    
    @Test
    @DisplayName("Test con Arreglo Vacío")
    public void testEmptyArray() {
        Integer[] empty = new Integer[0];
        AlgoritmosSort.mergeSort(empty);
        assertEquals(0, empty.length, "Un arreglo vacío debería seguir vacío tras el ordenamiento");
    }
}