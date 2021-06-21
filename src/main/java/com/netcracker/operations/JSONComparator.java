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
        //compareServicesArray(jsonModel1.getServices(), jsonModel2.getServices());

        //comparison artifacts field


    }

    private Difference createNewDiff(AbstractModel firstModel, AbstractModel secondModel,
                                     String path, Operations operation) {

        Difference difference = new Difference(firstModel, secondModel);
        difference.addOperation(operation);
        difference.addPath(path);
        return difference;
    }

    private void addDataInDiff(Difference difference, Operations operation, String path) {
        difference.addOperation(operation);
        difference.addPath(path);
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
                diffLinkedList.addLast(createNewDiff(appFirst, appSecond,
                                    "metadata/application/name", Operations.REPLACE));
            }
        } else if (appFirst.getName() != null) {
            diffLinkedList.addLast(createNewDiff(appFirst, appSecond,
                    "metadata/application/name", Operations.DELETE));
        } else if (appSecond.getName() != null) {
            diffLinkedList.addLast(createNewDiff(appFirst, appSecond,
                    "metadata/application/name", Operations.ADD));
        }
    }

    private void compareDescription(Description desFirst, Description desSecond) {
        if (desFirst.getVersion() != null && desSecond.getVersion() != null) {
            if (!desFirst.getVersion().equals(desSecond.getVersion())) {
                diffLinkedList.addLast(createNewDiff(desFirst, desSecond,
                        "metadata/description/version", Operations.REPLACE));
            }
        } else if (desFirst.getVersion() != null) {
            diffLinkedList.addLast(createNewDiff(desFirst, desSecond,
                    "metadata/description/version", Operations.DELETE));
        } else if (desSecond.getVersion() != null) {
            diffLinkedList.addLast(createNewDiff(desFirst, desSecond,
                    "metadata/description/version", Operations.ADD));
        }
    }

    /*private void compareServicesArray(ArrayList<Service> services1, ArrayList<Service> services2) {
        if (services1.size() == services2.size()) {
            for (int i = 0; i < services1.size(); ++i) {
                compareService(services1.get(i), services2.get(i),i);
            }
        } //непонятно, что делать, если размеры массивов разные
    }

    private void compareService(Service service1, Service service2, int objNumber) {
        //comparison service-short-name field
        if (service1.getServiceShortName() != null && service2.getServiceShortName() != null) {
            if (!service1.getServiceShortName().equals(service2.getServiceShortName())) {
                diffLinkedList.addLast(new Difference(Operations.REPLACE, "services/" + objNumber + "/service-short-name"));
            }
        } else if (service1.getServiceShortName() != null) {
            diffLinkedList.addLast(new Difference(Operations.DELETE, "services/" + objNumber + "/service-short-name"));
        } else if (service2.getServiceShortName() != null) {
            diffLinkedList.addLast(new Difference(Operations.ADD, "services/" + objNumber + "/service-short-name"));
        }

        //comparison service_name field
        if (service1.getServiceName() != null && service2.getServiceName() != null) {
            if (!service1.getServiceName().equals(service2.getServiceName())) {
                diffLinkedList.addLast(new Difference(Operations.REPLACE, "services/" + objNumber + "/service_name"));
            }
        } else if (service1.getServiceName() != null) {
            diffLinkedList.addLast(new Difference(Operations.DELETE, "services/" + objNumber + "/service_name"));
        } else if (service2.getServiceName() != null) {
            diffLinkedList.addLast(new Difference(Operations.ADD, "services/" + objNumber + "/service_name"));
        }

        //comparison artifact_type field
        if (service1.getArtifactType() != null && service2.getArtifactType() != null) {
            if (!service1.getArtifactType().equals(service2.getArtifactType())) {
                diffLinkedList.addLast(new Difference(Operations.REPLACE, "services/" + objNumber + "/artifact_type"));
            }
        } else if (service1.getArtifactType() != null) {
            diffLinkedList.addLast(new Difference(Operations.DELETE, "services/" + objNumber + "/artifact_type"));
        } else if (service2.getArtifactType() != null) {
            diffLinkedList.addLast(new Difference(Operations.ADD, "services/" + objNumber + "/artifact_type"));
        }

        //comparison docker_registry field
        if (service1.getDockerRegistry() != null && service2.getDockerRegistry() != null) {
            if (!service1.getDockerRegistry().equals(service2.getDockerRegistry())) {
                diffLinkedList.addLast(new Difference(Operations.REPLACE, "services/" + objNumber + "/docker_registry"));
            }
        } else if (service1.getDockerRegistry() != null) {
            diffLinkedList.addLast(new Difference(Operations.DELETE, "services/" + objNumber + "/docker_registry"));
        } else if (service2.getDockerRegistry() != null) {
            diffLinkedList.addLast(new Difference(Operations.ADD, "services/" + objNumber + "/docker_registry"));
        }

        //comparison docker_image_name field
        if (service1.getDockerImageName() != null && service2.getDockerImageName() != null) {
            if (!service1.getDockerImageName().equals(service2.getDockerImageName())) {
                diffLinkedList.addLast(new Difference(Operations.REPLACE, "services/" + objNumber + "/docker_image_name"));
            }
        } else if (service1.getDockerImageName() != null) {
            diffLinkedList.addLast(new Difference(Operations.DELETE, "services/" + objNumber + "/docker_image_name"));
        } else if (service2.getDockerImageName() != null) {
            diffLinkedList.addLast(new Difference(Operations.ADD, "services/" + objNumber + "/docker_image_name"));
        }

        //comparison docker_image_name field
        if (service1.getDockerTag() != null && service2.getDockerTag() != null) {
            if (!service1.getDockerTag().equals(service2.getDockerTag())) {
                diffLinkedList.addLast(new Difference(Operations.REPLACE, "services/" + objNumber + "/docker_tag"));
            }
        } else if (service1.getDockerTag() != null) {
            diffLinkedList.addLast(new Difference(Operations.DELETE, "services/" + objNumber + "/docker_tag"));
        } else if (service2.getDockerTag() != null) {
            diffLinkedList.addLast(new Difference(Operations.ADD, "services/" + objNumber + "/docker_tag"));
        }

        //comparison force field
        if (service1.getForce() != null && service2.getForce() != null) {
            if (!service1.getForce().equals(service2.getForce())) {
                diffLinkedList.addLast(new Difference(Operations.REPLACE, "services/" + objNumber + "/force"));
            }
        } else if (service1.getForce() != null) {
            diffLinkedList.addLast(new Difference(Operations.DELETE, "services/" + objNumber + "/force"));
        } else if (service2.getForce() != null) {
            diffLinkedList.addLast(new Difference(Operations.ADD, "services/" + objNumber + "/force"));
        }

        //comparison github_repository field
        if (service1.getGithubRepository() != null && service2.getGithubRepository() != null) {
            if (!service1.getGithubRepository().equals(service2.getGithubRepository())) {
                diffLinkedList.addLast(new Difference(Operations.REPLACE, "services/" + objNumber + "/github_repository"));
            }
        } else if (service1.getGithubRepository() != null) {
            diffLinkedList.addLast(new Difference(Operations.DELETE, "services/" + objNumber + "/github_repository"));
        } else if (service2.getGithubRepository() != null) {
            diffLinkedList.addLast(new Difference(Operations.ADD, "services/" + objNumber + "/github_repository"));
        }

        //comparison github_branch field
        if (service1.getGithubBranch() != null && service2.getGithubBranch() != null) {
            if (!service1.getGithubBranch().equals(service2.getGithubBranch())) {
                diffLinkedList.addLast(new Difference(Operations.REPLACE, "services/" + objNumber + "/github_branch"));
            }
        } else if (service1.getGithubBranch() != null) {
            diffLinkedList.addLast(new Difference(Operations.DELETE, "services/" + objNumber + "/github_branch"));
        } else if (service2.getGithubBranch() != null) {
            diffLinkedList.addLast(new Difference(Operations.ADD, "services/" + objNumber + "/github_branch"));
        }

        //comparison github_hash field
        if (service1.getGithubHash() != null && service2.getGithubHash() != null) {
            if (!service1.getGithubHash().equals(service2.getGithubHash())) {
                diffLinkedList.addLast(new Difference(Operations.REPLACE, "services/" + objNumber + "/github_hash"));
            }
        } else if (service1.getGithubBranch() != null) {
            diffLinkedList.addLast(new Difference(Operations.DELETE, "services/" + objNumber + "/github_hash"));
        } else if (service2.getGithubBranch() != null) {
            diffLinkedList.addLast(new Difference(Operations.ADD, "services/" + objNumber + "/github_hash"));
        }

        //comparison hashes field
        if (service1.getHashes().getSha1() != null && service2.getHashes().getSha1() != null) {
            if (!service1.getHashes().getSha1().equals(service2.getHashes().getSha1())) {
                diffLinkedList.addLast(new Difference(Operations.REPLACE, "services/" + objNumber + "/hashes/sha1"));
            }
        } else if (service1.getHashes().getSha1() != null) {
            diffLinkedList.addLast(new Difference(Operations.DELETE, "services/" + objNumber + "/hashes/sha1"));
        } else if (service2.getHashes().getSha1() != null) {
            diffLinkedList.addLast(new Difference(Operations.ADD, "services/" + objNumber + "/hashes/sha1"));
        }
        if (service1.getHashes().getSha256() != null && service2.getHashes().getSha256() != null) {
            if (!service1.getHashes().getSha256().equals(service2.getHashes().getSha256())) {
                diffLinkedList.addLast(new Difference(Operations.REPLACE, "services/" + objNumber + "/hashes/sha256"));
            }
        } else if (service1.getHashes().getSha256() != null) {
            diffLinkedList.addLast(new Difference(Operations.DELETE, "services/" + objNumber + "/hashes/sha256"));
        } else if (service2.getHashes().getSha256() != null) {
            diffLinkedList.addLast(new Difference(Operations.ADD, "services/" + objNumber + "/hashes/sha256"));
        }
    }

    private void compareArtifactsArray(ArrayList<Artifact> artifacts1, ArrayList<Artifact> artifacts2) {
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
