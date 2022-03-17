package collections.simulator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Hand implements Iterable<Card>, Comparable<Hand> {

    private List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    @Override
    public String toString() {
        return cards.toString();
    }

    public HandType getHandType() {
        List<Card.CardValue> cardValuesList = new ArrayList<>();
        List<Card.CardSuit> cardSuitsList = new ArrayList<>();
        Card.CardValue a = null, b = null, c = null, d = null, e = null;
        Card.CardSuit x, y, z, o, j;
        for (Card card : cards) {
            if (a == null) {
                a = card.getValue();
                x = card.getSuit();
                cardValuesList.add(a);
                cardSuitsList.add(x);
            } else if (b == null) {
                b = card.getValue();
                y = card.getSuit();
                cardValuesList.add(b);
                cardSuitsList.add(y);
            } else if (c == null) {
                c = card.getValue();
                z = card.getSuit();
                cardValuesList.add(c);
                cardSuitsList.add(z);
            } else if (d == null) {
                d = card.getValue();
                o = card.getSuit();
                cardValuesList.add(d);
                cardSuitsList.add(o);
            } else if (e == null) {
                e = card.getValue();
                j = card.getSuit();
                cardValuesList.add(e);
                cardSuitsList.add(j);
            }
        }
        Collections.sort(cardValuesList);

        int pairCount = -1, straightCount = 0, tripsCount = 0, suitCount = 0;
        Card.CardValue now;
        Card.CardValue previous = null;
        Card.CardValue firstOfSame = null;
        Card.CardSuit flushCount = cardSuitsList.get(0);

        // Flush
        for (Card.CardSuit cardSuit : cardSuitsList) {
            if (flushCount == cardSuit) {
                suitCount += 1;
            }
        }

        for (Card.CardValue cardValue : cardValuesList) {
            now = cardValue;
            // Trips
            if (firstOfSame == now && firstOfSame == previous) {
                tripsCount += 1;
            }
            // Pair
            if (now == previous || pairCount == -1){
                pairCount += 1;
                if (firstOfSame != previous) {
                    firstOfSame = now;
                }
            }
            // Straight
            if (previous != null && (now.compareTo(previous) == 1
                    || now.compareTo(previous) == -1
                    || now == Card.CardValue.A)) {
                straightCount += 1;
            }
            previous = cardValue;

        }
        System.out.println("l: " + cardValuesList);
        System.out.println("Pair count: " + pairCount);
        System.out.println("Straight count: " + straightCount);
        System.out.println("Trips count: " + tripsCount);
        System.out.println("-----");
        if (pairCount == 1) {
            return HandType.ONE_PAIR;
        } else if (suitCount == 5 && straightCount == 4) {
            return HandType.STRAIGHT_FLUSH;
        } else if (tripsCount == 1 && pairCount != 2){
            return HandType.FULL_HOUSE;
        } else if (suitCount == 5) {
            return HandType.FLUSH;
        } else if (tripsCount == 1) {
            return HandType.TRIPS;
        }  else if (pairCount == 2) {
            return HandType.TWO_PAIRS;
        } else if (straightCount == 4) {
            return HandType.STRAIGHT;
        } else if (tripsCount == 2) {
            return HandType.FOUR_OF_A_KIND;
        }
        return null;
    }

    public boolean contains(Card card) {
        return cards.contains(card);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    @Override
    public Iterator<Card> iterator() {
        return cards.iterator();
    }

    @Override
    public int compareTo(Hand other) {
        return 0;
    }
}
