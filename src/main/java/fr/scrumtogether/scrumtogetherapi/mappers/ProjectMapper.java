package fr.scrumtogether.scrumtogetherapi.mappers;


import fr.scrumtogether.scrumtogetherapi.controllers.ProjectController;
import fr.scrumtogether.scrumtogetherapi.dtos.ProjectDto;
import fr.scrumtogether.scrumtogetherapi.dtos.TeamDto;
import fr.scrumtogether.scrumtogetherapi.entities.Project;
import fr.scrumtogether.scrumtogetherapi.entities.Team;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProjectMapper extends RepresentationModelAssemblerSupport<Project, ProjectDto> {
    public ProjectMapper() {
        super(ProjectController.class, ProjectDto.class);
    }

    public Project toEntity(ProjectDto projectDto) {
        log.debug("Mapping registration request for user: {}", projectDto.getName());
        return Project.builder()
                .name(projectDto.getName().trim())
                .description(projectDto.getDescription().trim())
                .startDate(projectDto.getStartDate())
                .endDate(projectDto.getEndDate())
                .gitRepositories(projectDto.getGitRepositories())
                .projectUsers(projectDto.getProjectUsers())
                .items(projectDto.getItems())
                .sprints(projectDto.getSprints())
                .team(projectDto.getTeam())
                .build();
    }

    public void updateEntity(Project project, ProjectDto projectDto) {
        log.debug("Updating project: {}", project.getName());

        project.setName(projectDto.getName());
        project.setDescription(projectDto.getDescription());
        project.setStartDate(projectDto.getStartDate());
        project.setEndDate(projectDto.getEndDate());
        project.setGitRepositories(projectDto.getGitRepositories());
//        project.setProjectUsers(projectDto.getProjectUsers());
//        project.setItems(projectDto.getItems());
//        project.setSprints(projectDto.getSprints());
//        project.setTeam(projectDto.getTeam());
    }

    @Override
    public ProjectDto toModel(@NonNull Project entity) {
        log.debug("Mapping project to projectDto: {}", entity.getName());
        ProjectDto projectDto = instantiateModel(entity);
        projectDto.setId(entity.getId());
        projectDto.setName(entity.getName());
        projectDto.setDescription(entity.getDescription());
        projectDto.setStartDate(entity.getStartDate());
        projectDto.setEndDate(entity.getEndDate());
        projectDto.setGitRepositories(entity.getGitRepositories());
        projectDto.setProjectUsers(entity.getProjectUsers());
        projectDto.setSprints(entity.getSprints());
        projectDto.setTeam(entity.getTeam());
        projectDto.setItems(entity.getItems());
        return projectDto;
    }
}
