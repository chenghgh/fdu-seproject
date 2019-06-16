package fudan.se.lab4.dto;

import java.io.Serializable;

public class Ingredient implements Serializable {
    private static final long serialVersionUID = 7600387145905184435L;
    private int ID;
    private int number;

    public Ingredient(int id, int number) {
        this.ID = id;
        this.number = number;
    }

    public Ingredient() {
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int id) {
        this.ID = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
