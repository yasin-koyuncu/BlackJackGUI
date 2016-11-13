
import javax.swing.ImageIcon;

public class Card {

	protected String face;
	protected ImageIcon cardpic;
	protected int value;
	protected String suit;

	/**
	 * Constructs a card.
	 */
	public Card() {
		cardpic = null;
		value = 0;
		suit = null;
		face = null;
	}

	/**
	 * 
	 * @param x
	 *            the image of the card
	 * @param val
	 *            the value of the card
	 * @param s
	 *            the suit of the card
	 * @param f
	 *            the type of the card
	 */
	public Card(ImageIcon x, int val, String s, String f) {
		cardpic = x;
		value = val;
		face = f;
		suit = s;
	}

	/**
	 * 
	 * @return Returns the image.
	 */
	public ImageIcon getimage() {
		return cardpic;
	}

	/**
	 * 
	 * @return Returns the value.
	 */
	public int getvalue() {
		return value;
	}

	/**
	 * Allows the user to set the value.
	 * 
	 * @param v
	 *            new value of card
	 */
	public void setvalue(int v) {
		value = v;
	}

	/**
	 * 
	 * @return Returns the suit
	 */
	public String getsuit() {
		return suit;
	}

	/**
	 * 
	 * @return Returns the face
	 */
	public String getface() {
		return face;
	}

	/**
	 * Returns a string representing the card
	 */
	public String toString() {
		return "Face: " + face + " Suit" + suit + " Value: " + value;
	}

}// end Card