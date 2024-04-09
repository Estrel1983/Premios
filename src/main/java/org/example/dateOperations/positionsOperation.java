package org.example.dateOperations;

import org.example.dataMapper.PositionMapper;
import org.example.model.Position;
import org.example.utils.Constants;

import java.util.ArrayList;

import static org.example.visual.ErrorFrame.errorFrame;

public class positionsOperation {
    private static ArrayList <Position> posList;
    private static PositionMapper postionMapper = new PositionMapper();
    public static ArrayList<Position> getList(){
        posList = postionMapper.selectAll();
        if (posList == null){
            errorFrame(Constants.SELECT_ERROR_MESSAGE);
            throw new RuntimeException(Constants.SELECT_ERROR_MESSAGE);
        }
        return posList;
    }
    public static Position sirchPosition(String posName){
        getList();
        for (Position pos : posList){
            if (posName.equals(pos.getPositionName()))
                return pos;
        }
        return null;
    }
    public static void savePosition (Position pos, String posName){
        pos.setPositionName(posName);
        if (!postionMapper.update(pos)){
            errorFrame(Constants.INSERT_ERROR_MESSAGE);
            throw new RuntimeException(Constants.INSERT_ERROR_MESSAGE);
        }
    }
    public static void savePosition (String posName){
        Position pos = new Position(posName);
        if (!postionMapper.insert(pos)){
            errorFrame(Constants.INSERT_ERROR_MESSAGE);
            throw new RuntimeException(Constants.INSERT_ERROR_MESSAGE);
        }
    }
}
