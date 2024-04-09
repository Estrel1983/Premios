package org.example.model;

import org.example.utils.Constants;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Employe implements MyTableModel {
    private Integer id;
    private String name;
    private String positionName;
    private Date startDate;
    private Date endDate;
    private static final int TABLE_SIZE = 4;
    private static final ArrayList<String> TABLE_TITLE = new ArrayList(Arrays.asList( "Имя", "Должность", "Дата приема", "Дата увольнения"));

    public Employe(Integer id, String name, String positionName, Date startDate) {
        this.id = id;
        this.name = name;
        this.positionName = positionName;
        this.startDate = startDate;
    }

    public Employe(String name, String positionName, Date startDate) {
        this.name = name;
        this.positionName = positionName;
        this.startDate = startDate;
    }
    public Employe(){}

    public Employe(Integer id, String name, String positionName, Date startDate, Date endDate) {
        this.id = id;
        this.name = name;
        this.positionName = positionName;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public Employe(String name, String positionName, Date startDate, Date endDate) {
        this.name = name;
        this.positionName = positionName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public int getTableSize() {
        return TABLE_SIZE;
    }

    @Override
    public ArrayList<String> getTitles() {
        return TABLE_TITLE;
    }
    public Object[] toObject(){
        return new Object[]{this.name, this.positionName,
                (new SimpleDateFormat(Constants.DATE_FORMAT_FOR_TABLE)).format(this.startDate),
                this.endDate == null ? "" : (new SimpleDateFormat(Constants.DATE_FORMAT_FOR_TABLE)).format(this.endDate)};
    }
}
