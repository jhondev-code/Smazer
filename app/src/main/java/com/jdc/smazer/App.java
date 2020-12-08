package com.jdc.smazer;

import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

import com.jdc.smazer.control.ViewController;

/**
 * Esta es la clase principal la cual es llamada para iniciar el programa.
 * 
 * @author jhon fernandez
 * @version 1.0.0
 */
public class App {

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
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, ex.getMessage());
            }
        });
    }

}
