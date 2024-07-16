package org.example.dateOperations;

import org.example.dataMapper.EmployeMapper;
import org.example.exceptions.NonexistingEmployeeException;
import org.example.model.Employe;
import org.example.model.TableModel;
import org.example.utils.Constants;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import static org.example.visual.ErrorFrame.errorFrame;

public class employeeOperations {
    private static ArrayList<TableModel> empList;
    private static EmployeMapper employeMapper = new EmployeMapper();

    public static ArrayList<TableModel> getList(){
        empList = employeMapper.selectAll();
        if (empList == null){
            errorFrame(Constants.SELECT_ERROR_MESSAGE);
            throw new RuntimeException(Constants.SELECT_ERROR_MESSAGE);
        }
        return empList;
    }

    public static ArrayList<Employe> getClearList(){
        if (empList == null){
            errorFrame(Constants.SELECT_ERROR_MESSAGE);
            throw new RuntimeException(Constants.SELECT_ERROR_MESSAGE);
        }
        ArrayList<Employe> emplClearList = new ArrayList<>();
        for (TableModel model : empList)
            emplClearList.add((Employe) model);
        return  emplClearList;
    }

    public static void saveEmployee (String name, String position, Date startDate, Optional<Date> endDate) throws NonexistingEmployeeException{
        if (!endDate.isPresent()) {
            Employe employe = new Employe(name, position, startDate);
            if (!employeMapper .insert(employe)){
                errorFrame(Constants.INSERT_ERROR_MESSAGE);
                throw new RuntimeException(Constants.INSERT_ERROR_MESSAGE);
            }
        }
        else {
            employeMapper.update(getClearList().stream().filter(e -> name.equals(e.getName()) && position.equals(e.getPositionName()))
                    .findFirst().orElseThrow(NonexistingEmployeeException::new));
        }
    }
}
