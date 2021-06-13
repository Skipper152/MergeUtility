package com.netcracker.operations;

import com.netcracker.models.JSONModel;

import java.util.HashMap;
import java.util.Map;


public class Validator {
    private HashMap<String, Boolean> nullCheckMap;

    public void validationJSON(JSONModel jsonModel) {

        nullCheckMap = jsonModel.validate();

        for (Map.Entry<String,Boolean> entry : nullCheckMap.entrySet()) {
            switch (entry.getKey()) {
                case "metadata":
                    if (!entry.getValue()) {
                        //вызов функции для проверки metadata
                    }

                    break;
                case "services":
                    if (!entry.getValue()) {
                        //вызов функции для проверки services
                    }

                    break;
                case "artifacts":
                    if (!entry.getValue()) {
                        //вызов функции для проверки artifacts
                    }

                    break;
                case "script":
                    if (!entry.getValue()) {
                        //вызов функции для проверки script
                    }

                    break;
                case "rpm":
                    if (!entry.getValue()) {
                        //вызов функции для проверки rpm
                    }

                    break;
                case "parameters":
                    if (!entry.getValue()) {
                        //вызов функции для проверки parameters
                    }

                    break;
            }
        }


    }
}
