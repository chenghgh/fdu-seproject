package fudan.se.lab4.dto;

import fudan.se.lab4.service.DataService;
import org.apache.spark.sql.sources.In;

import javax.xml.crypto.Data;
import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
    private static final long serialVersionUID = 6442456165785725948L;

    private String id;
    private List<OrderItem> orderItems;


    public double getPrice() {
        double price = 0.0;
        for (OrderItem it : orderItems) {
            List<Ingredient> ingredientsList = it.getIngredients();
            double basicPrice = DataService.getDrinkBasicPrice(it.getID());
            double sizeExtraPrice = DataService.getSizeExtraPrice(it.getID(), it.getSize());
            price += basicPrice + sizeExtraPrice;
            if (ingredientsList != null) {
                for (Ingredient ingredient : ingredientsList) {
                    price += DataService.getIngredientPrice(ingredient.getID());
                }
            }
        }
        return price;
    }

    public Order(String id, List<OrderItem> orderItems) {
        this.id = id;
        this.orderItems = orderItems;
    }

    public Order() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public String showOrder() {
        StringBuffer sb = new StringBuffer();
        sb.append("orderId: " + this.id + " ");
        for (OrderItem it : orderItems) {
            String itname = DataService.getDrinkBasicName(it.getID());

            sb.append(itname + ":");
            sb.append(DataService.getDrinkBasicPrice(it.getID()) + " ");
            sb.append(DataService.getSizeExtraPrice(it.getID(), it.getSize()) + " ");

            List<Ingredient> ingredientList = it.getIngredients();
            if (ingredientList != null) {
                for (Ingredient ingre : ingredientList) {
                    String ingreName = DataService.getIngredientName(ingre.getID());
                    sb.append(ingreName);
                }
            }

        }
        if (sb != null)
            return sb.toString();
        else return null;
    }
}