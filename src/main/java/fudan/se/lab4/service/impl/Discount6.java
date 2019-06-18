package fudan.se.lab4.service.impl;

import fudan.se.lab4.dto.Order;
import fudan.se.lab4.dto.OrderItem;
import fudan.se.lab4.service.Discount;
import scala.Tuple2;

import java.text.MessageFormat;

import static fudan.se.lab4.Util.InitUtil.InfoLanguage;


public class Discount6 implements Discount {
    @Override
    public Tuple2<Double, String> calculate(Order order) {
        double price = order.getPrice();
        double discount = 0.0;
        String msg = "";
        int teaCount = 0;
        int cofCount = 0;
        for (OrderItem it : order.getOrderItems()) {
            if (it.getID() == 101 || it.getID() == 102)
                cofCount++;
            if (it.getID() == 201 || it.getID() == 202)
                teaCount++;
        }
        if(cofCount >=1 && teaCount >= 1){
            discount = price * 0.15;
            msg = MessageFormat.format(InfoLanguage.getString("DISCOUNT_OF_NOTFUSSY"),discount);
        }
        return Tuple2.apply(discount, msg);
    }
}
