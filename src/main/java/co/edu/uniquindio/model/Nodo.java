package co.edu.uniquindio.model;

public class Nodo<T>{
    private Nodo<T> padre;
    private Nodo<T> izquierda;
    private Nodo<T> derecha;
    private T valor;

    public Nodo(T valor){
        this.padre = null;
        this.izquierda = null;
        this.derecha = null;
        this.valor = valor;
    }

    public Nodo<T> getPadre() {
        return padre;
    }

    public void setPadre(Nodo<T> padre) {
        this.padre = padre;
    }

    public Nodo<T> getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(Nodo<T> izquierda) {
        this.izquierda = izquierda;
    }

    public Nodo<T> getDerecha() {
        return derecha;
    }

    public void setDerecha(Nodo<T> derecha) {
        this.derecha = derecha;
    }

    public T getValor() {
        return valor;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }
}
