package TareaCuatro;

package com.tuempresa.kanban.logica;

import java.io.Serializable;

/**
 * Clase que representa una tarea en un tablero Kanban.
 */
public class Tarea implements Serializable {
    private static final long serialVersionUID = 1L;

    private String descripcion;
    private EstadoTarea estado;

    /**
     * Constructor de la clase Tarea.
     *
     * @param descripcion Descripción de la tarea.
     */
    public Tarea(String descripcion) {
        this.descripcion = descripcion;
        this.estado = EstadoTarea.POR_HACER;
    }

    /**
     * Obtiene la descripción de la tarea.
     *
     * @return Descripción de la tarea.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Obtiene el estado actual de la tarea.
     *
     * @return Estado de la tarea.
     */
    public EstadoTarea getEstado() {
        return stado;
    }

    /**
     * Establece el estado de la tarea.
     *
     * @param estado Nuevo estado de la tarea.
     */
    public void setEstado(EstadoTarea estado) {
        this.estado = estado;
    }
}

