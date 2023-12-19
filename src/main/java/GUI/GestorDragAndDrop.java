package GUI;

import TareaCuatro.TableroKanban;
import TareaCuatro.Tarea;
import TareaCuatro.EstadoTarea;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestorDragAndDrop extends JFrame {
    private TableroKanban tableroKanban;
    private JPanel panelPorHacer, panelEnProceso, panelTerminado;

    public GestorDragAndDrop() {
        tableroKanban = cargarTableroKanban();

        // Configuración de la interfaz gráfica aquí
        panelPorHacer = new JPanel();
        panelEnProceso = new JPanel();
        panelTeminado = new JPanel();

        // Configuración de los paneles y el diseño
        configurarPanel(panelPorHacer, "Por Hacer", EstadoTarea.POR_HACER);
        configurarPanel(panelEnProceso, "En Proceso", EstadoTarea.EN_PROCESO);
        configurarPanel(panelTerminado, "Terminado", EstadoTarea.TERMINADO);

        // Configuración del drag and drop
        configurarDragAndDrop(panelPorHacer, EstadoTarea.POR_HACER);
        configurarDragAndDrop(panelEnProceso, EstadoTarea.EN_PROCESO);
        configurarDragAndDrop(panelTerminado, EstadoTarea.TERMINADO);

        setLayout(new GridLayout(1, 3));
        add(panelPorHacer);
        add(panelEnProceso);
        add(panelTerminado);
    }

    private void configurarPanel(JPanel panel, String titulo, EstadoTarea estado) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder(titulo));
        actualizarPanel(panel, estado);
    }

    private void actualizarPanel(JPanel panel, EstadoTarea estado) {
        panel.removeAll();
        for (Tarea tarea : tableroKanban.getTareasPorEstado(estado)) {
            JButton btnTarea = new JButton(tarea.getDescripcion());
            panel.add(btnTarea);
        }
        panel.revalidate();
        panel.repaint();
    }

    private void configurarDragAndDrop(JPanel panel, EstadoTarea estado) {
        panel.setTransferHandler(new TransferHandler("text"));

        panel.addDragGestureListener(new DragGestureListener() {
            @Override
            public void dragGestureRecognized(DragGestureEvent dge) {
                JComponent c = (JComponent) dge.getComponent();
                TransferHandler handler = c.getTransferHandler();
                handler.exportAsDrag(c, dge.getTriggerEvent(), TransferHandler.COPY);
            }
        });

        panel.setDropTarget(new DropTarget() {
            @Override
            public synchronized void drop(DropTargetDropEvent dtde) {
                try {
                    Transferable transferable = dtde.getTransferable();
                    DataFlavor[] flavors = transferable.getTransferDataFlavors();

                    for (DataFlavor flavor : flavors) {
                        if (flavor.isFlavorTextType()) {
                            dtde.acceptDrop(DnDConstants.ACTION_COPY);
                            String data = (String) transferable.getTransferData(flavor);
                            Tarea tarea = tableroKanban.getTareaPorDescripcion(data);

                            if (tarea != null) {
                                tableroKanban.moverTarea(tarea, estado);
                                actualizarPanel(panel, estado);
                            }

                            dtde.dropComplete(true);
                            return;
                        }
                    }

                    dtde.rejectDrop();
                } catch (Exception e) {
                    e.printStackTrace();
                    dtde.rejectDrop();
                }
            }
        });
    }

    private TableroKanban cargarTableroKanban() {
        // Implementa la lógica para cargar el tablero desde algún lugar (puede ser un archivo, base de datos, etc.)
        return new TableroKanban();
    }

    private void guardarTableroKanban() {
        // Implementa la lógica para guardar el tablero en algún lugar (puede ser un archivo, base de datos, etc.)
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GestorDragAndDrop gestorDragAndDrop = new GestorDragAndDrop();
            gestorDragAndDrop.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gestorDragAndDrop.setSize(800, 600);
            gestorDragAndDrop.setVisible(true);
        });
    }
}
