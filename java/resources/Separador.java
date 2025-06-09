package resources;

import javax.swing.*;

public class Separador {
    private JLabel separador;

    /**
     * Separador de texto generico, creado por comodidad.
     */
    public Separador(){
        separador = new JLabel("--------------------------------------------------");
    }

    /**
     * Getter de Label
     * @return JLabel con el separador
     */
    public JLabel getLabel(){
        return separador;
    }
}
