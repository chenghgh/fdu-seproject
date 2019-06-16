package fudan.se.lab4.service.impl;

import fudan.se.lab4.constant.InfoConstant;
import fudan.se.lab4.dto.Order;
import fudan.se.lab4.dto.OrderItemInfos;
import fudan.se.lab4.dto.PaymentInfo;
import fudan.se.lab4.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private final static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    @Override
    public PaymentInfo pay(Order order) {
        if(order==null||order.getOrderItems()==null||order.getOrderItems().size()==0){
            logger.info(InfoConstant.NULL_ORDER);
            throw new RuntimeException(InfoConstant.NULL_ORDER);
        }
        PaymentInfo paymentInfo = new PaymentInfo();
        OrderItemInfos items = new OrderItemInfos(order);

        double price = getPrice(items);
        double discount = getDiscount(items, paymentInfo);
        double discountPrice = price - discount;
        logger.info(InfoConstant.GET_DISCOUNT_SUCCESSFUL);

        paymentInfo.setPrice(price);
        paymentInfo.setDiscountPrice(discountPrice);

        //FBInteraction.PrintBill(items, paymentInfo);

        return paymentInfo;
    }


    //get the total price
    private double getPrice(OrderItemInfos items){

        return items.getPrice();
    }

    //get the discount
    private double getDiscount(OrderItemInfos orderItemInfos,PaymentInfo info){

        DiscountServerImpl discountServer = new DiscountServerImpl();

        return discountServer.getDiscount(orderItemInfos, info);
    }


}

