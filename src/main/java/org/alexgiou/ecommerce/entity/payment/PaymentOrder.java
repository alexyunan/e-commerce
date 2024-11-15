package org.alexgiou.ecommerce.entity.payment;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.alexgiou.ecommerce.domain.PaymentMethod;
import org.alexgiou.ecommerce.domain.PaymentOrderStatus;
import org.alexgiou.ecommerce.entity.BaseEntity;
import org.alexgiou.ecommerce.entity.user.User;
import org.alexgiou.ecommerce.entity.order.Order;

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
public class PaymentOrder extends BaseEntity {

    private Long amount;

    private PaymentOrderStatus status = PaymentOrderStatus.PENDING;

    private PaymentMethod paymentMethod;

    private String paymentLinkId;

    @ManyToOne
    private User user;

    @OneToMany
    private Set<Order> orders = new HashSet<>();
}
