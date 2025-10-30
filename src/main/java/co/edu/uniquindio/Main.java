package co.edu.uniquindio;

import co.edu.uniquindio.model.Arbol;
import co.edu.uniquindio.view.PanelArbol;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame ventana = new JFrame("Animación del Árbol Binario");
        Arbol<Integer> arbol = new Arbol<>();
        PanelArbol<Integer> panel = new PanelArbol<>(arbol);

        ventana.add(panel);
        ventana.setSize(800, 600);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);

        new Thread(() -> {
            int[] valores = {8, 3, 10, 1, 6, 14, 4, 7, 13};
            for (int v : valores) {
                arbol.agregarAnimado(v, panel);
                try { Thread.sleep(1000); } catch (InterruptedException e) {}
            }

            try { Thread.sleep(1500); } catch (InterruptedException e) {}

            // 🔸 Ahora eliminar algunos nodos con animación
            int[] eliminar = {1, 6, 10, 8};
            for (int v : eliminar) {
                arbol.eliminarAnimado(v, panel);
                try { Thread.sleep(1500); } catch (InterruptedException e) {}
            }
        }).start();

    }
}
