package fudan.se.lab4.service.impl;

import fudan.se.lab4.dto.Order;
import fudan.se.lab4.dto.OrderItem;
import fudan.se.lab4.service.Discount;
import scala.Tuple2;

import java.text.MessageFormat;
import java.util.List;

import static fudan.se.lab4.Util.InitUtil.InfoLanguage;


public class Discount3 implements Discount {
    @Override
    public Tuple2<Double, String> calculate(Order order) {
        String msg = "";
        double discount = 0.0;
        int capCount = 0;
        List<OrderItem> orderItems =  order.getOrderItems();
        for(OrderItem it : orderItems){
            int id = it.getID();
            if(id == 102)
                capCount++;
        }
        int discountNum = capCount / 2;
        discount = discountNum * 22 * 0.5;
        if(capCount >= 2)
            msg = MessageFormat.format(InfoLanguage.getString("DISCOUNT_OF_CAPPUCCINO"), capCount);
        Tuple2.apply(discount, msg);
        return Tuple2.apply(discount, msg);
    }
}
