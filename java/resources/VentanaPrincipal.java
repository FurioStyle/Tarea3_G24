package resources;

import Logica.*;
import Logica.Excepciones.NoHayProductoException;
import Logica.Excepciones.PagoIncorrectoException;
import Logica.Monedas.*;
import Logica.Productos.*;

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
        setSize(800, 700);
        setLocationRelativeTo(null);

        // Lógica
        expendedor = new Expendedor(6);
        panelExpendedor = new PanelExpendedor(expendedor);
        panelExpendedor.setLayout(null); // importante para colocar botones encima

        panelComprador = new PanelComprador();
        List<Moneda> vuelto = new ArrayList<>();
        panelMonedas = new PanelMonedas(vuelto);

        // Agregamos los botones sobre el panel que tiene la imagen
        agregarBotones(panelExpendedor);

        // Layout general
        setLayout(new BorderLayout());

        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new GridLayout(2, 1));
        panelIzquierdo.add(panelComprador);
        panelIzquierdo.add(panelMonedas);

        add(panelIzquierdo, BorderLayout.WEST);
        add(panelExpendedor, BorderLayout.CENTER); // usamos el expendedor como panel principal visual

        setVisible(true);
    }

    public void agregarBotones(JPanel panel) {
        int y = 0;
        int x = 0;

        for (int i = 1; i <= 5; i++) {
            if (i % 2 == 1) {
                y += 40;
                x = 0;
            }

            JButton boton = new JButton(String.valueOf(i));
            boton.setBounds(350 + x, 250 + y, 40, 40);

            int numeroProducto = i;

            boton.addActionListener(e -> {
                try {
                    int saldo = panelMonedas.getSaldo();

                    if (saldo <= 0) {
                        throw new PagoIncorrectoException("No has ingresado dinero");
                    }

                    Moneda moneda = panelMonedas.monedaVirtual();
                    comprador = new Comprador(moneda, numeroProducto, expendedor);

                    List<Moneda> vuelto = new ArrayList<>();
                    Moneda m = expendedor.getVuelto();
                    while (m != null){
                        vuelto.add(m);
                        m = expendedor.getVuelto();
                    }

                    panelExpendedor.actualizarStock(expendedor);
                    panelComprador.actualizarComprador(comprador, panelMonedas);
                    panelMonedas.mostrarSaldo();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this,
                            "No se pudo completar la compra: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            panel.add(boton);
            x += 40;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPrincipal());
    }
}
