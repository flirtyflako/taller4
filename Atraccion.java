import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Atraccion {
    private final String nombre;
    private final int capacidadMaxima;
    private int visitantesActuales = 0;
    private final ReentrantLock lock = new ReentrantLock();

    public Atraccion(String nombre, int capacidadMaxima) {
        this.nombre = nombre;
        this.capacidadMaxima = capacidadMaxima;
    }

    public boolean intentarSubir(String visitanteNombre) {
        try {
            if (lock.tryLock(500, TimeUnit.MILLISECONDS)) {
                try {
                    if (visitantesActuales < capacidadMaxima) {
                        visitantesActuales++;
                        System.out.println(visitanteNombre + " subió a " + nombre + ". (" + visitantesActuales + "/" + capacidadMaxima + ")");
                        return true;
                    } else {
                        System.out.println(visitanteNombre + " intentó subir a " + nombre + " pero estaba llena.");
                        return false;
                    }
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println(visitanteNombre + " no pudo obtener acceso a " + nombre + " (tiempo de espera agotado).");
                return false;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    public void bajar(String visitanteNombre) {
        lock.lock();
        try {
            visitantesActuales--;
            System.out.println(visitanteNombre + " bajó de " + nombre + ". (" + visitantesActuales + "/" + capacidadMaxima + ")");
        } finally {
            lock.unlock();
        }
    }

    public String getNombre() {
        return nombre;
    }
}
