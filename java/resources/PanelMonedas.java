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

        dineroLabel = new JLabel("Saldo Disponible:" + getSaldo());
        JButton boton1 = new JButton("100");
        JButton boton2 = new JButton("500");
        JButton boton3 = new JButton("1000");

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

    public void agregarMoneda(Moneda m){
        this.saldo += m.getValor();
        mostrarSaldo();
    }

    public void mostrarSaldo() {
        dineroLabel.setText("Saldo Disponible:" + getSaldo());
    }

    public int getSaldo(){
        return saldo;
    }

    public void agregarSaldo(int x)
    {
        saldo += x;
    }
    public void quitarSaldo(int x){
        saldo -= x;
    }

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