package com.implementation.SM.Service;


import com.implementation.SM.Entity.Project;
import com.implementation.SM.Entity.Status;

import java.util.List;

public interface ProjectService {
    Project saveProject(Project project);
    List<Project> fetchAllProjects();
    String getCurrentStatusOfProject(Long projectId);
    void saveStatus(Long projectId, Status status);
    Project updateProjectStatus(Long projectId, List<Status> statuses);
    Project findById(Long id);
    Project updateProject(Long id, Project updatedProject);
    void deleteProject(Long id);
}