package GUI;
import TareaCuatro.TableroKanban;
import TareaCuatro.Tarea;
import TareaCuatro.EstadoTarea;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

/**
 * Clase que representa la interfaz gráfica para un tablero Kanban.
 */
public class InterfazKanban extends JFrame {
    private TableroKanban tableroKanban;

    /**
     * Constructor de la clase InterfazKanban.
     */
    public InterfazKanban() {
        tableroKanban = cargarTableroKanban();

        // Configuración de la interfaz gráfica aquí

        // Ejemplo de cómo crear una tarea
        Tarea tarea = new Tarea("Tarea de ejemplo");
        tableroKanban.agregarTarea(tarea);

        // Ejemplo de cómo mover una tarea
        tableroKanban.moverTarea(tarea, EstadoTarea.EN_PROCESO);
    }

    private TableroKanban cargarTableroKanban() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("kanban.ser"))) {
            return (TableroKanban) ois.readObject();
        } catch (FileNotFoundException e) {
            return new TableroKanban(); // Si no existe el archivo, devolvemos un nuevo tablero vacío
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new TableroKanban(); // En caso de error, devolvemos un nuevo tablero vacío
        }
    }

    private void guardarTableroKanban() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("kanban.ser"))) {
            oos.writeObject(tableroKanban);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método principal para ejecutar la aplicación
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InterfazKanban interfazKanban = new InterfazKanban();
            interfazKanban.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            interfazKanban.setSize(800, 600);
            interfazKanban.setVisible(true);
        });
    }
}

