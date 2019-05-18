/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Food UP");
    }
    public void submitOrder(View view) {
        // Get user's name
        EditText nameField = (EditText) findViewById(R.id.name_field);
        Editable nameEditable = nameField.getText();

        FoodDatabase foodBase= new FoodDatabase();
        ArrayList<FoodItem> foodList=foodBase.createBase();
        Optimization optimize = new Optimization();

        String price = nameEditable.toString();

        double calcPrice = Double.parseDouble(price);

        int calories;
        int sodium;
        int protein;
        int carb;
        int fat;


        RadioButton caloriesHigh = (RadioButton) findViewById(R.id.calories_high_field);
        boolean hasHighColories = caloriesHigh.isChecked();


        RadioButton caloriesLow = (RadioButton) findViewById(R.id.calories_low_field);
        boolean hasLowColories = caloriesLow.isChecked();

        if (hasHighColories == true)
        {
            calories = 2;
        }
        else if(hasLowColories == true)
        {
            calories = 1;
        }
        else
        {
            calories = 0;
        }

        // Figure out if the user wants whipped cream topping
        RadioButton carbsHigh = (RadioButton) findViewById(R.id.carbs_high_field);
        boolean hasHighCarbs = carbsHigh.isChecked();


        RadioButton carbsLow = (RadioButton) findViewById(R.id.carbs_low_field);
        boolean hasLowCarbs = carbsLow.isChecked();

        if (hasHighCarbs == true)
        {
            carb = 2;
        }
        else if(hasLowCarbs == true)
        {
            carb = 1;
        }
        else
        {
            carb = 0;
        }


        RadioButton fatHigh = (RadioButton) findViewById(R.id.fat_high_field);
        boolean hasHighFat = fatHigh.isChecked();


        RadioButton fatLow = (RadioButton) findViewById(R.id.fat_low_field);
        boolean hasLowFat = fatLow.isChecked();

        if (hasHighFat == true)
        {
            fat = 2;
        }
        else if(hasLowFat == true)
        {
            fat = 1;
        }
        else
        {
            fat = 0;
        }

        RadioButton proteinHigh = (RadioButton) findViewById(R.id.protein_high_field);
        boolean hasHighProtein = proteinHigh.isChecked();

        RadioButton proteinLow = (RadioButton) findViewById(R.id.protein_low_field);
        boolean hasLowProtein = proteinLow.isChecked();

        if (hasHighProtein == true)
        {
            protein = 2;
        }
        else if(hasLowProtein == true)
        {
            protein =1;
        }
        else
        {
            protein = 0;
        }
        RadioButton sodiumHigh = (RadioButton) findViewById(R.id.sodium_high_field);
        boolean hasHighSodium = sodiumHigh.isChecked();

        RadioButton sodiumLow = (RadioButton) findViewById(R.id.sodium_low_field);
        boolean hasLowSodium = sodiumLow.isChecked();

        if (hasHighSodium == true)
        {
            sodium = 2;
        }
        else if(hasLowProtein == true)
        {
            sodium =1;
        }
        else
        {
            sodium = 0;
        }
        ArrayList<FoodItem> suggestedFoods = optimize.suggest(foodList,calcPrice,protein,carb,calories,sodium,fat);
        FoodItem top1 = suggestedFoods.get(0);
        FoodItem top2 = suggestedFoods.get(1);
        FoodItem top3 = suggestedFoods.get(2);



        String message = "";

        message += " Your optimal lowest priced foods";
        message += "\n base on DV for under: $"+calcPrice +"\n";

        message += "\n Price: " +top1.getCost();
        message += "\n Food Name: " +top1.getName();
        message += "\n Calories: " +top1.getCalories() +"g";
        message += "\n Protein: " +top1.getProtein() +"g";
        message += "\n Carb: " +top1.getCarbs() +"g";
        message += "\n Fat: " + top1.getFat()+"g";
        message += "\n From: "+ top1.getRestaurant()+ "\n";

        message += "\n Price: " +top2.getCost();
        message += "\n Food Name: " +top2.getName();
        message += "\n Calories: " +top2.getCalories() +"g";
        message += "\n Protein: " +top2.getProtein() +"g";
        message += "\n Carb: " +top2.getCarbs() +"g";
        message += "\n Fat: " + top2.getFat()+"g";
        message += "\n From: "+ top2.getRestaurant()+ "\n";

        message += "\n Price: " +top3.getCost();
        message += "\n Food Name: " +top3.getName();
        message += "\n Calories: " +top3.getCalories() +"g";
        message += "\n Protein: " +top3.getProtein() +"g";
        message += "\n Carb: " +top3.getCarbs() +"g";
        message += "\n Fat: " + top3.getFat()+"g";
        message += "\n From: "+ top3.getRestaurant();

        displayFood(message);

    }



    public void increment(View view) {
        if (quantity == 100) {
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }
    public void decrement(View view) {
        if (quantity == 0) {
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }
    private void displayFood(String message)
    {
        TextView displayReccommendedFood = (TextView) findViewById(
                R.id.myOrder_text_view);
       displayReccommendedFood.setText(message);
    }
}


