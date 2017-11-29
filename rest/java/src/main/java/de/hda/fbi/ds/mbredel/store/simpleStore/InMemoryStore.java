package de.hda.fbi.ds.mbredel.store.simpleStore;

import de.hda.fbi.ds.mbredel.model.User;
import de.hda.fbi.ds.mbredel.store.StoreService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.PersistenceException;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The in-memory store stores users
 * in a hash-map.
 *
 * @author Michael Bredel
 */
public class InMemoryStore implements StoreService {

    /** The in-memory user store. */
    private ConcurrentHashMap<String, User> store;

    /**
     * Default constructor used by Guice.
     */
    public InMemoryStore() {
        this.store = new ConcurrentHashMap<String, User>();
    }

    @Nonnull
    @Override
    public String create(User user) throws PersistenceException, IllegalArgumentException {
        this.store.put(user.username, user);
        return user.username;
    }

    @Nullable
    @Override
    public User read(String username) throws IllegalArgumentException {
        return this.store.get(username);
    }

    @Nonnull
    @Override
    public Collection<User> readAll() {
        return this.store.values();
    }

    @Nonnull
    @Override
    public User delete(String username) throws PersistenceException, IllegalArgumentException {
        if (username == null) {
            throw new IllegalArgumentException();
        }

        User user = this.store.remove(username);

        if (user != null) {
            return user;
        } else {
            throw new PersistenceException("User does not exist.");
        }
    }
}
