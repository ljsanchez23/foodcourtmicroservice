package com.foodcourt.FoodCourtMicroservice.domain.util;

public class UpdateDishStatus {
    private boolean newStatus;

    public UpdateDishStatus(boolean newStatus) {
        this.newStatus = newStatus;
    }

    public boolean isNewStatus() {
        return newStatus;
    }

    public void setNewStatus(boolean newStatus) {
        this.newStatus = newStatus;
    }
}
