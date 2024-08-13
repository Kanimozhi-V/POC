package com.implementation.SM.Controller;

import com.implementation.SM.Entity.Project;
import com.implementation.SM.Entity.Status;
import com.implementation.SM.Repository.ProjectRepository;
import com.implementation.SM.Service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("/projects")
    public ResponseEntity<String> saveProject(@RequestBody Project project) {
        Project project1=projectService.saveProject(project);
        return ResponseEntity.ok("Project Inserted Successfully!!");
    }

    @PostMapping("/projects/{id}/savestatus")
    public ResponseEntity<?> saveStatus(@PathVariable Long id, @RequestBody Status status) {
        projectService.saveStatus(id, status);
        return ResponseEntity.ok("Status Added Successfully!!");
    }

    @GetMapping("/projects")
    public List<Project> getAllProjects() {
        return projectService.fetchAllProjects();
    }

    @GetMapping("/projects/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        Project project = projectService.findById(id);
        return ResponseEntity.ok(project);
    }

//    @GetMapping("/projects/{id}/status")
//    public String getCurrentStatus(@PathVariable Long id) {
//        return projectService.getCurrentStatusOfProject(id);
//    }

    @GetMapping("/projects/{id}/status")
    public ResponseEntity<Map<String, String>> getStatus(@PathVariable Long id) {
        Map<String, String> response = new HashMap<>();
        // Assume you fetch the status string from your service layer
        String status = projectService.getCurrentStatusOfProject(id);
        response.put("status", status);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/projects/{id}/status")
    public ResponseEntity<String> updateProjectStatus(@PathVariable Long id, @RequestBody List<Status> statuses) {
        Project project=projectService.updateProjectStatus(id, statuses);
        return ResponseEntity.ok("Project Status Updated Successfully!!");
    }

    @PutMapping("/projects/{id}")
    public ResponseEntity<String> updateProject(@PathVariable Long id,@RequestBody Project project){
        Project project1=projectService.updateProject(id,project);
        return ResponseEntity.ok("Project Updated Successfully!!");
    }

    @DeleteMapping("/projects/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteProject(@PathVariable Long id){
        projectService.deleteProject(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}