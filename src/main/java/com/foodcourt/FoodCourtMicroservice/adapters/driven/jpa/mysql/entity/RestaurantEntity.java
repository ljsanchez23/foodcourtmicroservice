package com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.entity;

import com.foodcourt.FoodCourtMicroservice.adapters.util.AdaptersConstants;
import jakarta.persistence.*;

@Entity
@Table(name = AdaptersConstants.RESTAURANT_TABLE_NAME)
public class RestaurantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer ein;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String urlLogo;
    @Column(nullable = false)
    private Long userId;

    public RestaurantEntity(Long id, String name, Integer ein, String address, String phone, String urlLogo, Long userId) {
        this.id = id;
        this.name = name;
        this.ein = ein;
        this.address = address;
        this.phone = phone;
        this.urlLogo = urlLogo;
        this.userId = userId;
    }

    public RestaurantEntity(){}

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
