package com.foodcourt.FoodCourtMicroservice.domain.model;

public class Restaurant {
    private Long id;
    private String name;
    private Integer ein;
    private String address;
    private String phone;
    private String urlLogo;
    private Long userId;

    public Restaurant(Long id, String name, Integer ein, String address, String phone, String urlLogo, Long userId) {
        this.id = id;
        this.name = name;
        this.ein = ein;
        this.address = address;
        this.phone = phone;
        this.urlLogo = urlLogo;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEin(Integer ein) {
        this.ein = ein;
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
}
