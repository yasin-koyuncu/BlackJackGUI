import java.util.ArrayList;
import java.util.Iterator;

public class Hand {
	private ArrayList<Card> inHand;
	private int handvalue;
	private int count;

	/**
	 * Constructs a hand of Cards.
	 */
	public Hand() {
		inHand = new ArrayList<Card>();
		handvalue = 0;
		count = 0;
	}

	/**
	 * To reduce hand when newcard makes player go over 21 and there is an ace
	 * in the hand.
	 * 
	 * @param newCard
	 */
	private void reduceHand(Card newCard) {
		if ((handvalue) > 21) {
			if (aceInHand())
				handvalue -= 10;

		}
	}// end reduceHand

	/**
	 * To check if there is an ace in the hand.
	 * 
	 * @return
	 */
	private boolean aceInHand() {
		boolean result = false;
		Card cardchk = null;
		Iterator<Card> scan = inHand.iterator();

		while (scan.hasNext() && !result) {
			cardchk = scan.next();
			if (cardchk.getvalue() == 11) {
				cardchk.setvalue(1);
				result = true;
			}

		}
		return result;
	}

	/**
	 * Adds a new card to the hand.
	 * 
	 * @param currentdeck
	 *            currentdeck-the Deck the game is playing with
	 * @return result
	 */
	public Card newCard(Deck currentdeck) {
		Card result;
		currentdeck.shuffleCards();
		result = currentdeck.getCard();
		inHand.add(result);
		handvalue += result.getvalue();
		reduceHand(result);
		count++;

		return result;
	}

	/**
	 * Returns the value of this hand.
	 * 
	 * @return handvalue
	 */
	public int getHandValue() {
		return handvalue;
	}

	/**
	 * Returns an iterator over this hand.
	 * 
	 * @return inhand.iterator()
	 */
	/*public Iterator<Card> iterator() {
		return inHand.iterator();
	}*/

	public ArrayList<Card> Inhand() {
		return inHand;
	}

	/**
	 * Removes a card from this hand.
	 * 
	 * @param position
	 *            choose position to remove
	 * @return Returns remove card
	 */
	public Card remove(int position) {
		count--;
		return inHand.remove(position);
	}

	/**
	 * Return count Card
	 * 
	 * @return count
	 */
	public int Count() {
		return count;
	}

	/**
	 * @return Returns a string representation of this hand.
	 */
	public String toString() {
		String result = "";

		Card cardstr = null;
		int i = 0;
		Iterator<Card> scan = inHand.iterator();
		while (scan.hasNext()) {
			cardstr = scan.next();
			result += "card" + i + ": " + cardstr.getvalue() + "\n";
			i++;
		}

		return result;
	}
}
