package org.alexgiou.ecommerce.entity.product;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.*;
import org.alexgiou.ecommerce.entity.BaseEntity;
import org.alexgiou.ecommerce.entity.user.User;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: Alexandros Giounan
 * @code @created: 10/18/2024
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Wishlist extends BaseEntity {

    @OneToOne
    private User user;

    @ManyToMany
    private Set<Product> products = new HashSet<>();
}
