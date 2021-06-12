package com.netcracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.models.JSONModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
public class MergeUtilityController {
    @GetMapping("/string")
    public String returnString() {

        return "sjfsdhjfsdjsfdkjsdfjksfdkjdsfkj";
    }

    @GetMapping("/json")
    public JSONModel returnJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JSONModel jsonModel1 = objectMapper.readValue(new File("C:\\Users\\Skipper\\IdeaProjects\\2021\\03\\MergeUtility\\src\\main\\resources\\json-files\\v2_json_sample.json"), JSONModel.class);
        JSONModel jsonModel2 = objectMapper.readValue(new File("C:\\Users\\Skipper\\IdeaProjects\\2021\\03\\MergeUtility\\src\\main\\resources\\json-files\\v2_json_sample 2.json"), JSONModel.class);

        //OperationsForJSON.checkFields(jsonModel1);
        //OperationsForJSON.checkFields(jsonModel2);

        System.out.println(jsonModel1);
        System.out.println(jsonModel2);
        return jsonModel1;
    }
}
