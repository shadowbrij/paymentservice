package dev.brijesh.paymentservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequestDTO {
    private String orderId;
    private Long amount;
    private String phoneNumber;
    private String email;
}
