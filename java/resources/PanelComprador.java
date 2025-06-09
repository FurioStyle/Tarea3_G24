package resources;
import Logica.*;
import javax.swing.*;
import java.awt.*;

public class PanelComprador extends JPanel {
    private JLabel productoLabel;
    private JLabel vueltoLabel;

    /**
     * Crea un nuevo Panel con texto, donde se muestran el producto comprado y el vuelto recibido.
     */
    public PanelComprador() {
        setLayout(new GridLayout(2, 1));
        setBorder(BorderFactory.createTitledBorder("Comprador"));

        productoLabel = new JLabel("Producto comprado: ");
        vueltoLabel = new JLabel("Vuelto recibido: ");

        add(productoLabel);
        add(vueltoLabel);
    }

    /**
     * Este metodo actualiza lo mostrado en el panel de comprador, muestra lo que se compró y el vuelto recibido,
     * también, añade el vuelto al saldo disponible.
     * @param comprador comprador, para saber que compro y cuanto vuelto necesita.
     * @param panelMonedas el panel donde se administra todo lo del saldo, para actualizar el saldo disponible.
     */
    public void actualizarComprador(Comprador comprador, PanelMonedas panelMonedas) {
        productoLabel.setText("Producto comprado: " + comprador.queCompraste());
        int totalVuelto = comprador.cuantoVuelto();
        panelMonedas.agregarSaldo(totalVuelto);
        vueltoLabel.setText("Vuelto recibido: $" + totalVuelto);
    }

    /**
     * Este metodo representa como texto en el panel comprador, cuando se debe retirar el producto.
     */
    public void empuja(){
        productoLabel.setText("Producto comprado: Retírelo" );
    }
}
