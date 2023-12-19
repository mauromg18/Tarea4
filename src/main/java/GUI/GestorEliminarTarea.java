package GUI;
import TareaCuatro.TableroKanban;
import TareaCuatro.Tarea;
import TareaCuatro.EstadoTarea;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestorEliminarTarea implements ActionListener {
    private TableroKanban tableroKanban;
    private JPanel panel;
    private EstadoTarea estado;

    public GestorEliminarTarea(TableroKanban tableroKanban, JPanel panel, EstadoTarea estado) {
        this.tableroKanban = tableroKanban;
        this.panel = panel;
        this.estado = estado;
    }

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

    private void actualizarPanel() {
        panel.removeAll();
        for (Tarea tarea : tableroKanban.getTareasPorEstado(estado)) {
            JButton btnTarea = crearBotonTarea(tarea);
            panel.add(btnTarea);
        }
        panel.revalidate();
        panel.repaint();
    }

    private JButton crearBotonTarea(Tarea tarea) {
        JButton btnTarea = new JButton(tarea.getDescripcion());
        btnTarea.setTransferHandler(new TransferHandler("text"));
        btnTarea.addActionListener(new GestorEliminarTarea(tableroKanban, panel, estado)); // Nuevo
        return btnTarea;
    }
}