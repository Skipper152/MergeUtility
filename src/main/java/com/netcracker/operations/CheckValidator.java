package com.netcracker.operations;

import com.netcracker.models.*;

import java.util.*;


public class CheckValidator {
    private TreeMap<String, TreeMap<String,String>> checkMapForMetadata = new TreeMap<>();
    private TreeMap<String, LinkedList<TreeMap<String,LinkedList<String>>>> checkMapForServices = new TreeMap<>();

    public void validationJSON(JSONModel jsonModel) {

        HashMap<String, Boolean> checkMapJSONModel = jsonModel.validate();

        if (!checkMapJSONModel.isEmpty()) {
            for (Map.Entry<String, Boolean> entry : checkMapJSONModel.entrySet()) {
                switch (entry.getKey()) {
                    case "metadata":
                        if (!entry.getValue()) {
                            //вызов функции для проверки metadata
                            TreeMap<String,String> checkTreeMap = validationMetadataModel(jsonModel.getMetadata());
                            if (!checkTreeMap.isEmpty()) {
                                checkMapForMetadata.put("metadata", checkTreeMap);
                            }
                        } else {
                            checkMapForMetadata.put("metadata", null);
                        }
                        break;
                    case "services":
                        if (!entry.getValue()) {
                            //вызов функции для проверки services
                            LinkedList<TreeMap<String, LinkedList<String>>> checkLinkedList = validationServiceModel(jsonModel.getServices());
                            if (!checkLinkedList.isEmpty()) {
                                checkMapForServices.put("services", checkLinkedList);
                            }
                        } else {
                            checkMapForServices.put("services", null);
                        }
                        break;
                    case "artifacts":
                        if (!entry.getValue()) {
                            //вызов функции для проверки artifacts
                            //validationArtifactModel(jsonModel.getArtifacts());
                        }

                        break;
                    case "script":
                        if (!entry.getValue()) {
                            //вызов функции для проверки script
                            //validationScriptModel(jsonModel.getScripts());
                        }

                        break;
                    case "rpm":
                        if (!entry.getValue()) {
                            //вызов функции для проверки rpm
                            //validationRPMModel(jsonModel.getRpms());
                        }

                        break;
                    case "parameters":
                        if (!entry.getValue()) {
                            //вызов функции для проверки parameters
                            //validationParametersModel(jsonModel.getParameters());
                        }

                        break;
                }
            }
        }

        System.out.println(checkMapForMetadata);
        System.out.println(checkMapForServices);
    }

    private TreeMap<String, String> validationMetadataModel(Metadata metadata) {
        HashMap<String, Boolean> checkMapMetadata = metadata.validate();
        TreeMap<String, String> checkTreeMap = new TreeMap<>();
        for (Map.Entry<String,Boolean> entry : checkMapMetadata.entrySet()) {
            switch (entry.getKey()) {
                case "application":
                    if (!entry.getValue()) {
                        //вызов функции для проверки application
                        String validApp = validationApplicationModel(metadata.getApplication());
                        if (validApp != null) {
                            checkTreeMap.put("application",validApp);
                        }
                    } else {
                        checkTreeMap.put("application",null);
                    }
                    break;
                case "description":
                    if (!entry.getValue()) {
                        //вызов функции для проверки description
                        String validDes = validationDescriptionModel(metadata.getDescription());
                        if (validDes != null) {
                            checkTreeMap.put("description",validDes);
                        }
                    } else {
                        checkTreeMap.put("description",null);
                    }
                    break;
            }
        }
        return checkTreeMap;
    }

    private String validationApplicationModel(Application application) {
        HashMap<String, Boolean> checkMapApplication = application.validate();
        if (checkMapApplication.get("name")) {
            return "name";
        }
        return null;
    }

    private String validationDescriptionModel(Description description) {
        HashMap<String, Boolean> checkMapDescription = description.validate();
        if (checkMapDescription.get("version")) {
            return "version";
        }
        return null;
    }

    private LinkedList<TreeMap<String, LinkedList<String>>> validationServiceModel(ArrayList<Service> services) {
        LinkedList<TreeMap<String,LinkedList<String>>> checkLinkedList = new LinkedList<>();
        for (Service service : services) {
            HashMap<String, Boolean> checkMapService = service.validate();

            TreeMap<String, LinkedList<String>> checkTreeMap = new TreeMap<>();
            for (Map.Entry<String, Boolean> entry : checkMapService.entrySet()) {
                switch (entry.getKey()) {
                    case "service_name":
                        if (entry.getValue()) {
                            //проверка поля service_name
                            checkTreeMap.put("service_name", null);
                        }
                        break;
                    case "artifact_type":
                        if (entry.getValue()) {
                            //проверка поля artifact_type
                            checkTreeMap.put("artifact_type", null);
                        }
                        break;
                    case "docker_registry":
                        if (entry.getValue()) {
                            //проверка поля docker_registry
                            checkTreeMap.put("docker_registry", null);
                        }
                        break;
                    case "docker_image_name":
                        if (entry.getValue()) {
                            //проверка поля docker_image_name
                            checkTreeMap.put("docker_image_name", null);
                        }
                        break;
                    case "docker_tag":
                        if (entry.getValue()) {
                            //проверка поля docker_tag
                            checkTreeMap.put("docker_tag", null);
                        }
                        break;
                    case "hashes":
                        if (!entry.getValue()) {
                            //вызов функции для проверки поля hashes
                            LinkedList<String> checkHashes = validationHashesModel(service.getHashes());
                            if (!checkHashes.isEmpty()) {
                                checkTreeMap.put("hashes", checkHashes);
                            }
                        } else {
                            checkTreeMap.put("hashes", null);
                        }
                        break;
                }
            }
            checkLinkedList.add(checkTreeMap);
        }
        return checkLinkedList;
    }

    private LinkedList<String> validationHashesModel(Hashes hashes) {
        HashMap<String, Boolean> checkMapHashes = hashes.validate();
        LinkedList<String> checkLinkedList = null;
        if (!checkMapHashes.isEmpty()) {
            checkLinkedList = new LinkedList<>();
            for (Map.Entry<String, Boolean> entry : checkMapHashes.entrySet()) {
                switch (entry.getKey()) {
                    case "sha1":
                        if (entry.getValue()) {
                            //вызов функции для проверки name
                            checkLinkedList.addLast("sha1");
                        }
                        break;
                    case "sha256":
                        if (entry.getValue()) {
                            //вызов функции для проверки name
                            checkLinkedList.addLast("sha256");
                        }
                        break;
                }
            }
        }
        return checkLinkedList;
    }

    private void validationArtifactModel(ArrayList<Artifact> artifacts) {
        ArrayList<HashMap<String, Boolean>> checkMapArtifacts = new ArrayList<>();
        for (Artifact artifact : artifacts) {
            checkMapArtifacts.add(artifact.validate());
        }
        for (HashMap<String, Boolean> item : checkMapArtifacts) {
            for (Map.Entry<String, Boolean> entry : item.entrySet()) {
                switch (entry.getKey()) {
                    case "service_name":
                        if (!entry.getValue()) {
                            //проверка поля service_name

                        }

                        break;

                }
            }
        }
    }

    private void validationScriptModel(ArrayList<Script> scripts) {
        ArrayList<HashMap<String, Boolean>> checkMapScript = new ArrayList<>();
        for (Script script : scripts) {
            checkMapScript.add(script.validate());
        }
        for (HashMap<String, Boolean> item : checkMapScript) {
            for (Map.Entry<String, Boolean> entry : item.entrySet()) {
                switch (entry.getKey()) {
                    case "script_name":
                        if (!entry.getValue()) {
                            //проверка поля script_name
                        }

                        break;
                    case "hashes":
                        if (!entry.getValue()) {
                            //проверка поля hashes
                        }

                        break;
                    case "url":
                        if (!entry.getValue()) {
                            //проверка поля url
                        }

                        break;
                }
            }
        }
    }

    private void validationRPMModel(ArrayList<RPM> rpms) {
        ArrayList<HashMap<String, Boolean>> checkMapRPM = new ArrayList<>();
        for (RPM rpm : rpms) {
            checkMapRPM.add(rpm.validate());
        }
        for (HashMap<String, Boolean> item : checkMapRPM) {
            for (Map.Entry<String, Boolean> entry : item.entrySet()) {
                switch (entry.getKey()) {
                    case "url":
                        if (!entry.getValue()) {
                            //проверка поля url
                        }

                        break;
                    case "rpm_repository_name":
                        if (!entry.getValue()) {
                            //проверка поля rpm_repository_name
                        }

                        break;
                    case "hashes":
                        if (!entry.getValue()) {
                            //проверка поля hashes
                        }

                        break;
                }
            }
        }
    }

    private void validationParametersModel(Parameters parameters) {
        HashMap<String, Boolean> checkMapParameters = parameters.validate();
        for (Map.Entry<String,Boolean> entry : checkMapParameters.entrySet()) {
            switch (entry.getKey()) {
                case "common":
                    if (!entry.getValue()) {
                        //вызов функции для проверки application
                    }

                    break;
                case "services":
                    if (!entry.getValue()) {
                        //вызов функции для проверки services
                    }

                    break;
            }
        }
    }
}
