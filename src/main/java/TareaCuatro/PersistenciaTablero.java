package TareaCuatro;

import TareaCuatro.TableroKanban;

import java.io.*;

/**
 * Clase para gestionar la persistencia del tablero Kanban utilizando serialización.
 */
public class PersistenciaTablero {
    private static final String NOMBRE_ARCHIVO = "tablero.dat";

    /**
     * Guarda el estado actual del tablero Kanban en un archivo.
     *
     * @param tableroKanban El tablero Kanban a guardar.
     */
    public static void guardarTablero(TableroKanban tableroKanban) {
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(NOMBRE_ARCHIVO))) {
            salida.writeObject(tableroKanban);
            System.out.println("Tablero guardado con éxito.");
        } catch (IOException e) {
            System.err.println("Error al guardar el tablero: " + e.getMessage());
        }
    }

    /**
     * Carga el estado del tablero Kanban desde un archivo.
     *
     * @return El tablero Kanban cargado, o un nuevo tablero si no se puede cargar.
     */
    public static TableroKanban cargarTablero() {
        TableroKanban tableroKanban = null;
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(NOMBRE_ARCHIVO))) {
            tableroKanban = (TableroKanban) entrada.readObject();
            System.out.println("Tablero cargado con éxito.");
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo. Se creará un nuevo tablero.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar el tablero: " + e.getMessage());
        }

        return (tableroKanban != null) ? tableroKanban : new TableroKanban();
    }
}

