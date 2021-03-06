package ka.bookstorewebapp.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
@DiscriminatorValue("UnapprovedAuthor")
public class UnapprovedAuthor extends User {

    public UnapprovedAuthor() {
    }

    public String generateEmailAddress() {
        setEmailAddress(getFirstName() + "." + getLastName() + "@books4u.com");
        return getEmailAddress();
    }

}

