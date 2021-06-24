package com.netcracker.operations;

import com.netcracker.models.*;

import java.util.*;

public class JSONComparator {

    private final LinkedList<Difference> diffLinkedList;

    public JSONComparator(JSONModel jsonModelFirst, JSONModel jsonModelSecond) {
        diffLinkedList = new LinkedList<>();
        compareJSON(jsonModelFirst, jsonModelSecond);
    }

    private void compareJSON(JSONModel jsonModelFirst, JSONModel jsonModelSecond) {
        //comparison metadata field
        compareMetadata(jsonModelFirst.getMetadata(), jsonModelSecond.getMetadata());

        //comparison services field
        compareServicesArray(jsonModelFirst.getServices(), jsonModelSecond.getServices());

        //comparison artifacts field
        compareArtifactsArray(jsonModelFirst.getArtifacts(), jsonModelSecond.getArtifacts());

        //comparison script field
        compareScriptArray(jsonModelFirst.getScripts(), jsonModelSecond.getScripts());

        //comparison rpm field
        compareRPMsArray(jsonModelFirst.getRpms(), jsonModelSecond.getRpms());

        //comparison parameters field
        compareParameters(jsonModelFirst.getParameters(), jsonModelSecond.getParameters());

    }

    private Difference createNewDiff(AbstractModel firstModel, AbstractModel secondModel,
                                     String pathFirst, String pathSecond, Operations operation) {

        Difference difference = new Difference(firstModel, secondModel);
        difference.addOperation(operation);
        difference.addPathFirstModel(pathFirst);
        difference.addPathSecondModel(pathSecond);
        return difference;
    }

    private void addDataInDiff(Difference difference, Operations operation, String pathFirst, String pathSecond) {
        difference.addOperation(operation);
        difference.addPathFirstModel(pathFirst);
        difference.addPathSecondModel(pathSecond);
    }

    private void compareMetadata(Metadata metadataFirst, Metadata metadataSecond) {
        //comparison application field
        compareApplication(metadataFirst.getApplication(), metadataSecond.getApplication());

        //comparison description field
        compareDescription(metadataFirst.getDescription(), metadataSecond.getDescription());
    }

    private void compareApplication(Application appFirst, Application appSecond) {
        if (appFirst.getName() != null && appSecond.getName() != null) {
            if (!appFirst.getName().equals(appSecond.getName())) {
                diffLinkedList.addLast(createNewDiff(appFirst, appSecond,"metadata/application/name",
                                                    "metadata/application/name", Operations.REPLACE));
            }
        } else if (appFirst.getName() != null) {
            diffLinkedList.addLast(createNewDiff(appFirst, appSecond,"metadata/application/name",
                                                "metadata/application/name", Operations.DELETE));
        } else if (appSecond.getName() != null) {
            diffLinkedList.addLast(createNewDiff(appFirst, appSecond,"metadata/application/name",
                                                    "metadata/application/name", Operations.ADD));
        }
    }

    private void compareDescription(Description desFirst, Description desSecond) {
        if (desFirst.getVersion() != null && desSecond.getVersion() != null) {
            if (!desFirst.getVersion().equals(desSecond.getVersion())) {
                diffLinkedList.addLast(createNewDiff(desFirst, desSecond,"metadata/description/version",
                                                    "metadata/description/version", Operations.REPLACE));
            }
        } else if (desFirst.getVersion() != null) {
            diffLinkedList.addLast(createNewDiff(desFirst, desSecond,"metadata/description/version",
                    "metadata/description/version", Operations.DELETE));
        } else if (desSecond.getVersion() != null) {
            diffLinkedList.addLast(createNewDiff(desFirst, desSecond,"metadata/description/version",
                                                    "metadata/description/version", Operations.ADD));
        }
    }

    private void compareServicesArray(ArrayList<Service> servicesFirst, ArrayList<Service> servicesSecond) {
        for (int i = 0; i < servicesFirst.size(); ++i) {
            boolean flag = true;
            for (int j = 0; j < servicesSecond.size(); ++j) {
                if (servicesFirst.get(i).equals(servicesSecond.get(j))) {
                    compareService(servicesFirst.get(i), servicesSecond.get(j), i, j);
                    flag = false;
                    break;
                }
            }
            if (flag) {
                compareServiceSecondNull(servicesFirst.get(i), i);
            }
        }

        for (int i = 0; i < servicesSecond.size(); ++i) {
            boolean flag = true;
            for (int j = 0; j < servicesFirst.size(); ++j) {
                if (servicesFirst.get(j).equals(servicesSecond.get(i))) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                compareServiceFirstNull(servicesSecond.get(i), i);
            }
        }
    }

    private void compareServiceSecondNull(Service service, int objNumber) {

        Difference diffNull = new Difference(service, null);

        // mandatory fields
        diffNull.addOperation(Operations.DELETE);
        diffNull.addPathFirstModel("services/" + objNumber + "/service_name");
        diffNull.addOperation(Operations.DELETE);
        diffNull.addPathFirstModel("services/" + objNumber + "/artifact_type");
        diffNull.addOperation(Operations.DELETE);
        diffNull.addPathFirstModel("services/" + objNumber + "/docker_registry");
        diffNull.addOperation(Operations.DELETE);
        diffNull.addPathFirstModel("services/" + objNumber + "/docker_image_name");
        diffNull.addOperation(Operations.DELETE);
        diffNull.addPathFirstModel("services/" + objNumber + "/docker_tag");
        diffNull.addOperation(Operations.DELETE);
        diffNull.addPathFirstModel("services/" + objNumber + "/hashes");

        // optional fields
        if (service.getServiceShortName() != null) {
            diffNull.addOperation(Operations.DELETE);
            diffNull.addPathFirstModel("services/" + objNumber + "/service-short-name");
        }
        if (service.getForce() != null) {
            diffNull.addOperation(Operations.DELETE);
            diffNull.addPathFirstModel("services/" + objNumber + "/force");
        }
        if (service.getGithubRepository() != null) {
            diffNull.addOperation(Operations.DELETE);
            diffNull.addPathFirstModel("services/" + objNumber + "/github_repository");
        }
        if (service.getGithubBranch() != null) {
            diffNull.addOperation(Operations.DELETE);
            diffNull.addPathFirstModel("services/" + objNumber + "/github_branch");
        }
        if (service.getGithubHash() != null) {
            diffNull.addOperation(Operations.DELETE);
            diffNull.addPathFirstModel("services/" + objNumber + "/github_hash");
        }

        diffLinkedList.addLast(diffNull);
    }

    private void compareServiceFirstNull(Service service, int objNumber) {

        Difference diffNull = new Difference(null, service);

        // mandatory fields
        diffNull.addOperation(Operations.ADD);
        diffNull.addPathSecondModel("services/" + objNumber + "/service_name");
        diffNull.addOperation(Operations.ADD);
        diffNull.addPathSecondModel("services/" + objNumber + "/artifact_type");
        diffNull.addOperation(Operations.ADD);
        diffNull.addPathSecondModel("services/" + objNumber + "/docker_registry");
        diffNull.addOperation(Operations.ADD);
        diffNull.addPathSecondModel("services/" + objNumber + "/docker_image_name");
        diffNull.addOperation(Operations.ADD);
        diffNull.addPathSecondModel("services/" + objNumber + "/docker_tag");
        diffNull.addOperation(Operations.ADD);
        diffNull.addPathSecondModel("services/" + objNumber + "/hashes");

        // optional fields
        if (service.getServiceShortName() != null) {
            diffNull.addOperation(Operations.ADD);
            diffNull.addPathSecondModel("services/" + objNumber + "/service-short-name");
        }
        if (service.getForce() != null) {
            diffNull.addOperation(Operations.ADD);
            diffNull.addPathSecondModel("services/" + objNumber + "/force");
        }
        if (service.getGithubRepository() != null) {
            diffNull.addOperation(Operations.ADD);
            diffNull.addPathSecondModel("services/" + objNumber + "/github_repository");
        }
        if (service.getGithubBranch() != null) {
            diffNull.addOperation(Operations.ADD);
            diffNull.addPathSecondModel("services/" + objNumber + "/github_branch");
        }
        if (service.getGithubHash() != null) {
            diffNull.addOperation(Operations.ADD);
            diffNull.addPathSecondModel("services/" + objNumber + "/github_hash");
        }

        diffLinkedList.addLast(diffNull);
    }

    private void compareService(Service serviceFirst, Service serviceSecond, int firstObjNumber, int secondObjNumber) {
        Difference serviceDifference = new Difference(serviceFirst,serviceSecond);

        //comparison service-short-name field
        if (serviceFirst.getServiceShortName() != null && serviceSecond.getServiceShortName() != null) {
            if (!serviceFirst.getServiceShortName().equals(serviceSecond.getServiceShortName())) {
                addDataInDiff(serviceDifference,Operations.REPLACE,
                            "services/" + firstObjNumber + "/service-short-name",
                            "services/" + secondObjNumber + "/service-short-name");
            }
        } else if (serviceFirst.getServiceShortName() != null) {
            addDataInDiff(serviceDifference,Operations.DELETE,
                    "services/" + firstObjNumber + "/service-short-name",
                    "services/" + secondObjNumber + "/service-short-name");
        } else if (serviceSecond.getServiceShortName() != null) {
            addDataInDiff(serviceDifference,Operations.ADD,
                    "services/" + firstObjNumber + "/service-short-name",
                    "services/" + secondObjNumber + "/service-short-name");
        }

        //comparison force field
        if (serviceFirst.getForce() != null && serviceSecond.getForce() != null) {
            if (!serviceFirst.getForce().equals(serviceSecond.getForce())) {
                addDataInDiff(serviceDifference,Operations.REPLACE,
                        "services/" + firstObjNumber + "/force",
                        "services/" + secondObjNumber + "/force");
            }
        } else if (serviceFirst.getForce() != null) {
            addDataInDiff(serviceDifference,Operations.DELETE,
                    "services/" + firstObjNumber + "/force",
                    "services/" + secondObjNumber + "/force");
        } else if (serviceSecond.getForce() != null) {
            addDataInDiff(serviceDifference,Operations.ADD,
                    "services/" + firstObjNumber + "/force",
                    "services/" + secondObjNumber + "/force");
        }

        //comparison github_repository field
        if (serviceFirst.getGithubRepository() != null && serviceSecond.getGithubRepository() != null) {
            if (!serviceFirst.getGithubRepository().equals(serviceSecond.getGithubRepository())) {
                addDataInDiff(serviceDifference,Operations.REPLACE,
                        "services/" + firstObjNumber + "/github_repository",
                        "services/" + secondObjNumber + "/github_repository");
            }
        } else if (serviceFirst.getGithubRepository() != null) {
            addDataInDiff(serviceDifference,Operations.DELETE,
                    "services/" + firstObjNumber + "/github_repository",
                    "services/" + secondObjNumber + "/github_repository");
        } else if (serviceSecond.getGithubRepository() != null) {
            addDataInDiff(serviceDifference,Operations.ADD,
                    "services/" + firstObjNumber + "/github_repository",
                    "services/" + secondObjNumber + "/github_repository");
        }

        //comparison github_branch field
        if (serviceFirst.getGithubBranch() != null && serviceSecond.getGithubBranch() != null) {
            if (!serviceFirst.getGithubBranch().equals(serviceSecond.getGithubBranch())) {
                addDataInDiff(serviceDifference,Operations.REPLACE,
                        "services/" + firstObjNumber + "/github_branch",
                        "services/" + secondObjNumber + "/github_branch");
            }
        } else if (serviceFirst.getGithubBranch() != null) {
            addDataInDiff(serviceDifference,Operations.DELETE,
                    "services/" + firstObjNumber + "/github_branch",
                    "services/" + secondObjNumber + "/github_branch");
        } else if (serviceSecond.getGithubBranch() != null) {
            addDataInDiff(serviceDifference,Operations.ADD,
                    "services/" + firstObjNumber + "/github_branch",
                    "services/" + secondObjNumber + "/github_branch");
        }

        //comparison github_hash field
        if (serviceFirst.getGithubHash() != null && serviceSecond.getGithubHash() != null) {
            if (!serviceFirst.getGithubHash().equals(serviceSecond.getGithubHash())) {
                addDataInDiff(serviceDifference,Operations.REPLACE,
                        "services/" + firstObjNumber + "/github_hash",
                        "services/" + secondObjNumber + "/github_hash");
            }
        } else if (serviceFirst.getGithubBranch() != null) {
            addDataInDiff(serviceDifference,Operations.DELETE,
                    "services/" + firstObjNumber + "/github_hash",
                    "services/" + secondObjNumber + "/github_hash");
        } else if (serviceSecond.getGithubBranch() != null) {
            addDataInDiff(serviceDifference,Operations.ADD,
                    "services/" + firstObjNumber + "/github_hash",
                    "services/" + secondObjNumber + "/github_hash");
        }

        if (serviceDifference.checkDifference()) {
            diffLinkedList.addLast(serviceDifference);
        }
    }

    private void compareArtifactsArray(ArrayList<Artifact> artifactsFirst, ArrayList<Artifact> artifactsSecond) {
        for (int i = 0; i < artifactsFirst.size(); ++i) {
            boolean flag = true;
            for (int j = 0; j < artifactsSecond.size(); ++j) {
                if (artifactsFirst.get(i).isOther() && artifactsSecond.get(j).isOther() &&
                        artifactsFirst.get(i).equals(artifactsSecond.get(j))) {

                    compareArtifact(artifactsFirst.get(i), artifactsSecond.get(j), i, j);
                    flag = false;
                    break;
                } else if (artifactsFirst.get(i).isMVN() && artifactsSecond.get(j).isMVN() &&
                        artifactsFirst.get(i).getTargetRepository().equals(artifactsSecond.get(j).getTargetRepository())) {
                    compareMVNsArray(artifactsFirst.get(i).getMvns(), artifactsSecond.get(j).getMvns(), i, j);
                    flag = false;
                    break;
                }
            }
            if (flag) {
                if (artifactsFirst.get(i).isOther()) {
                    compareArtifactSecondNull(artifactsFirst.get(i), i);
                } else {
                    for (int j = 0; j < artifactsFirst.get(i).getMvns().size(); ++j) {
                        compareMVNSecondNull(artifactsFirst.get(i).getMvns().get(j), i, j);
                    }
                    Difference diffNull = new Difference(artifactsFirst.get(i), null);
                    diffNull.addOperation(Operations.DELETE);
                    diffNull.addPathFirstModel("artifacts/" + i + "/target_repository");
                    diffLinkedList.addLast(diffNull);
                }
            }
        }

        for (int i = 0; i < artifactsSecond.size(); ++i) {
            boolean flag = true;
            for (int j = 0; j < artifactsFirst.size(); ++j) {
                if (artifactsFirst.get(j).isOther() && artifactsSecond.get(i).isOther() &&
                        artifactsFirst.get(j).equals(artifactsSecond.get(i))) {

                    flag = false;
                    break;
                } else if (artifactsFirst.get(i).isMVN() && artifactsSecond.get(j).isMVN() &&
                                artifactsFirst.get(i).getTargetRepository().equals(artifactsSecond.get(j).getTargetRepository())) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                if (artifactsSecond.get(i).isOther()) {
                    compareArtifactFirstNull(artifactsSecond.get(i), i);
                } else {
                    for (int j = 0; j < artifactsSecond.get(i).getMvns().size(); ++j) {
                        compareMVNFirstNull(artifactsSecond.get(i).getMvns().get(j), i, j);
                    }
                    Difference diffNull = new Difference(null, artifactsSecond.get(i));
                    diffNull.addOperation(Operations.ADD);
                    diffNull.addPathSecondModel("artifacts/" + i + "/target_repository");
                    diffLinkedList.addLast(diffNull);
                }
            }
        }
    }

    private void compareMVNsArray(ArrayList<MVN> mvnsFirst, ArrayList<MVN> mvnsSecond, int firstObjNumber, int secondObjNumber) {
        for (int i = 0; i < mvnsFirst.size(); ++i) {
            boolean flag = true;
            for (int j = 0; j < mvnsSecond.size(); ++j) {
                if (mvnsFirst.get(i).equals(mvnsSecond.get(j))) {
                    compareMVN(mvnsFirst.get(i), mvnsSecond.get(j), i, j, firstObjNumber, secondObjNumber);
                    flag = false;
                    break;
                }
            }
            if (flag) {
                compareMVNSecondNull(mvnsFirst.get(i), i, firstObjNumber);
            }
        }

        for (int i = 0; i < mvnsSecond.size(); ++i) {
            boolean flag = true;
            for (int j = 0; j < mvnsFirst.size(); ++j) {
                if (mvnsFirst.get(j).equals(mvnsSecond.get(i))) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                compareMVNFirstNull(mvnsSecond.get(i), i, secondObjNumber);
            }
        }
    }

    private void compareMVNSecondNull(MVN mvn, int objNumber, int mvnNumber) {
        Difference diffNull = new Difference(mvn, null);

        // mandatory fields
        diffNull.addOperation(Operations.DELETE);
        diffNull.addPathFirstModel("artifacts/" + mvnNumber + "/mvn/" + objNumber + "/groupId");
        diffNull.addOperation(Operations.DELETE);
        diffNull.addPathFirstModel("artifacts/" + mvnNumber + "/mvn/" + objNumber + "/artifactId");
        diffNull.addOperation(Operations.DELETE);
        diffNull.addPathFirstModel("artifacts/" + mvnNumber + "/mvn/" + objNumber + "/version");
        diffNull.addOperation(Operations.DELETE);
        diffNull.addPathFirstModel("artifacts/" + mvnNumber + "/mvn/" + objNumber + "/mvn_type");
        diffNull.addOperation(Operations.DELETE);
        diffNull.addPathFirstModel("artifacts/" + mvnNumber + "/mvn/" + objNumber + "/mvn_repository");
        diffNull.addOperation(Operations.DELETE);
        diffNull.addPathFirstModel("artifacts/" + mvnNumber + "/mvn/" + objNumber + "/hashes");

        // optional fields
        if (mvn.getServiceName() != null) {
            diffNull.addOperation(Operations.DELETE);
            diffNull.addPathFirstModel("artifacts/" + mvnNumber + "/mvn/" + objNumber + "/service_name");
        }
        if (mvn.getClassifier() != null) {
            diffNull.addOperation(Operations.DELETE);
            diffNull.addPathFirstModel("artifacts/" + mvnNumber + "/mvn/" + objNumber + "/classifier");
        }

        diffLinkedList.addLast(diffNull);
    }

    private void compareMVNFirstNull(MVN mvn, int objNumber, int mvnNumber) {
        Difference diffNull = new Difference(null, mvn);

        // mandatory fields
        diffNull.addOperation(Operations.ADD);
        diffNull.addPathSecondModel("artifacts/" + mvnNumber + "/mvn/" + objNumber + "/groupId");
        diffNull.addOperation(Operations.ADD);
        diffNull.addPathSecondModel("artifacts/" + mvnNumber + "/mvn/" + objNumber + "/artifactId");
        diffNull.addOperation(Operations.ADD);
        diffNull.addPathSecondModel("artifacts/" + mvnNumber + "/mvn/" + objNumber + "/version");
        diffNull.addOperation(Operations.ADD);
        diffNull.addPathSecondModel("artifacts/" + mvnNumber + "/mvn/" + objNumber + "/mvn_type");
        diffNull.addOperation(Operations.ADD);
        diffNull.addPathSecondModel("artifacts/" + mvnNumber + "/mvn/" + objNumber + "/mvn_repository");
        diffNull.addOperation(Operations.ADD);
        diffNull.addPathSecondModel("artifacts/" + mvnNumber + "/mvn/" + objNumber + "/hashes");

        // optional fields
        if (mvn.getServiceName() != null) {
            diffNull.addOperation(Operations.ADD);
            diffNull.addPathSecondModel("artifacts/" + mvnNumber + "/mvn/" + objNumber + "/service_name");
        }
        if (mvn.getClassifier() != null) {
            diffNull.addOperation(Operations.ADD);
            diffNull.addPathSecondModel("artifacts/" + mvnNumber + "/mvn/" + objNumber + "/classifier");
        }

        diffLinkedList.addLast(diffNull);
    }

    private void compareMVN(MVN mvnFirst, MVN mvnSecond, int firstObjNumber, int secondObjNumber,
                                                            int firstMVNNumber, int secondMVNNumber) {
        Difference mvnDifference = new Difference(mvnFirst,mvnSecond);

        //comparison service_name field
        if (mvnFirst.getServiceName() != null && mvnSecond.getServiceName() != null) {
            if (mvnFirst.getServiceName().equals(mvnSecond.getServiceName())) {
                addDataInDiff(mvnDifference,Operations.REPLACE,
                        "artifacts/" + firstMVNNumber + "/mvn/" + firstObjNumber + "/service_name",
                        "artifacts/" + secondMVNNumber + "/mvn/" + secondObjNumber + "/service_name");
            }
        } else if (mvnFirst.getServiceName() != null) {
            addDataInDiff(mvnDifference,Operations.DELETE,
                    "artifacts/" + firstMVNNumber + "/mvn/" + firstObjNumber + "/service_name",
                    "artifacts/" + secondMVNNumber + "/mvn/" + secondObjNumber + "/service_name");
        } else if (mvnSecond.getServiceName() != null) {
            addDataInDiff(mvnDifference,Operations.ADD,
                    "artifacts/" + firstMVNNumber + "/mvn/" + firstObjNumber + "/service_name",
                    "artifacts/" + secondMVNNumber + "/mvn/" + secondObjNumber + "/service_name");
        }

        //comparison classifier field
        if (mvnFirst.getServiceName() != null && mvnSecond.getServiceName() != null) {
            if (mvnFirst.getServiceName().equals(mvnSecond.getServiceName())) {
                addDataInDiff(mvnDifference,Operations.REPLACE,
                        "artifacts/" + firstMVNNumber + "/mvn/" + firstObjNumber + "/classifier",
                        "artifacts/" + secondMVNNumber + "/mvn/" + secondObjNumber + "/classifier");
            }
        } else if (mvnFirst.getServiceName() != null) {
            addDataInDiff(mvnDifference,Operations.DELETE,
                    "artifacts/" + firstMVNNumber + "/mvn/" + firstObjNumber + "/classifier",
                    "artifacts/" + secondMVNNumber + "/mvn/" + secondObjNumber + "/classifier");
        } else if (mvnSecond.getServiceName() != null) {
            addDataInDiff(mvnDifference,Operations.ADD,
                    "artifacts/" + firstMVNNumber + "/mvn/" + firstObjNumber + "/classifier",
                    "artifacts/" + secondMVNNumber + "/mvn/" + secondObjNumber + "/classifier");
        }

        if (mvnDifference.checkDifference()) {
            diffLinkedList.addLast(mvnDifference);
        }
    }

    private void compareArtifactSecondNull(Artifact artifact, int objNumber) {
        Difference diffNull = new Difference(artifact, null);

        // mandatory fields
        diffNull.addOperation(Operations.DELETE);
        diffNull.addPathFirstModel("artifacts/" + objNumber + "/hashes");
        diffNull.addOperation(Operations.DELETE);
        diffNull.addPathFirstModel("artifacts/" + objNumber + "/file");
        diffNull.addOperation(Operations.DELETE);
        diffNull.addPathFirstModel("artifacts/" + objNumber + "/target_repository");
        diffNull.addOperation(Operations.DELETE);

        // optional fields
        if (artifact.getServiceShortName() != null) {
            diffNull.addOperation(Operations.DELETE);
            diffNull.addPathFirstModel("artifacts/" + objNumber + "/service-short-name");
        }
        if (artifact.getServiceName() != null) {
            diffNull.addOperation(Operations.DELETE);
            diffNull.addPathFirstModel("artifacts/" + objNumber + "/service_name");
        }

        diffLinkedList.addLast(diffNull);
    }

    private void compareArtifactFirstNull(Artifact artifact, int objNumber) {
        Difference diffNull = new Difference(null, artifact);

        // mandatory fields
        diffNull.addOperation(Operations.ADD);
        diffNull.addPathSecondModel("artifacts/" + objNumber + "/hashes");
        diffNull.addOperation(Operations.ADD);
        diffNull.addPathSecondModel("artifacts/" + objNumber + "/file");
        diffNull.addOperation(Operations.ADD);
        diffNull.addPathSecondModel("artifacts/" + objNumber + "/target_repository");
        diffNull.addOperation(Operations.ADD);

        // optional fields
        if (artifact.getServiceShortName() != null) {
            diffNull.addOperation(Operations.ADD);
            diffNull.addPathSecondModel("artifacts/" + objNumber + "/service-short-name");
        }
        if (artifact.getServiceName() != null) {
            diffNull.addOperation(Operations.ADD);
            diffNull.addPathSecondModel("artifacts/" + objNumber + "/service_name");
        }

        diffLinkedList.addLast(diffNull);
    }

    private void compareArtifact(Artifact artifactFirst, Artifact artifactSecond,
                                            int firstObjNumber, int secondObjNumber) {

        Difference artifactDifference = new Difference(artifactFirst,artifactSecond);

        if (artifactFirst.getServiceShortName() != null && artifactSecond.getServiceShortName() != null) {
            if (artifactFirst.getServiceShortName().equals(artifactSecond.getServiceShortName())) {
                addDataInDiff(artifactDifference,Operations.REPLACE,
                        "artifacts/" + firstObjNumber + "/service-short-name",
                        "artifacts/" + secondObjNumber + "/service-short-name");
            }
        } else if (artifactFirst.getServiceShortName() != null) {
            addDataInDiff(artifactDifference,Operations.DELETE,
                    "artifacts/" + firstObjNumber + "/service-short-name",
                    "artifacts/" + secondObjNumber + "/service-short-name");
        } else if (artifactSecond.getServiceShortName() != null) {
            addDataInDiff(artifactDifference,Operations.ADD,
                    "artifacts/" + firstObjNumber + "/service-short-name",
                    "artifacts/" + secondObjNumber + "/service-short-name");
        }

        if (artifactFirst.getServiceName() != null && artifactSecond.getServiceName() != null) {
            if (artifactFirst.getServiceName().equals(artifactSecond.getServiceName())) {
                addDataInDiff(artifactDifference,Operations.REPLACE,
                        "artifacts/" + firstObjNumber + "/service_name",
                        "artifacts/" + secondObjNumber + "/service_name");
            }
        } else if (artifactFirst.getServiceName() != null) {
            addDataInDiff(artifactDifference,Operations.DELETE,
                    "artifacts/" + firstObjNumber + "/service_name",
                    "artifacts/" + secondObjNumber + "/service_name");
        } else if (artifactSecond.getServiceName() != null) {
            addDataInDiff(artifactDifference,Operations.ADD,
                    "artifacts/" + firstObjNumber + "/service_name",
                    "artifacts/" + secondObjNumber + "/service_name");
        }

        if (artifactDifference.checkDifference()) {
            diffLinkedList.addLast(artifactDifference);
        }
    }

    private void compareScriptArray(ArrayList<Script> scriptsFirst, ArrayList<Script> scriptsSecond) {
        for (int i = 0; i < scriptsFirst.size(); ++i) {
            boolean flag = true;
            for (int j = 0; j < scriptsSecond.size(); ++j) {
                if (scriptsFirst.get(i).equals(scriptsSecond.get(j))) {
                    compareScript(scriptsFirst.get(i), scriptsSecond.get(j), i, j);
                    flag = false;
                    break;
                }
            }
            if (flag) {
                compareScriptSecondNull(scriptsFirst.get(i), i);
            }
        }

        for (int i = 0; i < scriptsSecond.size(); ++i) {
            boolean flag = true;
            for (int j = 0; j < scriptsFirst.size(); ++j) {
                if (scriptsFirst.get(j).equals(scriptsSecond.get(i))) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                compareScriptFirstNull(scriptsSecond.get(i), i);
            }
        }
    }

    private void compareScriptSecondNull(Script script, int objNumber) {

        Difference diffNull = new Difference(script, null);

        // mandatory fields
        diffNull.addOperation(Operations.DELETE);
        diffNull.addPathFirstModel("script/" + objNumber + "/script_name");
        diffNull.addOperation(Operations.DELETE);
        diffNull.addPathFirstModel("script/" + objNumber + "/hashes");
        diffNull.addOperation(Operations.DELETE);
        diffNull.addPathFirstModel("script/" + objNumber + "/url");

        // optional fields
        if (script.getServiceShortName() != null) {
            diffNull.addOperation(Operations.DELETE);
            diffNull.addPathFirstModel("script/" + objNumber + "/service-short-name");
        }
        if (script.getStartPoint() != null) {
            diffNull.addOperation(Operations.DELETE);
            diffNull.addPathFirstModel("script/" + objNumber + "/start-point");
        }
        if (script.getEndPoint() != null) {
            diffNull.addOperation(Operations.DELETE);
            diffNull.addPathFirstModel("script/" + objNumber + "/end-point");
        }

        diffLinkedList.addLast(diffNull);
    }

    private void compareScriptFirstNull(Script script, int objNumber) {

        Difference diffNull = new Difference(null, script);

        // mandatory fields
        diffNull.addOperation(Operations.ADD);
        diffNull.addPathSecondModel("script/" + objNumber + "/script_name");
        diffNull.addOperation(Operations.ADD);
        diffNull.addPathSecondModel("script/" + objNumber + "/hashes");
        diffNull.addOperation(Operations.ADD);
        diffNull.addPathSecondModel("script/" + objNumber + "/url");

        // optional fields
        if (script.getServiceShortName() != null) {
            diffNull.addOperation(Operations.ADD);
            diffNull.addPathSecondModel("script/" + objNumber + "/service-short-name");
        }
        if (script.getStartPoint() != null) {
            diffNull.addOperation(Operations.ADD);
            diffNull.addPathSecondModel("script/" + objNumber + "/start-point");
        }
        if (script.getEndPoint() != null) {
            diffNull.addOperation(Operations.ADD);
            diffNull.addPathSecondModel("script/" + objNumber + "/end-point");
        }

        diffLinkedList.addLast(diffNull);
    }

    private void compareScript(Script scriptFirst, Script scriptSecond, int firstObjNumber, int secondObjNumber) {
        Difference scriptDifference = new Difference(scriptFirst,scriptSecond);

        //comparison service-short-name field
        if (scriptFirst.getServiceShortName() != null && scriptSecond.getServiceShortName() != null) {
            if (!scriptFirst.getServiceShortName().equals(scriptSecond.getServiceShortName())) {
                addDataInDiff(scriptDifference,Operations.REPLACE,
                        "script/" + firstObjNumber + "/service-short-name",
                        "script/" + secondObjNumber + "/service-short-name");
            }
        } else if (scriptFirst.getServiceShortName() != null) {
            addDataInDiff(scriptDifference,Operations.DELETE,
                    "script/" + firstObjNumber + "/service-short-name",
                    "script/" + secondObjNumber + "/service-short-name");
        } else if (scriptSecond.getServiceShortName() != null) {
            addDataInDiff(scriptDifference,Operations.ADD,
                    "script/" + firstObjNumber + "/service-short-name",
                    "script/" + secondObjNumber + "/service-short-name");
        }

        //comparison start-point field
        if (scriptFirst.getStartPoint() != null && scriptSecond.getStartPoint() != null) {
            if (!scriptFirst.getStartPoint().equals(scriptSecond.getStartPoint())) {
                addDataInDiff(scriptDifference,Operations.REPLACE,
                        "script/" + firstObjNumber + "/start-point",
                        "script/" + secondObjNumber + "/start-point");
            }
        } else if (scriptFirst.getStartPoint() != null) {
            addDataInDiff(scriptDifference,Operations.DELETE,
                    "script/" + firstObjNumber + "/start-point",
                    "script/" + secondObjNumber + "/start-point");
        } else if (scriptSecond.getStartPoint() != null) {
            addDataInDiff(scriptDifference,Operations.ADD,
                    "script/" + firstObjNumber + "/start-point",
                    "script/" + secondObjNumber + "/start-point");
        }

        //comparison end-point field
        if (scriptFirst.getEndPoint() != null && scriptSecond.getEndPoint() != null) {
            if (!scriptFirst.getEndPoint().equals(scriptSecond.getEndPoint())) {
                addDataInDiff(scriptDifference,Operations.REPLACE,
                        "script/" + firstObjNumber + "/end-point",
                        "script/" + secondObjNumber + "/end-point");
            }
        } else if (scriptFirst.getEndPoint() != null) {
            addDataInDiff(scriptDifference,Operations.DELETE,
                    "script/" + firstObjNumber + "/end-point",
                    "script/" + secondObjNumber + "/end-point");
        } else if (scriptSecond.getEndPoint() != null) {
            addDataInDiff(scriptDifference,Operations.ADD,
                    "script/" + firstObjNumber + "/end-point",
                    "script/" + secondObjNumber + "/end-point");
        }

        if (scriptDifference.checkDifference()) {
            diffLinkedList.addLast(scriptDifference);
        }
    }

    private void compareRPMsArray(ArrayList<RPM> rpmsFirst, ArrayList<RPM> rpmsSecond) {
        for (int i = 0; i < rpmsFirst.size(); ++i) {
            boolean flag = true;
            for (int j = 0; j < rpmsSecond.size(); ++j) {
                if (rpmsFirst.get(i).equals(rpmsSecond.get(j))) {
                    compareRPM(rpmsFirst.get(i), rpmsSecond.get(j), i, j);
                    flag = false;
                    break;
                }
            }
            if (flag) {
                compareRPMSecondNull(rpmsFirst.get(i), i);
            }
        }

        for (int i = 0; i < rpmsSecond.size(); ++i) {
            boolean flag = true;
            for (int j = 0; j < rpmsFirst.size(); ++j) {
                if (rpmsFirst.get(j).equals(rpmsSecond.get(i))) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                compareRPMFirstNull(rpmsSecond.get(i), i);
            }
        }
    }

    private void compareRPMSecondNull(RPM rpm, int objNumber) {

        Difference diffNull = new Difference(rpm, null);

        // mandatory fields
        diffNull.addOperation(Operations.DELETE);
        diffNull.addPathFirstModel("rpm/" + objNumber + "/rpm_repository_name");
        diffNull.addOperation(Operations.DELETE);
        diffNull.addPathFirstModel("rpm/" + objNumber + "/hashes");
        diffNull.addOperation(Operations.DELETE);
        diffNull.addPathFirstModel("rpm/" + objNumber + "/url");

        // optional fields
        if (rpm.getServiceShortName() != null) {
            diffNull.addOperation(Operations.DELETE);
            diffNull.addPathFirstModel("rpm/" + objNumber + "/service-short-name");
        }

        diffLinkedList.addLast(diffNull);
    }

    private void compareRPMFirstNull(RPM rpm, int objNumber) {

        Difference diffNull = new Difference(null, rpm);

        // mandatory fields
        diffNull.addOperation(Operations.ADD);
        diffNull.addPathSecondModel("rpm/" + objNumber + "/rpm_repository_name");
        diffNull.addOperation(Operations.ADD);
        diffNull.addPathSecondModel("rpm/" + objNumber + "/hashes");
        diffNull.addOperation(Operations.ADD);
        diffNull.addPathSecondModel("rpm/" + objNumber + "/url");

        // optional fields
        if (rpm.getServiceShortName() != null) {
            diffNull.addOperation(Operations.ADD);
            diffNull.addPathSecondModel("rpm/" + objNumber + "/service-short-name");
        }

        diffLinkedList.addLast(diffNull);
    }

    private void compareRPM(RPM rpmFirst, RPM rpmSecond, int firstObjNumber, int secondObjNumber) {
        Difference rpmDifference = new Difference(rpmFirst, rpmSecond);

        //comparison service-short-name field
        if (rpmFirst.getServiceShortName() != null && rpmSecond.getServiceShortName() != null) {
            if (!rpmFirst.getServiceShortName().equals(rpmSecond.getServiceShortName())) {
                addDataInDiff(rpmDifference, Operations.REPLACE,
                        "rpm/" + firstObjNumber + "/service-short-name",
                        "rpm/" + secondObjNumber + "/service-short-name");
            }
        } else if (rpmFirst.getServiceShortName() != null) {
            addDataInDiff(rpmDifference, Operations.DELETE,
                    "rpm/" + firstObjNumber + "/service-short-name",
                    "rpm/" + secondObjNumber + "/service-short-name");
        } else if (rpmSecond.getServiceShortName() != null) {
            addDataInDiff(rpmDifference, Operations.ADD,
                    "rpm/" + firstObjNumber + "/service-short-name",
                    "rpm/" + secondObjNumber + "/service-short-name");
        }

        if (rpmDifference.checkDifference()) {
            diffLinkedList.addLast(rpmDifference);
        }
    }

    private void compareParameters(Parameters parametersFirst, Parameters parametersSecond) {
        // comparison common fields
        compareCommon(parametersFirst.getCommon(), parametersSecond.getCommon());

        // comparison services fields
        compareServiceName(parametersFirst.getServices(), parametersSecond.getServices());
    }

    private void compareCommon(Common commonFirst, Common commonSecond) {

        Difference commonDifference = new Difference(commonFirst, commonSecond);

        if (commonFirst != null && commonSecond != null) {
            if (commonFirst.equals(commonSecond)) {
                // comparison some-other-param field
                if (commonFirst.getSomeOtherParam() != null && commonSecond.getSomeOtherParam() != null) {
                    if (!commonFirst.getSomeOtherParam().equals(commonSecond.getSomeOtherParam())) {
                        addDataInDiff(commonDifference, Operations.REPLACE,
                                "parameters/common/some-other-param",
                                "parameters/common/some-other-param");
                    }
                } else if (commonFirst.getSomeOtherParam() != null) {
                    addDataInDiff(commonDifference, Operations.DELETE,
                            "parameters/common/some-other-param",
                            "parameters/common/some-other-param");
                } else if (commonSecond.getSomeOtherParam() != null) {
                    addDataInDiff(commonDifference, Operations.ADD,
                            "parameters/common/some-other-param",
                            "parameters/common/some-other-param");
                }

                // comparison some-else-param field
                if (commonFirst.getSomeElseParam() != null && commonSecond.getSomeElseParam() != null) {
                    if (!commonFirst.getSomeElseParam().equals(commonSecond.getSomeElseParam())) {
                        addDataInDiff(commonDifference, Operations.REPLACE,
                                "parameters/common/some-else-param",
                                "parameters/common/some-else-param");
                    }
                } else if (commonFirst.getSomeElseParam() != null) {
                    addDataInDiff(commonDifference, Operations.DELETE,
                            "parameters/common/some-else-param",
                            "parameters/common/some-else-param");
                } else if (commonSecond.getSomeElseParam() != null) {
                    addDataInDiff(commonDifference, Operations.ADD,
                            "parameters/common/some-else-param",
                            "parameters/common/some-else-param");
                }
            }
        } else if (commonFirst != null) {
            // mandatory fields
            commonDifference.addOperation(Operations.DELETE);
            commonDifference.addPathFirstModel("parameters/common/some-param");

            // optional fields
            if (commonFirst.getSomeOtherParam() != null) {
                commonDifference.addOperation(Operations.DELETE);
                commonDifference.addPathFirstModel("parameters/common/some-other-param");
            }
            if (commonFirst.getSomeElseParam() != null) {
                commonDifference.addOperation(Operations.DELETE);
                commonDifference.addPathFirstModel("parameters/common/some-else-param");
            }
        } else if (commonSecond != null) {
            // mandatory fields
            commonDifference.addOperation(Operations.ADD);
            commonDifference.addPathSecondModel("parameters/common/some-param");

            // optional fields
            if (commonSecond.getSomeOtherParam() != null) {
                commonDifference.addOperation(Operations.ADD);
                commonDifference.addPathSecondModel("parameters/common/some-other-param");
            }
            if (commonSecond.getSomeElseParam() != null) {
                commonDifference.addOperation(Operations.ADD);
                commonDifference.addPathSecondModel("parameters/common/some-else-param");
            }
        }

        if (commonDifference.checkDifference()) {
            diffLinkedList.addLast(commonDifference);
        }
    }

    private void compareServiceName(ServiceName serviceNameFirst, ServiceName serviceNameSecond) {
        Difference servicesDifference = new Difference(serviceNameFirst, serviceNameSecond);

        if (serviceNameFirst != null && serviceNameSecond != null) {
            compareMapsServiceName(serviceNameFirst.getServiceName(), serviceNameSecond.getServiceName(), servicesDifference, "service_name");
            compareMapsServiceName(serviceNameFirst.getServiceName1(), serviceNameSecond.getServiceName1(), servicesDifference, "service_name_1");
            compareMapsServiceName(serviceNameFirst.getServiceName2(), serviceNameSecond.getServiceName2(), servicesDifference, "service_name_2");
        } else if (serviceNameFirst != null) {
            serviceNameSecondNull(serviceNameFirst.getServiceName(), servicesDifference, "service_name");
            serviceNameSecondNull(serviceNameFirst.getServiceName1(), servicesDifference, "service_name_1");
            serviceNameSecondNull(serviceNameFirst.getServiceName2(), servicesDifference, "service_name_2");
        } else if (serviceNameSecond != null) {
            serviceNameFirstNull(serviceNameSecond.getServiceName(), servicesDifference, "service_name");
            serviceNameFirstNull(serviceNameSecond.getServiceName1(), servicesDifference, "service_name_1");
            serviceNameFirstNull(serviceNameSecond.getServiceName2(), servicesDifference, "service_name_2");
        }

        if (servicesDifference.checkDifference()) {
            diffLinkedList.addLast(servicesDifference);
        }
    }

    private void compareMapsServiceName(HashMap<String,String> firstMap, HashMap<String,String> secondMap,
                                                                        Difference difference, String field) {
        if (firstMap != null && secondMap != null) {
            for (Map.Entry<String, String> entry : firstMap.entrySet()) {
                if (secondMap.containsKey(entry.getKey())) {
                    if (entry.getValue() != null && secondMap.get(entry.getKey()) != null) {
                        if (!entry.getValue().equals(secondMap.get(entry.getKey()))) {
                            addDataInDiff(difference, Operations.REPLACE,
                                    "parameters/services/" + field + "/" + entry.getKey(),
                                    "parameters/services/" + field + "/" + entry.getKey());
                        }
                    } else if (entry.getValue() != null) {
                        addDataInDiff(difference, Operations.DELETE,
                                "parameters/services/" + field + "/" + entry.getKey(),
                                "parameters/services/" + field + "/" + entry.getKey());
                    } else if (secondMap.get(entry.getKey()) != null) {
                        addDataInDiff(difference, Operations.ADD,
                                "parameters/services/" + field + "/" + entry.getKey(),
                                "parameters/services/" + field + "/" + entry.getKey());
                    }
                } else {
                    difference.addOperation(Operations.DELETE);
                    difference.addPathFirstModel("parameters/services/" + field + "/" + entry.getKey());
                }
            }

            for (Map.Entry<String, String> entry : secondMap.entrySet()) {
                if (!firstMap.containsKey(entry.getKey())) {
                    difference.addOperation(Operations.ADD);
                    difference.addPathSecondModel("parameters/services/" + field + "/" + entry.getKey());
                }
            }
        } else if (firstMap != null) {
            for (Map.Entry<String, String> entry : firstMap.entrySet()) {
                difference.addOperation(Operations.DELETE);
                difference.addPathFirstModel("parameters/services/" + field + "/" + entry.getKey());
                /*addDataInDiff(difference, Operations.DELETE,
                        "parameters/services/service_name/" + entry.getKey(),
                        "parameters/services/service_name/" + entry.getKey());*/
            }
        } else if (secondMap != null) {
            for (Map.Entry<String, String> entry : secondMap.entrySet()) {
                difference.addOperation(Operations.ADD);
                difference.addPathSecondModel("parameters/services/" + field + "/" + entry.getKey());
                /*addDataInDiff(difference, Operations.ADD,
                        "parameters/services/service_name/" + entry.getKey(),
                        "parameters/services/service_name/" + entry.getKey());*/
            }
        }
    }

    private void serviceNameSecondNull(HashMap<String,String> firstMap, Difference difference, String field) {
        if (firstMap != null) {
            for (Map.Entry<String, String> entry : firstMap.entrySet()) {
                difference.addOperation(Operations.DELETE);
                difference.addPathFirstModel("parameters/services/" + field + "/" + entry.getKey());
                /*addDataInDiff(difference, Operations.DELETE,
                        "parameters/services/service_name/" + entry.getKey(),
                        "parameters/services/service_name/" + entry.getKey());*/
            }
        } else {
            difference.addOperation(Operations.DELETE);
            difference.addPathFirstModel("parameters/services/" + field);
        }
    }

    private void serviceNameFirstNull(HashMap<String,String> secondMap, Difference difference, String field) {
        if (secondMap != null) {
            for (Map.Entry<String, String> entry : secondMap.entrySet()) {
                difference.addOperation(Operations.ADD);
                difference.addPathSecondModel("parameters/services/" + field + "/" + entry.getKey());
                /*addDataInDiff(difference, Operations.DELETE,
                        "parameters/services/service_name/" + entry.getKey(),
                        "parameters/services/service_name/" + entry.getKey());*/
            }
        } else {
            difference.addOperation(Operations.ADD);
            difference.addPathSecondModel("parameters/services/" + field);
        }
    }

    public LinkedList<Difference> getDiffsList() {
        return diffLinkedList;
    }
}
