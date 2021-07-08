package com.netcracker.operations;
import com.netcracker.mergemodels.*;
import com.netcracker.mergemodels.wrapper.*;
import com.netcracker.models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JSONComparator {

    private final JSONModelMerge jsonModelMerge;

    public JSONComparator(JSONModel jsonModelFirst, JSONModel jsonModelSecond) {
        jsonModelMerge = new JSONModelMerge();
        compareJSON(jsonModelFirst, jsonModelSecond);
    }

    private void compareJSON(JSONModel jsonModelFirst, JSONModel jsonModelSecond) {
        //comparison metadata field
        jsonModelMerge.setMetadataMerge(new MetadataMerge());
        compareMetadata(jsonModelFirst.getMetadata(), jsonModelSecond.getMetadata(), jsonModelMerge.getMetadataMerge());

        //comparison services field
        jsonModelMerge.setServiceMerges(new ArrayList<ServiceMerge>());
        compareServicesArray(jsonModelFirst.getServices(), jsonModelSecond.getServices(), jsonModelMerge.getServiceMerges());

        //comparison artifacts field
        jsonModelMerge.setArtifactMerges(new ArrayList<ArtifactMerge>());
        compareArtifactsArray(jsonModelFirst.getArtifacts(), jsonModelSecond.getArtifacts(), jsonModelMerge.getArtifactMerges());

        //comparison script field
        jsonModelMerge.setScriptMerges(new ArrayList<ScriptMerge>());
        compareScriptArray(jsonModelFirst.getScripts(), jsonModelSecond.getScripts(), jsonModelMerge.getScriptMerges());

        //comparison rpm field
        jsonModelMerge.setRpmMerges(new ArrayList<RPMMerge>());
        compareRPMsArray(jsonModelFirst.getRpms(), jsonModelSecond.getRpms(), jsonModelMerge.getRpmMerges());

        //comparison parameters field
        jsonModelMerge.setParametersMerge(new ParametersMerge());
        compareParameters(jsonModelFirst.getParameters(), jsonModelSecond.getParameters(), jsonModelMerge.getParametersMerge());

    }

    private void compareMetadata(Metadata metadataFirst, Metadata metadataSecond, MetadataMerge metadataMerge) {
        metadataMerge.setApplicationMerge(new ApplicationMerge());
        metadataMerge.setDescriptionMerge(new DescriptionMerge());

        if (metadataFirst != null && metadataSecond != null) {
            //comparison application field
            compareApplication(metadataFirst.getApplication(), metadataSecond.getApplication(), metadataMerge.getApplicationMerge());
            //comparison description field
            compareDescription(metadataFirst.getDescription(), metadataSecond.getDescription(), metadataMerge.getDescriptionMerge());
        } else if (metadataFirst != null) {
            compareApplication(metadataFirst.getApplication(), null, metadataMerge.getApplicationMerge());
            compareDescription(metadataFirst.getDescription(), null, metadataMerge.getDescriptionMerge());
            metadataMerge.setOperationType(Type.DELETED);
        } else if (metadataSecond != null) {
            compareApplication(null, metadataSecond.getApplication(), metadataMerge.getApplicationMerge());
            compareDescription(null, metadataSecond.getDescription(), metadataMerge.getDescriptionMerge());
            metadataMerge.setOperationType(Type.ADDED);
        }
    }

    private void compareApplication(Application appFirst, Application appSecond, ApplicationMerge applicationMerge) {
        if (appFirst != null && appSecond != null) {
            if (appFirst.getName() != null && appSecond.getName() != null) {
                if (!appFirst.getName().equals(appSecond.getName())) {
                    applicationMerge.setName(new StringWrapper(appFirst.getName(), appSecond.getName(), Type.UPDATED));
                } else {
                    applicationMerge.setName(new StringWrapper(appFirst.getName(), appSecond.getName(), Type.NONE));
                }
            } else if (appFirst.getName() != null) {
                applicationMerge.setName(new StringWrapper(appFirst.getName(), null, Type.DELETED));
            } else if (appSecond.getName() != null) {
                applicationMerge.setName(new StringWrapper(null, appSecond.getName(), Type.ADDED));
            }
        } else if (appFirst != null) {
            if (appFirst.getName() != null) {
                applicationMerge.setName(new StringWrapper(appFirst.getName(), null, Type.DELETED));
            } else {
                applicationMerge.setName(new StringWrapper(null, null, Type.NONE));
            }
            applicationMerge.setOperationType(Type.DELETED);
        } else if (appSecond != null) {
            if (appSecond.getName() != null) {
                applicationMerge.setName(new StringWrapper(null, appSecond.getName(), Type.ADDED));
            } else {
                applicationMerge.setName(new StringWrapper(null, null, Type.NONE));
            }
            applicationMerge.setOperationType(Type.ADDED);
        }
    }

    private void compareDescription(Description desFirst, Description desSecond, DescriptionMerge descriptionMerge) {
        if (desFirst != null && desSecond != null) {
            if (desFirst.getVersion() != null && desSecond.getVersion() != null) {
                if (!desFirst.getVersion().equals(desSecond.getVersion())) {
                    descriptionMerge.setVersion(new IntegerWrapper(desFirst.getVersion(), desSecond.getVersion(), Type.UPDATED));
                } else {
                    descriptionMerge.setVersion(new IntegerWrapper(desFirst.getVersion(), desSecond.getVersion(), Type.NONE));
                }
            } else if (desFirst.getVersion() != null) {
                descriptionMerge.setVersion(new IntegerWrapper(desFirst.getVersion(), null, Type.DELETED));
            } else if (desSecond.getVersion() != null) {
                descriptionMerge.setVersion(new IntegerWrapper(null, desSecond.getVersion(), Type.ADDED));
            }
        } else if (desFirst != null) {
            if (desFirst.getVersion() != null) {
                descriptionMerge.setVersion(new IntegerWrapper(desFirst.getVersion(), null, Type.DELETED));
            } else {
                descriptionMerge.setVersion(new IntegerWrapper(null, null, Type.NONE));
            }
            descriptionMerge.setOperationType(Type.DELETED);
        } else if (desSecond != null) {
            if (desSecond.getVersion() != null) {
                descriptionMerge.setVersion(new IntegerWrapper(null, desSecond.getVersion(), Type.ADDED));
            } else {
                descriptionMerge.setVersion(new IntegerWrapper(null, null, Type.NONE));
            }
            descriptionMerge.setOperationType(Type.ADDED);
        }
    }

    private void compareServicesArray(ArrayList<Service> servicesFirst, ArrayList<Service> servicesSecond,
                                                                        ArrayList<ServiceMerge> serviceMerges) {
        for (Service first : servicesFirst) {
            boolean flag = true;
            for (Service second : servicesSecond) {
                if (first.equals(second)) {
                    serviceMerges.add(compareService(first, second));
                    flag = false;
                    break;
                }
            }
            if (flag) {
                serviceMerges.add(compareServiceNull(first, null));
            }
        }

        for (Service second : servicesSecond) {
            boolean flag = true;
            for (Service first : servicesFirst) {
                if (second.equals(first)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                serviceMerges.add(compareServiceNull(null, second));
            }
        }
    }

    private ServiceMerge compareServiceNull(Service serviceFirst, Service serviceSecond) {
        ServiceMerge serviceMerge = new ServiceMerge();

        if (serviceFirst != null && serviceSecond == null) {
            // mandatory fields
            serviceMerge.setServiceName(new StringWrapper(serviceFirst.getServiceName(), null, Type.DELETED));
            serviceMerge.setArtifactType(new StringWrapper(serviceFirst.getArtifactType(), null, Type.DELETED));
            serviceMerge.setDockerRegistry(new StringWrapper(serviceFirst.getDockerRegistry(), null, Type.DELETED));
            serviceMerge.setDockerImageName(new StringWrapper(serviceFirst.getDockerImageName(), null, Type.DELETED));
            serviceMerge.setDockerTag(new StringWrapper(serviceFirst.getDockerTag(), null, Type.DELETED));
            serviceMerge.setHashesMerge(compareHashes(serviceFirst.getHashes(), null));

            // optional fields
            if (serviceFirst.getServiceShortName() != null) {
                serviceMerge.setServiceShortName(new StringWrapper(serviceFirst.getServiceShortName(), null, Type.DELETED));
            }
            if (serviceFirst.getForce() != null) {
                serviceMerge.setForce(new BooleanWrapper(serviceFirst.getForce(), null, Type.DELETED));
            }
            if (serviceFirst.getGithubRepository() != null) {
                serviceMerge.setGithubRepository(new StringWrapper(serviceFirst.getGithubRepository(), null, Type.DELETED));
            }
            if (serviceFirst.getGithubBranch() != null) {
                serviceMerge.setGithubBranch(new StringWrapper(serviceFirst.getGithubBranch(), null, Type.DELETED));
            }
            if (serviceFirst.getGithubHash() != null) {
                serviceMerge.setGithubHash(new StringWrapper(serviceFirst.getGithubHash(), null, Type.DELETED));
            }
            serviceMerge.setOperationType(Type.DELETED);
        } else if (serviceFirst == null && serviceSecond != null) {
            // mandatory fields
            serviceMerge.setServiceName(new StringWrapper(null, serviceSecond.getServiceName(), Type.ADDED));
            serviceMerge.setArtifactType(new StringWrapper(null, serviceSecond.getArtifactType(), Type.ADDED));
            serviceMerge.setDockerRegistry(new StringWrapper(null, serviceSecond.getDockerRegistry(), Type.ADDED));
            serviceMerge.setDockerImageName(new StringWrapper(null, serviceSecond.getDockerImageName(), Type.ADDED));
            serviceMerge.setDockerTag(new StringWrapper(null, serviceSecond.getDockerTag(), Type.ADDED));
            serviceMerge.setHashesMerge(compareHashes(null, serviceSecond.getHashes()));

            // optional fields
            if (serviceSecond.getServiceShortName() != null) {
                serviceMerge.setServiceShortName(new StringWrapper(null, serviceSecond.getServiceShortName(), Type.ADDED));
            }
            if (serviceSecond.getForce() != null) {
                serviceMerge.setForce(new BooleanWrapper(null, serviceSecond.getForce(), Type.ADDED));
            }
            if (serviceSecond.getGithubRepository() != null) {
                serviceMerge.setGithubRepository(new StringWrapper(null, serviceSecond.getGithubRepository(), Type.ADDED));
            }
            if (serviceSecond.getGithubBranch() != null) {
                serviceMerge.setGithubBranch(new StringWrapper(null, serviceSecond.getGithubBranch(), Type.ADDED));
            }
            if (serviceSecond.getGithubHash() != null) {
                serviceMerge.setGithubHash(new StringWrapper(null, serviceSecond.getGithubHash(), Type.ADDED));
            }
            serviceMerge.setOperationType(Type.ADDED);
        }

        return serviceMerge;
    }

    private ServiceMerge compareService(Service serviceFirst, Service serviceSecond) {
        ServiceMerge serviceMerge = new ServiceMerge();

        //comparison service_name field
        if (serviceFirst.getServiceName() != null && serviceSecond.getServiceName() != null) {
            if (!serviceFirst.getServiceName().equals(serviceSecond.getServiceName())) {
                serviceMerge.setServiceName(new StringWrapper(serviceFirst.getServiceName(),
                                                                    serviceSecond.getServiceName(), Type.UPDATED));
            } else {
                serviceMerge.setServiceName(new StringWrapper(serviceFirst.getServiceName(),
                                                                    serviceSecond.getServiceName(), Type.NONE));
            }
        } else if (serviceFirst.getServiceName() != null) {
            serviceMerge.setServiceName(new StringWrapper(serviceFirst.getServiceName(), null, Type.DELETED));
        } else if (serviceSecond.getServiceName() != null) {
            serviceMerge.setServiceName(new StringWrapper(null, serviceSecond.getServiceName(), Type.ADDED));
        }

        //comparison artifact_type field
        if (serviceFirst.getArtifactType() != null && serviceSecond.getArtifactType() != null) {
            if (!serviceFirst.getArtifactType().equals(serviceSecond.getArtifactType())) {
                serviceMerge.setArtifactType(new StringWrapper(serviceFirst.getArtifactType(),
                                                                        serviceSecond.getArtifactType(), Type.UPDATED));
            } else {
                serviceMerge.setArtifactType(new StringWrapper(serviceFirst.getArtifactType(),
                                                                        serviceSecond.getArtifactType(), Type.NONE));
            }
        } else if (serviceFirst.getArtifactType() != null) {
            serviceMerge.setArtifactType(new StringWrapper(serviceFirst.getArtifactType(), null, Type.DELETED));
        } else if (serviceSecond.getArtifactType() != null) {
            serviceMerge.setArtifactType(new StringWrapper(null, serviceSecond.getArtifactType(), Type.ADDED));
        }

        //comparison docker_registry field
        if (serviceFirst.getDockerRegistry() != null && serviceSecond.getDockerRegistry() != null) {
            if (!serviceFirst.getDockerRegistry().equals(serviceSecond.getDockerRegistry())) {
                serviceMerge.setDockerRegistry(new StringWrapper(serviceFirst.getDockerRegistry(),
                                                                        serviceSecond.getDockerRegistry(), Type.UPDATED));
            } else {
                serviceMerge.setDockerRegistry(new StringWrapper(serviceFirst.getDockerRegistry(),
                                                                        serviceSecond.getDockerRegistry(), Type.NONE));
            }
        } else if (serviceFirst.getDockerRegistry() != null) {
            serviceMerge.setDockerRegistry(new StringWrapper(serviceFirst.getDockerRegistry(), null, Type.DELETED));
        } else if (serviceSecond.getDockerRegistry() != null) {
            serviceMerge.setDockerRegistry(new StringWrapper(null, serviceSecond.getDockerRegistry(), Type.ADDED));
        }

        //comparison docker_image_name field
        if (serviceFirst.getDockerImageName() != null && serviceSecond.getDockerImageName() != null) {
            if (!serviceFirst.getDockerImageName().equals(serviceSecond.getDockerImageName())) {
                serviceMerge.setDockerImageName(new StringWrapper(serviceFirst.getDockerImageName(),
                                                                        serviceSecond.getDockerImageName(), Type.UPDATED));
            } else {
                serviceMerge.setDockerImageName(new StringWrapper(serviceFirst.getDockerImageName(),
                                                                        serviceSecond.getDockerImageName(), Type.NONE));
            }
        } else if (serviceFirst.getDockerImageName() != null) {
            serviceMerge.setDockerImageName(new StringWrapper(serviceFirst.getDockerImageName(), null, Type.DELETED));
        } else if (serviceSecond.getDockerImageName() != null) {
            serviceMerge.setDockerImageName(new StringWrapper(null, serviceSecond.getDockerImageName(), Type.NONE));
        }

        //comparison docker_tag field
        if (serviceFirst.getDockerTag() != null && serviceSecond.getDockerTag() != null) {
            if (!serviceFirst.getDockerTag().equals(serviceSecond.getDockerTag())) {
                serviceMerge.setDockerTag(new StringWrapper(serviceFirst.getDockerTag(), serviceSecond.getDockerTag(), Type.UPDATED));
            } else {
                serviceMerge.setDockerTag(new StringWrapper(serviceFirst.getDockerTag(), serviceSecond.getDockerTag(), Type.NONE));
            }
        } else if (serviceFirst.getDockerTag() != null) {
            serviceMerge.setDockerTag(new StringWrapper(serviceFirst.getDockerTag(), null, Type.DELETED));
        } else if (serviceSecond.getDockerTag() != null) {
            serviceMerge.setDockerTag(new StringWrapper(null, serviceSecond.getDockerTag(), Type.ADDED));
        }

        //comparison hashes field (hashes fields mustn`t be null)
        serviceMerge.setHashesMerge(compareHashes(serviceFirst.getHashes(), serviceSecond.getHashes()));

        //comparison service-short-name field
        if (serviceFirst.getServiceShortName() != null && serviceSecond.getServiceShortName() != null) {
            if (!serviceFirst.getServiceShortName().equals(serviceSecond.getServiceShortName())) {
                serviceMerge.setServiceShortName(new StringWrapper(serviceFirst.getServiceShortName(),
                                                            serviceSecond.getServiceShortName(), Type.UPDATED));
            } else {
                serviceMerge.setServiceShortName(new StringWrapper(serviceFirst.getServiceShortName(),
                                                            serviceSecond.getServiceShortName(), Type.NONE));
            }
        } else if (serviceFirst.getServiceShortName() != null) {
            serviceMerge.setServiceShortName(new StringWrapper(serviceFirst.getServiceShortName(),
                                                                                null, Type.DELETED));
        } else if (serviceSecond.getServiceShortName() != null) {
            serviceMerge.setServiceShortName(new StringWrapper(null,
                                                            serviceSecond.getServiceShortName(), Type.ADDED));
        }

        //comparison force field
        if (serviceFirst.getForce() != null && serviceSecond.getForce() != null) {
            if (!serviceFirst.getForce().equals(serviceSecond.getForce())) {
                serviceMerge.setForce(new BooleanWrapper(serviceFirst.getForce(),
                                                serviceSecond.getForce(), Type.UPDATED));
            } else {
                serviceMerge.setForce(new BooleanWrapper(serviceFirst.getForce(),
                                                serviceSecond.getForce(), Type.NONE));
            }
        } else if (serviceFirst.getForce() != null) {
            serviceMerge.setForce(new BooleanWrapper(serviceFirst.getForce(),
                                                        null, Type.DELETED));
        } else if (serviceSecond.getForce() != null) {
            serviceMerge.setForce(new BooleanWrapper(null,
                                                serviceSecond.getForce(), Type.ADDED));
        }

        //comparison github_repository field
        if (serviceFirst.getGithubRepository() != null && serviceSecond.getGithubRepository() != null) {
            if (!serviceFirst.getGithubRepository().equals(serviceSecond.getGithubRepository())) {
                serviceMerge.setGithubRepository(new StringWrapper(serviceFirst.getGithubRepository(),
                                                                            serviceSecond.getGithubRepository(), Type.UPDATED));
            } else {
                serviceMerge.setGithubRepository(new StringWrapper(serviceFirst.getGithubRepository(),
                                                                            serviceSecond.getGithubRepository(), Type.NONE));
            }
        } else if (serviceFirst.getGithubRepository() != null) {
            serviceMerge.setGithubRepository(new StringWrapper(serviceFirst.getGithubRepository(),null, Type.DELETED));
        } else if (serviceSecond.getGithubRepository() != null) {
            serviceMerge.setGithubRepository(new StringWrapper(null, serviceSecond.getGithubRepository(), Type.ADDED));
        }

        //comparison github_branch field
        if (serviceFirst.getGithubBranch() != null && serviceSecond.getGithubBranch() != null) {
            if (!serviceFirst.getGithubBranch().equals(serviceSecond.getGithubBranch())) {
                serviceMerge.setGithubBranch(new StringWrapper(serviceFirst.getGithubBranch(),
                                                                    serviceSecond.getGithubBranch(), Type.UPDATED));
            } else {
                serviceMerge.setGithubBranch(new StringWrapper(serviceFirst.getGithubBranch(),
                                                                    serviceSecond.getGithubBranch(), Type.NONE));
            }
        } else if (serviceFirst.getGithubBranch() != null) {
            serviceMerge.setGithubBranch(new StringWrapper(serviceFirst.getGithubBranch(), null, Type.DELETED));
        } else if (serviceSecond.getGithubBranch() != null) {
            serviceMerge.setGithubBranch(new StringWrapper(null, serviceSecond.getGithubBranch(), Type.ADDED));
        }

        //comparison github_hash field
        if (serviceFirst.getGithubHash() != null && serviceSecond.getGithubHash() != null) {
            if (!serviceFirst.getGithubHash().equals(serviceSecond.getGithubHash())) {
                serviceMerge.setGithubHash(new StringWrapper(serviceFirst.getGithubHash(),
                                                                        serviceSecond.getGithubHash(), Type.UPDATED));
            } else {
                serviceMerge.setGithubHash(new StringWrapper(serviceFirst.getGithubHash(),
                                                                        serviceSecond.getGithubHash(), Type.NONE));
            }
        } else if (serviceFirst.getGithubHash() != null) {
            serviceMerge.setGithubHash(new StringWrapper(serviceFirst.getGithubHash(),
                                                                        serviceSecond.getGithubHash(), Type.DELETED));
        } else if (serviceSecond.getGithubHash() != null) {
            serviceMerge.setGithubHash(new StringWrapper(serviceFirst.getGithubHash(),
                                                                        serviceSecond.getGithubHash(), Type.ADDED));
        }

        return serviceMerge;
    }

    private void compareArtifactsArray(ArrayList<Artifact> artifactsFirst, ArrayList<Artifact> artifactsSecond,
                                                                                ArrayList<ArtifactMerge> artifactMerges) {
        for (Artifact first : artifactsFirst) {
            boolean flag = true;
            for (Artifact second : artifactsSecond) {
                if (first.isOther() && second.isOther() && first.equals(second)) {
                    artifactMerges.add(compareArtifact(first, second));
                    flag = false;
                    break;
                } else if (first.isMVN() && second.isMVN() && first.getTargetRepository().equals(second.getTargetRepository())) {
                    ArtifactMerge artifactMerge = compareMVNsArray(first.getMvns(), second.getMvns());
                    artifactMerge.setTargetRepository(new StringWrapper(first.getTargetRepository(), second.getTargetRepository(), Type.NONE));
                    artifactMerge.setMVN(1);
                    artifactMerges.add(artifactMerge);
                    flag = false;
                    break;
                }
            }
            if (flag) {
                if (first.isOther()) {
                    artifactMerges.add(compareArtifactNull(first, null));
                } else {
                    ArrayList<MVNMerge> mvnMerges = new ArrayList<>();
                    for (MVN mvn : first.getMvns()) {
                        mvnMerges.add(compareMVNNull(mvn, null));
                    }

                    ArtifactMerge artifactMerge = new ArtifactMerge();
                    artifactMerge.setMVN(1);
                    artifactMerge.setMvnMerges(mvnMerges);
                    artifactMerge.setTargetRepository(new StringWrapper(first.getTargetRepository(), null, Type.DELETED));
                    artifactMerge.setOperationType(Type.DELETED);
                    artifactMerges.add(artifactMerge);
                }
            }
        }

        for (Artifact second : artifactsSecond) {
            boolean flag = true;
            for (Artifact first : artifactsFirst) {
                if (first.isOther() && second.isOther() && second.equals(first)) {
                    flag = false;
                    break;
                } else if (second.isMVN() && first.isMVN() && second.getTargetRepository().equals(first.getTargetRepository())) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                if (second.isOther()) {
                    compareArtifactNull(null, second);
                } else {
                    ArrayList<MVNMerge> mvnMerges = new ArrayList<>();
                    for (MVN mvn : second.getMvns()) {
                        mvnMerges.add(compareMVNNull(null, mvn));
                    }

                    ArtifactMerge artifactMerge = new ArtifactMerge();
                    artifactMerge.setMVN(1);
                    artifactMerge.setMvnMerges(mvnMerges);
                    artifactMerge.setTargetRepository(new StringWrapper(null, second.getTargetRepository(), Type.ADDED));
                    artifactMerge.setOperationType(Type.ADDED);
                    artifactMerges.add(artifactMerge);
                }
            }
        }
    }

    private ArtifactMerge compareMVNsArray(ArrayList<MVN> mvnsFirst, ArrayList<MVN> mvnsSecond) {
        ArrayList<MVNMerge> mvnMerges = new ArrayList<>();
        for (MVN first : mvnsFirst) {
            boolean flag = true;
            for (MVN second : mvnsSecond) {
                if (first.equals(second)) {
                    mvnMerges.add(compareMVN(first, second));
                    flag = false;
                    break;
                }
            }
            if (flag) {
                mvnMerges.add(compareMVNNull(first, null));
            }
        }

        for (MVN second : mvnsSecond) {
            boolean flag = true;
            for (MVN first : mvnsFirst) {
                if (second.equals(first)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                mvnMerges.add(compareMVNNull(null, second));
            }
        }

        ArtifactMerge artifactMerge = new ArtifactMerge();
        artifactMerge.setMvnMerges(mvnMerges);
        return artifactMerge;
    }

    private MVNMerge compareMVNNull(MVN mvnFirst, MVN mvnSecond) {
        MVNMerge mvnMerge = new MVNMerge();

        // mandatory fields
        if (mvnFirst != null && mvnSecond == null) {
            mvnMerge.setGroupId(new StringWrapper(mvnFirst.getGroupId(), null, Type.DELETED));
            mvnMerge.setArtifactId(new StringWrapper(mvnFirst.getArtifactId(), null, Type.DELETED));
            mvnMerge.setVersion(new StringWrapper(mvnFirst.getVersion(), null, Type.DELETED));
            mvnMerge.setMvnType(new StringWrapper(mvnFirst.getMvnType(), null, Type.DELETED));
            mvnMerge.setMvnRepository(new StringWrapper(mvnFirst.getMvnRepository(), null, Type.DELETED));
            mvnMerge.setHashesMerge(compareHashes(mvnFirst.getHashes(), null));

            // optional fields
            if (mvnFirst.getServiceName() != null) {
                mvnMerge.setServiceName(new StringWrapper(mvnFirst.getServiceName(), null, Type.DELETED));
            }
            if (mvnFirst.getClassifier() != null) {
                mvnMerge.setClassifier(new StringWrapper(mvnFirst.getClassifier(), null, Type.DELETED));
            }

            mvnMerge.setOperationType(Type.DELETED);
        } else if (mvnFirst == null && mvnSecond != null) {
            mvnMerge.setGroupId(new StringWrapper(null, mvnSecond.getGroupId(), Type.ADDED));
            mvnMerge.setArtifactId(new StringWrapper(null, mvnSecond.getArtifactId(), Type.ADDED));
            mvnMerge.setVersion(new StringWrapper(null, mvnSecond.getVersion(), Type.ADDED));
            mvnMerge.setMvnType(new StringWrapper(null, mvnSecond.getMvnType(), Type.ADDED));
            mvnMerge.setMvnRepository(new StringWrapper(null, mvnSecond.getMvnRepository(), Type.ADDED));
            mvnMerge.setHashesMerge(compareHashes(null, mvnSecond.getHashes()));

            // optional fields
            if (mvnSecond.getServiceName() != null) {
                mvnMerge.setServiceName(new StringWrapper(null, mvnSecond.getServiceName(), Type.ADDED));
            }
            if (mvnSecond.getClassifier() != null) {
                mvnMerge.setClassifier(new StringWrapper(null, mvnSecond.getClassifier(), Type.ADDED));
            }

            mvnMerge.setOperationType(Type.ADDED);
        }

        return mvnMerge;
    }

    private MVNMerge compareMVN(MVN mvnFirst, MVN mvnSecond) {
        MVNMerge mvnMerge = new MVNMerge();

        //add groupId field
        mvnMerge.setGroupId(new StringWrapper(mvnFirst.getGroupId(), mvnSecond.getGroupId(), Type.NONE));
        //add artifactId field
        mvnMerge.setArtifactId(new StringWrapper(mvnFirst.getArtifactId(), mvnSecond.getArtifactId(), Type.NONE));
        //add version field
        mvnMerge.setVersion(new StringWrapper(mvnFirst.getVersion(), mvnSecond.getVersion(), Type.NONE));
        //add mvn_type field
        mvnMerge.setMvnType(new StringWrapper(mvnFirst.getMvnType(), mvnSecond.getMvnType(), Type.NONE));
        //add mvn_repository field
        mvnMerge.setMvnRepository(new StringWrapper(mvnFirst.getMvnRepository(), mvnSecond.getMvnRepository(), Type.NONE));
        //add hashes field
        mvnMerge.setHashesMerge(compareHashes(mvnFirst.getHashes(), mvnSecond.getHashes()));

        //comparison service_name field
        if (mvnFirst.getServiceName() != null && mvnSecond.getServiceName() != null) {
            if (!mvnFirst.getServiceName().equals(mvnSecond.getServiceName())) {
                mvnMerge.setServiceName(new StringWrapper(mvnFirst.getServiceName(), mvnSecond.getServiceName(), Type.UPDATED));
            } else {
                mvnMerge.setServiceName(new StringWrapper(mvnFirst.getServiceName(), mvnSecond.getServiceName(), Type.NONE));
            }
        } else if (mvnFirst.getServiceName() != null) {
            mvnMerge.setServiceName(new StringWrapper(mvnFirst.getServiceName(), null, Type.DELETED));
        } else if (mvnSecond.getServiceName() != null) {
            mvnMerge.setServiceName(new StringWrapper(null, mvnSecond.getServiceName(), Type.UPDATED));
        }

        //comparison classifier field
        if (mvnFirst.getClassifier() != null && mvnSecond.getClassifier() != null) {
            if (!mvnFirst.getClassifier().equals(mvnSecond.getClassifier())) {
                mvnMerge.setClassifier(new StringWrapper(mvnFirst.getClassifier(), mvnSecond.getClassifier(), Type.UPDATED));
            } else {
                mvnMerge.setClassifier(new StringWrapper(mvnFirst.getClassifier(), mvnSecond.getClassifier(), Type.NONE));
            }
        } else if (mvnFirst.getClassifier() != null) {
            mvnMerge.setClassifier(new StringWrapper(mvnFirst.getClassifier(), null, Type.DELETED));
        } else if (mvnSecond.getServiceName() != null) {
            mvnMerge.setClassifier(new StringWrapper(null, mvnSecond.getClassifier(), Type.UPDATED));
        }

        return mvnMerge;
    }

    private ArtifactMerge compareArtifactNull(Artifact artifactFirst, Artifact artifactSecond) {
        ArtifactMerge artifactMerge = new ArtifactMerge();

        if (artifactFirst != null && artifactSecond == null) {
            // mandatory fields
            artifactMerge.setHashesMerge(compareHashes(artifactFirst.getHashes(), null));
            artifactMerge.setFileMerges(compareArrayFile(artifactFirst.getFiles(), null));
            artifactMerge.setTargetRepository(new StringWrapper(artifactFirst.getTargetRepository(), null, Type.DELETED));

            // optional fields
            if (artifactFirst.getServiceShortName() != null) {
                artifactMerge.setServiceShortName(new StringWrapper(artifactFirst.getServiceShortName(), null, Type.DELETED));
            }
            if (artifactFirst.getServiceName() != null) {
                artifactMerge.setServiceName(new StringWrapper(artifactFirst.getServiceName(), null, Type.DELETED));
            }

            artifactMerge.setOperationType(Type.DELETED);
        } else if (artifactFirst == null && artifactSecond != null) {
            // mandatory fields
            artifactMerge.setHashesMerge(compareHashes(null, artifactSecond.getHashes()));
            artifactMerge.setFileMerges(compareArrayFile(null, artifactSecond.getFiles()));
            artifactMerge.setTargetRepository(new StringWrapper(null, artifactSecond.getTargetRepository(), Type.ADDED));

            // optional fields
            if (artifactSecond.getServiceShortName() != null) {
                artifactMerge.setServiceShortName(new StringWrapper(null, artifactSecond.getServiceShortName(), Type.ADDED));
            }
            if (artifactSecond.getServiceName() != null) {
                artifactMerge.setServiceName(new StringWrapper(null, artifactSecond.getServiceName(), Type.ADDED));
            }
            artifactMerge.setOperationType(Type.ADDED);
        }

        return artifactMerge;
    }

    private ArtifactMerge compareArtifact(Artifact artifactFirst, Artifact artifactSecond) {
        ArtifactMerge artifactMerge = new ArtifactMerge();

        //add hashes field
        artifactMerge.setHashesMerge(compareHashes(artifactFirst.getHashes(), artifactSecond.getHashes()));
        //add file field
        artifactMerge.setFileMerges(compareArrayFile(artifactFirst.getFiles(), artifactSecond.getFiles()));
        //add target_repository
        artifactMerge.setTargetRepository(new StringWrapper(artifactFirst.getTargetRepository(), artifactSecond.getTargetRepository(), Type.NONE));

        if (artifactFirst.getServiceShortName() != null && artifactSecond.getServiceShortName() != null) {
            if (!artifactFirst.getServiceShortName().equals(artifactSecond.getServiceShortName())) {
                artifactMerge.setServiceShortName(new StringWrapper(artifactFirst.getServiceShortName(),
                                                                        artifactSecond.getServiceShortName(), Type.UPDATED));
            } else {
                artifactMerge.setServiceShortName(new StringWrapper(artifactFirst.getServiceShortName(),
                                                                        artifactSecond.getServiceShortName(), Type.NONE));
            }
        } else if (artifactFirst.getServiceShortName() != null) {
            artifactMerge.setServiceShortName(new StringWrapper(artifactFirst.getServiceShortName(),null, Type.DELETED));
        } else if (artifactSecond.getServiceShortName() != null) {
            artifactMerge.setServiceShortName(new StringWrapper(null, artifactSecond.getServiceShortName(), Type.ADDED));
        }

        if (artifactFirst.getServiceName() != null && artifactSecond.getServiceName() != null) {
            if (!artifactFirst.getServiceName().equals(artifactSecond.getServiceName())) {
                artifactMerge.setServiceName(new StringWrapper(artifactFirst.getServiceName(),
                                                                        artifactSecond.getServiceName(), Type.UPDATED));
            } else {
                artifactMerge.setServiceName(new StringWrapper(artifactFirst.getServiceName(),
                                                                        artifactSecond.getServiceName(), Type.NONE));
            }
        } else if (artifactFirst.getServiceName() != null) {
            artifactMerge.setServiceName(new StringWrapper(artifactFirst.getServiceName(), null, Type.DELETED));
        } else if (artifactSecond.getServiceName() != null) {
            artifactMerge.setServiceName(new StringWrapper(null, artifactSecond.getServiceName(), Type.ADDED));
        }

        return artifactMerge;
    }

    private void compareScriptArray(ArrayList<Script> scriptsFirst, ArrayList<Script> scriptsSecond,
                                                                                    ArrayList<ScriptMerge> scriptMerges) {
        for (Script first : scriptsFirst) {
            boolean flag = true;
            for (Script second : scriptsSecond) {
                if (first.equals(second)) {
                    scriptMerges.add(compareScript(first, second));
                    flag = false;
                    break;
                }
            }
            if (flag) {
                scriptMerges.add(compareScriptNull(first, null));
            }
        }

        for (Script second : scriptsSecond) {
            boolean flag = true;
            for (Script first : scriptsFirst) {
                if (second.equals(first)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                scriptMerges.add(compareScriptNull(null, second));
            }
        }
    }

    private ScriptMerge compareScriptNull(Script scriptFirst, Script scriptSecond) {
        ScriptMerge scriptMerge = new ScriptMerge();

        if (scriptFirst != null && scriptSecond == null) {
            // mandatory fields
            scriptMerge.setScriptName(new StringWrapper(scriptFirst.getScriptName(), null, Type.DELETED));
            scriptMerge.setHashes(compareHashes(scriptFirst.getHashes(), null));
            scriptMerge.setUrl(new StringWrapper(scriptFirst.getUrl(), null, Type.DELETED));

            // optional fields
            if (scriptFirst.getServiceShortName() != null) {
                scriptMerge.setServiceShortName(new StringWrapper(scriptFirst.getServiceShortName(), null, Type.DELETED));
            }
            if (scriptFirst.getStartPoint() != null) {
                scriptMerge.setStartPoint(new StringWrapper(scriptFirst.getStartPoint(), null, Type.DELETED));
            }
            if (scriptFirst.getEndPoint() != null) {
                scriptMerge.setStartPoint(new StringWrapper(scriptFirst.getEndPoint(), null, Type.DELETED));
            }
            scriptMerge.setOperationType(Type.DELETED);
        } else if (scriptFirst == null && scriptSecond != null) {
            // mandatory fields
            scriptMerge.setScriptName(new StringWrapper(null, scriptSecond.getScriptName(), Type.ADDED));
            scriptMerge.setHashes(compareHashes(null, scriptSecond.getHashes()));
            scriptMerge.getHashes().setOperationType(Type.ADDED);
            scriptMerge.setUrl(new StringWrapper(null, scriptSecond.getUrl(), Type.ADDED));

            // optional fields
            if (scriptSecond.getServiceShortName() != null) {
                scriptMerge.setServiceShortName(new StringWrapper(null, scriptSecond.getServiceShortName(), Type.ADDED));
            }
            if (scriptSecond.getStartPoint() != null) {
                scriptMerge.setStartPoint(new StringWrapper(null, scriptSecond.getStartPoint(), Type.ADDED));
            }
            if (scriptSecond.getEndPoint() != null) {
                scriptMerge.setStartPoint(new StringWrapper(null, scriptSecond.getEndPoint(), Type.ADDED));
            }
            scriptMerge.setOperationType(Type.ADDED);
        }

        return scriptMerge;
    }

    private ScriptMerge compareScript(Script scriptFirst, Script scriptSecond) {
        ScriptMerge scriptMerge = new ScriptMerge();

        //add script_name field
        scriptMerge.setScriptName(new StringWrapper(scriptFirst.getScriptName(), scriptSecond.getScriptName(), Type.NONE));
        //add hashes field
        scriptMerge.setHashes(compareHashes(scriptFirst.getHashes(), scriptSecond.getHashes()));
        //add url field
        scriptMerge.setUrl(new StringWrapper(scriptFirst.getUrl(), scriptSecond.getUrl(), Type.NONE));

        //comparison service-short-name field
        if (scriptFirst.getServiceShortName() != null && scriptSecond.getServiceShortName() != null) {
            if (!scriptFirst.getServiceShortName().equals(scriptSecond.getServiceShortName())) {
                scriptMerge.setServiceShortName(new StringWrapper(scriptFirst.getServiceShortName(),
                                                                            scriptSecond.getServiceShortName(), Type.UPDATED));
            } else {
                scriptMerge.setServiceShortName(new StringWrapper(scriptFirst.getServiceShortName(),
                        scriptSecond.getServiceShortName(), Type.NONE));
            }
        } else if (scriptFirst.getServiceShortName() != null) {
            scriptMerge.setServiceShortName(new StringWrapper(scriptFirst.getServiceShortName(), null, Type.DELETED));
        } else if (scriptSecond.getServiceShortName() != null) {
            scriptMerge.setServiceShortName(new StringWrapper(null, scriptSecond.getServiceShortName(), Type.ADDED));
        }

        //comparison start-point field
        if (scriptFirst.getStartPoint() != null && scriptSecond.getStartPoint() != null) {
            if (!scriptFirst.getStartPoint().equals(scriptSecond.getStartPoint())) {
                scriptMerge.setStartPoint(new StringWrapper(scriptFirst.getStartPoint(), scriptSecond.getStartPoint(), Type.UPDATED));
            } else {
                scriptMerge.setStartPoint(new StringWrapper(scriptFirst.getStartPoint(), scriptSecond.getStartPoint(), Type.NONE));
            }
        } else if (scriptFirst.getStartPoint() != null) {
            scriptMerge.setStartPoint(new StringWrapper(scriptFirst.getStartPoint(), null, Type.DELETED));
        } else if (scriptSecond.getStartPoint() != null) {
            scriptMerge.setStartPoint(new StringWrapper(null, scriptSecond.getStartPoint(), Type.ADDED));
        }

        //comparison end-point field
        if (scriptFirst.getEndPoint() != null && scriptSecond.getEndPoint() != null) {
            if (!scriptFirst.getEndPoint().equals(scriptSecond.getEndPoint())) {
                scriptMerge.setEndPoint(new StringWrapper(scriptFirst.getEndPoint(), scriptSecond.getEndPoint(), Type.UPDATED));
            } else {
                scriptMerge.setEndPoint(new StringWrapper(scriptFirst.getEndPoint(), scriptSecond.getEndPoint(), Type.NONE));
            }
        } else if (scriptFirst.getEndPoint() != null) {
            scriptMerge.setEndPoint(new StringWrapper(scriptFirst.getEndPoint(), null, Type.DELETED));
        } else if (scriptSecond.getEndPoint() != null) {
            scriptMerge.setEndPoint(new StringWrapper(null, scriptSecond.getEndPoint(), Type.ADDED));
        }

        return scriptMerge;
    }

    private void compareRPMsArray(ArrayList<RPM> rpmsFirst, ArrayList<RPM> rpmsSecond, ArrayList<RPMMerge> rpmMerges) {
        for (RPM first : rpmsFirst) {
            boolean flag = true;
            for (RPM second : rpmsSecond) {
                if (first.equals(second)) {
                    rpmMerges.add(compareRPM(first, second));
                    flag = false;
                    break;
                }
            }
            if (flag) {
                rpmMerges.add(compareRPMNull(first, null));
            }
        }

        for (RPM second : rpmsSecond) {
            boolean flag = true;
            for (RPM first : rpmsFirst) {
                if (second.equals(first)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                rpmMerges.add(compareRPMNull(null, second));
            }
        }
    }

    private RPMMerge compareRPMNull(RPM rpmFirst, RPM rpmSecond) {
        RPMMerge rpmMerge = new RPMMerge();

        if (rpmFirst != null && rpmSecond == null) {
            // mandatory fields
            rpmMerge.setRpmRepositoryName(new StringWrapper(rpmFirst.getRpmRepositoryName(), null, Type.DELETED));
            rpmMerge.setHashesMerge(compareHashes(rpmFirst.getHashes(), null));
            rpmMerge.setUrl(new StringWrapper(rpmFirst.getUrl(), null, Type.DELETED));

            // optional fields
            if (rpmFirst.getServiceShortName() != null) {
                rpmMerge.setServiceShortName(new StringWrapper(rpmFirst.getServiceShortName(), null, Type.DELETED));
            }

            rpmMerge.setOperationType(Type.DELETED);
        } else if (rpmFirst == null && rpmSecond != null) {
            // mandatory fields
            rpmMerge.setRpmRepositoryName(new StringWrapper(null, rpmSecond.getRpmRepositoryName(), Type.ADDED));
            rpmMerge.setHashesMerge(compareHashes(null, rpmSecond.getHashes()));
            rpmMerge.setUrl(new StringWrapper(null, rpmSecond.getUrl(), Type.ADDED));

            // optional fields
            if (rpmSecond.getServiceShortName() != null) {
                rpmMerge.setServiceShortName(new StringWrapper(null, rpmSecond.getServiceShortName(), Type.ADDED));
            }

            rpmMerge.setOperationType(Type.ADDED);
        }

        return rpmMerge;
    }

    private RPMMerge compareRPM(RPM rpmFirst, RPM rpmSecond) {
        RPMMerge rpmMerge = new RPMMerge();

        //add url field
        rpmMerge.setUrl(new StringWrapper(rpmFirst.getUrl(), rpmSecond.getUrl(), Type.NONE));
        //add rpm_repository_name field
        rpmMerge.setRpmRepositoryName(new StringWrapper(rpmFirst.getRpmRepositoryName(), rpmSecond.getRpmRepositoryName(), Type.NONE));
        //add hashes field
        rpmMerge.setHashesMerge(compareHashes(rpmFirst.getHashes(),rpmSecond.getHashes()));

        //comparison service-short-name field
        if (rpmFirst.getServiceShortName() != null && rpmSecond.getServiceShortName() != null) {
            if (!rpmFirst.getServiceShortName().equals(rpmSecond.getServiceShortName())) {
                rpmMerge.setServiceShortName(new StringWrapper(rpmFirst.getServiceShortName(),
                                                                        rpmSecond.getServiceShortName(), Type.UPDATED));
            } else {
                rpmMerge.setServiceShortName(new StringWrapper(rpmFirst.getServiceShortName(),
                        rpmSecond.getServiceShortName(), Type.NONE));
            }
        } else if (rpmFirst.getServiceShortName() != null) {
            rpmMerge.setServiceShortName(new StringWrapper(rpmFirst.getServiceShortName(), null, Type.DELETED));
        } else if (rpmSecond.getServiceShortName() != null) {
            rpmMerge.setServiceShortName(new StringWrapper(null, rpmSecond.getServiceShortName(), Type.ADDED));
        }

        return rpmMerge;
    }

    private void compareParameters(Parameters parametersFirst, Parameters parametersSecond, ParametersMerge parametersMerge) {
        parametersMerge.setCommonMerge(new CommonMerge());
        parametersMerge.setServices(new ServicesMerge());

        if (parametersFirst != null && parametersSecond != null) {
            // comparison common fields
            compareCommon(parametersFirst.getCommon(), parametersSecond.getCommon(), parametersMerge.getCommonMerge());
            // comparison services fields
            compareServiceName(parametersFirst.getServices(), parametersSecond.getServices(), parametersMerge.getServices());
        } else if (parametersFirst != null) {
            // comparison common fields
            compareCommon(parametersFirst.getCommon(), null, parametersMerge.getCommonMerge());
            // comparison services fields
            compareServiceName(parametersFirst.getServices(), null, parametersMerge.getServices());
            parametersMerge.setOperationType(Type.DELETED);
        } else if (parametersSecond != null) {
            // comparison common fields
            compareCommon(null, parametersSecond.getCommon(), parametersMerge.getCommonMerge());
            // comparison services fields
            compareServiceName(null, parametersSecond.getServices(), parametersMerge.getServices());
            parametersMerge.setOperationType(Type.ADDED);
        }
    }

    private void compareCommon(Common commonFirst, Common commonSecond, CommonMerge commonMerge) {

        if (commonFirst != null && commonSecond != null) {
            //if (commonFirst.equals(commonSecond)) {
            //add some-param field
            commonMerge.setSomeParam(new StringWrapper(commonFirst.getSomeParam(), commonSecond.getSomeParam(), Type.NONE));

            // comparison some-other-param field
            if (commonFirst.getSomeOtherParam() != null && commonSecond.getSomeOtherParam() != null) {
                if (!commonFirst.getSomeOtherParam().equals(commonSecond.getSomeOtherParam())) {
                    commonMerge.setSomeOtherParam(new StringWrapper(commonFirst.getSomeOtherParam(),
                                                                            commonSecond.getSomeOtherParam(), Type.UPDATED));
                } else {
                    commonMerge.setSomeOtherParam(new StringWrapper(commonFirst.getSomeOtherParam(),
                                                                            commonFirst.getSomeOtherParam(), Type.NONE));
                }
            } else if (commonFirst.getSomeOtherParam() != null) {
                commonMerge.setSomeOtherParam(new StringWrapper(commonFirst.getSomeOtherParam(), null, Type.DELETED));
            } else if (commonSecond.getSomeOtherParam() != null) {
                commonMerge.setSomeOtherParam(new StringWrapper(null, commonFirst.getSomeOtherParam(), Type.ADDED));
            }

            // comparison some-else-param field
            if (commonFirst.getSomeElseParam() != null && commonSecond.getSomeElseParam() != null) {
                if (!commonFirst.getSomeElseParam().equals(commonSecond.getSomeElseParam())) {
                    commonMerge.setSomeElseParam(new StringWrapper(commonFirst.getSomeElseParam(),
                                                                                commonSecond.getSomeElseParam(), Type.UPDATED));
                } else {
                    commonMerge.setSomeElseParam(new StringWrapper(commonFirst.getSomeElseParam(),
                                                                                commonSecond.getSomeElseParam(), Type.NONE));
                }
            } else if (commonFirst.getSomeElseParam() != null) {
                commonMerge.setSomeElseParam(new StringWrapper(commonFirst.getSomeElseParam(), null, Type.DELETED));
            } else if (commonSecond.getSomeElseParam() != null) {
                commonMerge.setSomeElseParam(new StringWrapper(null, commonSecond.getSomeElseParam(), Type.ADDED));
            }
            //}
        } else if (commonFirst != null) {
            // mandatory fields
            commonMerge.setSomeParam(new StringWrapper(commonFirst.getSomeParam(), null, Type.DELETED));

            // optional fields
            if (commonFirst.getSomeOtherParam() != null) {
                commonMerge.setSomeOtherParam(new StringWrapper(commonFirst.getSomeOtherParam(), null, Type.DELETED));
            }
            if (commonFirst.getSomeElseParam() != null) {
                commonMerge.setSomeElseParam(new StringWrapper(commonFirst.getSomeElseParam(), null, Type.DELETED));
            }

            commonMerge.setOperationType(Type.DELETED);
        } else if (commonSecond != null) {
            // mandatory fields
            commonMerge.setSomeParam(new StringWrapper(null, commonSecond.getSomeParam(), Type.ADDED));

            // optional fields
            if (commonSecond.getSomeOtherParam() != null) {
                commonMerge.setSomeOtherParam(new StringWrapper(null, commonSecond.getSomeOtherParam(), Type.ADDED));
            }
            if (commonSecond.getSomeElseParam() != null) {
                commonMerge.setSomeElseParam(new StringWrapper(null, commonSecond.getSomeElseParam(), Type.ADDED));
            }

            commonMerge.setOperationType(Type.ADDED);
        }
    }

    private void compareServiceName(ServiceName serviceNameFirst, ServiceName serviceNameSecond, ServicesMerge servicesMerge) {

        if (serviceNameFirst != null && serviceNameSecond != null) {
            servicesMerge.setServiceName(compareMapsServiceName(serviceNameFirst.getServiceName(), serviceNameSecond.getServiceName()));
            servicesMerge.setServiceName1(compareMapsServiceName(serviceNameFirst.getServiceName1(), serviceNameSecond.getServiceName1()));
            servicesMerge.setServiceName2(compareMapsServiceName(serviceNameFirst.getServiceName2(), serviceNameSecond.getServiceName2()));
        } else if (serviceNameFirst != null) {
            servicesMerge.setServiceName(compareMapsServiceName(serviceNameFirst.getServiceName(), null));
            servicesMerge.setServiceName1(compareMapsServiceName(serviceNameFirst.getServiceName1(), null));
            servicesMerge.setServiceName2(compareMapsServiceName(serviceNameFirst.getServiceName2(), null));
            servicesMerge.setOperationType(Type.DELETED);
        } else if (serviceNameSecond != null) {
            servicesMerge.setServiceName(compareMapsServiceName(null, serviceNameSecond.getServiceName()));
            servicesMerge.setServiceName1(compareMapsServiceName(null, serviceNameSecond.getServiceName1()));
            servicesMerge.setServiceName2(compareMapsServiceName(null, serviceNameSecond.getServiceName2()));
            servicesMerge.setOperationType(Type.ADDED);
        }
    }

    private ArrayList<ServiceStringWrapper> compareMapsServiceName(HashMap<String,String> firstMap, HashMap<String,String> secondMap) {
        ArrayList<ServiceStringWrapper> mergeArr = new ArrayList<>();

        if (firstMap != null && secondMap != null) {
            for (Map.Entry<String, String> entry : firstMap.entrySet()) {
                ServiceStringWrapper serviceStringWrapper = new ServiceStringWrapper();

                if (secondMap.containsKey(entry.getKey())) {
                    serviceStringWrapper.setOldKey(entry.getKey());
                    serviceStringWrapper.setNewKey(entry.getKey());
                    serviceStringWrapper.setOldValue(entry.getValue());
                    serviceStringWrapper.setNewValue(secondMap.get(entry.getKey()));
                    if (entry.getValue() != null && secondMap.get(entry.getKey()) != null) {
                        if (!entry.getValue().equals(secondMap.get(entry.getKey()))) {
                            serviceStringWrapper.setOperationType(Type.UPDATED);
                        } else {
                            serviceStringWrapper.setOperationType(Type.NONE);
                        }
                    }
                } else {
                    serviceStringWrapper.setOldKey(entry.getKey());
                    serviceStringWrapper.setOperationType(Type.DELETED);
                    serviceStringWrapper.setOldValue(entry.getValue());
                }

                mergeArr.add(serviceStringWrapper);
            }

            for (Map.Entry<String, String> entry : secondMap.entrySet()) {
                ServiceStringWrapper serviceStringWrapper = new ServiceStringWrapper();
                boolean flag = false;

                if (!firstMap.containsKey(entry.getKey())) {
                    flag = true;
                    serviceStringWrapper.setNewKey(entry.getKey());
                    serviceStringWrapper.setOperationType(Type.ADDED);
                    serviceStringWrapper.setNewValue(entry.getValue());
                }

                if (flag) {
                    mergeArr.add(serviceStringWrapper);
                }
            }
        } else if (firstMap != null) {
            for (Map.Entry<String, String> entry : firstMap.entrySet()) {
                ServiceStringWrapper serviceStringWrapper = new ServiceStringWrapper();

                serviceStringWrapper.setOldKey(entry.getKey());
                serviceStringWrapper.setOldValue(entry.getValue());
                serviceStringWrapper.setOperationType(Type.DELETED);

                mergeArr.add(serviceStringWrapper);
            }
        } else if (secondMap != null) {
            for (Map.Entry<String, String> entry : secondMap.entrySet()) {
                ServiceStringWrapper serviceStringWrapper = new ServiceStringWrapper();

                serviceStringWrapper.setNewKey(entry.getKey());
                serviceStringWrapper.setNewValue(entry.getValue());
                serviceStringWrapper.setOperationType(Type.ADDED);

                mergeArr.add(serviceStringWrapper);
            }
        } else {
            return null;
        }
        return mergeArr;
    }

    /*private ArrayList<ServiceStringWrapper> serviceNameNull(HashMap<String,String> firstMap, HashMap<String,String> secondMap) {
        ArrayList<ServiceStringWrapper> mergeMap = new HashMap<>();
        if (firstMap != null && secondMap == null) {
            for (Map.Entry<String, String> entry : firstMap.entrySet()) {
                mergeMap.put(new StringWrapper(entry.getKey(), null, Type.DELETED),
                        new StringWrapper(entry.getValue(), null, Type.DELETED));
            }
            return mergeMap;
        } else if (firstMap == null && secondMap != null) {
            for (Map.Entry<String, String> entry : secondMap.entrySet()) {
                mergeMap.put(new StringWrapper(entry.getKey(), null, Type.DELETED),
                        new StringWrapper(entry.getValue(), null, Type.DELETED));
            }
            return mergeMap;
        }
        return null;
    }*/

    private HashesMerge compareHashes(Hashes hashesFirst, Hashes hashesSecond) {
        HashesMerge hashesMerge = new HashesMerge();

        if (hashesFirst != null && hashesSecond != null) {
            if (hashesFirst.getSha1() != null && hashesSecond.getSha1() != null) {
                if (!hashesFirst.getSha1().equals(hashesSecond.getSha1())) {
                    hashesMerge.setSha1(new StringWrapper(hashesFirst.getSha1(), hashesSecond.getSha1(), Type.UPDATED));
                } else {
                    hashesMerge.setSha1(new StringWrapper(hashesFirst.getSha1(), hashesSecond.getSha1(), Type.NONE));
                }
            } else if (hashesFirst.getSha1() != null) {
                hashesMerge.setSha1(new StringWrapper(hashesFirst.getSha1(), hashesSecond.getSha1(), Type.DELETED));
            } else if (hashesSecond.getSha1() != null) {
                hashesMerge.setSha1(new StringWrapper(hashesFirst.getSha1(), hashesSecond.getSha1(), Type.ADDED));
            }

            if (hashesFirst.getSha256() != null && hashesSecond.getSha256() != null) {
                if (!hashesFirst.getSha256().equals(hashesSecond.getSha256())) {
                    hashesMerge.setSha256(new StringWrapper(hashesFirst.getSha256(), hashesSecond.getSha256(), Type.UPDATED));
                } else {
                    hashesMerge.setSha256(new StringWrapper(hashesFirst.getSha256(), hashesSecond.getSha256(), Type.NONE));
                }
            } else if (hashesFirst.getSha256() != null) {
                hashesMerge.setSha256(new StringWrapper(hashesFirst.getSha256(), hashesSecond.getSha256(), Type.DELETED));
            } else if (hashesSecond.getSha256() != null) {
                hashesMerge.setSha256(new StringWrapper(hashesFirst.getSha256(), hashesSecond.getSha256(), Type.ADDED));
            }
        } else if (hashesFirst != null) {
            hashesMerge.setOperationType(Type.DELETED);

            if (hashesFirst.getSha1() != null) {
                hashesMerge.setSha1(new StringWrapper(hashesFirst.getSha1(), null, Type.DELETED));
            }
            if (hashesFirst.getSha256() != null) {
                hashesMerge.setSha256(new StringWrapper(hashesFirst.getSha256(), null, Type.DELETED));
            }
        } else if (hashesSecond != null) {
            hashesMerge.setOperationType(Type.ADDED);

            if (hashesSecond.getSha1() != null) {
                hashesMerge.setSha1(new StringWrapper(null, hashesSecond.getSha1(), Type.ADDED));
            }
            if (hashesSecond.getSha256() != null) {
                hashesMerge.setSha256(new StringWrapper(null, hashesSecond.getSha256(), Type.ADDED));
            }
        }

        return hashesMerge;
    }

    private FileMerge compareFiles(File fileFirst, File fileSecond) {
        FileMerge fileMerge = new FileMerge();

        if (fileFirst != null && fileSecond != null) {
            if (!fileFirst.getFile().equals(fileSecond.getFile())) {
                fileMerge.setFile(new StringWrapper(fileFirst.getFile(), fileSecond.getFile(), Type.UPDATED));
            } else {
                fileMerge.setFile(new StringWrapper(fileFirst.getFile(), fileSecond.getFile(), Type.NONE));
            }
        } else if (fileFirst != null) {
            if (fileFirst.getFile() != null) {
                fileMerge.setFile(new StringWrapper(fileFirst.getFile(), null, Type.DELETED));
            }
            fileMerge.setOperationType(Type.DELETED);
        } else if (fileSecond != null) {
            if (fileSecond.getFile() != null) {
                fileMerge.setFile(new StringWrapper(null, fileSecond.getFile(), Type.ADDED));
            }
            fileMerge.setOperationType(Type.ADDED);
        }

        return fileMerge;
    }

    private ArrayList<FileMerge> compareArrayFile(ArrayList<File> fileArrFirst, ArrayList<File> fileArrSecond) {
        ArrayList<FileMerge> fileArrMerge = new ArrayList<>();

        if (fileArrFirst != null && fileArrSecond != null) {
            if (!fileArrFirst.isEmpty() && !fileArrSecond.isEmpty()) {
                fileArrMerge.add(compareFiles(fileArrFirst.get(0), fileArrSecond.get(0)));
            } else if (!fileArrFirst.isEmpty()) {
                fileArrMerge.add(compareFiles(fileArrFirst.get(0), null));
            } else if (!fileArrSecond.isEmpty()) {
                fileArrMerge.add(compareFiles(null, fileArrSecond.get(0)));
            }
        } else if (fileArrFirst != null) {
            if (!fileArrFirst.isEmpty()) {
                fileArrMerge.add(compareFiles(fileArrFirst.get(0), null));
            }
        } else if (fileArrSecond != null) {
            if (!fileArrSecond.isEmpty()) {
                fileArrMerge.add(compareFiles(fileArrSecond.get(0), null));
            }
        }

        if (!fileArrMerge.isEmpty()) {
            return fileArrMerge;
        }
        return null;
    }

    public JSONModelMerge getJsonModelMerge() {
        return jsonModelMerge;
    }
}
