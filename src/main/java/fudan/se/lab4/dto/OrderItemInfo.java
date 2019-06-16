package fudan.se.lab4.dto;

import java.util.List;

public class OrderItemInfo{
    private static final long serialVersionUID = -2451304424331432011L;
    private int ID;
    private int size;
    private List<Ingredient> ingredients;
    private List<Double> ingredientsPrice;
    private double sizePrice;
    private double basicPrice;

    public OrderItemInfo(int id, List<Ingredient> ingredients, int size, List<Double> ingredientsPrice, double sizePrice, double basicPrice) {
        this.ID = id;
        this.ingredients = ingredients;
        this.size = size;
        this.ingredientsPrice = ingredientsPrice;
        this.sizePrice = sizePrice;
        this.basicPrice = basicPrice;
    }

    public OrderItemInfo() {
    }
    public int getID() {
        return this.ID;
    }
    public List<Ingredient> getIngredients() {
        return ingredients;
    }
    public int getSize() {
        return size;
    }
    public double getBasicPrice() {
        return basicPrice;
    }
    public double getSizePrice() {
        return sizePrice;
    }
    public List<Double> getIngredientsPrice(){
        return ingredientsPrice;
    }

}