package com.morg4n.morg4n_users.persistence.repositories;

import com.morg4n.morg4n_users.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Julian David Camacho Erazo (Morg4n) {@literal <jdacamachoe@gmail.com>}
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
}
