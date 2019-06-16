package fudan.se.lab4.dto;

import java.io.Serializable;
import java.util.List;

public class OrderItem implements Serializable {
    private static final long serialVersionUID = -2451304424331432011L;

    private int ID;
    private int size;
    private List<Ingredient> ingredients;

    public OrderItem(OrderItem orderItem) {
        this.ID = orderItem.ID;
        this.ingredients = orderItem.ingredients;
        this.size = orderItem.size;
    }

    public OrderItem(int id, List<Ingredient> ingredients, int size) {
        this.ID = id;
        this.ingredients = ingredients;
        this.size = size;
    }

    public OrderItem() {
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int id) {
        this.ID = id;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
