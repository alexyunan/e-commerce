package org.alexgiou.ecommerce.entity.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.alexgiou.ecommerce.entity.BaseEntity;
import org.alexgiou.ecommerce.entity.product.Product;

import java.math.BigDecimal;

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
public class OrderItem extends BaseEntity {

    @JsonIgnore
    @ManyToOne
    private Order order;

    @ManyToOne
    private Product product;

    private String size;

    private int quantity;

    private BigDecimal mrpPrice;

    private BigDecimal sellingPrice;

    private Long userId;
}
