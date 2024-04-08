package org.example.dateOperations;

import org.example.dataMapper.IndividualMapper;
import org.example.model.Individual;
import org.example.utils.Constants;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.example.visual.ErrorFrame.errorFrame;

public class IndividualsOperations {
    private static ArrayList<Individual> indList;
    private static IndividualMapper individualMapper = new IndividualMapper();
    public static void createNewEmployee(String name, Date birthDate){
        Individual individual = new Individual(name, birthDate);
        if (!individualMapper.insert (individual)) {
            errorFrame(Constants.INSERT_ERROR_MESSAGE);
            throw new RuntimeException(Constants.INSERT_ERROR_MESSAGE);
        }
    }
    public static ArrayList<Individual> getList(){
        indList = individualMapper.selectAll();
        if (indList == null){
            errorFrame(Constants.SELECT_ERROR_MESSAGE);
            throw new RuntimeException(Constants.SELECT_ERROR_MESSAGE);
        }
        return indList;
    }
    public static Individual sirchIndividual (String name, String birthDate){
        getList();
        for (Individual e : indList){
            if (e.getName().equals(name) && e.getBirthDateAsString().equals(birthDate)){
                return e;
            }
        }
        return null;
    }
    public static void saveIndividual (Individual individual, String name, Date birthDate){
            individual.setName(name);
            individual.setBirthDate(birthDate);
            if(!individualMapper.update(individual)){
                errorFrame(Constants.INSERT_ERROR_MESSAGE);
                throw new RuntimeException(Constants.INSERT_ERROR_MESSAGE);
            }
    }
    public static List<Individual> searchIndividualByName(String namePart){
        return individualMapper.selectByNamePart(namePart);
    }
}
