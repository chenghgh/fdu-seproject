package fudan.se.lab4.service.impl;

import fudan.se.lab4.dto.Order;
import fudan.se.lab4.dto.OrderItem;
import fudan.se.lab4.service.Discount;
import scala.Tuple2;

import java.text.MessageFormat;
import java.util.List;
import static fudan.se.lab4.Lab4Application.InfoLanguage;
public class Discount2 implements Discount {
    @Override
    public Tuple2<Double, String> calculate(Order order) {
        String msg = "";
        double discount = 0.0;
        int teaCount = 0;
        int blackTeaCount = 0;
        List<OrderItem> orderItems =  order.getOrderItems();
        for(OrderItem it : orderItems){
            int id = it.getID();
            if(id == 201){
                teaCount++;
                blackTeaCount++;
            }
            if(id == 202)
                teaCount++;
        }
        int discountNum = teaCount / 4;
        if(discountNum > blackTeaCount)
            discount = blackTeaCount * 18.0 + (discountNum - blackTeaCount) * 16.0;
        else
            discount = discountNum * 18.0;
        if(discount != 0)
            msg = MessageFormat.format(InfoLanguage.getString("DISCOUNT_OF_TEA"), teaCount, discountNum);
        Tuple2.apply(discount, msg);
        return Tuple2.apply(discount, msg);
    }
}
