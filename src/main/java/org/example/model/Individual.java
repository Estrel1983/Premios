package org.example.model;

import org.example.utils.Constants;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Individual {
    private Integer id;
    private String name;
    private Date birthDate;

    public Individual(Integer id, String name, Date birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public Individual(String name, Date birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getBirthDateAsString(){
        return (new SimpleDateFormat(Constants.DATE_FORMAT_FOR_TABLE)).format(this.birthDate);
    }

    public Object[] toObject(){
        return new Object[]{this.name, (new SimpleDateFormat(Constants.DATE_FORMAT_FOR_TABLE)).format(this.birthDate)};
    }
}
