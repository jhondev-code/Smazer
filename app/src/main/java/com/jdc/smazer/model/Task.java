package com.jdc.smazer.model;

import java.util.Date;

/**
 * Esta clase sirve para poder crear tareas que luego seran almacenadas en una
 * base de datos como tambien recuperadas de la base de datos.
 * 
 * @author jhon fernandez
 * @version 1.0.0
 */
public class Task {

    /**
     * Representa el nombre de la tarea.
     */
    private String taskName;

    /**
     * Descripcion de la tarea.
     */
    private String taskDescription;

    /**
     * Hora de inicio de la tarea.
     */
    private Date taskStartTime;

    /**
     * Hora de finalizacion de la tarea.
     */
    private Date taskEndTime;

    /**
     * Constructor por defecto, no inicia ningun datos ya que los datos seran
     * ingresados a travez de los metodos set y get.
     */
    public Task() {
    }

    /**
     * Este constructor inicia los datos obligatorios dejando de lado la descripcion
     * de la tarea.
     * 
     * @param name      nombre de la tarea.
     * @param startTime hora de inicio de la tarea.
     * @param endTime   hora de finalizacion de la tare.
     */
    public Task(String name, Date startTime, Date endTime) {
        this.setTaskName(name);
        this.setTaskDescription("Sin descripcion");
        this.setTaskStartTime(startTime);
        this.setTaskEndTime(endTime);
    }

    /**
     * Este constructor inicia todos los datos de la tarea.
     * 
     * @param name        nombre de la tarea.
     * @param description descripcion de la tarea.
     * @param startTime   hora de inicio de la tarea.
     * @param endTime     hora de finalizacion de la tarea.
     */
    public Task(String name, String description, Date startTime, Date endTime) {
        this.setTaskName(name);
        this.setTaskDescription(description);
        this.setTaskStartTime(startTime);
        this.setTaskEndTime(endTime);
    }

    /**
     * Retorna el nombre de la tarea.
     * 
     * @return task name.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Establece el nombre de la tarea.
     * 
     * @param taskName task name.
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Retorna la descripcion de la tarea.
     * 
     * @return task description.
     */
    public String getTaskDescription() {
        return taskDescription;
    }

    /**
     * Establece la descripcion de la tarea.
     * 
     * @param taskDescription task description.
     */
    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /**
     * Retorna la hora de inicio de la tarea.
     * 
     * @return task start time.
     */
    public Date getTaskStartTime() {
        return taskStartTime;
    }

    /**
     * Establece la hora de inicio de la tarea.
     * 
     * @param taskStartTime task start time.
     */
    public void setTaskStartTime(Date taskStartTime) {
        this.taskStartTime = taskStartTime;
    }

    /**
     * Retorna la hora de finalizacion de la tarea.
     * 
     * @return task end time.
     */
    public Date getTaskEndTime() {
        return taskEndTime;
    }

    /**
     * Establece la hora de finalizacion de la tarea.
     * 
     * @param taskEndTime task end time.
     */
    public void setTaskEndTime(Date taskEndTime) {
        this.taskEndTime = taskEndTime;
    }

}
