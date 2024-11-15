package org.alexgiou.ecommerce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.alexgiou.ecommerce.entity.user.User;

/**
 * @author: Alexandros Giounan
 * @code @created: 10/15/2024
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Address extends BaseEntity{

    private String streetName;
    private String locality;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String mobileNumber;
    @ManyToOne
    private User user;
}
