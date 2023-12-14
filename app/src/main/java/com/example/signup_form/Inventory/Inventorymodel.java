package com.example.signup_form.Inventory;

public class Inventorymodel {
    String Name,Specifications,Stock,Unit,Amount;

    Inventorymodel()
    {

    }


    public Inventorymodel(String name, String specifications, String stock, String unit, String amount) {
        Name = name;
        Specifications = specifications;
        Stock = stock;
        Unit = unit;
        Amount= amount;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSpecifications() {
        return Specifications;
    }

    public void setSpecifications(String specifications) {
        Specifications = specifications;
    }

    public String getStock() {
        return Stock;
    }

    public void setStock(String stock) {
        Stock = stock;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public String getAmount() { return Amount; }

    public void setAmount(String amount) { Amount = amount; }
}
