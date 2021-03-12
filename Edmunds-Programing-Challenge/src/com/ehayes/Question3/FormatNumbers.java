package com.ehayes.Question3;

import com.ehayes.QuestionsPrompt.QuestionsPrompt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class FormatNumbers {

    private final BufferedReader BReader;
    private final Map<String, String> Directions;

    public void main() {
        String choose = "";
        do {
            try {
                choose = prompt();
                switch (choose) {
                    case "1":
                        PhoneNumbers();
                        break;
                    case "2":
                        SocialSecurityNumbers();
                        break;
                    case "3":
                        BothNumbers();
                        break;
                    case "4":
                        System.out.printf("%n");
                        QuestionsPrompt questionsPrompt = new QuestionsPrompt();
                        questionsPrompt.run();
                        break;
                    case "q":
                        System.out.printf("%nThank Your for this opportunity.%n" +
                                "I look forward to your feed back.%n" +
                                "Thank You! Program has ended%n");
                        System.exit(0);
                        break;
                    default:
                        System.out.printf("Unknown choice: '%s'. Try again.  %n%n",
                                choose);
                }
            } catch (IOException ioe) {
                System.out.println("Problem with input");
                ioe.printStackTrace();
            }

        } while (!choose.equals("q"));
    }

    public FormatNumbers() {
        BReader = new BufferedReader(new InputStreamReader(System.in));
        Directions = new HashMap<>();
        Directions.put("Q", "Quit the program");
        Directions.put("1", "Enter Phone Number");
        Directions.put("2", "Enter Social Security Number");
        Directions.put("3", "Enter both Social Security and Phone Number");
        Directions.put("4", "Go back to menu");

    }

    private String prompt() throws IOException {
        System.out.printf("Welcome to Formatting Numbers. Your options are: %n");

        for (Map.Entry<String, String> option : Directions.entrySet()) {
            System.out.printf("%s - %s %n", option.getKey(), option.getValue());
        }
        System.out.print("What do you want to enter: ");
        String choice = BReader.readLine();
        return choice.trim().toLowerCase();
    }

    public void PhoneNumbers() throws IOException {
        System.out.print("Enter the Phone Number: ");
        String phoneNumber = BReader.readLine();
        //Check what they entered if not a number or less than 10
        if (isInteger(phoneNumber)) {
            System.out.printf("1" + "%n" + "Phone Number: (%s)%s-%s %n%n", phoneNumber.substring(0, 3),
                    phoneNumber.substring(3, 6),
                    phoneNumber.substring(6, 10));
        } else {
            PhoneNumbers();
        }

    }

    public void SocialSecurityNumbers() throws IOException {
        System.out.print("Enter the Social Security Number: ");
        String SocialSecurityNumber = BReader.readLine();
        //Check what they entered if not a number or less than 10
        if (checkSocialSecurityNumber(SocialSecurityNumber)) {
            System.out.printf("2" + "%n" + "Social Security Number: %s-%s-%s %n%n", SocialSecurityNumber.substring(0, 3),
                    SocialSecurityNumber.substring(3, 5),
                    SocialSecurityNumber.substring(5, 9));
        } else {
            SocialSecurityNumbers();
        }

    }

    public void BothNumbers() throws IOException {
        PhoneNumbers();
        SocialSecurityNumbers();

    }

    public boolean isInteger(String str) {
        if (str.length() != 10) {
            System.out.printf("Error! Phone numbers should be 10 digits long. and no negatives. %nYou Entered %s %n", str.length());
            return false;
        }

        int length = str.length();
        int i = 0;

        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                System.out.printf("You can only enter numbers. No Letters! or Special Characters! %n");
                return false;
            }
        }
        return true;
    }

    public boolean checkSocialSecurityNumber(String str) {
        if (str.length() != 9) {
            System.out.printf("Error! Social security numbers should be 9 digits long and no negatives. %nYou Entered %s %n",
                    str.length());
            return false;
        }

        int length = str.length();
        int i = 0;

        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                System.out.printf("You can only enter numbers. No Letters! or Special Characters! %n");
                return false;
            }
        }
        return true;
    }
}
