package com.jdc.smazer.view.component;

import javax.swing.AbstractButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;

/**
 * Esta clase es usada para poder colocar los Items en la barra de menu.
 * 
 * @author jhon fernandez
 * @version 1.0.0
 */
public class ToolMenuBar extends JMenuBar {

    /**
     * Este menu contiene operaciones para manipular aspectos funcionales del
     * programa.
     */
    private JMenu fileMenu;

    /**
     * Estos menu item son usados para manipular operaciones del software.
     */
    private JMenuItem importItem;
    private JMenuItem exportItem;
    private JMenuItem configItem;
    private JMenuItem exitItem;

    /**
     * Este menu contiene operaciones para gestionar las tareas.
     */
    private JMenu taskMenu;

    /**
     * Estos menu son usados para poder realizar operaciones con las tareas.
     */
    private JMenuItem addTask;
    private JMenuItem modifyTask;
    private JMenuItem deleteTask;

    /**
     * Este menu contiene operaciones para mostrar ayuda al usuario.
     */
    private JMenu helpMenu;

    /**
     * Estos menu son usados para poder mostrar ayuda al usuario.
     */
    private JMenuItem viewOnGithub;
    private JMenuItem goToWebSite;
    private JMenuItem showHelp;

    /**
     * Este constructor es usado para poder iniciar las caracteristicas basicas del
     * la barra de menu.
     */
    public ToolMenuBar() {
        super();
        this.initFileMenuBar();
        this.initTaskMenuBar();
        this.initHelpMenuBar();
    }

    /**
     * Este metodo se encarga de iniciar los componentes del menu Archivo.
     */
    private void initFileMenuBar() {
        this.fileMenu = new JMenu("Archivo");
        this.add(this.fileMenu);
        {
            this.importItem = new JMenuItem("Importar");
            this.fileMenu.add(this.importItem);
        }
        {
            this.exportItem = new JMenuItem("Exportar");
            this.fileMenu.add(this.exportItem);
        }
        {
            this.configItem = new JMenuItem("Configuracion");
            this.fileMenu.add(this.configItem);
        }

        this.fileMenu.addSeparator();

        {
            this.exitItem = new JMenuItem("Salir");
            this.fileMenu.add(this.exitItem);
        }
    }

    /**
     * Este metodo se encarga de iniciar los componentes del menu Tarea.
     */
    private void initTaskMenuBar() {
        this.taskMenu = new JMenu("Tarea");
        this.add(this.taskMenu);
        {
            this.addTask = new JMenuItem("Agregar tarea");
            this.taskMenu.add(this.addTask);
        }
        {
            this.modifyTask = new JMenuItem("Modificar tarea seleccionada");
            this.taskMenu.add(this.modifyTask);
        }
        {
            this.deleteTask = new JMenuItem("Eliminar tarea seleccionada");
            this.taskMenu.add(this.deleteTask);
        }
    }

    /**
     * Este metodo se encarga de iniciar los componentes del menu Ayuda.
     */
    private void initHelpMenuBar() {
        this.helpMenu = new JMenu("Ayuda");
        this.add(this.helpMenu);
        {
            this.viewOnGithub = new JMenuItem("Ver el repositorio");
            this.helpMenu.add(this.viewOnGithub);
        }
        {
            this.goToWebSite = new JMenuItem("Ir al sitio web");
            this.helpMenu.add(this.goToWebSite);
        }
        {
            this.showHelp = new JMenuItem("Acerca de Smazer");
            this.helpMenu.add(this.showHelp);
        }
    }

    private void addEventInComponent(ActionListener action, AbstractButton button) {
        if (action != null && button != null) {
           button.addActionListener(action);
        }
    }

    /**
     * Agrega el evento al boton de importar.
     * 
     * @param action import action.
     */
    public void addImportAction(ActionListener action) {
        this.addEventInComponent(action, this.importItem);
    }

    /**
     * Agrega el evento al boton de exportar.
     * 
     * @param action export action.
     */
    public void addExportAction(ActionListener action) {
        this.addEventInComponent(action, this.exportItem);
    }

    /**
     * Agrega el evento al boton de configuracion.
     * 
     * @param action configuration action.
     */
    public void addConfigurationAction(ActionListener action) {
        this.addEventInComponent(action, this.configItem);
    }

    /**
     * Agrega el evento al boton de salir.
     * 
     * @param action exit action.
     */
    public void addExitAction(ActionListener action) {
        this.addEventInComponent(action, this.exitItem);
    }

    /**
     * Agrega el evento al boton de agregar tarea.
     * 
     * @param action new task action.
     */
    public void addNewTaskAction(ActionListener action) {
        this.addEventInComponent(action, this.addTask);
    }

    /**
     * Agrega el evento al boton de modificar tarea.
     * 
     * @param action modify action.
     */
    public void addModifyAction(ActionListener action) {
        this.addEventInComponent(action, this.modifyTask);
    }

    /**
     * Agrega el evento al boton de eliminar tarea.
     * 
     * @param action detele action.
     */
    public void addDeleteAction(ActionListener action) {
        this.addEventInComponent(action, this.deleteTask);
    }

    /**
     * Agrega el evento al boton de ir al repositorio.
     * 
     * @param action view repository action.
     */
    public void addViewRepositoryAction(ActionListener action) {
        this.addEventInComponent(action, this.viewOnGithub);
    }

    /**
     * Agrega el evento al boton de ir al sitio web.
     * 
     * @param action go web site action.
     */
    public void addGoWebSiteAction(ActionListener action) {
        this.addEventInComponent(action, this.goToWebSite);
    }

    /**
     * Agrega el evento al boton de acerca del software.
     * 
     * @param action view info action.
     */
    public void addShowHelpAction(ActionListener action) {
        this.addEventInComponent(action, this.showHelp);
    }

}
