package com.mg.konzorciatest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Quiz {

    private static final List<QuizQuestion> quizQuestions=new ArrayList<>();

    static {
        try (Scanner sc=new Scanner(new BufferedReader(new FileReader("src/questions.txt")))) {
            int counter=0;
            String[] qAndA=new String[5];
            while (sc.hasNextLine()) {
                qAndA[counter]=sc.nextLine();
                counter++;
                if (counter % qAndA.length == 0) {
                    quizQuestions.add(new QuizQuestion(qAndA[0], qAndA[1], qAndA[2], qAndA[3], qAndA[4]));
                    counter=0;
                }
            }
            Collections.shuffle(quizQuestions);
        } catch (IOException e) {
            System.out.println("Couldn't reach questions.txt" + e.getMessage());
            e.printStackTrace();
        }
    }

    public static List<QuizQuestion> getQuizQuestions() {
        return quizQuestions;
    }
}
