package com.netcracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.operations.JSONComparator;
import com.netcracker.models.JSONModel;
import com.netcracker.operations.JSONValidator;
import com.netcracker.operations.ValidationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Logger;


@RestController
@CrossOrigin("*")
public class MergeUtilityController {

    @PostMapping("/upload")
    public ResponseEntity<?> uploadJSON(@RequestParam("fileFirst") MultipartFile fileFirst,
                                        @RequestParam("fileSecond") MultipartFile fileSecond) throws IOException {

        String contentFirst = new String(fileFirst.getBytes());
        String contentSecond = new String(fileSecond.getBytes());

        //Logger logger = Logger.getLogger(MergeUtilityController.class.getName());
        //logger.warning(contentFirst);
        //logger.warning(contentSecond);

        if ("".equals(contentFirst) || "".equals(contentSecond)) {
            return ResponseEntity.badRequest().body(("".equals(contentFirst) && "".equals(contentSecond)) ?
                    ("The files are empty!") : (("".equals(contentFirst)) ? ("The first file is empty!") : ("The file are empty!")));
        }

        JSONModel jsonModel1;
        JSONModel jsonModel2;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            jsonModel1 = objectMapper.readValue(contentFirst, JSONModel.class);
            jsonModel2 = objectMapper.readValue(contentSecond, JSONModel.class);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Mapping the file is not possible!");
        }

        JSONValidator jsonValidatorFirst = new JSONValidator();
        JSONValidator jsonValidatorSecond = new JSONValidator();
        jsonValidatorFirst.validationJSON(jsonModel1);
        jsonValidatorSecond.validationJSON(jsonModel2);
        LinkedList<String> errorsFirstFile = jsonValidatorFirst.getLinkedListErrors();
        LinkedList<String> errorsSecondFile = jsonValidatorSecond.getLinkedListErrors();

        if (!errorsFirstFile.isEmpty() || !errorsSecondFile.isEmpty()) {
            return ResponseEntity.badRequest().body(new ValidationResponse(errorsFirstFile, errorsSecondFile));
        }

        try {
            JSONComparator jsonComparator = new JSONComparator(jsonModel1, jsonModel2);
            return ResponseEntity.ok(jsonComparator.getJsonModelMerge());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Unknown error!");
        }
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
