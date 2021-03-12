package com.ehayes.Question2;

import com.ehayes.QuestionsPrompt.QuestionsPrompt;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FormatAddress {

    private final BufferedReader BReader;
    private final Map<String, String> Question2;

    public void run() {
        String choice = "";
        do {
            try {
                choice = prompt();
                switch (choice) {
                    case "1":
                        importFrom();
                        break;
                    case "2":
                        System.out.printf("%n");
                        QuestionsPrompt questionsPrompt = new QuestionsPrompt();
                        questionsPrompt.run();
                    case "q":
                        System.out.printf("%nThank Your for this opportunity.%n" +
                                "I look forward to your feed back.%n" +
                                "Thank You! Program has ended%n");
                        System.exit(0);
                }
            } catch (IOException ioe) {
                System.out.println("Problem with input");
                ioe.printStackTrace();
            }
        } while (!choice.equals("q"));
    }

    public FormatAddress() {
        BReader = new BufferedReader(new InputStreamReader(System.in));
        Question2 = new HashMap<>();
        Question2.put("Q", "Quit the program");
        Question2.put("1", "Hit 1 to see Question 2 solution options");
        Question2.put("2", "go back to main Menu");

    }

    public String prompt() throws IOException {
        System.out.printf("%nWelcome To Question 2 Solution. %n");
        for (Map.Entry<String, String> option : Question2.entrySet()) {
            System.out.printf("%s - %s %n", option.getKey(), option.getValue());
        }
        System.out.print("What do you want to enter: ");
        String choice = BReader.readLine();
        return choice.toLowerCase();
    }

    public void importFrom() throws IOException {
        System.out.printf("Hit enter to see internal csv file or Type path to another cvs file%n");
        String file = BReader.readLine();
        if (file == null || file.isEmpty()) {
            file = "CsvExample.csv";
        }

        try (FileInputStream fis = new FileInputStream(file);
             BufferedReader reader = new BufferedReader(new InputStreamReader(fis))) {
            String line;
            int iteration = 0;
            while ((line = reader.readLine()) != null) {
                //skip first line in csv file
                if (iteration == 0) {
                    iteration++;
                    continue;
                }
                //find the lines that contain a double quote
                if (line.contains("\"")) {
                    //find the quotes and remove them
                    String newline = line.replace("\"", "");
                    //then print it with format
                    format(newline);
                } else {
                    format(line);
                }
            }
        } catch (IOException e) {
            System.out.printf("Problems loading %s %n", file);
            e.printStackTrace();
            run();
        }
        run();
    }

    private void format(String line) {
        //used a negative affect where we ignore commas that is not followed by a space
        String[] newline = line.split(",(?! )");
        Arrays.stream(newline).forEach(System.out::println);
    }
}
