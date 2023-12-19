package GUI;
import TareaCuatro.EstadoTarea;
import TareaCuatro.TableroKanban;

import javax.swing.*;
import java.awt.*;

public class PanelEstadisticas extends JPanel {
    private TableroKanban tableroKanban;
    private JLabel lblPorHacer, lblEnProceso, lblTerminado;

    public PanelEstadisticas(TableroKanban tableroKanban) {
        this.tableroKanban = tableroKanban;

        lblPorHacer = new JLabel("Por Hacer: 0");
        lblEnProceso = new JLabel("En Proceso: 0");
        lblTerminado = new JLabel("Terminado: 0");

        actualizarEstadisticas();

        setLayout(new GridLayout(3, 1));
        add(lblPorHacer);
        add(lblEnProceso);
        add(lblTerminado);
    }

    public void actualizarEstadisticas() {
        lblPorHacer.setText("Por Hacer: " + tableroKanban.getTareasPorEstado(EstadoTarea.POR_HACER).size());
        lblEnProceso.setText("En Proceso: " + tableroKanban.getTareasPorEstado(EstadoTarea.EN_PROCESO).size());
        lblTerminado.setText("Terminado: " + tableroKanban.getTareasPorEstado(EstadoTarea.TERMINADO).size());
    }
}