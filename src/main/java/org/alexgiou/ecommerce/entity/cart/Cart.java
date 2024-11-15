package org.alexgiou.ecommerce.entity.cart;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;
import org.alexgiou.ecommerce.entity.BaseEntity;
import org.alexgiou.ecommerce.entity.user.User;

import java.math.BigDecimal;
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
public class Cart extends BaseEntity {

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CartItem> cartItemSet = new HashSet<>();

    private BigDecimal totalPrice;

    private int totalItems;

    private int totalMrpPrice;

    private int discount;

    private String couponCode;
}
