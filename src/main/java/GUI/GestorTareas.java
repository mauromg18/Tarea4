package GUI;
import TareaCuatro.TableroKanban;
import TareaCuatro.Tarea;
import TareaCuatro.EstadoTarea;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestorTareas extends JFrame {
    private TableroKanban tableroKanban;
    private JTextField txtNuevaTarea;
    private JButton btnAgregarTarea;

    public GestorTareas() {
        tableroKanban = cargarTableroKanban();

        // Configuración de la interfaz gráfica aquí
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

    private TableroKanban cargarTableroKanban() {
        // Implementa la lógica para cargar el tablero desde algún lugar (puede ser un archivo, base de datos, etc.)
        return new TableroKanban();
    }

    private void guardarTableroKanban() {
        // Implementa la lógica para guardar el tablero en algún lugar (puede ser un archivo, base de datos, etc.)
    }

}