package TareaCuatro;
import GUI.GestorTareas;
import GUI.GestorDragAndDrop;
import TareaCuatro.PersistenciaTablero;
import TareaCuatro.TableroKanban;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        // Cargar el tablero al inicio
        TableroKanban tableroKanban = PersistenciaTablero.cargarTablero();

        // Crear la interfaz gráfica para agregar tareas
        SwingUtilities.invokeLater(() -> {
            GestorTareas gestorTareas = new GestorTareas(tableroKanban);
            gestorTareas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gestorTareas.setSize(400, 100);
            gestorTareas.setVisible(true);
        });

        // Crear la interfaz gráfica principal con el tablero como argumento
        SwingUtilities.invokeLater(() -> {
            GestorDragAndDrop gestorDragAndDrop = new GestorDragAndDrop(tableroKanban);
            gestorDragAndDrop.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gestorDragAndDrop.setSize(800, 600);
            gestorDragAndDrop.setVisible(true);
        });

        // Guardar el tablero al cerrar la aplicación
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            PersistenciaTablero.guardarTablero(tableroKanban);
        }));
    }
}