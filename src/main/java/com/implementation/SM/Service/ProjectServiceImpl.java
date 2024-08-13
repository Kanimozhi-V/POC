package com.implementation.SM.Service;

import com.implementation.SM.Entity.Project;
import com.implementation.SM.Entity.Status;
import com.implementation.SM.Entity.StatusEnum;
import com.implementation.SM.Repository.ProjectRepository;
import com.implementation.SM.Repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public Project saveProject(Project project) {
        for (Status status : project.getStatuses()) {
            status.setProject(project);
            calculateCurrentStatus(status);
        }
        return projectRepository.save(project);

    }

    @Override
    public void saveStatus(Long projectId, Status status) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Project not found"));
        status.setProject(project);
        calculateCurrentStatus(status);
        project.getStatuses().add(status);
        projectRepository.save(project);
    }

    @Override
    public List<Project> fetchAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project updateProject(Long id, Project updatedProject) {
        Project existingProject = findById(id);

        // Update fields
        existingProject.setName(updatedProject.getName());
        existingProject.setLead(updatedProject.getLead());
        existingProject.setTechStack(updatedProject.getTechStack());
        existingProject.setStatus(updatedProject.getStatus());
        existingProject.setDeadline(updatedProject.getDeadline());
        existingProject.setTotalPeopleAllocated(updatedProject.getTotalPeopleAllocated());
        existingProject.setActualAllocationSOW(updatedProject.getActualAllocationSOW());
        existingProject.setAdrItemDocumentation(updatedProject.getAdrItemDocumentation());
        existingProject.setC4Documentation(updatedProject.getC4Documentation());
        existingProject.setArchitectureHandover(updatedProject.getArchitectureHandover());
        existingProject.setDecodersRepository(updatedProject.getDecodersRepository());
        existingProject.setProcessAdoptionLatestVersion(updatedProject.getProcessAdoptionLatestVersion());
        existingProject.setProjectKickoff(updatedProject.getProjectKickoff());
        existingProject.setProjectSharedDocumentRepository(updatedProject.getProjectSharedDocumentRepository());
        // Update other fields as needed

        // Save updated project
        return projectRepository.save(existingProject);
    }

    @Override
    public Project updateProjectStatus(Long projectId, List<Status> statuses) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Project not found"));
        for (Status status : statuses) {
            status.setProject(project);
            calculateCurrentStatus(status);
            project.getStatuses().add(status);
        }
        return projectRepository.save(project);
    }

    @Override
    public Project findById(Long id) {
        return projectRepository.findById(id).orElseThrow(() -> new RuntimeException("Project not found with id " + id));
    }


//    @Override
//    public String getCurrentStatusOfProject(Long projectId) {
//        Project project = projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Project not found"));
//        Status latestStatus = project.getStatuses().get(project.getStatuses().size() - 1);
//        return latestStatus.getCurrentStatus();
//    }

    @Override
    public String getCurrentStatusOfProject(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        List<Status> statuses = project.getStatuses();

        if (statuses == null || statuses.isEmpty()) {
            return "not yet";
        } else {
            Status latestStatus = statuses.get(statuses.size() - 1);
            return latestStatus.getCurrentStatus();
        }
    }

    @Override
    public void deleteProject(Long id){
        Project project = findById(id);
        projectRepository.delete(project);

    }

    private void calculateCurrentStatus(Status status) {
        int totalTasks = 0;

        int completedTasks = 0;

        Project project = status.getProject();

        // Check the project-level attributes

       /* if (project.getAdrItemDocumentation() != StatusEnum.NOT_NECESSARY) {
            totalTasks++;
            completedTasks += project.getAdrItemDocumentation().getValue();
        }
        if (project.getC4Documentation() != StatusEnum.NOT_NECESSARY) {
            totalTasks++;
            completedTasks += project.getC4Documentation().getValue();;
        }
        if (project.getArchitectureHandover() != StatusEnum.NOT_NECESSARY) {
            totalTasks++;
            completedTasks += project.getArchitectureHandover().getValue();
        }
        if (project.getDecodersRepository() != StatusEnum.NOT_NECESSARY) {
            totalTasks++;
            completedTasks += project.getDecodersRepository().getValue();
        }
        if (project.getProcessAdoptionLatestVersion() != StatusEnum.NOT_NECESSARY) {
            totalTasks++;
            completedTasks += project.getProcessAdoptionLatestVersion().getValue();
        }
        if (project.getProjectKickoff() != StatusEnum.NOT_NECESSARY) {
            totalTasks++;
            completedTasks += project.getProjectKickoff().getValue();
        }

        if (project.getSalesHandover() != StatusEnum.NOT_NECESSARY) {
            totalTasks++;
            completedTasks += project.getSalesHandover().getValue();
        }

        if (project.getProjectSharedDocumentRepository() != StatusEnum.NOT_NECESSARY) {
            totalTasks++;
            completedTasks += project.getProjectSharedDocumentRepository().getValue();
        }

        // Check the status-level attributes

        if (status.getDailyUpdates() != StatusEnum.NOT_NECESSARY) {
            totalTasks++;
            completedTasks += status.getDailyUpdates().getValue();
        }

        if(status.getProcessFlows() != StatusEnum.NOT_NECESSARY){
            totalTasks++ ;
            completedTasks += status.getProcessFlows().getValue();
        }
        if (status.getQaUpdates() != StatusEnum.NOT_NECESSARY) {
            totalTasks++ ;
            completedTasks += status.getQaUpdates().getValue();
        }
        if (status.getSonarqube() != StatusEnum.NOT_NECESSARY) {
            totalTasks++;
            completedTasks += status.getSonarqube().getValue();
        }
        if (status.getWeeklyReports() != StatusEnum.NOT_NECESSARY) {
            totalTasks++;
            completedTasks += status.getWeeklyReports().getValue();
        }
        if (status.getCloseClientCommunication() != StatusEnum.NOT_NECESSARY) {
            totalTasks++;
            completedTasks += status.getCloseClientCommunication().getValue();
        }
        if (status.getGitReport() != StatusEnum.NOT_NECESSARY) {
            totalTasks++;
            completedTasks += status.getGitReport().getValue();
        }
        if (status.getEscalation() != StatusEnum.NOT_NECESSARY) {
            totalTasks++;
            completedTasks += status.getEscalation().getValue();
        }
        if (status.getBoardUpdates() != StatusEnum.NOT_NECESSARY) {
            totalTasks++;
            completedTasks += status.getBoardUpdates().getValue();
        }*/

        // Array of project-level attributes
        StatusEnum[] projectAttributes = {
                project.getAdrItemDocumentation(),
                project.getC4Documentation(),
                project.getArchitectureHandover(),
                project.getDecodersRepository(),
                project.getProcessAdoptionLatestVersion(),
                project.getProjectKickoff(),
                project.getSalesHandover(),
                project.getProjectSharedDocumentRepository()
        };

        // Check the project-level attributes
        for (StatusEnum attribute : projectAttributes) {
            if (attribute != StatusEnum.NOT_NECESSARY) {
                totalTasks++;
                completedTasks += attribute.getValue();
            }
        }

        // Array of status-level attributes
        StatusEnum[] statusAttributes = {
                status.getDailyUpdates(),
                status.getProcessFlows(),
                status.getQaUpdates(),
                status.getSonarqube(),
                status.getWeeklyReports(),
                status.getCloseClientCommunication(),
                status.getGitReport(),
                status.getEscalation(),
                status.getBoardUpdates()
        };

        // Check the status-level attributes
        for (StatusEnum attribute : statusAttributes) {
            if (attribute != StatusEnum.NOT_NECESSARY) {
                totalTasks++;
                completedTasks += attribute.getValue();
            }
        }

        // Adjust total and completed tasks based on boolean fields

        if (status.isDefectsReport()) totalTasks++; completedTasks--;

        if (status.isRisksInDelivery()) completedTasks--;

        double completionPercentage = (double) completedTasks / totalTasks * 100;

        String currentStatus;
        if (completionPercentage >= 90) {
            currentStatus = "On Track";
        } else if (completionPercentage >= 60 && completionPercentage <= 89) {
            currentStatus = "Slow";
        } else if (completionPercentage >= 30 && completionPercentage <= 59) {
            currentStatus = "Risk";
        } else {
            currentStatus = "Deep Risk";
        }
        status.setCurrentStatus(currentStatus);
        System.out.println("Percentage:"+completionPercentage);
        System.out.println("Current Status of the project:"+currentStatus);
    }
}
