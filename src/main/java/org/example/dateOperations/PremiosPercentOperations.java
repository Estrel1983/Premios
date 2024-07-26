package org.example.dateOperations;

import org.example.dataMapper.PremiosPercentMapper;
import org.example.datebase.columns.PremiosPercentColumnEnum;
import org.example.model.PremiosPercent;
import org.example.model.position;

import java.util.ArrayList;

public class PremiosPercentOperations {
    private static PremiosPercentMapper premiosPercentMapper = new PremiosPercentMapper();

    public static Double getPercentByPosition(position pos) {
        ArrayList<PremiosPercent> premList = premiosPercentMapper.selectByColumnValue(PremiosPercentColumnEnum.POSITION_NAME, pos.getPositionName());
        if (premList.isEmpty())
            return (double) 0;
        return premList.get(0).getPercent();
    }
}
