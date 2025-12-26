package dev.brijesh.paymentservice.services;

import org.springframework.stereotype.Service;

import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

import dev.brijesh.paymentservice.paymentgateway.PaymentGateway;

@Service
public class PaymentService {
    private PaymentGateway paymentGateway;
    
    public PaymentService(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public String initiatePayment(String orderId, Long amount, String phoneNumber, String email) throws StripeException, RazorpayException {
       //TODO: Get Order details

       // Generate payment link using the payment gateway
        return paymentGateway.generatePaymentLink(orderId, amount, phoneNumber, email);
    }
}
