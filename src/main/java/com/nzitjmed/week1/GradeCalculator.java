package com.nzitjmed.week1;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GradeCalculator {

    public static final int MIN_SCORE=0;
    public static final int MAX_SCORE=100;
    public static final int A_THRESHOLD=90;
    public static final int B_THRESHOLD=80;
    public static final int C_THRESHOLD=70;
    public static final int D_THRESHOLD=60;

    // This will prevent instantiation
    public GradeCalculator()
    {

    }
    /**
     * Calculates grade for a single score.
     * @param score 0-100
     * @return letter grade A-F
     * @throws IllegalArgumentException if score is outside 0-100
     ***/

    public String calculateGrade(int score)
    {
        // TODO: Validate input (Throw IllegalArgumentException for invalid scores)

        if(score <MIN_SCORE || score >MAX_SCORE)
        {
            throw new IllegalArgumentException(
                    String.format("Score must be between %d and %d. Got : %d",MIN_SCORE,MAX_SCORE)
            );
        }
        // TODO: Use a switch expression to return the grade
        // Hint: switch(score) { case 90..100 -> "A"; ... }
        // Note: Java 14+ supports range syntax in switch

        return switch (score )
        {
            case 90,91,92,93,94,95,96,97,98,99,100 ->"A";
            case 80,81,82,83,84,85,86,87,88,89 ->"B";
            case 70,71,72,73,74,75,76,77,78,79 ->"C";
            case 60,61,62,63,64,65,66,67,68,69 ->"D";
            default ->"F";

        };
    }
    /**
     * Calculates grades for multiple students.
     * @param scores list of student scores
     * @return map of grade -> list of scores that received that grade
     */

    public Map<String, List<Integer>> calculatorGrades(List<Integer>scores)
    {
        // TODO: Use Streams to group scores by their calculated grade
        // Hint: Collectors.groupingBy(...)
        return scores.stream()
                .collect(Collectors.groupingBy(
                        this::calculateGrade,  // classifier function
                        LinkedHashMap::new,  // map supplier (maintain A,B,C,D,F. order)
                        Collectors.toList()    // downstream collector
                ));


    }
    public static void main(String[] args) {
        GradeCalculator calculator= new GradeCalculator();
        // Test simple grade
        System.out.println(calculator.calculateGrade(85));
        //Test for multiple grades
        List<Integer>classScores=List.of(95,83,77,67,46,91,88);
        Map<String,List<Integer>> result= calculator.calculatorGrades(classScores);
        System.out.println(result);

        // Additional test demonstrating  input validation
        try {
            System.out.println(calculator.calculateGrade(125));
        } catch (IllegalArgumentException e) {
            System.out.println("Validation error:" + e.getMessage());
        }


    }

}







