package fudan.se.lab4.service.impl;

import fudan.se.lab4.dto.Order;
import fudan.se.lab4.dto.OrderItem;
import fudan.se.lab4.service.Discount;
import scala.Tuple2;

import java.text.MessageFormat;
import java.util.List;

import static fudan.se.lab4.Util.InitUtil.InfoLanguage;


public class Discount1 implements Discount {
    @Override
    public Tuple2<Double, String> calculate(Order order) {
        String msg = "";
        double discount = 0.0;
        int espCount = 0;
        List<OrderItem> orderItems =  order.getOrderItems();
        for(OrderItem it : orderItems){
            int id = it.getID();
            int size = it.getSize();
            if(id == 101 && size == 3)
                espCount++;
        }
        int discountNum = espCount / 2;
        discount = discountNum * 0.2 * 40;
        if(espCount >= 2)
            msg = MessageFormat.format(InfoLanguage.getString("DISCOUNT_OF_ESPRESSO"), espCount);
        Tuple2.apply(discount, msg);
        return Tuple2.apply(discount, msg);
    }
}
