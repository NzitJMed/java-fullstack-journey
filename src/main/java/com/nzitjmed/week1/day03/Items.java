package com.nzitjmed.week1.day03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Items {
    String laptop;
    int price;

    public Items(String laptop, int price) {
    }

    public String getLaptop() {
        return laptop;
    }

    public void setLaptop(String laptop) {
        this.laptop = laptop;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void Shop()
    {
        List< Items> items= Arrays.asList(
                new Items("Laptop",350),
                new Items("Mouse",432),
                new Items("Keyboard",20),
                new Items("Screen",931),
                new Items("Chair",120));

        List<Items> affordableItems=items.stream()
                .filter(n ->n.getPrice()<35)
                .toList();
        affordableItems.forEach(System.out::println);
    }

    public static void main(String[] args) {
        Items items=new Items("Laptop",30);
        items.Shop();

    }
}
