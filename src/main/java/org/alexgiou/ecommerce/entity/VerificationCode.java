package org.alexgiou.ecommerce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.alexgiou.ecommerce.entity.seller.Seller;
import org.alexgiou.ecommerce.entity.user.User;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author: Alexandros Giounan
 * @code @created: 10/18/2024
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VerificationCode extends BaseEntity {

    private String code;

    private String email;

    @OneToOne
    private User user;

    @OneToOne
    private Seller seller;

    public VerificationCode(Seller seller, User user) {
        this.seller = seller;
        this.user = user;
        this.code = RandomStringUtils.random(6, false, true);
    }
}
