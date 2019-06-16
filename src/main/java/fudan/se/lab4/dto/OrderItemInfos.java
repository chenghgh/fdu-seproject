package fudan.se.lab4.dto;

import fudan.se.lab4.service.DataService;

import java.util.ArrayList;
import java.util.List;


// a class to save the OrderItem and its prices
public class OrderItemInfos{

    List<OrderItemInfo> orderItemInfos;
    //get List<OrderItemInfo> from order, get the name and price information of items.
    public OrderItemInfos(Order order){

        List<OrderItemInfo> ItemInfos = new ArrayList<>();
        List<OrderItem> orderItems = order.getOrderItems();
        for (OrderItem orderItem : orderItems) {

            double basicPrice =  DataService.getDrinkBasicPrice(Integer.valueOf(orderItem.getID()));

            List<Double> ingredientsPrice = new ArrayList<>();
            int size = orderItem.getSize();
            if(orderItem.getIngredients() != null){
                for(Ingredient ingredient : orderItem.getIngredients()){
                    ingredientsPrice.add(DataService.getIngredientPrice(Integer.valueOf(ingredient.getID())));
                }
            }
            double sizePrice = DataService.getSizeExtraPrice(Integer.valueOf(orderItem.getID()),orderItem.getSize());
            ItemInfos.add(new OrderItemInfo(orderItem.getID(),orderItem.getIngredients(),size ,ingredientsPrice, sizePrice, basicPrice));
        }
        this.orderItemInfos = ItemInfos;
    }


    //get the total price
    public double getPrice(){
        double price = 0;
        for(int m=0 ;m<orderItemInfos.size();m++){
            for(int i=0 ; i<orderItemInfos.get(m).getIngredientsPrice().size();i++){
                price += orderItemInfos.get(m).getIngredientsPrice().get(i);

            }
        }
        for (OrderItemInfo item : orderItemInfos) {
            price += item.getBasicPrice() + item.getSizePrice();
        }
        return price;
    }

    public List<OrderItemInfo> getInfos(){
        return this.orderItemInfos;
    }


}

