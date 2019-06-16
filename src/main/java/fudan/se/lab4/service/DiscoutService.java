package fudan.se.lab4.service;

import fudan.se.lab4.dto.OrderItemInfos;
import fudan.se.lab4.dto.PaymentInfo;

public interface DiscoutService {

    double getDiscount(OrderItemInfos orderItemInfos, PaymentInfo paymentInfo);
}
