package dev.brijesh.paymentservice.paymentgateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
// import com.stripe.StripeClient;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;

@Service
@Primary
public class StripePaymentGateway implements PaymentGateway {

    @Value("${STRIPE_SECRET_KEY}")
    private String stripeKey;

    // private final StripeClient stripeClient;

    // public StripePaymentGateway(StripeClient stripeClient) {
    //     this.stripeClient = stripeClient;
    // }
    @Override
    public String generatePaymentLink(String orderId, Long amount, String phoneNumber, String email) throws StripeException {
        // Logic to generate payment link using Stripe API
        Stripe.apiKey = stripeKey;

        PaymentLinkCreateParams params =
        PaymentLinkCreateParams.builder()
            .addLineItem(
            PaymentLinkCreateParams.LineItem.builder()
                .setPrice(Price.create(
                    PriceCreateParams.builder()
                        .setCurrency("INR")
                        .setUnitAmount(amount)
                        .setProductData(
                            PriceCreateParams.ProductData.builder()
                                .setName("Order " + orderId)
                                .build()
                        )
                        .build()
                ).getId())
                .setQuantity(1L)
                .build()
            )
            .build();

        PaymentLink paymentLink = PaymentLink.create(params);
        return paymentLink.getUrl();
    }
    
}
