package fr.scrumtogether.scrumtogetherapi.tools.mail.contacts;

import fr.scrumtogether.scrumtogetherapi.entities.User;

public interface STContacts {
    Contact createContact(User user) throws ContactListException;
    void addContactToList(Long listId, Contact contact) throws ContactListException;
}
