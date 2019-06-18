package fudan.se.lab4.service.impl;

import fudan.se.lab4.Util.GenOrderId;
import fudan.se.lab4.Util.InitUtil;
import fudan.se.lab4.constant.InfoConstant;
import fudan.se.lab4.dto.Ingredient;
import fudan.se.lab4.dto.Order;
import fudan.se.lab4.dto.OrderItem;
import fudan.se.lab4.dto.PaymentInfo;
import org.junit.Assert;
import org.junit.Test;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;


import static fudan.se.lab4.Util.InitUtil.InfoLanguage;
import static net.sf.ezmorph.test.ArrayAssertions.assertEquals;
import static org.assertj.core.api.AssertionsForClassTypes.fail;

public class OrderServiceImplTest {
    OrderServiceImpl orderService = new OrderServiceImpl();
    OrderItem orderItem1 = new OrderItem(101,null,3);
    OrderItem orderItem2 = new OrderItem(102,null,1);
    OrderItem orderItem3 = new OrderItem(202,null,1);
    OrderItem orderItem4 = new OrderItem(201,null,1);
    OrderItem orderItem5 = new OrderItem(301,null,1);
    OrderItem orderItem6 = new OrderItem(302,null,1);
    Ingredient ingredient1 = new Ingredient(1,1);
    Ingredient ingredient2 = new Ingredient(2,1);
    Ingredient ingredient3 = new Ingredient(3,1);
    Ingredient ingredient4 = new Ingredient(4,1);


    @Test
    public void testPay() {
        InitUtil.init();
        List<OrderItem> orderItems = new ArrayList<>();
        List<Ingredient> ingredients = new ArrayList<>();

/*********************not double11 nor midmonth***************************************************/

        //test null order
//        Order order0;
//        order0 = new Order();
//        try {
//            orderService.pay(order0);
//            fail("no exception throw");
//        } catch (RuntimeException e) {
//            assertEquals(InfoConstant.NULL_ORDER, e.getMessage());
//        }
//
//        try {
//            orderService.pay(null);
//            fail("no exception throw");
//        } catch (RuntimeException e) {
//            assertEquals(InfoConstant.NULL_ORDER, e.getMessage());
//        }

        //test no discount
        orderItems.clear();


        orderItems.add(orderItem3);
        orderItems.add(orderItem5);


        Order order9 = new Order(GenOrderId.getId(),new ArrayList<>(orderItems));
        PaymentInfo info9 = orderService.pay(order9);
        assertEquals(34,info9.getPrice(),0.01);
        assertEquals(0,info9.getDiscount(),0.01);
        assertEquals(34,info9.getDiscountPrice(),0.01);


        //test discount5
        orderItems.clear();

        ingredients.clear();
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);

        orderItem1.setIngredients(new ArrayList<>(ingredients));
        orderItems.add(new OrderItem(orderItem1));

        ingredients.clear();
        ingredients.add(ingredient3);
        orderItem2.setIngredients(new ArrayList<>(ingredients));
        orderItems.add(new OrderItem(orderItem2));

        orderItems.add(orderItem3);
        orderItems.add(orderItem5);


        Order order1 = new Order(GenOrderId.getId(),new ArrayList<>(orderItems));
        PaymentInfo info1 = orderService.pay(order1);
        assertEquals(87.4,info1.getPrice(),0.01);
        assertEquals(13.11,info1.getDiscount(),0.01);
        assertEquals(74.29,info1.getDiscountPrice(),0.01);


        //test discount of notfussy
        orderItems.clear();
        ingredients.clear();

        orderItem2.setIngredients(new ArrayList<>());
        orderItems.add(new OrderItem(orderItem2));
        orderItem4.setIngredients(new ArrayList<>());
        orderItems.add(new OrderItem(orderItem4));

        Order order3 = new Order(GenOrderId.getId(),new ArrayList<>(orderItems));
        PaymentInfo info3 = orderService.pay(order3);
        Assert.assertEquals(6.6,info3.getDiscount(),0.01);



/******************************************double11***************************************************/
/**     passable only when the date is 11.11

        orderItems.clear();

        ingredients.clear();
        ingredients.add(ingredient3);
        ingredients.add(ingredient4);

        orderItem1.setIngredients(new ArrayList<>(ingredients));

        orderItems.add(new OrderItem(orderItem1));
        orderItems.add(new OrderItem(orderItem1));

        Order order2 = new Order(1,new ArrayList<>(orderItems));
        PaymentInfo info2 = orderService.pay(order2);
        Assert.assertEquals(28,info2.getDiscount(),0.01);
 **/
    }


}