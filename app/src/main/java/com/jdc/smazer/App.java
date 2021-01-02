package com.jdc.smazer;

import com.jdc.smazer.control.ViewController;
import org.tinylog.Logger;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Esta es la clase principal la cual es llamada para iniciar el programa.
 * 
 * @author jhon fernandez
 * @version 1.0.0
 */
public class App {

    /**
     * Enlace del repositorio del proyecto.
     */
    public static final String PROJECT_REPOSITORY = "https://www.github.com/jhondev-code/smazer";

    /**
     * Enlace de mi sitio web.
     */
    public static final String WEB_SITE = "https://jhondev-code.github.io/";

    /**
     * Este es el metodo principal el cual inicia todo el programa.
     * 
     * @param args argumentos pasados por consola. Por ahora no se requieren
     *             argumentos para este programa.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();
                UIManager.setLookAndFeel(looks[looks.length - 1].getClassName());
                ViewController controller = new ViewController();
                ViewController.localInstance = controller;
                controller.start();
            } catch (Exception ex) {
                Logger.error(ex);
            }
        });
    }

}
