package org.example.dateOperations;

import org.example.dataMapper.EmployeMapper;
import org.example.model.Employe;
import org.example.model.MyTableModel;
import org.example.utils.Constants;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.example.visual.ErrorFrame.errorFrame;

public class employeeOperations {
    private static ArrayList<Employe> empList;
    private static EmployeMapper employeMapper = new EmployeMapper();

    public static ArrayList<Employe> getList(){
        empList = employeMapper.selectAll();
        if (empList == null){
            errorFrame(Constants.SELECT_ERROR_MESSAGE);
            throw new RuntimeException(Constants.SELECT_ERROR_MESSAGE);
        }
        return empList;
    }

    public static ArrayList<MyTableModel> getListForTable(){
        getList();
        ArrayList<MyTableModel> response = new ArrayList<>();
        for (Employe employe : empList){
            response.add(employe);
        }
        return response;
    }
    public static Employe searchEmploye (String name, String positionName, String startDate, String endDate){
        getList();
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT_FOR_TABLE);
        for (Employe e : empList){
            if (e.getName().equals(name) &&
                    e.getPositionName().equals(positionName) &&
                    dateFormat.format(e.getStartDate()).equals(startDate) &&
                    ((e.getEndDate() == null && endDate == null) || ((e.getEndDate() != null && endDate != null) && dateFormat.format(e.getEndDate()).equals(endDate))) ){
                return e;
            }
        }
        return null;
    }

    public static void saveEmploye (Employe employe, Date startDate, Date endDate){
        employe.setStartDate(startDate);
        employe.setEndDate(endDate);
        if (!employeMapper.update(employe)){
            errorFrame(Constants.INSERT_ERROR_MESSAGE);
            throw new RuntimeException(Constants.INSERT_ERROR_MESSAGE);
        }
    }
    public static void saveEmploye (String name, String positionName, Date startDate, Date endDate){
        if (!employeMapper.insert(new Employe(name, positionName, startDate, endDate))){
            errorFrame(Constants.INSERT_ERROR_MESSAGE);
            throw new RuntimeException(Constants.INSERT_ERROR_MESSAGE);
        }
    }
}
