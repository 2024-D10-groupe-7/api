package fr.scrumtogether.scrumtogetherapi.dtos;

import fr.scrumtogether.scrumtogetherapi.entities.Item;
import fr.scrumtogether.scrumtogetherapi.entities.ProjectUser;
import fr.scrumtogether.scrumtogetherapi.entities.Sprint;
import fr.scrumtogether.scrumtogetherapi.entities.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


/**
 * DTO for {@link Project}
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class ProjectDto extends RepresentationModel<ProjectDto> implements Serializable {
    private Long id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Team team;
    private List<String> gitRepositories = new ArrayList<>();
    private Set<ProjectUser> projectUsers = new LinkedHashSet<>();
    private Set<Item> items = new LinkedHashSet<>();
    private Set<Sprint> sprints = new LinkedHashSet<>();

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProjectUserDto implements Serializable {
        private Long userId;
        private String userName;
        private String projectRole;
    }
}
