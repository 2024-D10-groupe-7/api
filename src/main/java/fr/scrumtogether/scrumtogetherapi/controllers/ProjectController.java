package fr.scrumtogether.scrumtogetherapi.controllers;

import org.springframework.data.domain.Page;
import fr.scrumtogether.scrumtogetherapi.dtos.ProjectDto;
import fr.scrumtogether.scrumtogetherapi.entities.Project;
import org.springframework.hateoas.PagedModel;
import fr.scrumtogether.scrumtogetherapi.mappers.ProjectMapper;
import fr.scrumtogether.scrumtogetherapi.services.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nullable;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {
    private final ProjectService projectService;
    private final PagedResourcesAssembler<Project> pagedResourcesAssembler;
    private final ProjectMapper projectMapper;

    @GetMapping
    public ResponseEntity<PagedModel<ProjectDto>> getAll(@RequestParam @Nullable Integer page, @RequestParam @Nullable Integer size) {
        Page<Project> paginated = projectService.getAll(page, size);
        PagedModel<ProjectDto> paginatedDto = pagedResourcesAssembler.toModel(paginated, projectMapper);
        return ResponseEntity.ok(paginatedDto);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProjectDto> getById(@PathVariable Long id) {
        Project project = projectService.getById(id);
        ProjectDto dto = projectMapper.toModel(project);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("{id}")
    public ResponseEntity<ProjectDto> update( @PathVariable Long id, @RequestBody ProjectDto projectDto) {
        Project project = projectService.update(id, projectDto);
        ProjectDto dto = projectMapper.toModel(project);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(Long id) {
        projectService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/create")
    public ResponseEntity<ProjectDto> create(@RequestBody ProjectDto projectDto) {
        Project project = projectService.create(projectDto);
        ProjectDto dto = projectMapper.toModel(project);
        return ResponseEntity.ok(dto);
    }
}
