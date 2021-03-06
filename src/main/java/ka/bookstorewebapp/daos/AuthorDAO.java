package ka.bookstorewebapp.daos;

import ka.bookstorewebapp.entities.Author;

import java.util.List;

public interface AuthorDAO {

    public void addAuthor(Author author);

    public Author getAuthor(String emailAddress);

    public void updateAuthor(Author author);

    public boolean removeAuthor(String emailAddress);

    public List<Author> getAllAuthors();

}
