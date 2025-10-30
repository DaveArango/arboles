package co.edu.uniquindio.model;

import co.edu.uniquindio.view.PanelArbol;

import java.util.LinkedList;
import java.util.Queue;

public class Arbol<T extends Comparable<T>> {
    private Nodo<T> raiz;
    private int tamanio;

    public Arbol() {
        this.raiz = null;
        this.tamanio = 0;
    }

    public void agregar(T valor){
        raiz = agregarRecursivamente(raiz, valor);
    }


    public void eliminarNodo(T valor) {
        raiz = eliminarRecursivamente(raiz, valor);
    }

    private Nodo<T> eliminarRecursivamente(Nodo<T> nodo, T valor) {
        if (nodo == null) return null;

        int cmp = valor.compareTo(nodo.getValor());

        // 🔹 Buscar el nodo a eliminar
        if (cmp < 0) {
            nodo.setIzquierda(eliminarRecursivamente(nodo.getIzquierda(), valor));
            if (nodo.getIzquierda() != null)
                nodo.getIzquierda().setPadre(nodo);
        } else if (cmp > 0) {
            nodo.setDerecha(eliminarRecursivamente(nodo.getDerecha(), valor));
            if (nodo.getDerecha() != null)
                nodo.getDerecha().setPadre(nodo);
        } else {
            // 🔹 Caso 1: hoja
            if (nodo.getIzquierda() == null && nodo.getDerecha() == null) {
                return null;
            }

            // 🔹 Caso 2: un solo hijo
            if (nodo.getIzquierda() == null) {
                Nodo<T> hijo = nodo.getDerecha();
                hijo.setPadre(nodo.getPadre());
                return hijo;
            } else if (nodo.getDerecha() == null) {
                Nodo<T> hijo = nodo.getIzquierda();
                hijo.setPadre(nodo.getPadre());
                return hijo;
            }

            // 🔹 Caso 3: dos hijos
            Nodo<T> sucesor = obtenerMinimo(nodo.getDerecha());
            nodo.setValor(sucesor.getValor());
            nodo.setDerecha(eliminarRecursivamente(nodo.getDerecha(), sucesor.getValor()));
            if (nodo.getDerecha() != null)
                nodo.getDerecha().setPadre(nodo);
        }

        return nodo;
    }




    // 🔹 Nueva versión animada
    public void agregarAnimado(T valor, PanelArbol<T> panel) {
        raiz = agregarRecursivamenteAnimado(raiz, valor, panel);
    }

    private Nodo<T> agregarRecursivamenteAnimado(Nodo<T> raiz, T valor, PanelArbol<T> panel) {
        panel.resaltar(raiz);
        panel.repaint();
        esperar(700); // Pausa para ver la animación

        if (raiz == null) {
            Nodo<T> nuevo = new Nodo<>(valor);
            panel.actualizarArbol();
            return nuevo;
        }

        if (raiz.getValor().compareTo(valor) > 0) {
            raiz.setIzquierda(agregarRecursivamenteAnimado(raiz.getIzquierda(), valor, panel));
        } else if (raiz.getValor().compareTo(valor) < 0) {
            raiz.setDerecha(agregarRecursivamenteAnimado(raiz.getDerecha(), valor, panel));
        }

        panel.actualizarArbol();
        return raiz;
    }

    private void esperar(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


    // 🔹 Eliminación animada
    public void eliminarAnimado(T valor, PanelArbol<T> panel) {
        raiz = eliminarRecursivamenteAnimado(raiz, valor, panel);
        panel.actualizarArbol();
    }

    private Nodo<T> eliminarRecursivamenteAnimado(Nodo<T> nodo, T valor, PanelArbol<T> panel) {
        if (nodo == null) return null;

        // Resaltar nodo actual
        panel.resaltar(nodo);
        panel.repaint();
        esperar(800);

        int cmp = valor.compareTo(nodo.getValor());

        // 🔸 Buscar nodo a eliminar
        if (cmp < 0) {
            nodo.setIzquierda(eliminarRecursivamenteAnimado(nodo.getIzquierda(), valor, panel));
            if (nodo.getIzquierda() != null)
                nodo.getIzquierda().setPadre(nodo);
        } else if (cmp > 0) {
            nodo.setDerecha(eliminarRecursivamenteAnimado(nodo.getDerecha(), valor, panel));
            if (nodo.getDerecha() != null)
                nodo.getDerecha().setPadre(nodo);
        } else {
            // 🔹 Nodo encontrado → mostrar visualmente que será eliminado
            panel.resaltar(nodo);
            panel.repaint();
            esperar(1000);

            // Caso 1: hoja
            if (nodo.getIzquierda() == null && nodo.getDerecha() == null) {
                panel.removerResaltado(nodo);
                panel.repaint();
                esperar(300);
                return null;
            }

            // Caso 2: un solo hijo
            if (nodo.getIzquierda() == null) {
                Nodo<T> hijo = nodo.getDerecha();
                hijo.setPadre(nodo.getPadre());
                panel.removerResaltado(nodo);
                panel.repaint();
                esperar(300);
                return hijo;
            } else if (nodo.getDerecha() == null) {
                Nodo<T> hijo = nodo.getIzquierda();
                hijo.setPadre(nodo.getPadre());
                panel.removerResaltado(nodo);
                panel.repaint();
                esperar(300);
                return hijo;
            }

            // Caso 3: dos hijos
            Nodo<T> sucesor = obtenerMinimo(nodo.getDerecha());
            panel.resaltar(sucesor);
            panel.repaint();
            esperar(1000);

            nodo.setValor(sucesor.getValor());
            nodo.setDerecha(eliminarRecursivamenteAnimado(nodo.getDerecha(), sucesor.getValor(), panel));
            if (nodo.getDerecha() != null)
                nodo.getDerecha().setPadre(nodo);
        }

        panel.actualizarArbol();
        return nodo;
    }


    private Nodo<T> agregarRecursivamente(Nodo<T> raiz, T valor) {
        if(raiz == null) return new Nodo<>(valor);
        if(raiz.getValor().compareTo(valor) > 0) {
            raiz.setIzquierda(agregarRecursivamente(raiz.getIzquierda(), valor));
        } else if (raiz.getValor().compareTo(valor) < 0){
            raiz.setDerecha(agregarRecursivamente(raiz.getDerecha(), valor));
        }
        return raiz;
    }

    public void inOrden(){
        recorrerInOrden(raiz);
    }

    public void recorrerInOrden(Nodo<T> raiz){
        if(raiz == null) return;
        recorrerInOrden(raiz.getIzquierda());
        System.out.println(raiz.getValor());
        recorrerInOrden(raiz.getDerecha());
    }

    public void recorrerPreorden() {
        preorden(raiz);
        System.out.println();
    }

    private void preorden(Nodo<T> nodo) {
        if (nodo != null) {
            System.out.print(nodo.getValor() + " ");
            preorden(nodo.getIzquierda());
            preorden(nodo.getDerecha());
        }
    }

    public void recorrerPostorden() {
        postorden(raiz);
        System.out.println();
    }

    private void postorden(Nodo<T> nodo) {
        if (nodo != null) {
            postorden(nodo.getIzquierda());
            postorden(nodo.getDerecha());
            System.out.print(nodo.getValor() + " ");
        }
    }

    public boolean buscar(Nodo<T> raiz, Nodo<T> buscando){
        return encontrarNodo(raiz, buscando);
    }

    public boolean encontrarNodo(Nodo<T> raiz, Nodo<T> buscando){
        if(raiz == null) return false;
        if(raiz.getValor().equals(buscando.getValor())) return true;
        return (raiz.getValor().compareTo(buscando.getValor()) > 0)?
                encontrarNodo(raiz.getIzquierda(), buscando) : encontrarNodo(raiz.getDerecha(), buscando);
    }

    public int obtenerAltura() {
        return altura(raiz);
    }

    private int altura(Nodo<T> nodo) {
        if (nodo == null) return 0;
        return 1 + Math.max(altura(nodo.getIzquierda()), altura(nodo.getDerecha()));
    }

    public int obtenerNivel(T peso) {
        return nivel(raiz, peso, 1);
    }

    private int nivel(Nodo<T> nodo, T peso, int nivel) {
        if (nodo == null) return 0;
        if (nodo.getValor() == peso) {
            return nivel;
        }
        int izq = nivel(nodo.getIzquierda(), peso, nivel + 1);
        if (izq != 0) return izq;
        return nivel(nodo.getDerecha(), peso, nivel + 1);
    }

    public void eliminarNodo(Nodo<T> raiz, Nodo<T> buscando){
        if(raiz == null || !buscar(raiz, buscando)) System.out.println("No existe el nodo o es vacío el arbol");

    }

    public int contarHojas() {
        return contarHojasRec(raiz);
    }

    private int contarHojasRec(Nodo<T> nodo) {
        if (nodo == null) return 0;
        if (nodo.getIzquierda() == null && nodo.getDerecha() == null) return 1;
        return contarHojasRec(nodo.getIzquierda()) + contarHojasRec(nodo.getDerecha());
    }

    public T obtenerMenor() {
        Nodo<T> actual = raiz;
        while (actual.getIzquierda() != null)
            actual = actual.getIzquierda();
        return actual.getValor();
    }

    public T obtenerNodoMayor() {
        Nodo<T> actual = raiz;
        while (actual.getDerecha() != null)
            actual = actual.getDerecha();
        return actual.getValor();
    }

    public T obtenerNodoMenor() {
        return obtenerMenor();
    }

    public void imprimirAmplitud() {
        if (raiz == null) return;
        Queue<Nodo> cola = new LinkedList<>();
        cola.add(raiz);
        while (!cola.isEmpty()) {
            Nodo actual = cola.poll();
            System.out.print(actual.getValor() + " ");
            if (actual.getIzquierda() != null) cola.add(actual.getIzquierda());
            if (actual.getDerecha() != null) cola.add(actual.getDerecha());
        }
        System.out.println();
    }

    private Nodo<T> obtenerMinimo(Nodo<T> nodo) {
        while (nodo.getIzquierda() != null)
            nodo = nodo.getIzquierda();
        return nodo;
    }

    public boolean existeDato(T peso) {
        return buscar(raiz, peso) != null;
    }

    private Nodo<T> buscar(Nodo<T> actual, T peso) {
        if (actual == null || actual.getValor().equals(peso))
            return actual;
        if (peso.compareTo(actual.getValor()) < 0)
            return buscar(actual.getIzquierda(), peso);
        else
            return buscar(actual.getDerecha(), peso);
    }


    public T obtenerPeso() {
        return raiz == null ?  null : raiz.getValor();
    }

    public void borrarArbol() {
        raiz = null;
    }

    public boolean esVacio(){
        return raiz == null;
    }

    public Nodo<T> getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo<T> raiz) {
        this.raiz = raiz;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }
}
