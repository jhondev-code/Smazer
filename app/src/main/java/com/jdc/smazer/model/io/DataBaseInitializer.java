package com.jdc.smazer.model.io;

import java.sql.Statement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jdc.smazer.model.db.DataBaseConnection;

/**
 * Esta clase es usada para poder crear la tabla inicial de la base de datos, es
 * decir inicializar la base de datos. Solamente debe de ser usada cuando la
 * base de datos no existe y se va a inicializar.
 * 
 * @author jhon fernandez
 * @version 1.0.0
 */
class DataBaseInitializer extends DataBaseConnection {

    /**
     * Logger usado para mostrar mensajes con los diferentes niveles.
     */
    private final Logger logger = Logger.getLogger(DataBaseInitializer.class.getName());

    /**
     * Constructor por defecto, no inicializa nada.
     */
    DataBaseInitializer() {
    }

    /**
     * Este metodo es usado para poder crear la tabla basica de tareas.
     */
    void initializeDateBase() {
        String sqlQuery = "CREATE TABLE tareas (nombre TEXT NOT NULL, "
                + "descripcion TEXT NOT NULL, inicio TEXT NOT NULL, fin TEXT NOT NULL)";
        try {
            if (this.open()) {
                Statement statement = null;
                try {
                    statement = this.getConnection().createStatement();
                    statement.executeUpdate(sqlQuery);
                } catch (SQLException e) {
                    this.logger.log(Level.SEVERE, e.getMessage());
                } finally {
                    if (statement != null) {
                        try {
                            statement.close();
                        } catch (SQLException e) {
                            this.logger.log(Level.SEVERE, e.getMessage());
                        }
                    }
                }
            } else {
                this.logger.log(Level.SEVERE, "No se puede abrir una conexion.");
            }
        } catch (SQLException ex) {
            this.logger.log(Level.SEVERE, ex.getMessage());
        } finally {
            if (this.isConnectionOpened()) {
                try {
                    this.close();
                } catch (SQLException e) {
                    this.logger.log(Level.SEVERE, e.getMessage());
                }
            }
        }
    }

}
