package com.foodcourt.FoodCourtMicroservice.domain.model;

import java.util.List;

public class Restaurant {
    private Long id;
    private String name;
    private Integer ein;
    private String address;
    private String phone;
    private String urlLogo;
    private Long userId;
    private List<Dish> dishes;
    private List<Long> employeesId;

    public Restaurant(Long id, String name, Integer ein, String address, String phone, String urlLogo, Long userId, List<Dish> dishes, List<Long> employeesId) {
        this.id = id;
        this.name = name;
        this.ein = ein;
        this.address = address;
        this.phone = phone;
        this.urlLogo = urlLogo;
        this.userId = userId;
        this.dishes = dishes;
        this.employeesId = employeesId;
    }

    public Restaurant(){}

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

    public Integer getEin() {
        return ein;
    }

    public void setEin(Integer ein) {
        this.ein = ein;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUrlLogo() {
        return urlLogo;
    }

    public void setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public List<Long> getEmployeesId() {
        return employeesId;
    }

    public void setEmployeesId(List<Long> employeesId) {
        this.employeesId = employeesId;
    }
}
