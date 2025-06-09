package resources;
import Logica.*;
import Logica.Productos.ProductoEnum;
import javax.swing.*;
import java.awt.*;

public class PanelExpendedor extends JPanel {
    private JLabel cocaLabel, spriteLabel, fantaLabel, super8Label, snickersLabel;

    public PanelExpendedor(Expendedor expendedor) {
        setLayout(new GridLayout(5, 1));
        setBorder(BorderFactory.createTitledBorder("Stock del Expendedor y Coste"));

        cocaLabel = new JLabel("CocaCola: " + expendedor.getStockCoca() + "  $" + ProductoEnum.COCA_COLA.getPrecio()) ;
        spriteLabel = new JLabel("Sprite: " + expendedor.getStockSprite() + "  $" + ProductoEnum.SPRITE.getPrecio());
        fantaLabel = new JLabel("Fanta: " + expendedor.getStockFanta() + "  $" + ProductoEnum.FANTA.getPrecio());
        super8Label = new JLabel("Super8: " + expendedor.getStockSuper8() + "  $" + ProductoEnum.SUPER8.getPrecio());
        snickersLabel = new JLabel("Snickers: " + expendedor.getStockSnickers() + "  $" + ProductoEnum.SNICKERS.getPrecio());

        add(cocaLabel);
        add(spriteLabel);
        add(fantaLabel);
        add(super8Label);
        add(snickersLabel);
    }

    // Método para refrescar datos
    public void actualizarStock(Expendedor expendedor) {
        cocaLabel.setText("CocaCola: " + expendedor.getStockCoca());
        spriteLabel.setText("Sprite: " + expendedor.getStockSprite());
        fantaLabel.setText("Fanta: " + expendedor.getStockFanta());
        super8Label.setText("Super8: " + expendedor.getStockSuper8());
        snickersLabel.setText("Snickers: " + expendedor.getStockSnickers());
    }
}
