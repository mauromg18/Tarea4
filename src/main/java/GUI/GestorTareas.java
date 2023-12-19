package GUI;
import TareaCuatro.TableroKanban;
import TareaCuatro.Tarea;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;

/**
 * Clase que representa un gestor de tareas en un tablero Kanban.
 */
public class GestorTareas extends JFrame {
    private TableroKanban tableroKanban;
    private JTextField txtNuevaTarea;
    private JButton btnAgregarTarea;

    /**
     * Constructor de la clase GestorTareas.
     *
     * @param tableroKanban Tablero Kanban asociado al gestor.
     */
    public GestorTareas(TableroKanban tableroKanban) {
        this.tableroKanban = tableroKanban;

        // Configuración de la interfaz gráfica
        txtNuevaTarea = new JTextField(20);
        btnAgregarTarea = new JButton("Agregar Tarea");

        btnAgregarTarea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String descripcion = txtNuevaTarea.getText();
                if (!descripcion.isEmpty()) {
                    Tarea nuevaTarea = new Tarea(descripcion);
                    tableroKanban.agregarTarea(nuevaTarea);

                    // Actualizar la interfaz gráfica o mostrar un mensaje de éxito
                    JOptionPane.showMessageDialog(GestorTareas.this, "Tarea agregada correctamente.");

                    // Puedes también actualizar la interfaz gráfica para reflejar el cambio en el tablero
                    // por ejemplo, volver a cargar las listas en tu interfaz gráfica
                    // ...

                    // Limpia el cuadro de texto después de agregar la tarea
                    txtNuevaTarea.setText("");
                } else {
                    JOptionPane.showMessageDialog(GestorTareas.this, "Ingrese una descripción para la tarea.");
                }
            }
        });

        setLayout(new FlowLayout());
        add(txtNuevaTarea);
        add(btnAgregarTarea);
    }
}
