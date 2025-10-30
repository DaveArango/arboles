package co.edu.uniquindio;

import co.edu.uniquindio.model.Arbol;

import java.util.*;

public class Main2 {
    public static void main(String[] args) {

        // 🔹 Crear un árbol de enteros
        Arbol<Integer> arbol = new Arbol<>();

        // 🔹 Agregar elementos
        System.out.println("📥 Agregando elementos...");
        int[] valores = {50, 30, 70, 20, 40, 60, 80};
        for (int v : valores) {
            arbol.agregar(v);
        }

        // 🔹 Recorridos
        System.out.println("\n📋 Recorrido en INORDEN (de menor a mayor):");
        arbol.inOrden();

        System.out.println("\n📋 Recorrido en PREORDEN:");
        arbol.recorrerPreorden();

        System.out.println("\n📋 Recorrido en POSTORDEN:");
        arbol.recorrerPostorden();

        System.out.println("\n📋 Recorrido por AMPLITUD:");
        arbol.imprimirAmplitud();

        // 🔹 Buscar nodo
        System.out.println("\n🔎 Buscando el valor 40...");
        boolean encontrado = arbol.existeDato(40);
        System.out.println("¿Existe el valor 40?: " + (encontrado ? "Sí" : "No"));

        // 🔹 Obtener menor y mayor
        System.out.println("\n📊 Valor mínimo: " + arbol.obtenerNodoMenor());
        System.out.println("📊 Valor máximo: " + arbol.obtenerNodoMayor());

        // 🔹 Altura del árbol
        System.out.println("\n🌳 Altura del árbol: " + arbol.obtenerAltura());

        // 🔹 Nivel de un nodo
        System.out.println("📏 Nivel del nodo 60: " + arbol.obtenerNivel(60));

        // 🔹 Contar hojas
        System.out.println("\n🍃 Cantidad de hojas: " + arbol.contarHojas());

        // 🔹 Eliminar un nodo
        System.out.println("\n🗑️ Eliminando el nodo 70...");
        arbol.eliminarNodo(70);
        System.out.println("📋 Inorden después de eliminar 70:");
        arbol.inOrden();

        // 🔹 Comprobar si sigue existiendo el 70
        System.out.println("\n🔎 Buscando el valor 70...");
        System.out.println("¿Existe el valor 70?: " + (arbol.existeDato(70) ? "Sí" : "No"));

        // 🔹 Obtener el valor de la raíz
        System.out.println("\n🌰 Valor de la raíz actual: " + arbol.obtenerPeso());

        // 🔹 Borrar todo el árbol
        System.out.println("\n🚫 Borrando el árbol...");
        arbol.borrarArbol();
        System.out.println("¿El árbol quedó vacío? " + (arbol.obtenerPeso() == null ? "Sí" : "No"));
    }
}

