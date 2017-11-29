package de.hda.fbi.ds.mbredel.store;

import de.hda.fbi.ds.mbredel.model.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.PersistenceException;
import java.util.Collection;

/**
 * Interface that must be implemented by the
 * store module that actually stores the user
 * to a persistent storage, such as disk.
 *
 * @author  Michael Bredel
 */
public interface StoreService {
    /**
     * Extracts the uploaded file form the HTTP
     * request and stores it using the filename.
     *
     * @param user The user object to store.
     * @return The username as the primary key of the user object.
     * @throws PersistenceException When the file cannot be stored.
     * @throws IllegalArgumentException When at least one of the arguments is null.
     */
    @Nonnull
    String create(User user) throws PersistenceException, IllegalArgumentException;

    /**
     * Read and return a file by its file name.
     *
     * @param username The username that acts as primary key.
     * @return The user.
     * @throws IllegalArgumentException When the arguments is null.
     */
    @Nullable
    User read(String username) throws IllegalArgumentException;

    /**
     * Read and return all users stored in
     * the image store.
     *
     * @return A list of all users in the store. The list might be empty.
     */
    @Nonnull
    Collection<User> readAll();

    /**
     * Delete the file by its file name.
     *
     * @param username The username that acts as primary key.
     * @return The name of the file that has been deleted successfully.
     * @throws PersistenceException When the file cannot be deleted.
     * @throws IllegalArgumentException When the arguments is null.
     */
    @Nonnull
    User delete(String username) throws PersistenceException, IllegalArgumentException;
}
