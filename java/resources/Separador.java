package resources;

import javax.swing.*;

public class Separador {
    private JLabel separador;

    public Separador(){
        separador = new JLabel("----------------------------------");
    }

    public JLabel getLabel(){
        return separador;
    }
}
