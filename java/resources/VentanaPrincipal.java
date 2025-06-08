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
        setSize(700, 700);
        setLocationRelativeTo(null);

        expendedor = new Expendedor(6);

        try {
            // Usa una moneda que ya tengas, por ejemplo Moneda1000
            comprador = new Comprador(new Moneda1000(), 1, expendedor);
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
        e.setBounds(0, 0, exp.getIconWidth(), exp.getIconHeight());

        JPanel panelImagen = new JPanel(null); // null layout para posicionar manualmente
        BotonesMaquina(panelImagen);
        panelImagen.add(e);
        panelMonedas = new PanelMonedas(vuelto);

        setLayout(new BorderLayout());

        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new GridLayout(3, 1));
        panelIzquierdo.add(panelExpendedor);
        panelIzquierdo.add(panelComprador);
        panelIzquierdo.add(panelMonedas);

        add(panelIzquierdo, BorderLayout.WEST);
        add(panelImagen, BorderLayout.CENTER);

        setVisible(true);
    }

    public void BotonesMaquina(JPanel panelImagen) {
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

                    Moneda moneda = MonedaVirtual(panelMonedas);
                    comprador = new Comprador(moneda, numeroProducto, expendedor);
                    panelComprador.actualizarComprador(comprador, expendedor);
                    panelExpendedor.actualizarStock(expendedor);
                    List<Moneda> vuelto = new ArrayList<>();
                    Moneda m;
                    while ((m = expendedor.getVuelto()) != null) {
                        vuelto.add(m);
                    }

                    panelMonedas.actualizarVuelto(vuelto);

                    // Reiniciar saldo tras compra
                    panelMonedas.mostrarSaldo(); // Refresca el label

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this,
                            "No se pudo completar la compra: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            panelImagen.add(boton);
            x += 40;
        }
    }

    public Moneda MonedaVirtual(PanelMonedas panelMonedas){
        int m = panelMonedas.getSaldo();
        if(m >= 1000){
            panelMonedas.saldo -= 1000;
            return new Moneda1000();
        }
        else if (m >= 500 ){
            panelMonedas.saldo -= 500;
            return new Moneda500();
        }
        else if (m >= 100){
            panelMonedas.saldo -= 100;
            return new Moneda100();
        }
        else {
            return null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPrincipal());
    }
}
