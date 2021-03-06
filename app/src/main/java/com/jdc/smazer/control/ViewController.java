package com.jdc.smazer.control;

import com.jdc.smazer.App;
import com.jdc.smazer.control.util.SmazerUtils;
import com.jdc.smazer.model.Task;
import com.jdc.smazer.model.db.TaskConnection;
import com.jdc.smazer.model.io.HomeManager;
import com.jdc.smazer.view.MainWindow;
import com.jdc.smazer.view.component.TaskTable;
import com.jdc.smazer.view.component.ToolMenuBar;
import com.jdc.smazer.view.window.AboutDialog;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.List;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import static javax.swing.JOptionPane.showConfirmDialog;
import static javax.swing.JOptionPane.showMessageDialog;

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
                this.mainWindow.getContentPane().add(this.taskTable, BorderLayout.CENTER);
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
            SmazerUtils.browse(App.PROJECT_REPOSITORY);
        });

        this.toolMenuBar.addGoWebSiteAction(e -> {
            SmazerUtils.browse(App.WEB_SITE);
        });

        this.toolMenuBar.addShowHelpAction(e -> {
            new AboutDialog(this.mainWindow).setVisible(true);
        });
    }

    /**
     * Este metodo se usa para traer las tareas de la base de datos y colocarlas en
     * la tabla.
     */
    private void loadTasks() {
        TaskConnection connection = new TaskConnection();
        List<Task> tasks = connection.getTasks();
        if (tasks != null && tasks.size() > 0) {
            tasks.forEach(this.taskTable::addTask);
        }
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
