package com.jdc.smazer.control;

import static javax.swing.JOptionPane.WARNING_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import static javax.swing.JOptionPane.showConfirmDialog;

import java.awt.AWTException;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;

import com.jdc.smazer.view.component.TrayIconItems;

/**
 * Esta clase es usada para poder manipular el icono de la bandeja, con dicho
 * icono se podra mantener al programa ejecutandose en segundo plano.
 * 
 * @author jhon fernandez
 * @version 1.0.0
 */
public class TrayIconController {

    /**
     * Este es el popup el cual se usa para colocar los menu items.
     */
    private final TrayIconItems popupMenu;

    /**
     * Instancia de la ventana padre.
     */
    private final JFrame owner;

    /**
     * Icono de bandeja usado para mantener el programa en segundo plano.
     */
    private final TrayIcon trayIcon;

    /**
     * Este logger es usado para mostrar mensajes de depuracion en la consola.
     */
    private final Logger logger = Logger.getLogger(TrayIconController.class.getName());

    /**
     * Este constructor inicia solicitando la instancia de la ventana que lo invoca
     * e iniciando la lista en la cual se almacenaran los botones de opciones.
     * 
     * @param owner ventana padre la cual lo invoca
     */
    public TrayIconController(JFrame owner) {
        this.owner = owner;
        this.popupMenu = new TrayIconItems();
        this.popupMenu.addRestartWindowAction(e -> this.restartWindow());
        this.popupMenu.addExitMenuAction(e -> this.exitProgram());

        this.trayIcon = new TrayIcon(
                Toolkit.getDefaultToolkit().createImage(TrayIconController.class.getResource("/icon/tray-icon.png")),
                "Smazer", this.popupMenu);

        try {
            SystemTray.getSystemTray().add(this.trayIcon);
        } catch (AWTException e) {
            this.logger.log(Level.SEVERE, e.getMessage());
        }
    }

    /**
     * Este metodo es usado para mostrar un mensaje nativo de informacion.
     * 
     * @param message el mensaje que se quiere mostrar.
     */
    public void showInformationMessage(String message) {
        this.trayIcon.displayMessage("Smazer", message, TrayIcon.MessageType.INFO);
    }

    /**
     * Este metodo es usado para mostrar un mensaje nativo de advertencia.
     * 
     * @param message el mensaje que se quiere mostrar.
     */
    public void showWarningMessage(String message) {
        this.trayIcon.displayMessage("Smazer", message, TrayIcon.MessageType.WARNING);
    }

    /**
     * Este metodo es usado para mostrar un mensaje nativo de error.
     * 
     * @param message el mensaje que se quiere mostrar.
     */
    public void showErrorMessage(String message) {
        this.trayIcon.displayMessage("Smazer", message, TrayIcon.MessageType.ERROR);
    }

    /**
     * Este metodo es usado para mostrar un mensaje nativo anonimo.
     * 
     * @param message el mensaje que se quiere mostrar.
     */
    public void showAnonymousMessage(String message) {
        this.trayIcon.displayMessage("Smazer", message, TrayIcon.MessageType.NONE);
    }

    /**
     * Este metodo es usado para restaurar la ventana si esta se encuentra oculta.
     */
    public void restartWindow() {
        if (!this.owner.isVisible()) {
            this.owner.setVisible(true);
        }
    }

    /**
     * Este metodo es usado para poder ocultar la ventana padre.
     */
    public void exitProgram() {
        this.restartWindow();
        int option = showConfirmDialog(this.owner, "Estas seguro que quieres salir?", "Solicitud de confirmacion",
                YES_NO_OPTION, WARNING_MESSAGE);
        if (option == YES_NO_OPTION) {
            System.exit(0);
        }
    }

    /**
     * Este metodo retorna el panel de menus.
     * 
     * @return tray icon items popup menu.
     */
    public TrayIconItems getPopupMenu() {
        return this.popupMenu;
    }

    /**
     * @return {@code true} {si es que el sistema operativo admite iconos de
     *         bandeja}
     */
    public static boolean systemSupportTrayIcon() {
        return SystemTray.isSupported();
    }

}
