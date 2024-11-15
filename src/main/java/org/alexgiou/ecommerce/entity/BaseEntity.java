package org.alexgiou.ecommerce.entity;


import jakarta.persistence.GeneratedValue;

import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * @author: Alexandros Giounan
 * @code @created: 10/15/2024
 */
@Getter
@MappedSuperclass
public class BaseEntity {

    @jakarta.persistence.Id
    @GeneratedValue
    private Long Id;
}
