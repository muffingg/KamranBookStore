package ka.bookstorewebapp.daos;

import ka.bookstorewebapp.entities.UnapprovedAuthor;

import java.util.List;

public interface UnapprovedAuthorDAO {

    public void addUnapprovedAuthor(UnapprovedAuthor user);

    public UnapprovedAuthor getUnapprovedAuthor(String emailAddress);

    public void removeUnapprovedAuthor(String emailAddress);

    public List<UnapprovedAuthor> getAllUnapprovedAuthors();

}
