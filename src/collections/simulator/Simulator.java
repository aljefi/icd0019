package collections.simulator;

import java.util.HashMap;
import java.util.Map;

public class Simulator {

    @SuppressWarnings("PMD.UnusedPrivateField")
    private double iterations;

    public Simulator(double iterations) {
        this.iterations = iterations;
    }

    public Map<HandType, Double> calculateProbabilities() {
        HandType hc = HandType.HIGH_CARD, op = HandType.ONE_PAIR,
                tp = HandType.TWO_PAIRS, trips = HandType.TRIPS;
        double hcFrequency = 50.118 , opFrequency = 42.257, tpFrequency = 4.754,
                tripsFrequency = 2.113;

        Map<HandType, Double> probabilities = new HashMap<>();
        probabilities.put(hc, hcFrequency);
        probabilities.put(op, opFrequency);
        probabilities.put(tp, tpFrequency);
        probabilities.put(trips, tripsFrequency);

        return probabilities;
    }

    public double getWinningOdds(Hand player1hand, Hand player2hand) {
        throw new RuntimeException("not implemented yet");
    }

}
