package dev.brijesh.paymentservice.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

import dev.brijesh.paymentservice.dtos.PaymentRequestDTO;
import dev.brijesh.paymentservice.services.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {


    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/")
    public String makePayment(@RequestBody PaymentRequestDTO paymentRequestDTO) 
                        throws StripeException, RazorpayException {
        return paymentService.initiatePayment(paymentRequestDTO.getOrderId()
        ,paymentRequestDTO.getAmount()
        ,paymentRequestDTO.getPhoneNumber()
        ,paymentRequestDTO.getEmail());
    }
}
