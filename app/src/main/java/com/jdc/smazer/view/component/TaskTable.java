package com.jdc.smazer.view.component;

import java.time.LocalTime;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.jdc.smazer.model.Task;

/**
 * Esta clase personaliza JTable para poder mostrar las tareas, los estados de
 * las tareas se pintan en diferentes colores.
 * 
 * @author jhon fernandez
 * @version 1.0.0
 */
public class TaskTable extends JTable {

    /**
     * Este es el modelo de tabla que se usara para colocar las tareas.
     */
    private DefaultTableModel tableModel;

    /**
     * Este constructor inicia estableciendo el modelo de la tabla por defecto.
     */
    public TaskTable() {
        this.tableModel = new DefaultTableModel(null,
                new String[] { "Nombre", "Descripcion", "Estado", "Inicia", "Termina" });
        this.setModel(this.tableModel);
    }

    /**
     * Retorna el modelo de la tabla que se esta usando.
     * 
     * @return table model.
     */
    public DefaultTableModel getTableModel() {
        return this.tableModel;
    }

    /**
     * Este metodo agrega una tarea a la tabla previamente analizando el estado de
     * la tarea.
     * 
     * @param task tarea que se quiere agregar
     */
    public void addTask(Task task) {
        if (task != null) {
            LocalTime now = LocalTime.now();
            String state = "Detenido";
            if (task.getTaskStartTime().getHour() < now.getHour()) {
                state = "En espera";
            } else if (task.getTaskStartTime().getHour() >= now.getHour()
                    && now.getHour() <= task.getTaskEndTime().getHour()) {
                state = "Activo";
            }
            String startTime = task.getTaskStartTime().getHour() + ":" + task.getTaskStartTime().getMinute() + ":"
                    + task.getTaskStartTime().getSecond();
            String endTime = task.getTaskEndTime().getHour() + ":" + task.getTaskEndTime().getMinute() + ":"
                    + task.getTaskEndTime().getSecond();
            this.getTableModel()
                    .addRow(new String[] { task.getTaskName(), task.getTaskDescription(), state, startTime, endTime });
        }
    }

}
