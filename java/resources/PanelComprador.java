package resources;
import Logica.*;
import javax.swing.*;
import java.awt.*;

public class PanelComprador extends JPanel {
    private JLabel productoLabel;
    private JLabel vueltoLabel;

    public PanelComprador() {
        setLayout(new GridLayout(2, 1));
        setBorder(BorderFactory.createTitledBorder("Comprador"));

        productoLabel = new JLabel("Producto comprado: ");
        vueltoLabel = new JLabel("Vuelto recibido: ");

        add(productoLabel);
        add(vueltoLabel);
    }

    public void actualizarComprador(Comprador comprador) {
        productoLabel.setText("Producto comprado: " + comprador.queCompraste());
        int totalVuelto = comprador.cuantoVuelto();
        vueltoLabel.setText("Vuelto recibido: $" + totalVuelto);
    }
}
