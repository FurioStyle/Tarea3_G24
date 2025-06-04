import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelMonedas extends JPanel {
    private DefaultListModel<String> monedasModel;
    private JList<String> monedasList;

    public PanelMonedas(List<Moneda> monedas) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Monedas (Vuelto)"));

        monedasModel = new DefaultListModel<>();
        for (Moneda m : monedas) {
            monedasModel.addElement("Moneda: $" + m.getValor());
        }

        monedasList = new JList<>(monedasModel);
        add(new JScrollPane(monedasList), BorderLayout.CENTER);
    }

    public void actualizarMonedas(List<Moneda> monedas) {
        monedasModel.clear();
        for (Moneda m : monedas) {
            monedasModel.addElement("Moneda: $" + m.getValor());
        }
    }
}
