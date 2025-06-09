package resources;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import Logica.Expendedor;

public class PanelProductos extends JPanel {
    private Expendedor expendedor;
    private final String[] nombres = {"cocacola", "sprite", "fanta", "super8", "snickers"};
    private final int[] posicionesY = {40, 130, 220, 310, 400}; // Y por tipo
    private Map<String, Image> imagenes = new HashMap<>();

    public PanelProductos(Expendedor expendedor) {
        this.expendedor = expendedor;
        setOpaque(false);
        cargarImagenes();
    }

    private void cargarImagenes() {
        for (String nombre : nombres) {
            ImageIcon icono = new ImageIcon("java/resources/imagenes/" + nombre + ".png");
            imagenes.put(nombre, icono.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int[] stocks = {
                expendedor.getStockCoca(),
                expendedor.getStockSprite(),
                expendedor.getStockFanta(),
                expendedor.getStockSuper8(),
                expendedor.getStockSnickers()
        };

        for (int i = 0; i < nombres.length; i++) {
            Image img = imagenes.get(nombres[i]);
            for (int j = 0; j < stocks[i]; j++) {
                g.drawImage(img, 30 + (j * 50), posicionesY[i], this);
            }
        }
    }

    public void actualizar() {
        repaint();
    }
}
