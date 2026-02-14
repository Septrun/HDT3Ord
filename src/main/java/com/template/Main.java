package com.template;

import java.io.File;
import java.util.Scanner;

/**
 * El Main es la clase principal que gestiona la interfaz del usuario y la ejecución de los algoritmos tipo sort.
 * Esto permite al usuario seleccionar entre cinco algoritmos diferentes para ordenar datos leídos desde un archivo.
 * @author Diego Ayala, David Berganza
 * @version 1.0
 */

public class Main {

    /** 
    * El archivo numeros.txt contiene los números que se generarán al azar.
    */
    private static final String FILENAME = "numeros.txt";
    
    /**
    * Se van a generar y a ordenar 3000 números.
    */
    private static final int DATA_SIZE = 3000;

    /**
     * Se genera el archivo de datos si el usuario no elegió uno previamente, solicita la elección del usuario y ejecuta el algoritmo elegido.
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {

        System.out.println("Iniciando programa...");

        File file = new File(FILENAME);
        if (!file.exists()) {
            FileHandler.generateNumbersToFile(FILENAME, DATA_SIZE);
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("\nBienvenido al programa Sort! Por favor, seleccione algoritmo:");
        System.out.println("1. Gnome Sort");
        System.out.println("2. Merge Sort");
        System.out.println("3. Quick Sort");
        System.out.println("4. Radix Sort");
        System.out.println("5. Shell Sort");
        System.out.print("Opción: ");

        int choice = scanner.nextInt();

        Integer[] data = FileHandler.readNumbers(FILENAME);

        System.out.println("Ordenando " + data.length + " elementos...");

        switch (choice) {
            case 1:
                SortingAlgorithms.gnomeSort(data);
                break;

            case 2:
                SortingAlgorithms.mergeSort(data);
                break;

            case 3:
                SortingAlgorithms.quickSort(data, 0, data.length - 1);
                break;

            case 4:
                int[] primitive = FileHandler.toPrimitive(data);
                SortingAlgorithms.radixSort(primitive);
                for (int i = 0; i < primitive.length; i++) {
                    data[i] = primitive[i];
                }
                break;

            case 5:
                SortingAlgorithms.shellSort(data);
                break;

            default:
                System.out.println("Hubo un error. La opción es inválida");
                scanner.close();
                return;
        }

        FileHandler.saveSortedArray("ordenados.txt", data);

        System.out.println("El programa Sort ha finalizado. Muchas gracias por usarlo!");
        scanner.close();
    }
}