package com.jdc.smazer.model.io;

import java.io.File;
import java.io.IOException;
import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * Esta clase es usada para manipular el directorio donde se encuentran los
 * archivos con los cuales trabaja el programa.
 * 
 * @author jhon fernandez
 * @version 1.0.0
 */
public class HomeManager {

    /**
     * Lugar donde se creara el directorio de trabajo del programa.
     */
    private final String homePath;

    /**
     * Constructor vacio
     */
    public HomeManager() {
        this.homePath = System.getProperty("user.home");
    }

    /**
     * Este metodo se encarga de verificar que los archivos que usa el programa
     * esten disponibles.
     * 
     * @return {@code true} {si no hubieron errores verificando los archivos del
     *         programa.}
     */
    public boolean checkHome() {
        File homeDirectory = new File(this.getHomePath() + "/smazer");
        if (homeDirectory.exists()) {
            return true;
        } else {
            return this.createHome(homeDirectory);
        }
    }

    /**
     * Este metodo es el encargado de verificar que la base de datos se encuentre
     * disponible.
     * 
     * @return {@code true} {si no hubieron errores verificando la existencia de la
     *         base de datos.}
     */
    public boolean checkDataBase() {
        File dataBase = new File(this.getHomePath() + "/smazer/dates.db");
        if (dataBase.exists()) {
            return true;
        } else {
            return this.createDataBase(dataBase);
        }
    }

    /**
     * Si el directorio de trabajo no existiese, este metodo se encargara de
     * crearlo.
     * 
     * @return {@code true} {si no hubieron problemas creando el directorio.}
     */
    private boolean createHome(File d) {
        return AccessController.doPrivileged(new PrivilegedAction<Boolean>() {

            @Override
            public Boolean run() {
                return d.mkdir();
            }

        });
    }

    /**
     * Si la base de datos no se encuentra, este metodo se encargara de crearla e
     * inicializarlas.
     * 
     * @return {@code true} {si no hubieron problemas durante la creacion de la base
     *         de datos}
     */
    private boolean createDataBase(File b) {
        return AccessController.doPrivileged(new PrivilegedAction<Boolean>() {

            @Override
            public Boolean run() {
                try {
                    return b.createNewFile();
                } catch (IOException e) {
                    return false;
                }
            }

        });
    }

    /**
     * Este metodo devuelve el directorio donde se almacenaran los archivos de
     * trabajo del programa.
     * 
     * @return home path
     */
    public String getHomePath() {
        return this.homePath;
    }

}
