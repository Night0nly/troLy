package com.night.troly.models;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by night on 07/05/2017
 */
@Table(database = WordDatabase.class)
public class Command extends BaseModel {
    @Column(name = "Id")
    @PrimaryKey(autoincrement = true)
    private int id;

    @Column(name = "action")
    private int action;

    @Column(name = "command_name")
    private String commandName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }
}
