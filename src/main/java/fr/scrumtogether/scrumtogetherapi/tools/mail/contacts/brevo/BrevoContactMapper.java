package fr.scrumtogether.scrumtogetherapi.tools.mail.contacts.brevo;

import brevoModel.CreateContact;
import brevoModel.CreateUpdateContactModel;
import fr.scrumtogether.scrumtogetherapi.entities.User;
import fr.scrumtogether.scrumtogetherapi.tools.mail.contacts.Contact;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
public class BrevoContactMapper {
    public CreateContact toBrevoContact(User user) {
        CreateContact createContact = new CreateContact();

        createContact.setEmail(user.getEmail());
        createContact.setExtId(user.getId().toString());
        Properties attributes = new Properties();
        attributes.setProperty("PRÉNOM", user.getFirstName());
        attributes.setProperty("NOM", user.getLastName());
        createContact.setAttributes(attributes);
        List<Long> listIds = new ArrayList<>();
        listIds.add(user.getId());
        createContact.setListIds(listIds);
        createContact.setEmailBlacklisted(false);
        createContact.setSmsBlacklisted(false);
        createContact.setUpdateEnabled(false);
        List<String> smtpBlacklistedSender = new ArrayList<>();
        createContact.setListIds(listIds);
        createContact.setSmtpBlacklistSender(smtpBlacklistedSender);

        return createContact;
    }

    public Contact toContact(CreateUpdateContactModel model){
        return Contact.builder()
                .externalId(model.getId().toString())
                .build();
    }
}
