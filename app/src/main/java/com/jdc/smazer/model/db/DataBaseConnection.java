package com.jdc.smazer.model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.sqlite.JDBC;

/**
 * Esta clase es usada para poder manejar conexiones con la base de datos.
 * 
 * @author jhon fernandez
 * @version 1.0.0
 */
public abstract class DataBaseConnection {

    /**
     * Este logger es usado para mostrar los mensajes en la consola.
     */
    private Logger logger = Logger.getLogger(DataBaseConnection.class.getName());

    /**
     * Usado para poder crear Resulsets, Statements, etc.
     */
    private Connection dataBaseConnection;

    /**
     * Estado del driver de la base de datos.
     */
    private boolean driverState;

    /**
     * Constructor por defecto el cual inicia cargando el driver de la base de
     * datos.
     */
    public DataBaseConnection() {
        if (this.loadDriver()) {
            this.logger.log(Level.INFO, "El driver se cargo correctamente");
        } else {
            this.logger.log(Level.SEVERE, "No se pudo cargar el driver");
            // si no se puede cargar el driver se procede a finalizar al programa
            System.exit(1);
        }
    }

    /**
     * Este metodo se encarga de realizar la carga del driver de la base de datos.
     * 
     * @return true si el driver se cargo correctamente.
     */
    public boolean loadDriver() {
        try {
            Class.forName("org.sqlite.JDBC");
            this.driverState = true;
        } catch (ClassNotFoundException cnf) {
            this.driverState = false;
        }

        return this.driverState;
    }

    /**
     * Usado para verificar si el driver de la base de datos se cargo correctamente.
     **/
    public boolean isDriverLoaded() {
        return this.driverState;
    }

    /**
     * Este metodo es usado para poder abrir una conexion hacia la base de datos.
     * 
     * @return true si la conexion se abrio correctamante.
     * @throws SQLException se da si es que no se puede abrir conexion hacia la base
     *                      de datos o si es que la base de datos no existe.
     */
    public boolean open() throws SQLException {
        if (this.isDriverLoaded()) {
            this.dataBaseConnection = DriverManager.getConnection(JDBC.PREFIX + System.getProperty("user.home") + "/smazer/dates.db", "root", "");
        }

        return this.dataBaseConnection != null;
    }

    /**
     * Este metodo es usado para cerrar la conexion con la base de datos.
     * 
     * @return true si no hubo problemas cerrando la conexion con la base de datos.
     * @throws SQLException se da cuando no se pudo cerrar la conexion con la base
     *                      de datos. Las posibles causas son:
     *                      - la base de datos fue borrada
     *                      - la base de datos fue corrompida
     *                      - el driver fue movido o eliminado
     */
    public boolean close() throws SQLException {
        if (this.isConnectionOpened()) {
            this.dataBaseConnection.close();
            this.dataBaseConnection = null;
        }

        return this.dataBaseConnection == null;
    }

    /**
     * Este metodo es usado para poder verificar si la conexion con la base de datos
     * aun sigue abierta.
     * 
     * @return true si la conexion aun esta abierta.
     */
    public boolean isConnectionOpened() {
        return this.dataBaseConnection != null;
    }

    /**
     * Este metodo es usado para verificar si la conexion con la base de datos esta
     * cerrada.
     * 
     * @return true si la conexion esta cerrada.
     **/
    public boolean isConnectionClosed() {
        return this.dataBaseConnection == null;
    }

    /**
     * @return devuelve el objeto connection usado para crear Statements, Resulsets,
     *         etc.
     **/
    protected Connection getConnection() {
        return this.dataBaseConnection;
    }

}
