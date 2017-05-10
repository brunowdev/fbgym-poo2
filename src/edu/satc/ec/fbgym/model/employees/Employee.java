package edu.satc.ec.fbgym.model.employees;

import edu.satc.ec.fbgym.model.addresses.Address;
import edu.satc.ec.fbgym.model.commons.builders.entities.AbstractEntityBuilder;
import edu.satc.ec.fbgym.model.commons.persons.Person;
import edu.satc.ec.fbgym.model.contacts.MailContact;
import edu.satc.ec.fbgym.model.contacts.PhoneContact;
import edu.satc.ec.fbgym.model.users.User;

import java.time.LocalDate;
import java.util.Set;

/**
 * Created by BRUNO-PC on 09/05/2017.
 */
public class Employee extends Person {

    private Employee() {
    }

    private String registrationNumber;

    private User user;

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public User getUser() {
        return user;
    }

    private void setUser(User user) {
        this.user = user;
    }

    private void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public static class Builder extends AbstractEntityBuilder<Employee, Long> {

        public Builder() {
            super(new Employee());
        }

        public Builder firstName(String firstName) {
            this.entity.setFirstName(firstName);
            return this;
        }

        public Builder lastName(String lastName) {
            this.entity.setLastName(lastName);
            return this;
        }

        public Builder cpf(String cpf) {
            this.entity.setCpf(cpf);
            return this;
        }

        public Builder rg(String rg) {
            this.entity.setRg(rg);
            return this;
        }

        public Builder address(Address address) {
            this.entity.setAddress(address);
            return this;
        }

        public Builder phoneContacts(Set<PhoneContact> phoneContacts) {
            this.entity.setPhoneContacts(phoneContacts);
            return this;
        }

        public Builder mailContacts(Set<MailContact> mailContacts) {
            this.entity.setMailContacts(mailContacts);
            return this;
        }

        public Builder registrationNumber(String registrationNumber) {
            this.entity.setRegistrationNumber(registrationNumber);
            return this;
        }

        public Builder user(User user) {
            this.entity.setUser(user);
            return this;
        }

        public Builder user(LocalDate bornDate) {
            this.entity.setBornDate(bornDate);
            return this;
        }

    }

}
