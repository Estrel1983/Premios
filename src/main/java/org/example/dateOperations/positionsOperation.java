package org.example.dateOperations;

import org.example.dataMapper.EmployeMapper;
import org.example.dataMapper.PositionMapper;
import org.example.datebase.columns.EmployeColumnsEnum;
import org.example.model.position;
import org.example.utils.Constants;

import java.util.ArrayList;

import static org.example.visual.ErrorFrame.errorFrame;

public class positionsOperation {
    private static ArrayList <position> posList;
    private static PositionMapper postionMapper = new PositionMapper();
    public static ArrayList<position> getList(){
        posList = postionMapper.selectAll();
        if (posList == null){
            errorFrame(Constants.SELECT_ERROR_MESSAGE);
            throw new RuntimeException(Constants.SELECT_ERROR_MESSAGE);
        }
        return posList;
    }
    public static position sirchPosition(String posName){
        getList();
        for (position pos : posList){
            if (posName.equals(pos.getPositionName()))
                return pos;
        }
        return null;
    }
    public static void savePosition (position pos, String posName){
        String oldPosName = pos.getPositionName();
        pos.setPositionName(posName);
        if (!postionMapper.update(pos)){
            errorFrame(Constants.INSERT_ERROR_MESSAGE);
            throw new RuntimeException(Constants.INSERT_ERROR_MESSAGE);
        }
        if (!(new EmployeMapper().updateByColumnValue(EmployeColumnsEnum.POS_NAME, oldPosName, posName))){
            errorFrame(Constants.INSERT_ERROR_MESSAGE);
            throw new RuntimeException(Constants.INSERT_ERROR_MESSAGE);
        }
    }
    public static void savePosition (String posName){
        position pos = new position(posName);
        if (!postionMapper.insert(pos)){
            errorFrame(Constants.INSERT_ERROR_MESSAGE);
            throw new RuntimeException(Constants.INSERT_ERROR_MESSAGE);
        }
    }
}
