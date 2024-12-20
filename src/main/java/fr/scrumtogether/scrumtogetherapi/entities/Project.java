package fr.scrumtogether.scrumtogetherapi.entities;

import fr.scrumtogether.scrumtogetherapi.utils.jpa.JsonListConverter;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Convert(converter = JsonListConverter.class)
    @Column(name = "git_repositories", columnDefinition = "JSON NOT NULL")
    private List<String> gitRepositories = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToMany(mappedBy = "project", orphanRemoval = true)
    private Set<ProjectUser> projectUsers = new LinkedHashSet<>();

    @OneToMany(mappedBy = "project", orphanRemoval = true)
    private Set<Item> items = new LinkedHashSet<>();

    @OneToMany(mappedBy = "project", orphanRemoval = true)
    private Set<Sprint> sprints = new LinkedHashSet<>();

}