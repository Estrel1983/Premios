package org.example.dateOperations;

import org.example.dataMapper.PremiosIncomLowLimitMapper;
import org.example.dataMapper.PremiosPercentMapper;
import org.example.datebase.columns.PremiosIncomLowLimitColumnEnum;
import org.example.datebase.columns.PremiosPercentColumnEnum;
import org.example.model.PremiosIncomLowLimit;
import org.example.model.PremiosPercent;
import org.example.model.position;

import java.util.ArrayList;

public class PremiosIncomLowLimitOperations {
    private static PremiosIncomLowLimitMapper premiosIncomLowLimitMapper = new PremiosIncomLowLimitMapper();
    public static Integer getLimitByPosition (position pos){
        ArrayList<PremiosIncomLowLimit> premList = premiosIncomLowLimitMapper.selectByColumnValue(PremiosIncomLowLimitColumnEnum.POSITION_NAME, pos.getPositionName());
        if (premList.isEmpty())
            return 0;
        return premList.get(0).getLimit();
    }
}
