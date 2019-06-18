package fudan.se.lab4.service.impl;

import fudan.se.lab4.dto.Order;
import fudan.se.lab4.service.Discount;
import scala.Tuple2;

import java.text.MessageFormat;

import static fudan.se.lab4.Util.InitUtil.InfoLanguage;


public class Discount4 implements Discount {
    @Override
    public Tuple2<Double, String> calculate(Order order) {
        double discount = 0.0;
        String msg = "";
        double price = order.getPrice();
        int discountNum = (int)(price / 100);
        discount = discountNum * 30;
        if(price > 100){
            msg = MessageFormat.format(InfoLanguage.getString("FULL_DISCOUNT"),price, discount);
        }
        return Tuple2.apply(discount, msg);
    }
}
