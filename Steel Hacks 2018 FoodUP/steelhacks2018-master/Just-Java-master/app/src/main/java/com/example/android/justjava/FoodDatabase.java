package com.example.android.justjava;

import java.util.*;


    public class FoodDatabase {

        public ArrayList<FoodItem> createBase(){
            ArrayList<FoodItem> foodList = new ArrayList<FoodItem>();

            //Order: name, restaurant, cost, protein, carbs, calorie, sodium, fat
            foodList.add(new FoodItem("Big Mac", "McDonald's", 3.99, 24, 47, 530, 960, 27, 0.0, 0.0, 0.0, 0));
            foodList.add(new FoodItem("Chicken McNuggets (4 Pc)", "McDonald's", 1.99, 9, 12, 190, 360, 12, 0.0, 0.0, 0.0, 0));
            foodList.add(new FoodItem("McChicken", "McDonald's", 1.29, 14, 40, 360, 800, 16, 0.0, 0.0, 0.0, 0));
            foodList.add(new FoodItem("Medium French Fries", "McDonald's", 1.79, 4, 44, 510, 190, 16, 0.0, 0.0, 0.0, 0));
            foodList.add(new FoodItem("Ranch Snack Wrap (Crispy)", "McDonald's", 1.69, 15, 32, 360, 810, 20, 0.0, 0.0, 0.0, 0));

            foodList.add(new FoodItem("Black Forest Ham (6 inch)", "Subway", 3.75, 18, 46, 290, 800, 5, 0.0, 0.0, 0.0, 0));
            foodList.add(new FoodItem("Black Forest Ham (Footlong)", "Subway", 5.50, 36, 92, 580, 1600, 10, 0.0, 0.0, 0.0, 0));
            foodList.add(new FoodItem("Meatball Marinara (6 inch)", "Subway", 3.75, 20, 53, 460, 1090, 18, 0.0, 0.0, 0.0, 0));
            foodList.add(new FoodItem("Meatball Marinara (Footlong)", "Subway", 5.50, 40, 106, 920, 2180, 36, 0.0, 0.0, 0.0, 0));
            foodList.add(new FoodItem("Italian B.M.T. (6 inch)", "Subway", 4.25, 19, 43, 390, 1330, 17, 0.0, 0.0, 0.0, 0));
            foodList.add(new FoodItem("Italian B.M.T. (Footlong)", "Subway", 6.75, 38, 86, 780, 2660, 34, 0.0, 0.0, 0.0, 0));
            foodList.add(new FoodItem("Chicken & Bacon Ranch Melt (6 inch)", "Subway", 4.75, 37, 44, 590, 1360, 30, 0.0, 0.0, 0.0, 0));
            foodList.add(new FoodItem("Chicken & Bacon Ranch Melt (Footlong)", "Subway", 7.75, 74, 88, 1180, 2720, 60, 0.0, 0.0, 0.0, 0));

            foodList.add(new FoodItem("Wisconsin Mac & Cheese (Reg)", "Noodles and Company", 5.89, 42, 119, 980, 1560, 38, 0.0, 0.0, 0.0, 0));
            foodList.add(new FoodItem("Spaghetti & Meatballs (Reg)", "Noodles and Company", 5.80, 35, 101, 980, 1570, 48, 0.0, 0.0, 0.0, 0));
            foodList.add(new FoodItem("Pad Thai (Reg)", "Noodles and Company", 5.89, 19, 129, 1240, 1290, 70, 0.0, 0.0, 0.0, 0));
            foodList.add(new FoodItem("Buttered Noodles (Reg)", "Noodles and Company", 5.39, 22, 98, 760, 600, 35, 0.0, 0.0, 0.0, 0));

            foodList.add(new FoodItem("Classic Chicken Sandwich", "Chick-fil-A", 3.05, 31, 39, 430, 1370, 17, 0.0, 0.0, 0.0, 0));
            foodList.add(new FoodItem("Deluxe Chicken Sandwich", "Chick-fil-A", 3.65, 35, 43, 500, 1630, 21, 0.0, 0.0, 0.0, 0));
            foodList.add(new FoodItem("12 Nuggets", "Chick-fil-A", 4.45, 37, 1, 359, 1320, 16, 0.0, 0.0, 0.0, 0));
            foodList.add(new FoodItem("Medium Waffle Fries", "Chick-fil-A", 1.65, 4, 45, 387, 187, 21, 0.0, 0.0, 0.0, 0));

            return foodList;
        }


    }