package ka.bookstorewebapp.daos;

import ka.bookstorewebapp.entities.User;
import ka.bookstorewebapp.exceptions.RecordAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

import static ka.bookstorewebapp.utils.Logging.warn;

public class UserDaoImpl implements UserDAO {

    @Autowired
    private EntityManagerFactory factory;

    public UserDaoImpl(EntityManagerFactory factory) {
        super();
        this.factory = factory;
    }

    public UserDaoImpl() {
    }

    public void addUser(User newUser) throws RecordAlreadyExistsException {
        EntityManager manager = factory.createEntityManager();

        if (getUser(newUser.getEmailAddress()) == null) {

            manager.getTransaction().begin();
            manager.persist(newUser);
            manager.getTransaction().commit();
        } else {
            warn("User with email address '" + newUser.getEmailAddress() + "' already exists in database", getClass());
            throw new RecordAlreadyExistsException("User with email address '" + newUser.getEmailAddress() + "' already exists in database");
        }
    }

    public void updateUser(User user) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.merge(user);
        manager.getTransaction().commit();
    }

    public User getUser(String emailAddress) {
        EntityManager manager = factory.createEntityManager();
        User user = manager.find(User.class, emailAddress);

        return user;
    }

    public void removeUser(String emailAddress) {

        EntityManager manager = factory.createEntityManager();

        User user = manager.find(User.class, emailAddress);
        manager.getTransaction().begin();

        manager.remove(user);
        manager.getTransaction().commit();
    }

    public List<User> getAllUsers() {
        EntityManager manager = factory.createEntityManager();

        TypedQuery<User> query = manager.createQuery("select u from User u", User.class);
        List<User> listOfAllUsers = query.getResultList();

        return listOfAllUsers;
    }

}
