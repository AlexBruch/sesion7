package com.s7.lasalle.sesion7;

/**
 * Created by alexbruch on 5/12/16.
 */

public class ItemList {
    private String name;
    private String details;
    private String price;

    public ItemList(String name, String details, String price) {
        this.name = name;
        this.details = details;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
