package org.alexgiou.ecommerce.entity.product;

import jakarta.persistence.*;
import lombok.*;
import org.alexgiou.ecommerce.entity.BaseEntity;
import org.alexgiou.ecommerce.entity.category.Category;
import org.alexgiou.ecommerce.entity.Review;
import org.alexgiou.ecommerce.entity.seller.Seller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Alexandros Giounan
 * @code @created: 10/18/2024
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntity {

    private String name;
    private String description;
    private BigDecimal mrpPrice;
    private BigDecimal sellingPrice;
    private int discountPercentage;
    private int quantity;
    private String color;
    @ElementCollection
    private List<String> images = new ArrayList<>();
    private int numRatings;
    @ManyToOne
    private Category category;
    @ManyToOne
    private Seller seller;
    private LocalDateTime createdAt;
    private String size;
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();
}
