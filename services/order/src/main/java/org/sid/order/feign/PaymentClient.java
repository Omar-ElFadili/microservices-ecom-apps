package org.sid.order.feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.sid.order.feign.requests.PaymentPaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "payment-service",
        url = "${application.config.payment-url}"
)
public interface PaymentClient {

    @PostMapping
    @CircuitBreaker(name = "paymentServiceCB", fallbackMethod = "getDefaultOrderPayment")
    Integer requestOrderPayment(PaymentPaymentRequest paymentPaymentRequest);

    default Integer getDefaultOrderPayment() {
        return 1;
    }
}
