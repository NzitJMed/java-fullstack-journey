package com.nzitjmed.week1.day03;

import java.util.*;

public class DoItAgain {
    public void ToKnown()
    {
        List<Integer> numbers = Arrays.asList(13,22,30,40,55,74,23,45);
        //1.Print the filtered values

        System.out.println("Valued summed::");
        numbers.stream()
                .filter(n -> n % 2== 0)
                .forEach(System.out::println);
        System.out.println();
        // 2. We print and calculate the sum
        int sum=numbers.stream()
                .filter(n ->n%2==0)
                .peek(System.out::println)
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("The sum of these values are   ::" +sum);

    }



    public static void main(String[] args) {
        Map<String,Integer> shoppingCart=new HashMap<>();
        shoppingCart.put("Laptop",1);
        shoppingCart.put("Mouse",2);
        shoppingCart.put("Keyboard",3);
        for(Map.Entry<String,Integer> entry:shoppingCart.entrySet())
            {
            System.out.println(entry.getKey()+" "+entry.getValue());
            }





    }
}
