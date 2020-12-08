package com.jdc.smazer.control;

import com.jdc.smazer.model.io.HomeManager;
import com.jdc.smazer.view.MainWindow;
import com.jdc.smazer.view.component.TaskTable;
import com.jdc.smazer.view.component.ToolMenuBar;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static javax.swing.JOptionPane.*;

/**
 * Esta clase es la que enlaza todos los componentes dentro de la interfaz
 * grafica.
 * 
 * @author jhon fernandez
 * @version 1.0.0
 */
public class ViewController {

    /**
     * Se almacena en memoria la instancia del controlador principal para poder ser
     * usado desde otras clases sin necesidad de volver a instanciar al controlador.
     */
    public static ViewController localInstance = null;

    /**
     * Este es el manejador del icono de bandeja.
     */
    private final TrayIconController trayIconController;

    /**
     * Usado para realizar operaciones sobre el directorio de trabajo del programa.
     */
    private final HomeManager homeManager;

    /**
     * Ventana principal en donde se colocaran todos los componentes.
     */
    private final MainWindow mainWindow;

    /**
     * Barra de menu en la cual se encuentras los menus con sus operaciones
     * correspondientes.
     */
    private final ToolMenuBar toolMenuBar;

    /**
     * En esta tabla se mostraran todas las tareas que se tengan registradas.
     */
    private final TaskTable taskTable;

    /**
     * Este constructor es usado para poder iniciar a los componentes y
     * controladores del programa.
     */
    public ViewController() {
        this.homeManager = new HomeManager();
        this.mainWindow = new MainWindow();
        this.toolMenuBar = new ToolMenuBar();
        this.trayIconController = new TrayIconController(this.mainWindow);
        this.taskTable = new TaskTable();
    }

    /**
     * Esta instancia es usada para ejecutar las acciones cuando la ventana se
     * cierre.
     */
    private final WindowAdapter mainWindowAdapter = new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            super.windowClosing(e);
        }
    };

    /**
     * Este metodo se usa para poder iniciar el programa.
     */
    public void start() {
        if (this.homeManager.checkHome()) {
            if (this.homeManager.checkDataBase()) {
                this.mainWindow.setJMenuBar(this.toolMenuBar);
                this.initMenuBar();
                this.mainWindow.add(this.taskTable);
                this.mainWindow.addWindowListener(this.mainWindowAdapter);
                this.mainWindow.setVisible(true);
                this.loadTasks();
            } else {
                showMessageDialog(null, "Ocurrio un error inesperado i/o", "Error", ERROR_MESSAGE);
            }
        } else {
            showMessageDialog(null, "Ocurrio un error inesperado i/o", "Error", ERROR_MESSAGE);
        }
    }

    /**
     * Este metodo inicia las acciones de todos los botones.
     */
    private void initMenuBar() {
        this.toolMenuBar.addImportAction(e -> {
        });

        this.toolMenuBar.addExportAction(e -> {
        });

        this.toolMenuBar.addConfigurationAction(e -> {
        });

        this.toolMenuBar.addExitAction(e -> {
            int option = showConfirmDialog(this.mainWindow, "Esta seguro de salir?", "Confirme cierre", YES_NO_OPTION,
                    WARNING_MESSAGE);
            if (option == YES_NO_OPTION) {
                System.exit(0);
            }
        });

        this.toolMenuBar.addNewTaskAction(e -> {
        });

        this.toolMenuBar.addModifyAction(e -> {
        });

        this.toolMenuBar.addDeleteAction(e -> {
        });

        this.toolMenuBar.addViewRepositoryAction(e -> {
        });

        this.toolMenuBar.addGoWebSiteAction(e -> {
        });

        this.toolMenuBar.addShowHelpAction(e -> {
        });
    }

    /**
     * Este metodo se usa para traer las tareas de la base de datos y colocarlas en
     * la tabla.
     */
    private void loadTasks() {
        //
    }

    public TrayIconController getTrayIconController() {
        return this.trayIconController;
    }

    public HomeManager getHomeManager() {
        return this.homeManager;
    }

    public MainWindow getMainWindow() {
        return this.mainWindow;
    }

    public ToolMenuBar getToolMenuBar() {
        return this.toolMenuBar;
    }

    public TaskTable getTaskTable() {
        return this.taskTable;
    }

}
