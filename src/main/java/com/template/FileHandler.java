package com.template;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Es la clase encargada del manejo de archivos de texto.
 * Se encarga de proporcionar métodos para generar números aleatorios, leer archivos y guardar arreglos.
 */

public class FileHandler {

    /**
     * Genera 3000 números enteros aleatorios y los guarda en numeros.txt.
     * * @param fileName Nombre del archivo de destino.
     * @param amount Cantidad de números a generar.
     */

    public static void generateNumbersToFile(String fileName, int amount) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            Random rand = new Random();

            for (int i = 0; i < amount; i++) {
                writer.write(rand.nextInt(10000) + "\n");
            }

            System.out.println("Archivo generado: " + fileName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lee números enteros desde un archivo de texto y los devuelve en un arreglo de Integer.
     * @param fileName Nombre del archivo a leer.
     * @return Un arreglo de objetos Integer con los datos leídos.
     */

    public static Integer[] readNumbers(String fileName) {
        List<Integer> list = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                list.add(Integer.parseInt(line.trim()));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return list.toArray(new Integer[0]);
    }

    /**
     * Convierte un arreglo de objetos Integer a un arreglo de tipos primitivos int.
     * Es equerido principalmente para el funcionamiento de Radix Sort.
     * * @param arr Arreglo de Integer a convertir.
     * @return Arreglo de int primitivos.
     */

    public static int[] toPrimitive(Integer[] arr) {
        int[] primitive = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            primitive[i] = arr[i];
        }
        return primitive;
    }

    /**
     * Guarda un arreglo de objetos Integer ya ordenado en ordenados.txt.
     * @param fileName Nombre del archivo donde se guardarán los resultados.
     * @param arr El arreglo ordenado a guardar.
     */

    public static void saveSortedArray(String fileName, Integer[] arr) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Integer n : arr) {
                writer.write(n + "\n");
            }
            System.out.println("Guardado en: " + fileName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}