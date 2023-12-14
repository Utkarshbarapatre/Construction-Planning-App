package com.example.signup_form.TaskPages;

public class TaskModel {
    String TASKNAME,TASk_DESCRIPTION,AssignedWORKER,TASKDATESELECTED,TASKEDATESELECTED,Task_priorityET,Taskstatus;

    TaskModel(){

    }

    public TaskModel(String TASKNAME, String TASk_DESCRIPTION, String assignedWORKER, String TASKDATESELECTED, String TASKEDATESELECTED, String task_priorityET, String taskstatus) {
        this.TASKNAME = TASKNAME;
        this.TASk_DESCRIPTION = TASk_DESCRIPTION;
        this.AssignedWORKER = assignedWORKER;
        this.TASKDATESELECTED = TASKDATESELECTED;
        this.TASKEDATESELECTED = TASKEDATESELECTED;
        this.Task_priorityET = task_priorityET;
        this.Taskstatus = taskstatus;
    }

    public String getTASKNAME() {
        return TASKNAME;
    }

    public void setTASKNAME(String TASKNAME) {
        this.TASKNAME = TASKNAME;
    }

    public String getTASk_DESCRIPTION() {
        return TASk_DESCRIPTION;
    }

    public void setTASk_DESCRIPTION(String TASk_DESCRIPTION) {
        this.TASk_DESCRIPTION = TASk_DESCRIPTION;
    }

    public String getAssignedWORKER() {
        return AssignedWORKER;
    }

    public void setAssignedWORKER(String assignedWORKER) {
        AssignedWORKER = assignedWORKER;
    }

    public String getTASKDATESELECTED() {
        return TASKDATESELECTED;
    }

    public void setTASKDATESELECTED(String TASKDATESELECTED) {
        this.TASKDATESELECTED = TASKDATESELECTED;
    }

    public String getTASKEDATESELECTED() {
        return TASKEDATESELECTED;
    }

    public void setTASKEDATESELECTED(String TASKEDATESELECTED) {
        this.TASKEDATESELECTED = TASKEDATESELECTED;
    }

    public String getTask_priorityET() {
        return Task_priorityET;
    }

    public void setTask_priorityET(String task_priorityET) {
        Task_priorityET = task_priorityET;
    }

    public String getTaskstatus() {
        return Taskstatus;
    }

    public void setTaskstatus(String taskstatus) {
        Taskstatus = taskstatus;
    }
}
