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
    private JButton consumir;

    public VentanaPrincipal() {
        setTitle("Máquina Expendedora");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 700);
        setLocationRelativeTo(null);

        expendedor = new Expendedor(6);
        panelExpendedor = new PanelExpendedor(expendedor);
        panelComprador = new PanelComprador();

        List<Moneda> vuelto = new ArrayList<>();


        ImageIcon exp = new ImageIcon("java/resources/imagenes/expendedor.png");
        JLabel e = new JLabel(exp);
        e.setBounds(0, 0, exp.getIconWidth(), exp.getIconHeight());
        System.out.println(exp.getIconWidth()+ " " + exp.getIconHeight());
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

                    Moneda moneda = panelMonedas.monedaVirtual();
                    System.out.println(moneda.getValor());
                    comprador = new Comprador(moneda, numeroProducto, expendedor);
                    consumir = new JButton("...");
                    consumir.setBounds(25, 510, 300, 110);
                    panelImagen.add(consumir);
                    if (comprador.queCompraste() != null){
                        panelComprador.empuja();
                    }
                    consumir.setVisible(true);

                    consumir.addActionListener(ev ->{

                        List<Moneda> vuelto = new ArrayList<>();
                        Moneda m = expendedor.getVuelto();
                        while (m != null){
                            vuelto.add(m);
                            m = expendedor.getVuelto();
                        }
                        panelComprador.actualizarComprador(comprador, panelMonedas);
                        panelImagen.remove(consumir);
                        panelImagen.revalidate();
                        panelImagen.repaint();
                        panelMonedas.mostrarSaldo();
                    });
                    panelExpendedor.actualizarStock(expendedor);
                    panelMonedas.mostrarSaldo();

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
}
