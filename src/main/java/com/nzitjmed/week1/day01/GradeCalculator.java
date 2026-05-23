package com.nzitjmed.week1.day01;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class GradeCalculator {

    public static final int Min_SCORE = 0;
    public static final int Max_SCORE = 100;

    public static final int A_THRESHOLD = 90;
    public static final int B_THRESHOLD= 80;
    public static final int C_THRESHOLD= 70;
    public static final int D_THRESHOLD= 60;


    public GradeCalculator() {
        // public constructor, this will run when I create the object
    }

    /**
     * Remember " throw new IllegalArgumentException(...). this stops execution and reports ena error.
     * **/

    public String calculateGrade(int score) {
        if (score <Min_SCORE || score > Max_SCORE) { // This  checks whether the score is outside the allowed range
            throw new IllegalArgumentException(String.format("Score must be between %d and %d unfortunately. Got : %d",
                    Min_SCORE, Max_SCORE, score));
        }
        return gradeFromScore(score);

    }
    /**
     * It takes an integer and return a String like "A", "B".
     * We make this method private because we want to be use
     * inside the class only. External code cannot call it directly.
     * */
    private String gradeFromScore(final int score) { // It takes an integer and return a String like "A","B".
        if (score >=A_THRESHOLD) return "A";
        if (score >=B_THRESHOLD) return "B";
        if (score >=C_THRESHOLD) return "C";
        if (score >=D_THRESHOLD) return "D";
        return "F";


    }
    public Map<String, List<Integer>> calculateScores(final List<Integer> score) {
        return score.stream().collect(Collectors.groupingBy(this::calculateGrade, TreeMap::new,Collectors.toList()
        ));
    }



    public static void main(String[] args) {
        GradeCalculator calculator = new GradeCalculator();
        System.out.println(".........................");

        //Test single grade
        System.out.println("This part shows the single grades Test");
        System.out.println(calculator.calculateGrade(90));
        System.out.println("......................");


        // Test multiple grades
        System.out.println("This part is showing the multiple grades Test.");
        List<Integer> scores = List.of(29,94, 38,84, 75, 67, 46, 91, 88, 58,62);
        Map<String,List<Integer>> result = calculator.calculateScores(scores);
        System.out.println(result);
        System.out.println("..........................");


        // Test input validation
        System.out.println("This part is showing the validation Test.");

        try {
            System.out.println(calculator.calculateGrade(231));
        }catch (Exception e) {
            System.out.println("Validation error" + e.getMessage());

        }

    }







}
