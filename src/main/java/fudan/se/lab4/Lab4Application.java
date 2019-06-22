package fudan.se.lab4;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import fudan.se.lab4.Util.GenOrderId;
import fudan.se.lab4.Util.InitUtil;
import fudan.se.lab4.dto.Ingredient;
import fudan.se.lab4.dto.Order;
import fudan.se.lab4.dto.OrderItem;
import fudan.se.lab4.dto.PaymentInfo;
import fudan.se.lab4.entity.User;
import fudan.se.lab4.service.impl.AccountServiceImpl;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fudan.se.lab4.service.impl.OrderServiceImpl;


public class Lab4Application {
    public static void main(String[] args) throws IOException, GeoIp2Exception {
        InitUtil.init();
        AccountServiceImpl accountService = new AccountServiceImpl();
        User user = new User();
        user.setName("starbb_se19");
        user.setPassword("Se_3CARRY1");
        accountService.signup(user);
        accountService.login(user);
        accountService.checkStatus();
        accountService.getDescription();
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
        List<OrderItem> orderItems = new ArrayList<>();
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient1);
        orderItem1.setIngredients(ingredients);
        orderItems.add(orderItem1);
        orderItems.add(orderItem2);
        Order order = new Order(GenOrderId.getId(),new ArrayList<>(orderItems));
        PaymentInfo info9 = orderService.pay(order);
//        info9.showInfo();
    }
}

