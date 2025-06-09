package resources;
import javazoom.jl.player.Player;
import java.io.BufferedInputStream;
import java.io.InputStream;

public class ReproductorSonido {
    public void reproducir(String ruta) {
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(ruta);
            if (is == null) {
                System.err.println("No se encontró el archivo de sonido: " + ruta);
                return;
            }

            BufferedInputStream bis = new BufferedInputStream(is);
            Player player = new Player(bis);
            new Thread(() -> {
                try {
                    player.play();
                } catch (Exception e) {
                    System.err.println("Error al reproducir: " + e.getMessage());
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
