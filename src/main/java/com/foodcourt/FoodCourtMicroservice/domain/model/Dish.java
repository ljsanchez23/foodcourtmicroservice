package com.foodcourt.FoodCourtMicroservice.domain.model;

public class Dish {
    private Long id;
    private String name;
    private Integer price;
    private String description;
    private String urlLogo;
    private Boolean status;
    private Long categoryId;
    private Long restaurantId;

    public Dish(Long id, String name, Integer price, String description, String urlLogo, Boolean status, Long categoryId, Long restaurantId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.urlLogo = urlLogo;
        this.status = status;
        this.categoryId = categoryId;
        this.restaurantId = restaurantId;
    }
    public Dish(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getUrlLogo() {
        return urlLogo;
    }

    public void setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }
}
