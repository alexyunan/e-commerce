package org.alexgiou.ecommerce.repository;

import org.alexgiou.ecommerce.entity.cart.Cart;
import org.alexgiou.ecommerce.entity.user.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author: Alexandros Giounan
 * @code @created: 10/30/2024
 */

public interface CartRepository extends CrudRepository<Cart, Long> {
    Cart findByUser(User user);
}
