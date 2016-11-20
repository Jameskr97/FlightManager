package xyz.jameskr.fm.menu;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class for asking questions, getting input, and returning answers to source.
 *
 * @author James 
 * @date 11/13/16
 */
public class Interrogator {

    /**
     * Array for questions.
     */
    private ArrayList<String> questions;

    /**
     * Array for error messages if the question is answered with invalid response.
     */
    private ArrayList<String> invalidResponse;

    /**
     * Array for verifiers. Define when addQuestion is called to allow for different verification implementations for each method.
     */
    private ArrayList<VerifyResponse> verifiers;

    /**
     * Scanner for input.
     */
    private Scanner scanner;

    /**
     * Initializes above variables.
     */
    public Interrogator() {
        // CONSIDER: Maybe a class format would be better than three arraylists?
        questions = new ArrayList<>();
        invalidResponse = new ArrayList<>();
        verifiers = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    /**
     * Adds a question to be asked once ask() method is called.
     *
     * @param index    Order of question to be asked. Not strictly necessary, but makes clear when question will be asked.
     * @param question Question to ask to user.
     */
    public void addQuestion(int index, String question) {
        this.addQuestion(index, question, "Invalid Response.", (response, pastResponses) -> true);
    }

    /**
     * Same as above function, but allows for custom verification
     *
     * @param index    Order of question to be asked. Not strictly necessary, but makes clear when question will be asked.
     * @param question Question to ask to user.
     * @param verifier Anonymous class (used as lambda) in order to let the call location define if it is a valid response
     */
    public void addQuestion(int index, String question, VerifyResponse verifier) {
        this.addQuestion(index, question, "Invalid Response.", verifier);
    }

    /**
     * Same function as above, but allows for custom invalid message.
     *
     * @param invalid Error message to use if verifier returns false (i.e if the response is invalid)
     */
    public void addQuestion(int index, String question, String invalid, VerifyResponse verifier) {
        questions.add(index, question);
        invalidResponse.add(index, invalid);
        verifiers.add(index, verifier);
    }

    /**
     * Asks questions in "questions" array list, gathers response, validates response through validator,
     * and if the response is invalid, ask user if they want to try again, or stop.
     *
     * @return Returns null if user didn't want to try again, or string array of responses in the correct order.
     */
    public String[] ask() {
        boolean operationCanceled = false;

        ArrayList<String> answers = new ArrayList<>();
        for (int i = 0; i < questions.size(); i++) {
            if (operationCanceled) break;
            boolean validResponse;
            String questionResponse;
            do {
                questionResponse = askQuestion(questions.get(i));
                validResponse = verifiers.get(i).verify(questionResponse, answers.toArray(new String[answers.size()]));
                if (!validResponse) {
                    System.out.print(invalidResponse.get(i) + " Try again? (y/n): ");
                    String tryAgainResponse = scanner.nextLine();
                    if (tryAgainResponse.equals("n")) {
                        operationCanceled = true;
                        break;
                    }
                }
            } while (!validResponse);
            answers.add(i, questionResponse);
        }

        if (operationCanceled) return null;
        return answers.toArray(new String[answers.size()]);
    }

    /**
     * Asks question in array.
     *
     * @param question Question to ask
     * @return Response to question
     */
    private String askQuestion(String question) {
        //TODO: Consider remove this method and moving it directly inside ask() method.
        System.out.printf("%s", question);
        return scanner.nextLine();
    }

    /**
     * Functional interface which is used in addQuestion method, intended to be used as lambda,
     * in order to allow the response verification process to be defined where the
     * addQuestion method is called.
     */
    public interface VerifyResponse {

        /**
         * @param response      The answer to the question
         * @param pastResponses all previous answers
         * @return True if the response is valid. False is the response is not valid.
         */
        boolean verify(String response, String[] pastResponses);
    }
    
}
