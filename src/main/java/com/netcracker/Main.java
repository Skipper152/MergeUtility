package com.netcracker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.models.JSONModel;
import com.netcracker.operations.Difference;
import com.netcracker.operations.JSONComparator;


import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JSONModel jsonModel1 = objectMapper.readValue(new File("C:\\Users\\Skipper\\IdeaProjects\\2021\\03\\MergeUtility\\src\\main\\resources\\json-files\\v2_json_sample.json"), JSONModel.class);
        JSONModel jsonModel2 = objectMapper.readValue(new File("C:\\Users\\Skipper\\IdeaProjects\\2021\\03\\MergeUtility\\src\\main\\resources\\json-files\\v2_json_sample 2.json"), JSONModel.class);

        /*Validator validator = new Validator();

        validator.validationJSON(jsonModel2);

        for (String str : validator.getLinkedListErrors()) {
            System.out.println(str);
        }*/
        JSONComparator jsonComparator = new JSONComparator(jsonModel1,jsonModel2);

        for (Difference diff : jsonComparator.getDiffsList())
            System.out.println(diff);

        //System.out.println(jsonModel1);
        //System.out.println(jsonModel2);
    }
}
