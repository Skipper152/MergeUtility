package com.netcracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.operations.JSONComparator;
import com.netcracker.models.JSONModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;


@RestController
@CrossOrigin("*")
public class MergeUtilityController {

    @PostMapping("/upload")
    public ResponseEntity<?> uploadJSON(@RequestParam("fileFirst") MultipartFile fileFirst,
                                        @RequestParam("fileSecond") MultipartFile fileSecond) throws IOException {

        String contentFirst = new String(fileFirst.getBytes());
        String contentSecond = new String(fileSecond.getBytes());

        ObjectMapper objectMapper = new ObjectMapper();
        JSONModel jsonModel1 = objectMapper.readValue(contentFirst, JSONModel.class);
        JSONModel jsonModel2 = objectMapper.readValue(contentSecond, JSONModel.class);

        JSONComparator jsonComparator = new JSONComparator(jsonModel1,jsonModel2);

        return ResponseEntity.ok(jsonComparator.getJsonModelMerge());
    }

    /*@PostMapping("/upload1")
    public ResponseEntity<?> uploadJSON1(@RequestBody JSONModel file) throws IOException {

        System.out.println(file);

        ObjectMapper objectMapper = new ObjectMapper();
        JSONModel jsonModel2 = objectMapper.readValue(new File("C:\\Users\\Skipper\\IdeaProjects\\2021\\03\\MergeUtility\\src\\main\\resources\\json-files\\1.json"), JSONModel.class);
        System.out.println(jsonModel2);

        JSONComparator jsonComparator = new JSONComparator(file,jsonModel2);

        return ResponseEntity.ok(jsonComparator.getJsonModelMerge());
    }*/

}
