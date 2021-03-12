package com.ehayes.Question1;

import com.ehayes.QuestionsPrompt.QuestionsPrompt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PersonNameService {

    public Deque<String> personStack = new ArrayDeque<>();
    private final BufferedReader BReader;
    private final Map<String, String> Question1;

    public void main() {
        String choice = "";
        do {
            try {
                choice = prompt();
                switch (choice) {
                    case "1":
                        CreatePersonId();
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

    public PersonNameService() {
        BReader = new BufferedReader(new InputStreamReader(System.in));
        Question1 = new HashMap<>();
        Question1.put("Q", "Quit the program");
        Question1.put("1", "Enter a name");
        Question1.put("2", "Go back to main Menu");

    }

    public String prompt() throws IOException {
        System.out.printf("Welcome To Question 1 Solution. %n");
        for (Map.Entry<String, String> option : Question1.entrySet()) {
            System.out.printf("%s - %s %n", option.getKey(), option.getValue());
        }
        System.out.print("What do you want to enter: ");
        String choice = BReader.readLine();
        return choice.toLowerCase();
    }

    public void CreatePersonId() throws IOException {
        System.out.print("Enter a name: ");
        String name = BReader.readLine();
// first check name for condition 3 char long and if it contains any numbers
        if (name.length() < 3) {
            System.out.printf("Name has to be 3 characters long %n");
            CreatePersonId();
        } else if (name.chars().anyMatch(Character::isDigit)) {
            System.out.printf("Name cannot contain numbers %n");
            CreatePersonId();
        }
        //if name is correct only want the first 3 characters
        String checkName = name.substring(0, 3).toUpperCase();

        //id if enter id first 3 characters are new
        final String NewNameEntryId = "005";


        if (personStack.size() > 0) {
            //This is to find the first match in descending order of list from last to first
            //Time Complexity of loop is 0(N) think of ways to change to O(1) or 0(logN)?
            String person1 = personStack.stream().filter(e -> e.substring(0, 3).contains(checkName))
                    .reduce((a, b) -> b).orElse(null);
            if (person1 != null) {
                //parse the trailing numbers into a integer
                int getId = Integer.parseInt(person1.substring(4, 6));

                //add 5 to that number and change it back into a string
                int addId = getId + 5;
                add(String.format(checkName + "%03d", addId));
            } else {
                add(checkName + NewNameEntryId);
            }
            System.out.printf(personStack + "%n%n");
            main();
        }

        if (personStack.size() == 0) {
            add(checkName + NewNameEntryId);
            System.out.printf(personStack + "%n%n");
            main();
        }
    }


    public void add(String name) {
        personStack.add(name);
    }


}






