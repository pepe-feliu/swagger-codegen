package support;

import java.util.Random;

public class RandomNumberGenerator {
    public static void main(String[] args) {
        int randomNumber = generateRandomNumber(1, 1000000000);
        System.out.println(randomNumber);
    }

    public static int generateRandomNumber(int min, int max) {
        Random random = new Random();
        int randomNumber;
        
        randomNumber = random.nextInt(max - min + 1) + min;

        return randomNumber;
    }
}
