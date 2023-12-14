package com.example.signup_form.FloorPlanPages;

public class FloorplanModel {

    String FloorPlanName,FloorPlanDate,image;

    public FloorplanModel() {
    }

    public FloorplanModel(String floorPlanName, String floorPlanDate, String image) {
        FloorPlanName = floorPlanName;
        FloorPlanDate = floorPlanDate;
        this.image = image;
    }

    public String getFloorPlanName() {
        return FloorPlanName;
    }

    public void setFloorPlanName(String floorPlanName) {
        FloorPlanName = floorPlanName;
    }

    public String getFloorPlanDate() {
        return FloorPlanDate;
    }

    public void setFloorPlanDate(String floorPlanDate) {
        FloorPlanDate = floorPlanDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
