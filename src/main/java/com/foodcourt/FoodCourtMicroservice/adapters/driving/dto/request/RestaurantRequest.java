package com.foodcourt.FoodCourtMicroservice.adapters.driving.dto.request;

public class RestaurantRequest {
    private String name;
    private Integer ein;
    private String address;
    private String phone;
    private String urlLogo;
    private Long userId;

    public RestaurantRequest(String name, Integer ein, String address, String phone, String urlLogo, Long userId) {
        this.name = name;
        this.ein = ein;
        this.address = address;
        this.phone = phone;
        this.urlLogo = urlLogo;
        this.userId = userId;
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
}
