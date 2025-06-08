package Logica.Depositos;

import Logica.Monedas.Moneda;
import Logica.Monedas.Moneda100;
import Logica.Productos.Producto;
import java.util.ArrayList;

/**
 * Clase genérica que representa un depósito de elementos.
 *
 * <p>Puede usarse para contener productos o monedas.</p>
 *
 */
public class Deposito<T> {
    /** Lista que almacena los elementos del depósito. */
    private ArrayList<T> deposito;

    /**
     * Crea un nuevo depósito vacío.
     */
    public Deposito() {
        deposito = new ArrayList<>();
    }

    /**
     * Añade un elemento al depósito.
     *
     * @param b el elemento a añadir.
     */
    public void addElemento(T b) {
        deposito.add(b);
    }

    /** Saca un elemento del deposito
     *
     * @return el elemento si existe o {@code null} si el deposito esta vacio
     */
    public Producto getProducto() {
        if (!deposito.isEmpty()) {
            return (Producto) deposito.remove(0);
        } else {
            return null;
        }
    }
    /**
     * Saca y remueve una Moneda de 100 del deposito.
     *
     * @return una Moneda de 100 si existe o {@code null} si el deposito esta vacio.
     */
    public Moneda getVuelto(){
        if (!deposito.isEmpty()) {
            return (Moneda) deposito.remove(0);
        } else {
            return null;
        }
    }
    public int size() {
        return deposito.size();
    }
}