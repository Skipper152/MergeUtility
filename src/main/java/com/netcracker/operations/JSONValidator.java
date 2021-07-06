package com.netcracker.operations;

import com.netcracker.models.*;

import java.util.*;

public class JSONValidator {
    LinkedList<String> linkedListErrors = new LinkedList<>();

    public void validationJSON(JSONModel jsonModel) {
        HashMap<String,Boolean> jsonValidateMap = jsonModel.validate();

        for (Map.Entry<String, Boolean> entry : jsonValidateMap.entrySet()) {
            if (entry.getValue()) {
                linkedListErrors.addLast("The field \"" + entry.getKey() + "\" is null.");
            } else {
                switch (entry.getKey()) {
                    case "metadata":
                        //вызов функции для проверки metadata
                        validationMetadataModel(jsonModel.getMetadata());
                        break;
                    case "services":
                        //вызов функции для проверки services
                        validationServiceModel(jsonModel.getServices());
                        break;
                    case "artifacts":
                        //вызов функции для проверки artifacts
                        validationArtifactsModel(jsonModel.getArtifacts());
                        break;
                    case "script":
                        //вызов функции для проверки script
                        validationScriptModel(jsonModel.getScripts());
                        break;
                    case "rpm":
                        //вызов функции для проверки rpm
                        validationRPMModel(jsonModel.getRpms());
                        break;
                    case "parameters":
                        //вызов функции для проверки parameters
                        validationParametersModel(jsonModel.getParameters());
                        break;
                }
            }
        }
    }

    private void validationMetadataModel(Metadata metadata) {
        HashMap<String,Boolean> jsonValidateMap = metadata.validate();

        for (Map.Entry<String, Boolean> entry : jsonValidateMap.entrySet()) {
            switch (entry.getKey()) {
                case "application":
                    if (!entry.getValue()) {
                        validationApplicationModel(metadata.getApplication());
                    } else {
                        linkedListErrors.addLast("The field \"application\" in the \"metadata\" is null.");
                    }
                    break;
                case "description":
                    if (!entry.getValue()) {
                        validationDescriptionModel(metadata.getDescription());
                    } else {
                        linkedListErrors.addLast("The field \"description\" in the \"metadata\" is null.");
                    }
            }
        }
    }

    private void validationApplicationModel(Application application) {
        HashMap<String,Boolean> jsonValidateMap = application.validate();
        if (jsonValidateMap.get("name")) {
            linkedListErrors.addLast("The field \"name\" in the \"application\" is null.");
        }
    }

    private void validationDescriptionModel(Description description) {
        HashMap<String,Boolean> jsonValidateMap = description.validate();
        if (jsonValidateMap.get("version")) {
            linkedListErrors.addLast("The field \"version\" in the \"description\" is null.");
        }
    }

    private void validationServiceModel(ArrayList<Service> services) {
        for (int i = 0; i < services.size(); ++i) {
            HashMap<String, Boolean> jsonValidateMap = services.get(i).validate();
            for (Map.Entry<String, Boolean> entry : jsonValidateMap.entrySet()) {
                if (entry.getValue()) {
                    linkedListErrors.addLast("The field \"" + entry.getKey() + "\" in the "
                                + (i+1) + " object in the \"services\" is null.");
                } else if (entry.getKey().equals("hashes")) {
                    LinkedList<String> validationLinkedList = validationHashesModel(services.get(i).getHashes());
                    for (String field : validationLinkedList) {
                        linkedListErrors.addLast("The field \"" + field + "\" in the "
                                + (i+1) + " services object in the hashes field is null.");
                    }
                }
            }
        }
    }

    private LinkedList<String> validationHashesModel(Hashes hashes) {
        HashMap<String, Boolean> jsonValidateMap = hashes.validate();
        LinkedList<String> validationLinkedList = new LinkedList<>();
        for (Map.Entry<String, Boolean> entry : jsonValidateMap.entrySet()) {
            if (entry.getValue()) {
                validationLinkedList.addLast(entry.getKey());
            }
        }
        return validationLinkedList;
    }

    private void validationArtifactsModel(ArrayList<Artifact> artifacts) {
        for (int i = 0; i < artifacts.size(); ++i) {
            HashMap<String, Boolean> jsonValidateMap = artifacts.get(i).validate();
            if (jsonValidateMap.get("service-short-name") && jsonValidateMap.get("service_name") &&
                jsonValidateMap.get("hashes") && jsonValidateMap.get("file")) {

                if (jsonValidateMap.get("mvn")) {
                    linkedListErrors.addLast("The field \"mvn\" in the " + (i+1) + " of the artifacts field is null.");
                } else {
                    validationMVNModel(artifacts.get(i).getMvns());
                }

            } else if (jsonValidateMap.get("service-short-name") || jsonValidateMap.get("service_name") ||
                    jsonValidateMap.get("hashes") || jsonValidateMap.get("file")) {

                if (jsonValidateMap.get("hashes")) {
                    linkedListErrors.addLast("The field \"hashes\" in the " + (i+1) + " of the artifacts field is null.");
                } else {
                    LinkedList<String> validationLinkedList = validationHashesModel(artifacts.get(i).getHashes());
                    for (String field : validationLinkedList) {
                        linkedListErrors.addLast("The field \"" + field + "\" in the "
                                + (i+1) + " artifacts object in the hashes field is null.");
                    }
                }

                if (jsonValidateMap.get("file")) {
                    linkedListErrors.addLast("The field \"file\" in the " + (i+1) + " of the artifacts field is null.");
                }
            }

            if (jsonValidateMap.get("target_repository")) {
                linkedListErrors.addLast("The field \"target_repository\" in the "
                        + (i+1) + " object of the artifacts field is null.");
            }
        }
    }

    private void validationMVNModel(ArrayList<MVN> mvns) {
        for (int i = 0; i < mvns.size(); ++i) {
            HashMap<String, Boolean> jsonValidateMap = mvns.get(i).validate();
            for (Map.Entry<String, Boolean> entry : jsonValidateMap.entrySet()) {
                if (entry.getValue()) {
                    linkedListErrors.addLast("The field \"" + entry.getKey() + "\" in the "
                            + (i+1) + " object in the \"mvn\" is null.");
                } else if (entry.getKey().equals("hashes")) {
                    LinkedList<String> validationLinkedList = validationHashesModel(mvns.get(i).getHashes());
                    for (String field : validationLinkedList) {
                        linkedListErrors.addLast("The field \"" + field + "\" in the "
                                + (i+1) + " mvn object in the hashes field is null.");
                    }
                }
            }
        }
    }

    private void validationScriptModel(ArrayList<Script> scripts) {
        for (int i = 0; i < scripts.size(); ++i) {
            HashMap<String, Boolean> jsonValidateMap = scripts.get(i).validate();
            for (Map.Entry<String, Boolean> entry : jsonValidateMap.entrySet()) {
                if (entry.getValue()) {
                    linkedListErrors.addLast("The field \"" + entry.getKey() + "\" in the "
                            + (i+1) + " object in the \"script\" is null.");
                } else if (entry.getKey().equals("hashes")) {
                    LinkedList<String> validationLinkedList = validationHashesModel(scripts.get(i).getHashes());
                    for (String field : validationLinkedList) {
                        linkedListErrors.addLast("The field \"" + field + "\" in the "
                                + (i+1) + " script object in the hashes field is null.");
                    }
                }
            }
        }
    }

    private void validationRPMModel(ArrayList<RPM> rpms) {
        for (int i = 0; i < rpms.size(); ++i) {
            HashMap<String, Boolean> jsonValidateMap = rpms.get(i).validate();
            for (Map.Entry<String, Boolean> entry : jsonValidateMap.entrySet()) {
                if (entry.getValue()) {
                    linkedListErrors.addLast("The field \"" + entry.getKey() + "\" in the "
                            + (i+1) + " object in the \"rpm\" is null.");
                } else if (entry.getKey().equals("hashes")) {
                    LinkedList<String> validationLinkedList = validationHashesModel(rpms.get(i).getHashes());
                    for (String field : validationLinkedList) {
                        linkedListErrors.addLast("The field \"" + field + "\" in the "
                                + (i+1) + " rpm object in the hashes field is null.");
                    }
                }
            }
        }
    }

    private void validationParametersModel(Parameters parameters) {
        HashMap<String, Boolean> jsonValidateMap = parameters.validate();
        for (Map.Entry<String, Boolean> entry : jsonValidateMap.entrySet()) {
            switch (entry.getKey()) {
                case "common":
                    if (!entry.getValue()) {
                        validationCommonModel(parameters.getCommon());
                    }
                    break;
                case "services":
                    if (!entry.getValue()) {
                        validationServiceNameModel(parameters.getServices());
                    }
            }
        }
    }

    private void validationCommonModel(Common common) {
        HashMap<String, Boolean> jsonValidateMap = common.validate();
        if (jsonValidateMap.get("some_param")) {
            linkedListErrors.addLast("The field \"some_param\" in the \"common\"-parameter is null.");
        }
    }

    private void validationServiceNameModel(ServiceName services) {
        HashMap<String, Boolean> jsonValidateMap = services.validate();
        if (jsonValidateMap.get("service_name")) {
            linkedListErrors.addLast("The field \"service_name\" in the \"services\"-parameter is null.");
        }

        if (!jsonValidateMap.get("service_name") && jsonValidateMap.get("some-third-param")) {
            linkedListErrors.addLast("The field \"some-third-param\" in the service_name-parameter of the parameters field is null.");
        }
    }

    public LinkedList<String> getLinkedListErrors() {
        return linkedListErrors;
    }
}
