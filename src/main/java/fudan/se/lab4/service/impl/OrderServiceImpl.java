package fudan.se.lab4.service.impl;

import fudan.se.lab4.Util.InitUtil;
import fudan.se.lab4.constant.InfoConstant;
import fudan.se.lab4.dto.Order;
import fudan.se.lab4.dto.PaymentInfo;
import fudan.se.lab4.service.Discount;
import fudan.se.lab4.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import scala.Tuple2;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static fudan.se.lab4.Util.InitUtil.InfoLanguage;


@Service
public class OrderServiceImpl implements OrderService {
    private Logger logger = null;
    private static Logger logger2 = LoggerFactory.getLogger(OrderService.class);
    @Override
    public PaymentInfo pay(Order order) {
        logger = InitUtil.orderInfoLogger;
        if(order==null||order.getOrderItems()==null||order.getOrderItems().size()==0){
            logger.info(InfoConstant.NULL_ORDER);
            logger2.info(InfoLanguage.getString("NULL_ORDER"));
        }
        List<String> msg = new ArrayList<>();
        Discount discount1 = new Discount1();
        Discount discount2= new Discount2();
        Discount discount3 = new Discount3();
        Discount discount4 = new Discount4();
        Discount discount5 = new Discount5();
        Discount discount6 = new Discount6();
        Tuple2<Double, String> res1 = discount1.calculate(order);
        Tuple2<Double, String> res2 = discount2.calculate(order);
        Tuple2<Double, String> res3 = discount3.calculate(order);
        Tuple2<Double, String> res4 = discount4.calculate(order);
        Tuple2<Double, String> res5 = discount5.calculate(order);
        Tuple2<Double, String> res6 = discount6.calculate(order);
        double totalDiscount1 = 0.0;
        //using discount strategy 1 2 3 6
        totalDiscount1 += res1._1;
        totalDiscount1 += res2._1;
        totalDiscount1 += res3._1;
        totalDiscount1 += res6._1;

        //using discount strategy 4
        double totalDiscount2 = 0.0;
        totalDiscount2 += res4._1;

        //using discount strategy 5
        double totalDiscount3 = 0.0;
        totalDiscount3 += res5._1;

        //get the final discount
        double finalDiscount = Math.max(Math.max(totalDiscount1, totalDiscount2), totalDiscount3);
        if(finalDiscount == 0)
            msg.add(InfoLanguage.getString("NO_DISCOUNT"));
        else{
            if(finalDiscount == totalDiscount1){
                msg.add(res1._2);
                msg.add(res2._2);
                msg.add(res3._2);
                msg.add(res6._2);
            }
            else if(finalDiscount == totalDiscount2){
                msg.add(res4._2);
            }
            else {
                msg.add(res5._2);
            }
        }
        PaymentInfo paymentInfo = new PaymentInfo();

        double price = order.getPrice();

        double discountPrice = price - finalDiscount;
        logger.info(InfoConstant.GET_DISCOUNT_SUCCESSFUL);

        paymentInfo.setPrice(price);
        paymentInfo.setDiscountPrice(discountPrice);
        paymentInfo.setDiscount(finalDiscount);
        msg.removeAll(Arrays.asList("", null));
        paymentInfo.setMsgs(msg);
        for(String m : msg){
            logger.info(m);
            System.out.println(m);
        }
        //FBInteraction.PrintBill(items, paymentInfo);
        logger.info(order.showOrder());
        paymentInfo.showInfo();
        return paymentInfo;
    }


}

