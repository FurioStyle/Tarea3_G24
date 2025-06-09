package resources;

import Logica.Monedas.*;


import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelMonedas extends JPanel {
    private DefaultListModel<String> monedasModel;
    private JList<String> monedasList;
    private int saldo = 0;
    private JLabel dineroLabel;
    private Separador separador = new Separador();

    /**
     * Crea un nuevo panel que sirve como monedero, con tres botones para añadir saldo, cada moneda está representada
     * con un color.
     * @param monedas recibe una lista de monedas, para obtener su valor.
     */
    public PanelMonedas(List<Moneda> monedas) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Monedas"));
        monedasModel = new DefaultListModel<>();
        for (Moneda m : monedas) {
            monedasModel.addElement("Moneda: $" + m.getValor());
        }

        monedasList = new JList<>(monedasModel);
        add(new JScrollPane(monedasList), BorderLayout.WEST);


        JPanel botonesPanel = new JPanel();
        botonesPanel.setLayout(new GridLayout(5, 1, 5, 5));

        // label con el texto de saldo disponible

        dineroLabel = new JLabel("Saldo Disponible:" + getSaldo());

        // botones de monedas

        JButton boton1 = new JButton("100");
        boton1.setBackground(Color.RED);
        boton1.setOpaque(true);
        boton1.setBorderPainted(false);
        boton1.setForeground(Color.BLACK);

        JButton boton2 = new JButton("500");
        boton2.setBackground(Color.YELLOW);
        boton2.setOpaque(true);
        boton2.setBorderPainted(false);
        boton2.setForeground(Color.BLACK);

        JButton boton3 = new JButton("1000");
        boton3.setBackground(Color.GREEN);
        boton3.setOpaque(true);
        boton3.setBorderPainted(false);
        boton3.setForeground(Color.BLACK);

        // se añade todo

        botonesPanel.add(dineroLabel);
        botonesPanel.add(separador.getLabel());
        botonesPanel.add(boton1);
        botonesPanel.add(boton2);
        botonesPanel.add(boton3);

        boton1.addActionListener(e -> agregarMoneda(new Moneda100()));
        boton2.addActionListener(e -> agregarMoneda(new Moneda500()));
        boton3.addActionListener(e -> agregarMoneda(new Moneda1000()));

        add(botonesPanel, BorderLayout.WEST);


    }

    /**
     * Se toma una moneda, se lee su valor y se agrega al saldo disponible.
     * @param m Una Moneda.
     */
    public void agregarMoneda(Moneda m){
        if (m != null) {
            this.saldo += m.getValor();
            mostrarSaldo();
        } else {
            this.saldo += 0;
            mostrarSaldo();
        }
    }

    /**
     * Actualiza el saldo disponible mostrado en el panel
     */
    public void mostrarSaldo() {
        dineroLabel.setText("Saldo Disponible:" + getSaldo());
    }

    /**
     * Getter simple para obtener el saldo disponible.
     * @return entero con el saldo.
     */
    public int getSaldo(){
        return saldo;
    }

    /**
     * Metodo para agregar saldo de forma externa, usado generalmente para añadir el vuelto al saldo disponible.
     * @param x un entero con el valor a agregar.
     */
    public void agregarSaldo(int x)
    {
        saldo += x;
    }

    /**
     * Metodo usado en la creacion de la MonedaVirtual, donde se descuenta del saldo disponible la moneda que se
     * va a usar.
     * @param x entero con el valor a quitar.
     */
    public void quitarSaldo(int x){
        saldo -= x;
    }

    /**
     * Crea una moneda Virtual dependiendo del saldo, crea siempre la moneda con mayor valor posible, para
     * facilitar el proceso de compra
     * @return una Moneda generada aquí, con el mayor valor posible.
     */
    public Moneda monedaVirtual(){
        int m = getSaldo();
        if(m >= 1000){
            quitarSaldo(1000);
            return new Moneda1000();
        }
        else if (m >= 500 ){
            quitarSaldo(500);
            return new Moneda500();
        }
        else if (m >= 100){
            quitarSaldo(100);
            return new Moneda100();
        }
        else {
            return null;
        }
    }

}