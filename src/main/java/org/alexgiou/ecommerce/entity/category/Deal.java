package org.alexgiou.ecommerce.entity.category;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;
import org.alexgiou.ecommerce.entity.BaseEntity;
import org.alexgiou.ecommerce.entity.home.HomeCategory;

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
public class Deal extends BaseEntity {

  private Integer discount;

  @OneToOne
  private HomeCategory homeCategory;
}
