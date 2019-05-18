package com.example.android.justjava;

public class FoodItem {

    private String name;
    private String restaurant;
    private double cost;
    private int proteinAmount;
    private int carbAmount;
    private int calorieAmount;
    private int sodiumAmount;
    private int totalFat;
    private double proteinPercent;
    private double carbPercent;
    private double calPercent;
    private double sodiumPercent;
    private double fatPercent;
    private double compositeScore;

    public FoodItem(String name, String restaurant, double cost, int protein, int carb, int calorie, int sodium, int fat, double proteinPercent, double carbPercent, double calPercent, double compositeScore)
    {
        this.name = name;
        this.restaurant = restaurant;
        this.cost = cost;
        this.proteinAmount = protein;
        this.carbAmount = carb;
        this.calorieAmount = calorie;
        this.sodiumAmount = sodium;
        this.totalFat = fat;
        this.proteinPercent = proteinPercent;
        this.carbPercent = carbPercent;
        this.calPercent = calPercent;
        this.compositeScore = compositeScore;
    }

    public String getName(){
        return name;
    }

    public String getRestaurant(){
        return restaurant;
    }

    public double getCost(){
        return cost;
    }

    public int getProtein(){
        return proteinAmount;
    }

    public int getCarbs(){
        return carbAmount;
    }

    public int getCalories(){
        return calorieAmount;
    }

    public int getSodium(){
        return sodiumAmount;
    }

    public int getFat(){
        return totalFat;
    }

    public void setProteinPercent(double p){
        proteinPercent = p;
    }

    public double getProteinPercent(){
        return proteinPercent;
    }

    public void setCarbPercent(double p){
        carbPercent = p;
    }

    public double getCarbPercent(){
        return carbPercent;
    }

    public void setCalPercent(double p){
        calPercent = p;
    }

    public double getCalPercent(){
        return calPercent;
    }

    public void setSodiumPercent(double p){
        sodiumPercent = p;
    }

    public double getSodiumPercent(){
        return sodiumPercent;
    }

    public void setFatPercent(double p){
        fatPercent = p;
    }

    public double getFatPercent(){
        return fatPercent;
    }

    public void setCompScore(double score){
        compositeScore = score;
    }

    public double getCompScore(){
        return compositeScore;
    }

    public String toString(){

        return String.format("Food Name: %s/nCalories: %d/nProtein: %d/nCarbs: %d/nFat: %d/nFrom Restaurant: %s/n", name, calorieAmount, proteinAmount,
                carbAmount, totalFat, restaurant);
    }

}