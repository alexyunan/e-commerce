package org.alexgiou.ecommerce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.*;
import org.alexgiou.ecommerce.entity.order.Order;
import org.alexgiou.ecommerce.entity.seller.Seller;
import org.alexgiou.ecommerce.entity.user.User;

import java.time.LocalDateTime;

/**
    * @author:  Alexandros Giounan
    * @code @created: 10/18/2024
*/
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
 public class Transaction  extends BaseEntity{

    @ManyToOne
    private User user;

    @OneToOne
    private Order order;

    @ManyToOne
    private Seller seller;

    private LocalDateTime transactionDate = LocalDateTime.now();
}
