import java.util.*;

public class Task_4 {
    static class Question {
        String questionText;
        String[] options;
        int correctOption;

        public Question(String questionText, String[] options, int correctOption) {
            this.questionText = questionText;
            this.options = options;
            this.correctOption = correctOption;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Question> questions = new ArrayList<>();
        int score = 0;
        Map<Integer, Boolean> results = new HashMap<>();

        // Define quiz questions
        questions.add(new Question("What is the capital of France?",
                new String[]{"1. Berlin", "2. Madrid", "3. Paris", "4. Rome"}, 3));
        questions.add(new Question("What is 5 + 3?",
                new String[]{"1. 6", "2. 7", "3. 8", "4. 9"}, 3));
        questions.add(new Question("Who developed Java?",
                new String[]{"1. Microsoft", "2. Oracle", "3. Sun Microsystems", "4. IBM"}, 3));
        questions.add(new Question("What is the smallest prime number?",
                new String[]{"1. 1", "2. 2", "3. 3", "4. 5"}, 2));
        questions.add(new Question("Which planet is known as the Red Planet?",
                new String[]{"1. Earth", "2. Mars", "3. Jupiter", "4. Venus"}, 2));

        System.out.println("Welcome to the Quiz! You have 10 seconds per question.");

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            System.out.println("\nQuestion " + (i + 1) + ": " + question.questionText);
            for (String option : question.options) {
                System.out.println(option);
            }

            long startTime = System.currentTimeMillis();
            System.out.print("Enter your answer (1-4): ");

            int userAnswer = 0;
            boolean answeredInTime = false;
            while (System.currentTimeMillis() - startTime < 10000) {
                if (scanner.hasNextInt()) {
                    userAnswer = scanner.nextInt();
                    answeredInTime = true;
                    break;
                }
            }

            if (!answeredInTime) {
                System.out.println("\nTime's up! Moving to the next question.");
                results.put(i + 1, false);
                continue;
            }

            if (userAnswer == question.correctOption) {
                System.out.println("Correct!");
                score++;
                results.put(i + 1, true);
            } else {
                System.out.println("Incorrect! The correct answer was option " + question.correctOption);
                results.put(i + 1, false);
            }
        }

        // Display final results
        System.out.println("\n--- Quiz Results ---");
        System.out.println("Your Score: " + score + "/" + questions.size());
        for (int i = 1; i <= questions.size(); i++) {
            String result = results.get(i) ? "Correct" : "Incorrect";
            System.out.println("Question " + i + ": " + result);
        }

        System.out.println("Thank you for participating!");
        scanner.close();
    }
}
