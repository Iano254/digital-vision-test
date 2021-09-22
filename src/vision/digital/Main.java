package vision.digital;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            System.out.println("Lets go");
            Challenge challenge = new Challenge();
            List<Winner> winners = challenge.getData("Joshua,Mahesh,Lilian,Jamo,Marto,Travis,Ian", "100,800,500,300, 300, 540,1000,340,170,200,500,400,1000");
            for (Winner w : winners) {
                System.out.printf("%s - %s%n", w.getName(), w.getPrices());
            }
            System.out.println("===========================================");
            List<Winner> winners2 = challenge.getData("Barry,Sheila,Onyango,Wekesa", "400,400,500,600");
            for (Winner w : winners2) {
                System.out.printf("%s - %s%n", w.getName(), w.getPrices());
            }

            System.out.println("===========================================");
            List<Winner> winners3 = challenge.getData("Joshua,Mahesh,Lilian", "100,800,200,500,400,1000");
            for (Winner w : winners3) {
                System.out.printf("%s - %s%n", w.getName(), w.getPrices());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
