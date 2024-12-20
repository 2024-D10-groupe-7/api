package fr.scrumtogether.scrumtogetherapi.repositories;

import fr.scrumtogether.scrumtogetherapi.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}