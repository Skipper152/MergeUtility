package com.netcracker.mergemodels;

import com.netcracker.mergemodels.wrapper.BooleanWrapper;
import com.netcracker.mergemodels.wrapper.IntegerWrapper;
import com.netcracker.mergemodels.wrapper.StringWrapper;
import com.netcracker.mergemodels.wrapper.Type;
import com.netcracker.models.*;

import java.nio.file.FileVisitOption;
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
        //comparison application field
        metadataMerge.setApplicationMerge(new ApplicationMerge());
        compareApplication(metadataFirst.getApplication(), metadataSecond.getApplication(), metadataMerge.getApplicationMerge());

        //comparison description field
        metadataMerge.setDescriptionMerge(new DescriptionMerge());
        compareDescription(metadataFirst.getDescription(), metadataSecond.getDescription(), metadataMerge.getDescriptionMerge());
    }

    private void compareApplication(Application appFirst, Application appSecond, ApplicationMerge applicationMerge) {
        if (appFirst.getName() != null && appSecond.getName() != null) {
            if (!appFirst.getName().equals(appSecond.getName())) {
                applicationMerge.setName(new StringWrapper(appFirst.getName(),appSecond.getName(), Type.UPDATED));
            } else {
                applicationMerge.setName(new StringWrapper(appFirst.getName(),appSecond.getName(), Type.NONE));
            }
        } else if (appFirst.getName() != null) {
            applicationMerge.setName(new StringWrapper(appFirst.getName(), null, Type.DELETED));
        } else if (appSecond.getName() != null) {
            applicationMerge.setName(new StringWrapper(null, appSecond.getName(), Type.ADDED));
        }
    }

    private void compareDescription(Description desFirst, Description desSecond, DescriptionMerge descriptionMerge) {
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
                //
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
            serviceMerge.setHashesMerge(new HashesMerge(new StringWrapper(serviceFirst.getHashes().getSha1(), null, Type.DELETED),
                    new StringWrapper(serviceFirst.getHashes().getSha256(), null, Type.DELETED)));

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
        } else if (serviceFirst == null && serviceSecond != null) {
            // mandatory fields
            serviceMerge.setServiceName(new StringWrapper(null, serviceSecond.getServiceName(), Type.ADDED));
            serviceMerge.setArtifactType(new StringWrapper(null, serviceSecond.getArtifactType(), Type.ADDED));
            serviceMerge.setDockerRegistry(new StringWrapper(null, serviceSecond.getDockerRegistry(), Type.ADDED));
            serviceMerge.setDockerImageName(new StringWrapper(null, serviceSecond.getDockerImageName(), Type.ADDED));
            serviceMerge.setDockerTag(new StringWrapper(null, serviceSecond.getDockerTag(), Type.ADDED));
            serviceMerge.setHashesMerge(new HashesMerge(new StringWrapper(null, serviceSecond.getHashes().getSha1(), Type.ADDED),
                    new StringWrapper(null, serviceSecond.getHashes().getSha256(), Type.ADDED)));

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
        }

        return serviceMerge;
    }

    private ServiceMerge compareService(Service serviceFirst, Service serviceSecond) {
        ServiceMerge serviceMerge = new ServiceMerge();

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
                    artifactMerges.add(compareMVNsArray(first.getMvns(), second.getMvns()));
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
                    artifactMerge.setMvnMerges(mvnMerges);
                    artifactMerge.setTargetRepository(new StringWrapper(first.getTargetRepository(), null, Type.DELETED));
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
                    artifactMerge.setMvnMerges(mvnMerges);
                    artifactMerge.setTargetRepository(new StringWrapper(null, second.getTargetRepository(), Type.DELETED));
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
            mvnMerge.setHashesMerge(new HashesMerge(new StringWrapper(mvnFirst.getHashes().getSha1(), null, Type.DELETED),
                    new StringWrapper(mvnFirst.getHashes().getSha256(), null, Type.DELETED)));

            // optional fields
            if (mvnFirst.getServiceName() != null) {
                mvnMerge.setServiceName(new StringWrapper(mvnFirst.getServiceName(), null, Type.DELETED));
            }
            if (mvnFirst.getClassifier() != null) {
                mvnMerge.setClassifier(new StringWrapper(mvnFirst.getClassifier(), null, Type.DELETED));
            }
        } else if (mvnFirst == null && mvnSecond != null) {
            mvnMerge.setGroupId(new StringWrapper(null, mvnSecond.getGroupId(), Type.ADDED));
            mvnMerge.setArtifactId(new StringWrapper(null, mvnSecond.getArtifactId(), Type.ADDED));
            mvnMerge.setVersion(new StringWrapper(null, mvnSecond.getVersion(), Type.ADDED));
            mvnMerge.setMvnType(new StringWrapper(null, mvnSecond.getMvnType(), Type.ADDED));
            mvnMerge.setMvnRepository(new StringWrapper(null, mvnSecond.getMvnRepository(), Type.ADDED));
            mvnMerge.setHashesMerge(new HashesMerge(new StringWrapper(null, mvnSecond.getHashes().getSha1(), Type.ADDED),
                    new StringWrapper(null, mvnSecond.getHashes().getSha256(), Type.ADDED)));

            // optional fields
            if (mvnSecond.getServiceName() != null) {
                mvnMerge.setServiceName(new StringWrapper(null, mvnSecond.getServiceName(), Type.ADDED));
            }
            if (mvnSecond.getClassifier() != null) {
                mvnMerge.setClassifier(new StringWrapper(null, mvnSecond.getClassifier(), Type.ADDED));
            }
        }

        return mvnMerge;
    }

    private MVNMerge compareMVN(MVN mvnFirst, MVN mvnSecond) {
        MVNMerge mvnMerge = new MVNMerge();

        //comparison service_name field
        if (mvnFirst.getServiceName() != null && mvnSecond.getServiceName() != null) {
            if (mvnFirst.getServiceName().equals(mvnSecond.getServiceName())) {
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
            if (mvnFirst.getClassifier().equals(mvnSecond.getClassifier())) {
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
            artifactMerge.setHashesMerge(new HashesMerge(new StringWrapper(artifactFirst.getHashes().getSha1(), null, Type.DELETED),
                                                         new StringWrapper(artifactFirst.getHashes().getSha256(), null, Type.DELETED)));

            ArrayList<FileMerge> fileMerges = new ArrayList<>();
            fileMerges.add(new FileMerge(new StringWrapper(artifactFirst.getFiles().get(0).getFile(), null, Type.DELETED)));
            artifactMerge.setFileMerges(fileMerges);

            artifactMerge.setTargetRepository(new StringWrapper(artifactFirst.getTargetRepository(), null, Type.DELETED));

            // optional fields
            if (artifactFirst.getServiceShortName() != null) {
                artifactMerge.setServiceShortName(new StringWrapper(artifactFirst.getServiceShortName(), null, Type.DELETED));
            }
            if (artifactFirst.getServiceName() != null) {
                artifactMerge.setServiceName(new StringWrapper(artifactFirst.getServiceName(), null, Type.DELETED));
            }
        } else if (artifactFirst == null && artifactSecond != null) {
            // mandatory fields
            artifactMerge.setHashesMerge(new HashesMerge(new StringWrapper(null, artifactSecond.getHashes().getSha1(), Type.ADDED),
                    new StringWrapper(null, artifactSecond.getHashes().getSha256(), Type.ADDED)));

            ArrayList<FileMerge> fileMerges = new ArrayList<>();
            fileMerges.add(new FileMerge(new StringWrapper(null, artifactSecond.getFiles().get(0).getFile(), Type.ADDED)));
            artifactMerge.setFileMerges(fileMerges);

            artifactMerge.setTargetRepository(new StringWrapper(null, artifactSecond.getTargetRepository(), Type.ADDED));

            // optional fields
            if (artifactSecond.getServiceShortName() != null) {
                artifactMerge.setServiceShortName(new StringWrapper(null, artifactSecond.getServiceShortName(), Type.ADDED));
            }
            if (artifactSecond.getServiceName() != null) {
                artifactMerge.setServiceName(new StringWrapper(null, artifactSecond.getServiceName(), Type.ADDED));
            }
        }

        return artifactMerge;
    }

    private ArtifactMerge compareArtifact(Artifact artifactFirst, Artifact artifactSecond) {
        ArtifactMerge artifactMerge = new ArtifactMerge();

        if (artifactFirst.getServiceShortName() != null && artifactSecond.getServiceShortName() != null) {
            if (artifactFirst.getServiceShortName().equals(artifactSecond.getServiceShortName())) {
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
            if (artifactFirst.getServiceName().equals(artifactSecond.getServiceName())) {
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
            scriptMerge.setHashes(new HashesMerge(new StringWrapper(scriptFirst.getHashes().getSha1(), null, Type.DELETED),
                                                new StringWrapper(scriptFirst.getHashes().getSha256(), null, Type.DELETED)));
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
        } else if (scriptFirst == null && scriptSecond != null) {
            // mandatory fields
            scriptMerge.setScriptName(new StringWrapper(null, scriptSecond.getScriptName(), Type.ADDED));
            scriptMerge.setHashes(new HashesMerge(new StringWrapper(null, scriptSecond.getHashes().getSha1(), Type.ADDED),
                    new StringWrapper(null, scriptSecond.getHashes().getSha256(), Type.ADDED)));
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
        }

        return scriptMerge;
    }

    private ScriptMerge compareScript(Script scriptFirst, Script scriptSecond) {
        ScriptMerge scriptMerge = new ScriptMerge();

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
            rpmMerge.setHashesMerge(new HashesMerge(new StringWrapper(rpmFirst.getHashes().getSha1(), null, Type.DELETED),
                                                    new StringWrapper(rpmFirst.getHashes().getSha256(), null, Type.DELETED)));
            rpmMerge.setUrl(new StringWrapper(rpmFirst.getUrl(), null, Type.DELETED));

            // optional fields
            if (rpmFirst.getServiceShortName() != null) {
                rpmMerge.setServiceShortName(new StringWrapper(rpmFirst.getServiceShortName(), null, Type.DELETED));
            }
        } else if (rpmFirst == null && rpmSecond != null) {
            // mandatory fields
            rpmMerge.setRpmRepositoryName(new StringWrapper(null, rpmSecond.getRpmRepositoryName(), Type.ADDED));
            rpmMerge.setHashesMerge(new HashesMerge(new StringWrapper(null, rpmSecond.getHashes().getSha1(), Type.ADDED),
                    new StringWrapper(null, rpmSecond.getHashes().getSha256(), Type.ADDED)));
            rpmMerge.setUrl(new StringWrapper(null, rpmSecond.getUrl(), Type.ADDED));

            // optional fields
            if (rpmSecond.getServiceShortName() != null) {
                rpmMerge.setServiceShortName(new StringWrapper(null, rpmSecond.getServiceShortName(), Type.ADDED));
            }
        }

        return rpmMerge;
    }

    private RPMMerge compareRPM(RPM rpmFirst, RPM rpmSecond) {
        RPMMerge rpmMerge = new RPMMerge();

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
        // comparison common fields
        parametersMerge.setCommonMerge(new CommonMerge());
        compareCommon(parametersFirst.getCommon(), parametersSecond.getCommon(), parametersMerge.getCommonMerge());

        // comparison services fields
        parametersMerge.setServices(new ServiceNameMerge());
        //compareServiceName(parametersFirst.getServices(), parametersSecond.getServices(), parametersMerge.getServices());
    }

    private void compareCommon(Common commonFirst, Common commonSecond, CommonMerge commonMerge) {

        if (commonFirst != null && commonSecond != null) {
            //if (commonFirst.equals(commonSecond)) {
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
        }
    }

    private void compareServiceName(ServiceName serviceNameFirst, ServiceName serviceNameSecond, ServiceNameMerge serviceNameMerge) {

        if (serviceNameFirst != null && serviceNameSecond != null) {
            serviceNameMerge.setServiceName(compareMapsServiceName(serviceNameFirst.getServiceName(), serviceNameSecond.getServiceName()));
            serviceNameMerge.setServiceName1(compareMapsServiceName(serviceNameFirst.getServiceName1(), serviceNameSecond.getServiceName1()));
            serviceNameMerge.setServiceName2(compareMapsServiceName(serviceNameFirst.getServiceName2(), serviceNameSecond.getServiceName2()));
        } else if (serviceNameFirst != null) {
            serviceNameMerge.setServiceName(serviceNameNull(serviceNameFirst.getServiceName(), null));
            serviceNameMerge.setServiceName1(serviceNameNull(serviceNameFirst.getServiceName1(), null));
            serviceNameMerge.setServiceName2(serviceNameNull(serviceNameFirst.getServiceName2(), null));
        } else if (serviceNameSecond != null) {
            serviceNameMerge.setServiceName(serviceNameNull(null, serviceNameSecond.getServiceName()));
            serviceNameMerge.setServiceName1(serviceNameNull(null, serviceNameSecond.getServiceName1()));
            serviceNameMerge.setServiceName2(serviceNameNull(null, serviceNameSecond.getServiceName2()));
        }
    }

    private HashMap<StringWrapper, StringWrapper> compareMapsServiceName(HashMap<String,String> firstMap, HashMap<String,String> secondMap) {
        HashMap<StringWrapper, StringWrapper> mergeMap = new HashMap<>();
        if (firstMap != null && secondMap != null) {
            for (Map.Entry<String, String> entry : firstMap.entrySet()) {
                if (secondMap.containsKey(entry.getKey())) {
                    if (entry.getValue() != null && secondMap.get(entry.getKey()) != null) {
                        if (!entry.getValue().equals(secondMap.get(entry.getKey()))) {
                            mergeMap.put(new StringWrapper(entry.getKey(), entry.getKey(), Type.NONE),
                                         new StringWrapper(entry.getValue(), secondMap.get(entry.getKey()), Type.UPDATED));
                        } else {
                            mergeMap.put(new StringWrapper(entry.getKey(), entry.getKey(), Type.NONE),
                                    new StringWrapper(entry.getValue(), secondMap.get(entry.getKey()), Type.NONE));
                        }
                    } else if (entry.getValue() != null) {
                        mergeMap.put(new StringWrapper(entry.getKey(), entry.getKey(), Type.NONE),
                                new StringWrapper(entry.getValue(), secondMap.get(entry.getKey()), Type.DELETED));

                    } else if (secondMap.get(entry.getKey()) != null) {
                        mergeMap.put(new StringWrapper(entry.getKey(), entry.getKey(), Type.NONE),
                                new StringWrapper(entry.getValue(), secondMap.get(entry.getKey()), Type.ADDED));
                    }
                } else {
                    mergeMap.put(new StringWrapper(entry.getKey(), null, Type.DELETED),
                                 new StringWrapper(entry.getValue(), null, Type.DELETED));
                }
            }

            for (Map.Entry<String, String> entry : secondMap.entrySet()) {
                if (!firstMap.containsKey(entry.getKey())) {
                    mergeMap.put(new StringWrapper(null, entry.getKey(), Type.ADDED),
                                 new StringWrapper(null, entry.getValue(), Type.ADDED));
                }
            }
        } else if (firstMap != null) {
            for (Map.Entry<String, String> entry : firstMap.entrySet()) {
                mergeMap.put(new StringWrapper(entry.getKey(), null, Type.DELETED),
                             new StringWrapper(entry.getValue(), null, Type.DELETED));
            }
        } else if (secondMap != null) {
            for (Map.Entry<String, String> entry : secondMap.entrySet()) {
                mergeMap.put(new StringWrapper(null, entry.getKey(), Type.DELETED),
                        new StringWrapper(null, entry.getValue(), Type.DELETED));
            }
        }
        return mergeMap;
    }

    private HashMap<StringWrapper,StringWrapper> serviceNameNull(HashMap<String,String> firstMap, HashMap<String,String> secondMap) {
        HashMap<StringWrapper,StringWrapper> mergeMap = new HashMap<>();
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
    }

    public JSONModelMerge getJsonModelMerge() {
        return jsonModelMerge;
    }
}
