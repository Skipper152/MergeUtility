package com.netcracker.operations;

import com.netcracker.models.JSONModel;

public class OperationsForJSON {
    public static void checkFields(JSONModel jsonFile) {

            StringBuilder fields = new StringBuilder();
            // Check fields in the JSONModel-class
            if (jsonFile.getMetadata() == null)
                fields.append("metadata\n");
            if (jsonFile.getServices() == null)
                fields.append("services\n");
            if (jsonFile.getArtifacts() == null)
                fields.append("artifacts");
            if (jsonFile.getScripts() == null)
                fields.append("script");
            if (jsonFile.getRpms() == null)
                fields.append("rpm");
            if (jsonFile.getParameters() == null)
                fields.append("parameters");

            /*// Check fields in the Metadata-class
            if (jsonFile.getMetadata().getDescription() == null)
                throw new IOException("The field \"description\" is null! Please, choose the correct json-file!");
            if (jsonFile.getMetadata().getApplication() == null)
                throw new IOException("The field \"application\" is null! Please, choose the correct json-file!");

            // Check fields in the Service-class
            for (Service jM : jsonFile.getServices()) {
                if (jM.getHashes() == null)
                    throw new IOException("The field \"hashes\" in the field \"services\" is null! Please, choose the correct json-file!");
            }

            // Check fields in the Artifact-class
            for (Artifact jM : jsonFile.getArtifacts()) {
                if (jM.getMvns() == null)
                    throw new IOException("The field \"mvn\" in the field \"services\" is null! Please, choose the correct json-file!");
            }*/



    }

    public static void compareJSONFiles(JSONModel jsonFile1, JSONModel jsonFile2) {

    }
}
