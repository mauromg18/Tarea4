package GUI;

import TareaCuatro.TableroKanban;
import TareaCuatro.Tarea;
import TareaCuatro.EstadoTarea;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GestorDragAndDrop extends JFrame {
    private TableroKanban tableroKanban;
    private JPanel panelPorHacer, panelEnProceso, panelTerminado;
    private PanelEstadisticas panelEstadisticas;

    public GestorDragAndDrop(TableroKanban tableroKanban) {
        this.tableroKanban = tableroKanban;

        // Configuración de la interfaz gráfica aquí
        panelPorHacer = new JPanel();
        panelEnProceso = new JPanel();
        panelTerminado = new JPanel();
        panelEstadisticas = new PanelEstadisticas(tableroKanban);

        // Configuración de los paneles y el diseño
        configurarPanel(panelPorHacer, "Por Hacer", EstadoTarea.POR_HACER);
        configurarPanel(panelEnProceso, "En Proceso", EstadoTarea.EN_PROCESO);
        configurarPanel(panelTerminado, "Terminado", EstadoTarea.TERMINADO);

        // Configuración del drag and drop
        configurarDragAndDrop(panelPorHacer, EstadoTarea.POR_HACER);
        configurarDragAndDrop(panelEnProceso, EstadoTarea.EN_PROCESO);
        configurarDragAndDrop(panelTerminado, EstadoTarea.TERMINADO);

        setLayout(new GridLayout(2, 3));
        add(panelPorHacer);
        add(panelEnProceso);
        add(panelTerminado);
        add(panelEstadisticas);
    }

    private void configurarPanel(JPanel panel, String titulo, EstadoTarea estado) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder(titulo));
        actualizarPanel(panel, estado);
    }

    private void actualizarPanel(JPanel panel, EstadoTarea estado) {
        panel.removeAll();
        for (Tarea tarea : tableroKanban.getTareasPorEstado(estado)) {
            JButton btnTarea = crearBotonTarea(tarea, panel, estado);
            panel.add(btnTarea);
        }
        panelEstadisticas.actualizarEstadisticas();
        panel.revalidate();
        panel.repaint();
    }

    private JButton crearBotonTarea(Tarea tarea, JPanel panel, EstadoTarea estado) {
        JButton btnTarea = new JButton(tarea.getDescripcion());
        btnTarea.setTransferHandler(new TransferHandler("text"));

        // Configurar el GestorEliminarTarea para el botón actual
        btnTarea.addActionListener(new GestorEliminarTarea(tableroKanban, panel, estado));

        btnTarea.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JComponent c = (JComponent) e.getSource();
                TransferHandler handler = c.getTransferHandler();
                handler.exportAsDrag(c, e, TransferHandler.COPY);
            }
        });

        return btnTarea;
    }

    private void configurarDragAndDrop(JPanel panel, EstadoTarea estado) {
        panel.setTransferHandler(new TransferHandler("text"));
    }
}
