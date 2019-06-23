package fudan.se.lab4.service;

import fudan.se.lab4.Util.GenOrderId;
import fudan.se.lab4.dto.Ingredient;
import fudan.se.lab4.dto.Order;
import fudan.se.lab4.dto.OrderItem;
import fudan.se.lab4.entity.User;

import java.util.ArrayList;
import java.util.List;

public interface GetInputInfo {
    static  User getUser(){
        User user = new User();
        user.setName("starbb_se19");
        user.setPassword("Se_3CARRY1");
        return user;
    }

    static Order getOrder(){
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
        return new Order(new ArrayList<>(orderItems));
    }

}
