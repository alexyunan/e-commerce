package org.alexgiou.ecommerce.repository;

import org.alexgiou.ecommerce.entity.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author: Alexandros Giounan
 * @code @created: 10/23/2024
 */

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User>findByEmail(String email);

    boolean existsByEmail(String email);

}
