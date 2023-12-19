package GUI;

import TareaCuatro.TableroKanban;
import TareaCuatro.Tarea;
import TareaCuatro.EstadoTarea;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que implementa un gestor para eliminar tareas en un tablero Kanban.
 */
public class GestorEliminarTarea implements ActionListener {
    private TableroKanban tableroKanban;
    private JPanel panel;
    private EstadoTarea estado;

    /**
     * Constructor de la clase GestorEliminarTarea.
     *
     * @param tableroKanban Tablero Kanban asociado al gestor.
     * @param panel        Panel en el que se mostrarán las tareas.
     * @param estado       Estado de las tareas que se gestionarán.
     */
    public GestorEliminarTarea(TableroKanban tableroKanban, JPanel panel, EstadoTarea estado) {
        this.tableroKanban = tableroKanban;
        this.panel = panel;
        this.estado = estado;
    }

    /**
     * Método que se ejecuta cuando se realiza la acción de eliminar una tarea.
     *
     * @param e Evento de acción.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton botonTarea = (JButton) e.getSource();
        String descripcionTarea = botonTarea.getText();

        Tarea tarea = tableroKanban.getTareaPorDescripcion(descripcionTarea);
        if (tarea != null) {
            tableroKanban.eliminarTarea(tarea);
            actualizarPanel();
        }
    }

    /**
     * Actualiza el panel después de eliminar una tarea.
     */
    private void actualizarPanel() {
        panel.removeAll();
        for (Tarea tarea : tableroKanban.getTareasPorEstado(estado)) {
            JButton btnTarea = crearBotonTarea(tarea);
            panel.add(btnTarea);
        }
        panel.revalidate();
        panel.repaint();
    }

    /**
     * Crea un botón para representar una tarea.
     *
     * @param tarea Tarea a representar en el botón.
     * @return Botón de tarea creado.
     */
    private JButton crearBotonTarea(Tarea tarea) {
        JButton btnTarea = new JButton(tarea.getDescripcion());
        btnTarea.setTransferHandler(new TransferHandler("text"));
        btnTarea.addActionListener(new GestorEliminarTarea(tableroKanban, panel, estado)); // Nuevo
        return btnTarea;
    }
}
