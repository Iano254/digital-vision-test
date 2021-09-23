package vision.digital;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class Challenge {
    public Challenge() {
    }
//    Get list of winners and prices
    public List<Winner> getData(String winners, String prices) throws Exception {
        if (winners != null && !winners.isEmpty() && prices != null && !prices.isEmpty()) {
            List<String> w = Arrays.asList(winners.split(",")); //Convert winners and prices to List
            List<String> p = Arrays.asList(prices.split(","));

            if (w.size() > p.size()) {
                // Winners should not be more than the prices
                throw new Exception("Winners cannot be more than prices");
            }
            // Convert prices from initial string to Bigdecimal
            List<BigDecimal> x = p.stream().map(s -> new BigDecimal(s.trim())).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
            List<Winner> chosen = new ArrayList<>(); //Initialize final List of winners with prices
            if (w.size() == p.size()) {
                // When prices are same as winners give them out randomly
                this.giveEqual(x, w, chosen);
            } else {
                // Else Distribute prices
                this.getWinners(x, w, chosen);
            }
            return chosen;
        } else {
            throw new Exception("Invalid winners or prices");
        }
    }

    private void getWinners(List<BigDecimal> prices, List<String> w, List<Winner> chosen) {
        BigDecimal sum = prices.stream().reduce(BigDecimal.ZERO, BigDecimal::add); // get the sum of all prices
        BigDecimal avg = sum.divide(BigDecimal.valueOf((double) w.size()), RoundingMode.DOWN); // get the average amount each winner should get

//        prices = prices.stream().filter(s -> s.compareTo(avg) <= 0).collect(Collectors.toList()); // Get all the prices below or equals to the average amount each winner should get
//        System.out.printf("Available prices - %s%n", prices);

        if (w.size() == prices.size()) {
            // If available prices are equal to the remaining prices distribute them randomly
            this.giveEqual(prices, w, chosen);
        } else {
            for (String winner : w) {
                // add all winners to the chosen list
                chosen.add(new Winner(winner, new ArrayList<>()));
            }
            List<Integer> given = new ArrayList<>(); // create an array to store the already given out prices
            for(int i = 0; i < prices.size(); i++){
                BigDecimal price = prices.get(i);
                chosen = chosen.stream().sorted(Comparator.comparingDouble(a -> a.getPrices().stream().reduce(BigDecimal.ZERO, BigDecimal::add).doubleValue())).collect(Collectors.toList()); // sort the list with the number of prices each winner has to prioritizes winners with less
                for (int a = 0; a < chosen.size(); a++) {
                    if (!given.contains(i)){
                        Winner winner = chosen.get(a);
                        List<BigDecimal> myPrices = new ArrayList<>(List.copyOf(winner.getPrices()));
                        BigDecimal myTotal = myPrices.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
//                        if (myTotal.add(price).compareTo(avg) <= 0) {
                            // Issue out the price
                            myPrices.add(price);
                            winner.setPrices(myPrices);
                            chosen.set(chosen.indexOf(winner), winner);
                            given.add(i);
//                        }
                    }
                }
            }
        }
    }

    private void giveEqual(List<BigDecimal> prices, List<String> w, List<Winner> chosen) {
        for (int i = 0; i < prices.size(); i++) {
            chosen.add(new Winner(w.get(i), List.of(prices.get(i))));
        }
    }
}
