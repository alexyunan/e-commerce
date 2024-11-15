package org.alexgiou.ecommerce.entity;


import lombok.Data;

/**
 * @author: Alexandros Giounan
 * @code @created: 10/18/2024
 */

@Data
public class BankDetails {

    private String accountNumber;
    private String accountHolderName;
    private String ifscCode;
    private String bankName;
}
