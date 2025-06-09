<<<<<<< Updated upstream:resources/PanelExpendedor.java
=======
package resources;
import Logica.*;
import Logica.Monedas.Moneda;
import Logica.Productos.Producto;

>>>>>>> Stashed changes:java/resources/PanelExpendedor.java
import javax.swing.*;
import java.awt.*;



public class PanelExpendedor extends JPanel {
    private JButton cocaButton;
    private JButton spriteButton;
    private JButton fantaButton;
    private JButton super8Button;
    private JButton snickersButton;
    private Expendedor expendedor;
    private ReproductorSonido sonido = new ReproductorSonido();
    public PanelExpendedor(Expendedor expendedor) {
<<<<<<< Updated upstream:resources/PanelExpendedor.java
        setLayout(new GridLayout(5, 1));
        setBorder(BorderFactory.createTitledBorder("Stock Expendedor"));
=======
        this.expendedor = expendedor;
        setLayout(new GridLayout(1, 5));
        setBorder(BorderFactory.createTitledBorder("Expendedor"));
>>>>>>> Stashed changes:java/resources/PanelExpendedor.java

        cocaButton = crearBotonProducto("CocaCola", "resources/imagenes/cocacola.png", 1);
        spriteButton = crearBotonProducto("Sprite", "resources/imagenes/sprite.png", 2);
        fantaButton = crearBotonProducto("Fanta", "resources/imagenes/fanta.png", 3);
        super8Button = crearBotonProducto("Super8", "resources/imagenes/super8.png", 4);
        snickersButton = crearBotonProducto("Snickers", "resources/imagenes/snickers.png", 5);

        add(cocaButton);
        add(spriteButton);
        add(fantaButton);
        add(super8Button);
        add(snickersButton);
    }

    private JButton crearBotonProducto(String nombre, String rutaImagen, int productoId) {
        ImageIcon icono = new ImageIcon(getClass().getClassLoader().getResource(rutaImagen));
        Image imagenEscalada = icono.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);

        JButton boton = new JButton(nombre, iconoEscalado);
        boton.setVerticalTextPosition(SwingConstants.BOTTOM);
        boton.setHorizontalTextPosition(SwingConstants.CENTER);
        boton.setFocusPainted(false);

        boton.addActionListener(e -> comprarProducto(productoId));

        return boton;
    }

    private void comprarProducto(int productoId) {
        Moneda moneda = new Logica.Monedas.Moneda1000();

        try {
            Producto comprado = expendedor.comprarProducto(moneda, productoId);
            if (comprado != null) {
                switch (productoId) {
                    case 1:
                    case 2:
                    case 3:
                        sonido.reproducir("resources/sonidos/gluglu.mp3");
                        break;
                    case 4:
                    case 5:
                        sonido.reproducir("resources/sonidos/yamyam.mp3");
                        break;
                }
                JOptionPane.showMessageDialog(this, "Producto comprado: " + comprado.getClass().getSimpleName());
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo comprar el producto.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }

        actualizarStock();
    }

    public void actualizarStock() {
        cocaButton.setText("CocaCola");
        spriteButton.setText("Sprite");
        fantaButton.setText("Fanta");
        super8Button.setText("Super8");
        snickersButton.setText("Snickers");
    }
}

