package com.night.troly.models;

import com.raizlabs.android.dbflow.annotation.*;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by night on 07/05/2017
 */
@Table(name = "training_data", database = WordDatabase.class)
public class TrainingData extends BaseModel {

    @Column(name="Id")
    @PrimaryKey(autoincrement = true)
    private  int id;

    @Column(name = "IndexN")
    private int index;

    @Column(name = "Word")
    private String word;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
