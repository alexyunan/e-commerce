package org.alexgiou.ecommerce.entity.order;

import jakarta.persistence.*;
import lombok.*;
import org.alexgiou.ecommerce.domain.OrderStatus;
import org.alexgiou.ecommerce.domain.PaymentStatus;
import org.alexgiou.ecommerce.entity.*;
import org.alexgiou.ecommerce.entity.payment.PaymentDetails;
import org.alexgiou.ecommerce.entity.user.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
@Table(name = "orders")
public class Order extends BaseEntity {

    private String orderId;
    @ManyToOne
    private User user;

    private Long sellerId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderItem> orderItems = new HashSet<>();

    @ManyToOne
    private Address shippingAddress = new Address();

    @Embedded
    private PaymentDetails paymentDetails = new PaymentDetails();

    private BigDecimal totalMrpPrice;

    private BigDecimal totalSellingPrice;

    private int discount;

    private int totalItem;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.PENDING;

    @Enumerated(EnumType.STRING)
    @Column(insertable=false, updatable=false)
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;

    private LocalDateTime orderDate = LocalDateTime.now();

    private LocalDateTime deliveryDate = orderDate.plusDays(7);

}
