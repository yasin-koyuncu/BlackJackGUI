public class BlackJack {
	Hand dealer; // to hold the dealer's cards
	Hand player; // to hold the player's cards
	Deck newdeck; // a set of cards

	public BlackJack(Hand dlr, Hand plr) {
		dealer = dlr;
		player = plr;
		newdeck = new Deck();
	}// Blackjack constructor

	/**
	 * deal method - deals the initial cards to each player
	 */
	public void dealInitialCards() {
		dealer.newCard(newdeck);
		dealer.newCard(newdeck);
		player.newCard(newdeck);
		player.newCard(newdeck);

	}// end deal method

	/**
	 * hit method - adds the next random card from the deck to the given
	 * player's hand
	 * 
	 * @param whohit
	 *            dealer/player
	 * 
	 * @return Result
	 */
	public Card hit(Hand whohit) {
		Card result = whohit.newCard(newdeck);

		return result;

	}// end hit method

	/**
	 * handValue method - returns the value of the given player's hand
	 * 
	 * @param whohand
	 *            dealer/players hand
	 * @return result
	 */
	public int handValue(Hand whohand) {
		int result = whohand.getHandValue();

		return result;

	}// end handValue method

	/**
	 * blackj method - tests to see if the player's hand has a value of 21
	 * 
	 * @return result
	 */
	public boolean blackj() {
		boolean result = false;

		if (player.getHandValue() == 21)
			result = true;

		return result;

	}// end blackj

	/**
	 * bust method - tests a given player's hand to see if they have gone over
	 * 21
	 * 
	 * @param whobust
	 *            check the hand
	 * @return result
	 */
	public boolean bust(Hand whobust) {
		boolean result = false;

		if (whobust.getHandValue() > 21)
			result = true;

		return result;

	}// end bust

	/**
	 * dealerPlays method - adds cards to the dealer's hand until the value is
	 * bigger or equals 16
	 * 
	 * 
	 * @return result
	 */
	public Hand dealerPlays() {
		Hand result = dealer;

		while (dealer.getHandValue() <= 16) {
			dealer.newCard(newdeck);
		}

		return result;

	}// end dealerPlays

	/**
	 * winner method - determines the winner of the game
	 * 
	 * @return result
	 */
	public String winner() {
		String result = "";
		if ((player.getHandValue() < dealer.getHandValue())
				&& dealer.getHandValue() <= 21)
			result = "Lose";
		else if ((player.getHandValue() == dealer.getHandValue())
				&& dealer.getHandValue() <= 21)
			result = "Push";
		else
			result = "Win";

		return result;

	}// end winner

}