package com.ehayes.QuestionsPrompt;

import com.ehayes.Question1.PersonNameService;
import com.ehayes.Question2.FormatAddress;
import com.ehayes.Question3.FormatNumbers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class QuestionsPrompt {
    private final BufferedReader BReader;
    private final Map<String, String> Questions;


    public void run() {
        String choice = "";
        do {
            try {
                choice = prompt();
                switch (choice) {
                    case "1":
                        PersonNameService personNameService = new PersonNameService();
                        personNameService.main();
                        break;
                    case "2":
                        FormatAddress address = new FormatAddress();
                        address.run();
                        break;
                    case "3":
                        FormatNumbers formatNumbers = new FormatNumbers();
                        formatNumbers.main();
                        break;
                    case "4":
                        System.out.printf("Thank Your for this opportunity.%n" +
                                "I look forward to your feed back.%n" +
                                "Thank You! Program has ended");
                        System.exit(0);
                }
            } catch (IOException ioe) {
                System.out.println("Problem with input");
                ioe.printStackTrace();
            }
        } while (!choice.equals("4"));

    }

    public QuestionsPrompt() {
        BReader = new BufferedReader(new InputStreamReader(System.in));
        Questions = new HashMap<>();
        Questions.put("1", "To run Question 1");
        Questions.put("2", "To see Question 2");
        Questions.put("3", "To run Question 3");
        Questions.put("4", "Exit the program");
    }

    public String prompt() throws IOException {
        System.out.printf("Thank you for this opportunity.%nWhile your here check out a Question%n");
        for (Map.Entry<String, String> option : Questions.entrySet()) {
            System.out.printf("%s - %s %n", option.getKey(), option.getValue());
        }
        System.out.printf("Which question do you want to try?: " + "%n");
        return BReader.readLine();
    }

}
