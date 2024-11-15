package org.alexgiou.ecommerce.entity.seller;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.alexgiou.ecommerce.entity.BaseEntity;

/**
 * @author: Alexandros Giounan
 * @code @created: 10/18/2024
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SellerReport extends BaseEntity {

    @OneToOne
    private Seller seller;

    private Long totalEarnings = 0L;

    private Long totalSales = 0L;

    private  Long totalRefunds = 0L;

    private Long totalTax = 0L;

    private Long netEarnings = 0L;

    private Integer totalOrders = 0;

    private Integer cancelledOrders = 0;

    private Integer totalTransactions = 0;
}
