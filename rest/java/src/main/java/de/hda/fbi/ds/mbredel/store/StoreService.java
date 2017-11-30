/*
 Copyright (c) 2017, Michael Bredel, H-DA
 ALL RIGHTS RESERVED.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.

 Neither the name of the H-DA and Michael Bredel
 nor the names of its contributors may be used to endorse or promote
 products derived from this software without specific prior written
 permission.
 */
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
