package com.jdc.smazer.view.window;

import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 * Esta clase es usada para mostrar informacion acerca del programa y el entorno
 * en donde se esta ejecutando.
 * 
 * @author jhon fernandez
 * @version 1.0.0
 */
public class AboutDialog extends JDialog {

    /**
     * Este constructor inicia la ventana de informacion con un titulo con el nombre
     * del programa como tambien solicita la instancia de la ventana que la llama
     * para que cuando se muestre el dialogo se bloquee la ventana padre.
     * 
     * @param owner ventana padre la cual sera bloqueada cuando el cuadro de dialogo
     *              se muestre.
     */
    public AboutDialog(JFrame owner) {
        super(owner, "Acerca de Smazer", true);
    }

}
