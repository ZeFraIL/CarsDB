package com.zeev.fraiman.carsdb;

class Car {
    private String code;
    private String company;
    private String model;
    private int year;
    private double price;

    public Car(String code, String company, String model, int year, double price) {
        this.code = code;
        this.company = company;
        this.model = model;
        this.year = year;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return ""+company + " (" + model + ")\nfrom " + year +"\n"+ (int)price*1000;
    }
}
