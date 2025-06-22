import java.util.List;
import java.util.Random;

public class Visitante extends Thread {
    private final String nombre;
    private final List<Atraccion> atracciones;
    private final Random random = new Random();

    public Visitante(String nombre, List<Atraccion> atracciones) {
        this.nombre = nombre;
        this.atracciones = atracciones;
    }

    @Override
    public void run() {
        int intentos = 0;
        while (intentos < 5) {
            Atraccion atraccion = atracciones.get(random.nextInt(atracciones.size()));
            if (atraccion.intentarSubir(nombre)) {
                try {
                    Thread.sleep(1000 + random.nextInt(1000));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                atraccion.bajar(nombre);
                break;
            } else {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                intentos++;
            }
        }
        if (intentos == 5) {
            System.out.println(nombre + " no pudo subirse a ninguna atracción después de varios intentos.");
        }
    }
}
