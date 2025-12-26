package dev.brijesh.paymentservice.paymentgateway;

import org.springframework.stereotype.Service;

@Service
public class RazorPaymentGateway implements PaymentGateway {
    @Override
    public String generatePaymentLink(String orderId, Long amount, String phoneNumber, String email) {
        // Logic to integrate with Razorpay API and generate payment link
        return "https://razorpay.com/pay/" + orderId;
    }
    
}
