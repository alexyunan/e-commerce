package org.alexgiou.ecommerce.entity.seller;

import jakarta.persistence.*;
import lombok.*;
import org.alexgiou.ecommerce.domain.AccountStatus;
import org.alexgiou.ecommerce.domain.ROLE;
import org.alexgiou.ecommerce.entity.Address;
import org.alexgiou.ecommerce.entity.BankDetails;
import org.alexgiou.ecommerce.entity.BaseEntity;
import org.alexgiou.ecommerce.entity.BusinessDetails;

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
public class Seller extends BaseEntity {

    private String sellerName;

    private String mobile;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @Embedded
    private BusinessDetails businessDetails = new BusinessDetails();

    @Embedded
    private BankDetails bankDetails = new BankDetails();

    @OneToOne(cascade = CascadeType.ALL)
    private Address pickupAddress = new Address();

    private String gstin;

    private ROLE role = ROLE.SELLER;

    private boolean isEmailVerified = false;

    private AccountStatus accountStatus = AccountStatus.PENDING;

}
