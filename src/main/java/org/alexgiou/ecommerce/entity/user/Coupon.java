package org.alexgiou.ecommerce.entity.user;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.*;
import org.alexgiou.ecommerce.entity.BaseEntity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: Alexandros Giounan
 * @code @created: 10/15/2024
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Coupon extends BaseEntity {

        private String code;
        private double discount;
        private LocalDate startDate;
        private LocalDate endDate;
        private double minOrderAmount;
        private boolean isActive = true;
        @ManyToMany(mappedBy = "coupons")
        private Set<User> users = new HashSet<>();
}
