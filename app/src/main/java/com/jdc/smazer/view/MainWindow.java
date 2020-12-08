package com.jdc.smazer.view;

import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * Esta clase es usada para crear la ventana principal del programa. Aqui se
 * encuentran todos los componentes visuales de la interfaz grafica.
 * 
 * @author jhon fernandez
 * @version 1.0.0
 */
public class MainWindow extends JFrame {

    /**
     * Constructor por defecto el cual crea la ventana principal con un titulo y un
     * icono.
     */
    public MainWindow() {
        super("Smazer");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/image/icon.png")));
        this.configureWindow();
    }

    /**
     * Este metodo se usa solamente para establecer la configuracion de la ventana.
     */
    private void configureWindow() {
        this.setSize(800, 500);
        this.setLocationRelativeTo(null);
    }

}
