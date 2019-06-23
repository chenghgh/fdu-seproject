package fudan.se.lab4;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import fudan.se.lab4.Util.GenOrderId;
import fudan.se.lab4.Util.InitUtil;
import fudan.se.lab4.dto.Ingredient;
import fudan.se.lab4.dto.Order;
import fudan.se.lab4.dto.OrderItem;
import fudan.se.lab4.dto.PaymentInfo;
import fudan.se.lab4.entity.User;
import fudan.se.lab4.service.GetInputInfo;
import fudan.se.lab4.service.impl.AccountServiceImpl;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fudan.se.lab4.service.impl.OrderServiceImpl;


public class Lab4Application {
    public static void main(String[] args) throws IOException, GeoIp2Exception {
        InitUtil.init();
        AccountServiceImpl accountService = new AccountServiceImpl();
        User user= GetInputInfo.getUser();
        accountService.signup(user);
        accountService.login(user);
        accountService.checkStatus();
        accountService.getDescription();
        OrderServiceImpl orderService = new OrderServiceImpl();
        Order order = GetInputInfo.getOrder();
        PaymentInfo info9 = orderService.pay(order);
//        info9.showInfo();
    }
}

