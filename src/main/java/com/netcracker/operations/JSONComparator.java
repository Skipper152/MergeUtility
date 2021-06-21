package com.netcracker.operations;

import com.netcracker.models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class JSONComparator {

    private final LinkedList<Difference> diffLinkedList;

    public JSONComparator(JSONModel jsonModel1, JSONModel jsonModel2) {
        diffLinkedList = new LinkedList<>();
        compareJSON(jsonModel1, jsonModel2);
    }

    private void compareJSON(JSONModel jsonModel1, JSONModel jsonModel2) {
        //comparison metadata field
        compareMetadata(jsonModel1.getMetadata(), jsonModel2.getMetadata());

        //comparison services field
        compareServicesArray(jsonModel1.getServices(), jsonModel2.getServices());

        //comparison artifacts field


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
                if (checkForMatchServices(servicesFirst.get(i), servicesSecond.get(j))) {
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
                if (checkForMatchServices(servicesFirst.get(i), servicesSecond.get(j))) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                compareServiceFirstNull(servicesSecond.get(i), i);
            }
        }
    }

    private Boolean checkForMatchServices(Service serviceBegin, Service serviceEnd) {
        return serviceBegin.getServiceName().equals(serviceEnd.getServiceName()) &&
                serviceBegin.getArtifactType().equals(serviceEnd.getArtifactType()) &&
                serviceBegin.getDockerRegistry().equals(serviceEnd.getDockerRegistry()) &&
                serviceBegin.getDockerImageName().equals(serviceEnd.getDockerImageName()) &&
                serviceBegin.getDockerTag().equals(serviceEnd.getDockerTag()) &&
                serviceBegin.getHashes().getSha1().equals(serviceEnd.getHashes().getSha1()) &&
                serviceBegin.getHashes().getSha256().equals(serviceEnd.getHashes().getSha256());
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

    /*private void compareArtifactsArray(ArrayList<Artifact> artifacts1, ArrayList<Artifact> artifacts2) {
        if (artifacts1.size() == artifacts2.size()) {
            for (int i = 0; i < artifacts1.size(); ++i) {
                compareArtifact(artifacts1.get(i),artifacts2.get(i),i);
            }
        }
    }

    private void compareArtifact(Artifact artifact1, Artifact artifact2, int objNumber) {
        HashMap<String, Boolean> validateMap1 = artifact1.validate();
        HashMap<String, Boolean> validateMap2 = artifact2.validate();

        if ((validateMap1.get("service-short-name") || validateMap1.get("service_name") ||
                validateMap1.get("hashes") || validateMap1.get("file")) &&
            (validateMap2.get("service-short-name") || validateMap2.get("service_name") ||
                validateMap2.get("hashes") || validateMap2.get("file")) &&
                !validateMap1.get("mvn") && !validateMap2.get("mvn")) {

            if (artifact1.getServiceShortName() != null && artifact2.getServiceShortName() != null) {
                if (artifact1.getServiceShortName().equals(artifact2.getServiceShortName())) {
                    diffLinkedList.addLast(new Difference(Operations.REPLACE, "artifacts" + objNumber + "/service-short-name"));
                }
            } else if (artifact1.getServiceShortName() != null) {
                diffLinkedList.addLast(new Difference(Operations.DELETE, "artifacts" + objNumber + "/service-short-name"));
            } else if (artifact2.getServiceShortName() != null) {
                diffLinkedList.addLast(new Difference(Operations.ADD, "artifacts" + objNumber + "/service-short-name"));
            }

            if (artifact1.getServiceName() != null && artifact2.getServiceName() != null) {
                if (artifact1.getServiceName().equals(artifact2.getServiceName())) {
                    diffLinkedList.addLast(new Difference(Operations.REPLACE, "artifacts" + objNumber + "/service_name"));
                }
            } else if (artifact1.getServiceName() != null) {
                diffLinkedList.addLast(new Difference(Operations.DELETE, "artifacts" + objNumber + "/service_name"));
            } else if (artifact2.getServiceName() != null) {
                diffLinkedList.addLast(new Difference(Operations.ADD, "artifacts" + objNumber + "/service_name"));
            }

            //comparison hashes field
            if (artifact1.getHashes().getSha1() != null && artifact2.getHashes().getSha1() != null) {
                if (!artifact1.getHashes().getSha1().equals(artifact2.getHashes().getSha1())) {
                    diffLinkedList.addLast(new Difference(Operations.REPLACE, "artifacts/" + objNumber + "/hashes/sha1"));
                }
            } else if (artifact1.getHashes().getSha1() != null) {
                diffLinkedList.addLast(new Difference(Operations.DELETE, "artifacts/" + objNumber + "/hashes/sha1"));
            } else if (artifact2.getHashes().getSha1() != null) {
                diffLinkedList.addLast(new Difference(Operations.ADD, "artifacts/" + objNumber + "/hashes/sha1"));
            }
            if (artifact1.getHashes().getSha256() != null && artifact2.getHashes().getSha256() != null) {
                if (!artifact1.getHashes().getSha256().equals(artifact2.getHashes().getSha256())) {
                    diffLinkedList.addLast(new Difference(Operations.REPLACE, "artifacts/" + objNumber + "/hashes/sha256"));
                }
            } else if (artifact1.getHashes().getSha256() != null) {
                diffLinkedList.addLast(new Difference(Operations.DELETE, "artifacts/" + objNumber + "/hashes/sha256"));
            } else if (artifact2.getHashes().getSha256() != null) {
                diffLinkedList.addLast(new Difference(Operations.ADD, "artifacts/" + objNumber + "/hashes/sha256"));
            }

            //comparison file field
            if (artifact1.getFiles().get(0) != null && artifact2.getFiles().get(0) != null) {
                if (artifact1.getFiles().get(0).equals(artifact2.getFiles().get(0))) {
                    diffLinkedList.addLast(new Difference(Operations.REPLACE, "artifacts" + objNumber + "/file/0"));
                }
            } else if (artifact1.getFiles().get(0) != null) {
                diffLinkedList.addLast(new Difference(Operations.DELETE, "artifacts" + objNumber + "/file/0"));
            } else if (artifact2.getFiles().get(0) != null) {
                diffLinkedList.addLast(new Difference(Operations.ADD, "artifacts" + objNumber + "/file/0"));
            }
        } else if ()
    }*/

    public LinkedList<Difference> getDiffsList() {
        return diffLinkedList;
    }
}
