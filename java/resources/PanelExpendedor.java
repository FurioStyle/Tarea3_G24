package resources;
import Logica.*;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class PanelExpendedor extends JPanel {
    private JLabel cocaLabel, spriteLabel, fantaLabel, super8Label, snickersLabel;

    private Image fondo;
    private HashMap<String,Image> imagenes;
    private Expendedor expendedor;

    public PanelExpendedor(Expendedor expendedor) {
        this.expendedor = expendedor;

        setLayout(null);
        setBorder(BorderFactory.createTitledBorder("Stock del Expendedor"));

        fondo = new ImageIcon("java/resources/imagenes/expendedor.png").getImage();
        imagenes = new HashMap<>();
        imagenes.put("CocaCola", new ImageIcon("java/resources/imagenes/cocacola.png").getImage());
        imagenes.put("Sprite", new ImageIcon("java/resources/imagenes/sprite.png").getImage());
        imagenes.put("Fanta", new ImageIcon("java/resources/imagenes/fanta.png").getImage());
        imagenes.put("Super8", new ImageIcon("java/resources/imagenes/super8.png").getImage());
        imagenes.put("Snickers", new ImageIcon("java/resources/imagenes/snickers.png").getImage());


        setPreferredSize(new Dimension(fondo.getWidth(null), fondo.getHeight(null)));

        cocaLabel = new JLabel("CocaCola: " + expendedor.getStockCoca());
        spriteLabel = new JLabel("Sprite: " + expendedor.getStockSprite());
        fantaLabel = new JLabel("Fanta: " + expendedor.getStockFanta());
        super8Label = new JLabel("Super8: " + expendedor.getStockSuper8());
        snickersLabel = new JLabel("Snickers: " + expendedor.getStockSnickers());

        int xText = 20, yText = 30, dy = 40;
        cocaLabel.setBounds(xText, yText, 120, 20);
        spriteLabel.setBounds(xText, yText + dy, 120, 20);
        fantaLabel.setBounds(xText, yText + 2 * dy, 120, 20);
        super8Label.setBounds(xText, yText + 3 * dy, 120, 20);
        snickersLabel.setBounds(xText, yText + 4 * dy, 120, 20);

        add(cocaLabel);
        add(spriteLabel);
        add(fantaLabel);
        add(super8Label);
        add(snickersLabel);
    }

    public void actualizarStock(Expendedor expendedor) {
        cocaLabel.setText("CocaCola: " + expendedor.getStockCoca());
        spriteLabel.setText("Sprite: " + expendedor.getStockSprite());
        fantaLabel.setText("Fanta: " + expendedor.getStockFanta());
        super8Label.setText("Super8: " + expendedor.getStockSuper8());
        snickersLabel.setText("Snickers: " + expendedor.getStockSnickers());
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo, 0, 0, this);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(255, 255, 255, 180));
        g2d.fillRoundRect(15, 20, 130, 220, 15, 15);
        int xBase = 160, yBase = 100, dy = 70;
        int iconW = 30, iconH = 30, maxEnFila = 5, dx = 35;

        dibujarProducto(g, "CocaCola", expendedor.getStockCoca(), xBase, yBase, dx, iconW, iconH, maxEnFila);
        dibujarProducto(g, "Sprite", expendedor.getStockSprite(), xBase, yBase + dy, dx, iconW, iconH, maxEnFila);
        dibujarProducto(g, "Fanta", expendedor.getStockFanta(), xBase, yBase + 2 * dy, dx, iconW, iconH, maxEnFila);
        dibujarProducto(g, "Super8", expendedor.getStockSuper8(), xBase, yBase + 3 * dy, dx, iconW, iconH, maxEnFila);
        dibujarProducto(g, "Snickers", expendedor.getStockSnickers(), xBase, yBase + 4 * dy, dx, iconW, iconH, maxEnFila);
    }

    private void dibujarProducto(Graphics g, String clave, int stock, int x, int y, int dx, int w, int h, int max) {
        Image img = imagenes.get(clave);
        for (int i = 0; i < Math.min(stock, max); i++) {
            g.drawImage(img, x + i * dx, y - 5, w, h, this);
        }
    }
}
