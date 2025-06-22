import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Atraccion montañaRusa = new Atraccion("Montaña Rusa", 2);
        Atraccion noria = new Atraccion("Noria", 3);
        Atraccion carrusel = new Atraccion("Carrusel", 4);

        List<Atraccion> atracciones = new ArrayList<>();
        atracciones.add(montañaRusa);
        atracciones.add(noria);
        atracciones.add(carrusel);

        List<Visitante> visitantes = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            visitantes.add(new Visitante("Visitante-" + i, atracciones));
        }

        for (Visitante v : visitantes) {
            v.start();
        }

        for (Visitante v : visitantes) {
            try {
                v.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("\nSimulación terminada.");
    }
}
