package com.jdc.smazer.view.component;

import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionListener;

/**
 * Esta clase se usa para poder colocar los menu items.
 * 
 * Solamente se usa junto al icono de bandeja.
 *
 * @author jhon fernandez
 * @version 1.0.0
 */
public class TrayIconItems extends PopupMenu {

    /**
     * Este menu item es usado para poder restaurar la ventana principal si es que
     * se encuentra en segundo plano.
     */
    private MenuItem restartWindow;

    /**
     * Este menu item es usado para poder finalizar el programa.
     */
    private MenuItem exitMenuItem;

    /**
     * Este constructor inicia llamando al metodo que inicia los menu items.
     */
    public TrayIconItems() {
        this.initButtons();
    }

    /**
     * Metodo interno el cual se usa para poder iniciar los menu items.
     */
    private void initButtons() {
        this.restartWindow = new MenuItem("Restaurar ventana");
        this.add(this.restartWindow);

        this.addSeparator();

        this.exitMenuItem = new MenuItem("Salir");
        this.add(this.exitMenuItem);
    }

    /**
     * Establece la accion del boton de restauracion.
     * 
     * @param action button action.
     */
    public void addRestartWindowAction(ActionListener action) {
        if (action != null) {
            this.restartWindow.addActionListener(action);
        }
    }

    /**
     * Establece la accion del boton de cerrado.
     * 
     * @param action button action.
     */
    public void addExitMenuAction(ActionListener action) {
        if (action != null) {
            this.exitMenuItem.addActionListener(action);
        }
    }

}
