package support;
import java.util.Random;


public class RandomStringGenerator {
    private static final String[] FIRST_NAMES = {
        "John", "Jane", "David", "Emma", "Michael", "Olivia", "William", "Sophia", "James", "Isabella"
    };

    private static final String[] LAST_NAMES = {
        "Smith", "Johnson", "Brown", "Taylor", "Miller", "Wilson", "Moore", "Anderson", "Thomas", "Jackson"
    };

    public static String generateRandomName() {
        Random random = new Random();
        String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
        return firstName;
    }

    public static String generateRandomLastName() {
        Random random = new Random();
        String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
        return lastName;
    }

    public static void main(String[] args) {
        String randomName = generateRandomName();
        System.out.println("Random Name: " + randomName);
    }

    public static String generateRandomEmail() {
        Random random = new Random();
        String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
        String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
        return lastName + firstName;
    }
}
