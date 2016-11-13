package xyz.jameskr.fm.menu;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * Class for asking questions, getting input, and returning answers to source.
 *
 * @author James 
 * @date 11/13/16
 */
public class Interrogator {

    private ArrayList<String> questions;
    private ArrayList<String> invalidResponse;
    private ArrayList<VerifyResponse> verifiers;
    private Scanner scanner;


    public Interrogator(){
        // CONSIDER: Maybe a class format would be better than three arraylists?
        questions = new ArrayList<>();
        invalidResponse = new ArrayList<>();
        verifiers = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void addQuestion(int index, String question, String invalid, VerifyResponse verifier){
        questions.add(index, question);
        invalidResponse.add(index, invalid);
        verifiers.add(index, verifier);
    }

    public String[] ask(){
        ArrayList<String> answers = new ArrayList<>();
        for(int i = 0; i < questions.size(); i++){
            boolean validResponse = false;
            String questionResponse;
            do {
                questionResponse = askQuestion(questions.get(i));
                validResponse = verifiers.get(i).verify(questionResponse);
                if (!validResponse){
                    System.out.print(invalidResponse.get(i) + " Try again? (y/n): ");
                    String tryAgainResponse = scanner.nextLine();
                    if (tryAgainResponse.equals("n"))
                        return null;
                }
            } while (!validResponse);
            answers.add(i, questionResponse);

        }
        return answers.toArray(new String[answers.size()]);
    }

    public String askQuestion(String question){
        System.out.printf("%s", question);
        return scanner.nextLine();
    }


    public interface VerifyResponse {
        boolean verify(String response);
    }
}
