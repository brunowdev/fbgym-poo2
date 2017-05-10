package edu.satc.ec.fbgym.model.commons.persons;

import edu.satc.ec.fbgym.model.addresses.Address;
import edu.satc.ec.fbgym.model.contacts.MailContact;
import edu.satc.ec.fbgym.model.contacts.PhoneContact;

import java.util.Set;

/**
 * Created by BRUNO-PC on 09/05/2017.
 */
public abstract class Person {

    protected Long id;

    protected String firstName;

    protected String lastName;

    protected String cpf;

    protected String rg;

    protected Address address;

    protected Set<PhoneContact> phoneContacts;

    protected Set<MailContact> mailContacts;

}
