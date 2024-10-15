package com.foodcourt.FoodCourtMicroservice.domain.util;

public class UpdateDish {
    private Integer price;
    private String description;

    public UpdateDish(Integer price, String description) {
        this.price = price;
        this.description = description;
    }

    public UpdateDish() {
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
