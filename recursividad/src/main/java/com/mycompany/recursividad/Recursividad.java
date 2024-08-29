package com.mycompany.recursividad;

import java.util.ArrayList;
import java.util.List;

public class Recursividad {

    public static int sumarDigitos(int numero) {
        if (numero == 0) {
            return 0;
        }
        return numero % 10 + sumarDigitos(numero / 10);
    }

    public static String invertirCadena(String cadena) {
        if (cadena.isEmpty()) {
            return cadena;
        }
        return invertirCadena(cadena.substring(1)) + cadena.charAt(0);
    }

    public static int encontrarMaximo(int[] arreglo, int indice) {
        if (indice == arreglo.length - 1) {
            return arreglo[indice];
        }
        int maxEnResto = encontrarMaximo(arreglo, indice + 1);
        return Math.max(arreglo[indice], maxEnResto);
    }

    public static int contarOcurrencias(String cadena, char caracter) {
        if (cadena.isEmpty()) {
            return 0;
        }
        int conteoEnResto = contarOcurrencias(cadena.substring(1), caracter);
        return (cadena.charAt(0) == caracter ? 1 : 0) + conteoEnResto;
    }

    public static List<List<Integer>> generarSubconjuntos(List<Integer> conjunto, int indice) {
        List<List<Integer>> subconjuntos;
        if (indice == conjunto.size()) {
            subconjuntos = new ArrayList<>();
            subconjuntos.add(new ArrayList<>());
            return subconjuntos;
        }
        subconjuntos = generarSubconjuntos(conjunto, indice + 1);
        int elemento = conjunto.get(indice);
        List<List<Integer>> masSubconjuntos = new ArrayList<>();
        for (List<Integer> subconjunto : subconjuntos) {
            List<Integer> nuevoSubconjunto = new ArrayList<>(subconjunto);
            nuevoSubconjunto.add(elemento);
            masSubconjuntos.add(nuevoSubconjunto);
        }
        subconjuntos.addAll(masSubconjuntos);
        return subconjuntos;
    }

    public static void main(String[] args) {
        int numero = 12345;
        System.out.println("Suma de los dígitos: " + sumarDigitos(numero));

        String cadena = "hello";
        System.out.println("Cadena invertida: " + invertirCadena(cadena));

        int[] arreglo = {3, 5, 7, 2, 8};
        System.out.println("Máximo en el arreglo: " + encontrarMaximo(arreglo, 0));

        String cadenaPrueba = "banana";
        char caracter = 'a';
        System.out.println("Número de ocurrencias de '" + caracter + "': " + contarOcurrencias(cadenaPrueba, caracter));

        List<Integer> conjunto = List.of(1, 2, 3);
        List<List<Integer>> subconjuntos = generarSubconjuntos(conjunto, 0);
        System.out.println("Subconjuntos generados: " + subconjuntos);
    }
}
