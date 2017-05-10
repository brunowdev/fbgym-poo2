package edu.satc.ec.fbgym.model.commons.persons;

import edu.satc.ec.fbgym.model.addresses.Address;
import edu.satc.ec.fbgym.model.contacts.MailContact;
import edu.satc.ec.fbgym.model.contacts.PhoneContact;

import java.time.LocalDate;
import java.util.Set;

/**
 * Created by BRUNO-PC on 09/05/2017.
 */
public abstract class Person {

    private Long id;

    private String firstName;

    private String lastName;

    private String cpf;

    private String rg;

    private Address address;

    private Set<PhoneContact> phoneContacts;

    private Set<MailContact> mailContacts;

    private LocalDate bornDate;

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCpf() {
        return cpf;
    }

    public String getRg() {
        return rg;
    }

    public Address getAddress() {
        return address;
    }

    public Set<PhoneContact> getPhoneContacts() {
        return phoneContacts;
    }

    public Set<MailContact> getMailContacts() {
        return mailContacts;
    }

    protected void setId(Long id) {
        this.id = id;
    }

    protected void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public LocalDate getBornDate() {
        return bornDate;
    }

    protected void setBornDate(LocalDate bornDate) {
        this.bornDate = bornDate;
    }

    protected void setLastName(String lastName) {
        this.lastName = lastName;
    }

    protected void setCpf(String cpf) {
        this.cpf = cpf;
    }

    protected void setRg(String rg) {
        this.rg = rg;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    protected void setPhoneContacts(Set<PhoneContact> phoneContacts) {
        this.phoneContacts = phoneContacts;
    }

    protected void setMailContacts(Set<MailContact> mailContacts) {
        this.mailContacts = mailContacts;
    }
}
