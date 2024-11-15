package org.alexgiou.ecommerce.entity.payment;

import lombok.Data;
import org.alexgiou.ecommerce.domain.PaymentStatus;

/**
 * @author: Alexandros Giounan
 * @code @created: 10/18/2024
 */
@Data
public class PaymentDetails {

    private String paymentId;
    private String razorpayPaymentId;
    private String razorpayPaymentLinkReferenceId;
    private String razorpayPaymentLinkStatus;;
    private String razorpayPaymentIdZWSP;
    private PaymentStatus   paymentStatus;
}
