package fr.scrumtogether.scrumtogetherapi.tools.mail.contacts.brevo;

import brevoApi.ContactsApi;
import brevoModel.AddContactToList;
import brevoModel.CreateContact;
import brevoModel.CreateUpdateContactModel;
import fr.scrumtogether.scrumtogetherapi.config.BrevoConfig;
import fr.scrumtogether.scrumtogetherapi.entities.User;
import fr.scrumtogether.scrumtogetherapi.tools.mail.contacts.Contact;
import fr.scrumtogether.scrumtogetherapi.tools.mail.contacts.ContactListException;
import fr.scrumtogether.scrumtogetherapi.tools.mail.contacts.STContacts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BrevoContacts implements STContacts {
    private final BrevoContactMapper mapper;
    private final ContactsApi brevoContactsApi;
    private final BrevoConfig brevoConfig;


    @Override
    public Contact createContact(User user) throws ContactListException {
        try {
            CreateContact createContact = mapper.toBrevoContact(user);
            CreateUpdateContactModel contact = brevoContactsApi.createContact(createContact);
            return mapper.toContact(contact);
        } catch (brevo.ApiException e) {
            throw new ContactListException("Error while creating the contact on Brevo", e);
        }
    }

    @Override
    public void addContactToList(Long listId, Contact contact) throws ContactListException {
        try {
            AddContactToList addContactToList = new AddContactToList();
            addContactToList.ids(List.of(Long.valueOf(contact.getExternalId())));
            System.out.println(addContactToList.toString());
            brevoContactsApi.addContactToList(listId, addContactToList);
        } catch (brevo.ApiException e) {
            throw new ContactListException("Error while adding the contact to the list on Brevo", e);
        }
    }
}
