package org.alexgiou.ecommerce.entity.home;

import jakarta.persistence.Entity;
import lombok.*;
import org.alexgiou.ecommerce.domain.HomeCategorySection;
import org.alexgiou.ecommerce.entity.BaseEntity;

/**
 * @author: Alexandros Giounan
 * @code @created: 10/21/2024
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class HomeCategory extends BaseEntity {

    private String name;

    private String image;

    private String categoryId;

    private HomeCategorySection section;

}
