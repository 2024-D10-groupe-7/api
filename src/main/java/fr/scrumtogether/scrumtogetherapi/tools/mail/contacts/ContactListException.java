package fr.scrumtogether.scrumtogetherapi.tools.mail.contacts;

public class ContactListException extends RuntimeException {
    public ContactListException(String message) {
        super(message);
    }

    public ContactListException(String message, Throwable cause) {
        super(message, cause);
    }
}
