import javax.swing.*;
import java.awt.*;

public class PanelComprador extends JPanel {
    private JLabel productoLabel;
    private JLabel vueltoLabel;

    public PanelComprador(Comprador comprador) {
        setLayout(new GridLayout(2, 1));
        setBorder(BorderFactory.createTitledBorder("Comprador"));

        productoLabel = new JLabel("Producto comprado: " + comprador.queCompraste());
        vueltoLabel = new JLabel("Vuelto recibido: $" + comprador.cuantoVuelto());

        add(productoLabel);
        add(vueltoLabel);
    }

    public void actualizarComprador(Comprador comprador) {
        productoLabel.setText("Producto comprado: " + comprador.queCompraste());
        vueltoLabel.setText("Vuelto recibido: $" + comprador.cuantoVuelto());
    }
}
