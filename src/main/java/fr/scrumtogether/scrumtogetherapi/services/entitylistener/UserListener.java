package fr.scrumtogether.scrumtogetherapi.services.entitylistener;

import fr.scrumtogether.scrumtogetherapi.entities.User;
import fr.scrumtogether.scrumtogetherapi.events.UserCreationEvent;
import jakarta.persistence.PostPersist;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserListener {
    private final ApplicationEventPublisher publisher;

    @PostPersist
    public void postPersist(User entity) {
        this.publisher.publishEvent(new UserCreationEvent(this, entity));
    }
}
