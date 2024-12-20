package fr.scrumtogether.scrumtogetherapi.services;

import fr.scrumtogether.scrumtogetherapi.dtos.ProjectDto;
import fr.scrumtogether.scrumtogetherapi.entities.Project;
import fr.scrumtogether.scrumtogetherapi.exceptions.EntityNotFoundException;
import fr.scrumtogether.scrumtogetherapi.mappers.ProjectMapper;
import fr.scrumtogether.scrumtogetherapi.repositories.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    @Transactional(readOnly = true)
    public Page<Project> getAll(Integer pageNumber, Integer pageSize) {
        log.debug("Getting all projects - paginated");

        // Validate and set default page number
        int validatedPageNumber = Optional.ofNullable(pageNumber)
                .filter(page -> page > 0)
                .orElse(0);

        // Validate and set default page size with upper bound
        int validatedPageSize = Optional.ofNullable(pageSize)
                .filter(size -> size > 0 && size <= 100)
                .orElse(20);

        log.debug("Using page number: {}, page size: {}", validatedPageNumber, validatedPageSize);

        PageRequest pageRequest = PageRequest.of(validatedPageNumber, validatedPageSize);
        return projectRepository.findAll(pageRequest);
    }

    @Transactional(readOnly = true)
    public Project getById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));
    }

    @Transactional
    public Project update(Long projectId, ProjectDto projectDto) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));
        projectMapper.updateEntity(project, projectDto);
        return projectRepository.save(project);
    }

    @Transactional
    public void delete(Long id) {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isPresent()) {
            projectRepository.delete(project.get());
        } else {
            throw new EntityNotFoundException("Project not found");
        }
    }

    @Transactional
    public Project create(ProjectDto projectDto) {
        log.debug("Processing project creation: {}", projectDto.getName());
        try {
            Project project = projectMapper.toEntity(projectDto);
            projectRepository.save(project);
            return project;
        } catch (Exception e) {
            log.error("Unexpected error during creation for project: {}", projectDto.getName(), e);
            throw new EntityNotFoundException("Failled to create the project", e);
        }
    }

}