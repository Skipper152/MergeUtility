package com.netcracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.models.JSONModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;

@RestController
@CrossOrigin("*")
public class MergeUtilityController {

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

    @PostMapping("/upload")
    public ResponseEntity<?> uploadJSON(@RequestParam MultipartFile file) throws IOException {

        String content = new String(file.getBytes());

        //System.out.println(content);

        ObjectMapper objectMapper = new ObjectMapper();
        JSONModel jsonModel1 = objectMapper.readValue(content, JSONModel.class);

        System.out.println(jsonModel1);

        return ResponseEntity.ok(jsonModel1);
    }

    @PostMapping("/upload1")
    public String uploadJSON1(@RequestBody JSONModel file) throws IOException {

        System.out.println(file);


        return "ok2";
    }

}
