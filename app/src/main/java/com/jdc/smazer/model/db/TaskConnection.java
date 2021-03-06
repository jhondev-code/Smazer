package com.jdc.smazer.model.db;

import java.util.List;
import java.util.LinkedList;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import org.tinylog.Logger;

import com.jdc.smazer.model.Task;

/**
 * Esta clase permite realizar operaciones sobre la base de datos con los
 * objetos Task
 * 
 * @author jhon fernandez
 * @version 1.0.0
 */
public class TaskConnection extends DataBaseConnection {

    /**
     * Constructor por defecto el cual invoca al constructor de la clase padre con
     * lo cual se procedera a cargar el driver de la base de datos.
     */
    public TaskConnection() {
        super();
    }

    /**
     * Este metodo permite agregar los datos de una tarea en la base de datos.
     * 
     * @param task tarea que se quiere agregar a la base de datos.
     * @return {@code true} {si no hubieron problemas agregando la tarea.}
     */
    public boolean registerTask(Task task) {
        String sqlQuery = "INSERT INTO tareas values (?, ?, ?, ?)";

        try {
            if (this.open()) {
                PreparedStatement statement = null;
                try {
                    statement = this.getConnection().prepareStatement(sqlQuery);
                    statement.setString(1, task.getTaskName());
                    statement.setString(2, task.getTaskDescription());
                    statement.setString(3, task.getTaskStartTime().getHour() + ":" + task.getTaskStartTime().getMinute()
                            + ":" + task.getTaskStartTime().getSecond());
                    statement.setString(4, task.getTaskEndTime().getHour() + ":" + task.getTaskEndTime().getMinute()
                            + ":" + task.getTaskEndTime().getSecond());
                    try {
                        statement.executeUpdate();
                        return true;
                    } catch (SQLException ex) {
                        Logger.error(ex);
                    }
                } catch (SQLException ex) {
                    Logger.error(ex);
                } finally {
                    this.closeStatement(statement);
                }
            } else {
                Logger.warn("No se ha podido abrir una conexion con la base de datos.");
            }
        } catch (SQLException ex) {
            Logger.error(ex);
        } finally {
            this.closeConnection();
        }

        return false;
    }

    /**
     * Este metodo permite borrar una tarea de la base de datos.
     * 
     * @param task tarea que se quiere borrar de la base de datos.
     * @return true si no hubieron problemas borrando la tarea.
     */
    public boolean deleteTask(Task task) {
        String sqlQuery = "DELETE FROM tareas where nombre=" + task.getTaskName();
        try {
            if (this.open()) {
                Statement statement = null;
                try {
                    statement = this.getConnection().createStatement();
                    return statement.execute(sqlQuery);
                } catch (SQLException ex) {
                    Logger.error(ex);
                } finally {
                    this.closeStatement(statement);
                }
            } else {
                Logger.warn("No se ha podido abrir conexion con la base de datos.");
            }
        } catch (SQLException ex) {
            Logger.error(ex);
        } finally {
            this.closeConnection();
        }
        return false;
    }

    /**
     * Este metodo es usado para poder obtener todas las tareas almacenadas en la
     * base de datos.
     * 
     * @return una lista con todas las tareas.
     */
    public List<Task> getTasks() {
        String sqlQuery = "SELECT * FROM tareas";
        try {
            if (this.open()) {
                Statement statement = null;
                try {
                    statement = this.getConnection().createStatement();
                    try {
                        ResultSet resultSet = statement.executeQuery(sqlQuery);
                        LinkedList<Task> list = new LinkedList<>();
                        while (resultSet.next()) {
                            list.add(new Task(resultSet.getString("nombre"), resultSet.getString("descripcion"),
                                    resultSet.getString("inicio"), resultSet.getString("fin")));
                        }
                        try {
                            resultSet.close();
                        } catch (Exception ex) {
                            Logger.error(ex);
                        }
                        return list;
                    } catch (SQLException ex) {
                        Logger.error(ex);
                    }
                } catch (SQLException ex) {
                    Logger.error(ex);
                } finally {
                    this.closeStatement(statement);
                }
            } else {
                Logger.error("No se puede cerrar la conexion.");
            }
        } catch (SQLException ex) {
            Logger.error(ex);
        } finally {
            this.closeConnection();
        }
        return null;
    }

    /**
     * Este metodo cierra la conexion con la base de datos.
     */
    private void closeConnection() {
        if (this.isConnectionOpened()) {
            try {
                if (this.close()) {
                    Logger.info("Conexion cerrada con exito.");
                } else {
                    Logger.error("No se puede cerrar la conexion.");
                }
            } catch (Exception ex) {
                Logger.error(ex);
            }
        }
    }

    private void closeStatement(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (Exception ex) {
                Logger.error(ex);
            }
        }
    }

}
