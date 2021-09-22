package vision.digital;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
//Create the return POJO
public class Winner implements Serializable {
    private String name;
    private List<BigDecimal> prices;
    public Winner() {
    }

    public Winner(String name, List<BigDecimal> prices) {
        this.name = name;
        this.prices = prices;
    }

    public String getName() {
        return name;
    }

    public List<BigDecimal> getPrices() {
        return prices;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrices(List<BigDecimal> prices) {
        this.prices = prices;
    }

    @Override
    public String toString() {
        return "Winner{" +
                "name='" + name + '\'' +
                ", prices=" + prices +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Winner winner = (Winner) o;
        return Objects.equals(name, winner.name) && Objects.equals(prices, winner.prices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, prices);
    }
}
