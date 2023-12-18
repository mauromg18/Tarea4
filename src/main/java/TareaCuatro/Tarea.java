package TareaCuatros;
import java.io.Serializable;

public class Tarea implements Serializable {
    private static final long serialVersionUID = 1L;

    private String descripcion;
    private EstadoTarea estado;

    public Tarea(String descripcion) {
        this.descripcion = descripcion;
        this.estado = EstadoTarea.POR_HACER;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public EstadoTarea getEstado() {
        return estado;
    }

    public void setEstado(EstadoTarea estado) {
        this.estado = estado;
    }
}
