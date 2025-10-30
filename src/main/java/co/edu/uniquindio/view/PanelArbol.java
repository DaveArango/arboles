package co.edu.uniquindio.view;

import co.edu.uniquindio.model.Arbol;
import co.edu.uniquindio.model.Nodo;
import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class PanelArbol<T extends Comparable<T>> extends JPanel {

    private final Arbol<T> arbol;
    private final Set<Nodo<T>> resaltados = new HashSet<>();

    public PanelArbol(Arbol<T> arbol) {
        this.arbol = arbol;
        setBackground(Color.WHITE);
    }

    public void resaltar(Nodo<T> nodo) {
        if (nodo != null) {
            resaltados.add(nodo);
        }
    }

    public void actualizarArbol() {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (arbol.getRaiz() != null) {
            dibujarNodo(g, arbol.getRaiz(), getWidth() / 2, 50, getWidth() / 4);
        }
    }

    private void dibujarNodo(Graphics g, Nodo<T> nodo, int x, int y, int offsetX) {
        if (nodo == null) return;

        // Color del nodo
        g.setColor(resaltados.contains(nodo) ? Color.ORANGE : Color.CYAN);
        g.fillOval(x - 20, y - 20, 40, 40);
        g.setColor(Color.BLACK);
        g.drawOval(x - 20, y - 20, 40, 40);

        // Valor del nodo
        g.drawString(nodo.getValor().toString(), x - 6, y + 5);

        // Enlaces
        if (nodo.getIzquierda() != null) {
            g.drawLine(x, y, x - offsetX, y + 60);
            dibujarNodo(g, nodo.getIzquierda(), x - offsetX, y + 60, offsetX / 2);
        }
        if (nodo.getDerecha() != null) {
            g.drawLine(x, y, x + offsetX, y + 60);
            dibujarNodo(g, nodo.getDerecha(), x + offsetX, y + 60, offsetX / 2);
        }
    }

    public void removerResaltado(Nodo<T> nodo) {
        resaltados.remove(nodo);
    }
}

