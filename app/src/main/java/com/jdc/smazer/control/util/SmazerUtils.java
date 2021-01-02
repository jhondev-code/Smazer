package com.jdc.smazer.control.util;

import java.awt.Desktop;

import java.net.URI;

import org.tinylog.Logger;

/**
 * Esta clase contiene diversas utilidades para funciones especificas.
 *
 * @author jhon fernandez
 * @version 1.0.0
 */
public class SmazerUtils {

    /**
     * Este metodo es usado para poder abrir una url en el navegador por defecto.
     */
    public static void browse(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch(Exception ex) {
            Logger.error(ex);
        }
    }

}
