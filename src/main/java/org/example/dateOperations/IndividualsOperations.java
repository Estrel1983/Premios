package org.example.dateOperations;

import org.example.dataMapper.IndividualMapper;
import org.example.model.Individual;

import java.util.Date;

import static org.example.visual.ErrorFrame.errorFrame;

public class IndividualsOperations {
    public static IndividualMapper individualMapper = new IndividualMapper();
    public static final String INSERT_ERROR_MESSAGE = "Данные не могут быть внесены в базу данных";
    public static void createNewEmployee(String name, Date birthDate){
        Individual individual = new Individual(name, birthDate);
        if (!individualMapper.insert (individual)) {
            errorFrame(INSERT_ERROR_MESSAGE);
            throw new RuntimeException(INSERT_ERROR_MESSAGE);
        }
    }
}
