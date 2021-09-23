package vision.digital;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            Challenge challenge = new Challenge();
            System.out.println("Enter winners separated by comma");
            String winnersInput = scanner.nextLine();
            System.out.println("Enter prices separated by comma");
            String pricesInput = scanner.nextLine();
            List<Winner> winners = challenge.getData(winnersInput, pricesInput);
            for (Winner w : winners) {
                System.out.printf("%s - %s%n", w.getName(), w.getPrices());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
