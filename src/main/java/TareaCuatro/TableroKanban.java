package TareaCuatro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un tablero Kanban que contiene listas de tareas en diferentes estados.
 */
public class TableroKanban implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Tarea> porHacerList;
    private List<Tarea> enProcesoList;
    private List<Tarea> terminadoList;

    /**
     * Constructor de la clase TableroKanban.
     */
    public TableroKanban() {
        this.porHacerList = new ArrayList<>();
        this.enProcesoList = new ArrayList<>();
        this.terminadoList = new ArrayList<>();
    }

    /**
     * Obtiene la lista de tareas por hacer.
     *
     * @return Lista de tareas por hacer.
     */
    public List<Tarea> getPorHacerList() {
        return porHacerList;
    }

    /**
     * Obtiene la lista de tareas en proceso.
     *
     * @return Lista de tareas en proceso.
     */
    public List<Tarea> getEnProcesoList() {
        return enProcesoList;
    }

    /**
     * Obtiene la lista de tareas terminadas.
     *
     * @return Lista de tareas terminadas.
     */
    public List<Tarea> getTerminadoList() {
        return terminadoList;
    }

    /**
     * Agrega una tarea a la lista de tareas por hacer.
     *
     * @param tarea Tarea a agregar.
     */
    public void agregarTarea(Tarea tarea) {
        porHacerList.add(tarea);
    }

    /**
     * Mueve una tarea de un estado a otro.
     *
     * @param tarea       Tarea a mover.
     * @param nuevoEstado Nuevo estado de la tarea.
     */
    public void moverTarea(Tarea tarea, EstadoTarea nuevoEstado) {
        switch (tarea.getEstado()) {
            case POR_HACER:
                porHacerList.remove(tarea);
                break;
            case EN_PROCESO:
                enProcesoList.remove(tarea);
                break;
            case TERMINADO:
                terminadoList.remove(tarea);
                break;
        }

        tarea.setEstado(nuevoEstado);

        switch (nuevoEstado) {
            case POR_HACER:
                porHacerList.add(tarea);
                break;
            case EN_PROCESO:
                enProcesoList.add(tarea);
                break;
            case TERMINADO:
                terminadoList.add(tarea);
                break;
        }
    }

    /**
     * Elimina una tarea del tablero.
     *
     * @param tarea Tarea a eliminar.
     */
    public void eliminarTarea(Tarea tarea) {
        switch (tarea.getEstado()) {
            case POR_HACER:
                porHacerList.remove(tarea);
                break;
            case EN_PROCESO:
                enProcesoList.remove(tarea);
                break;
            case TERMINADO:
                terminadoList.remove(tarea);
                break;
        }
    }

    /**
     * Obtiene la lista de tareas por un estado específico.
     *
     * @param estado Estado de las tareas a obtener.
     * @return Lista de tareas en el estado especificado.
     */
    public List<Tarea> getTareasPorEstado(EstadoTarea estado) {
        switch (estado) {
            case POR_HACER:
                return porHacerList;
            case EN_PROCESO:
                return enProcesoList;
            case TERMINADO:
                return terminadoList;
            default:
                return new ArrayList<>();
        }
    }

    /**
     * Obtiene la tarea con la descripción dada.
     *
     * @param descripcion Descripción de la tarea a buscar.
     * @return Tarea con la descripción dada, o null si no se encuentra.
     */
    public Tarea getTareaPorDescripcion(String descripcion) {
        for (Tarea tarea : porHacerList) {
            if (tarea.getDescripcion().equals(descripcion)) {
                return tarea;
            }
        }
        for (Tarea tarea : enProcesoList) {
            if (tarea.getDescripcion().equals(descripcion)) {
                return tarea;
            }
        }
        for (Tarea tarea : terminadoList) {
            if (tarea.getDescripcion().equals(descripcion)) {
                return tarea;
            }
        }
        return null;
    }
}