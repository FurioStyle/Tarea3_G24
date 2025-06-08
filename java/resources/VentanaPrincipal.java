package resources;
import Logica.*;
import Logica.Monedas.Moneda;
import Logica.Monedas.Moneda1000;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaPrincipal extends JFrame {
    private Expendedor expendedor;
    private Comprador comprador;
    private PanelExpendedor panelExpendedor;
    private PanelComprador panelComprador;
    private PanelMonedas panelMonedas;

    public VentanaPrincipal() {
        setTitle("Máquina Expendedora");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(720, 600);
        setLocationRelativeTo(null);

        expendedor = new Expendedor(6);

        try {
            // Usa una moneda que ya tengas, por ejemplo Moneda1000
            comprador = new Comprador(new Moneda1000(), 1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        panelExpendedor = new PanelExpendedor(expendedor);
        panelComprador = new PanelComprador(comprador);

        List<Moneda> vuelto = new ArrayList<>();
        Moneda moneda;
        while ((moneda = expendedor.getVuelto()) != null) {
            vuelto.add(moneda);
        }

        ImageIcon exp = new ImageIcon("java/resources/imagenes/expendedor.png");
        JLabel e = new JLabel(exp);
        e.setHorizontalAlignment(JLabel.CENTER);

        panelMonedas = new PanelMonedas(vuelto);

        setLayout(new BorderLayout());

        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new GridLayout(3, 1));
        panelIzquierdo.add(panelExpendedor);
        panelIzquierdo.add(panelComprador);

        add(panelIzquierdo, BorderLayout.WEST);
        add(e, BorderLayout.CENTER);
        add(panelMonedas, BorderLayout.EAST);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPrincipal());
    }
}
