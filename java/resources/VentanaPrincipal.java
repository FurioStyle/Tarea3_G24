package resources;

import Logica.*;
import Logica.Excepciones.PagoIncorrectoException;
import Logica.Monedas.*;

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
    private JButton consumir, vueltoBoton;
    private PanelProductos panelProductos;

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
        JLayeredPane panelImagen = new JLayeredPane();
        panelImagen.setPreferredSize(new Dimension(exp.getIconWidth(), exp.getIconHeight()));

        panelImagen.setLayout(null);
        BotonesMaquina(panelImagen);
        panelImagen.add(e, Integer.valueOf(0));
        panelMonedas = new PanelMonedas(vuelto);

        panelProductos = new PanelProductos(expendedor);
        panelProductos.setBounds(0, 0, exp.getIconWidth(), exp.getIconHeight());
        panelImagen.add(panelProductos, Integer.valueOf(1));


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

    public void BotonesMaquina(JLayeredPane panelImagen) {
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

                    consumir = new JButton("...");
                    consumir.setBounds(25, 510, 300, 110);
                    panelImagen.add(consumir, Integer.valueOf(1));
                    consumir.setVisible(true);


                    int separacionVertical = 0;
                    int vuelto = comprador.cuantoVuelto();
                    Moneda m;
                    while ((m = expendedor.getVuelto()) != null) {
                        separacionVertical += 40;
                        JButton botonMoneda = new JButton("$" + m.getValor());
                        botonMoneda.setBounds(350, 500 + separacionVertical, 40, 40);
                        switch (m.getValor()) {
                            case (1000):
                                botonMoneda.setBackground(Color.GREEN);
                                break;
                            case (500):
                                botonMoneda.setBackground(Color.YELLOW);
                                break;
                            case (100):
                                botonMoneda.setBackground(Color.RED);
                        }

                        int valor = m.getValor();
                        botonMoneda.addActionListener(event -> {
                            panelMonedas.agregarSaldo(valor);
                            panelImagen.remove(botonMoneda);
                            panelImagen.repaint();
                        });

                        panelImagen.add(botonMoneda, Integer.valueOf(1));
                    }

                    if (comprador.queCompraste() != null) {
                        panelComprador.empuja();
                    }

                    consumir.setVisible(true);
                    consumir.addActionListener(ev -> {
                        panelComprador.actualizarComprador(comprador, panelMonedas);
                        panelImagen.remove(consumir);
                        panelImagen.revalidate();
                        panelImagen.repaint();
                        panelMonedas.mostrarSaldo();
                    });
                    panelExpendedor.actualizarStock(expendedor);
                    actualizarImagenesProductos();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this,
                            "No se pudo completar la compra: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            panelImagen.add(boton, Integer.valueOf(2));
            x += 40;
        }
    }
    public void actualizarImagenesProductos() {
        panelProductos.actualizar();
    }
}
