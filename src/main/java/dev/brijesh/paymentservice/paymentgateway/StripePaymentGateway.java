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

    @Value("${stripe.key.secret}")
    private String stripeKey;

    @Override
    public String generatePaymentLink(String orderId, Long amount, String phoneNumber, String email) throws StripeException {
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
