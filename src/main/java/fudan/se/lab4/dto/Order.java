package fudan.se.lab4.dto;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
    private static final long serialVersionUID = 6442456165785725948L;

    private int id;
    private List<OrderItem> orderItems;

    public Order(int id, List<OrderItem> orderItems) {
        this.id = id;
        this.orderItems = orderItems;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
