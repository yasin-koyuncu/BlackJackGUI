import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.JTextField;

public class BlackJackGUI extends JPanel {

	private int sum = 0;
	private static final long serialVersionUID = 1L;
	JPanel topPanel = new JPanel();
	JPanel dcardPanel = new JPanel();
	JPanel pcardPanel = new JPanel();

	JTextField winlosebox = new JTextField();

	// button
	JButton bet20 = new JButton();
	JButton bet50 = new JButton();
	JButton bet100 = new JButton();
	JButton hitbutton = new JButton();
	JButton dealbutton = new JButton();
	JButton staybutton = new JButton();
	JButton playagainbutton = new JButton();
	JButton Betbutton = new JButton();
	JLabel dealerlabel = new JLabel();
	JLabel playerlabel = new JLabel();
	JLabel betlabel = new JLabel();

	Hand dealer = new Hand(); // to hold the dealer's cards
	Hand player = new Hand(); // to hold the player's cards
	BlackJack game = new BlackJack(dealer, player);

	/**
	 * the labels to represent the cards for the game
	 */

	JLabel playercard1;
	JLabel playercard2;
	JLabel playercardhit;
	JLabel dealercard0;
	JLabel dealercard2;
	JLabel dealercard1;
	JLabel dealercardhit;
	JLabel dealchips;
	JLabel bets = new JLabel();

	/**
	 * Constructs the screen
	 */
	public BlackJackGUI() {

		topPanel.setBackground(new Color(0, 122, 0));
		dcardPanel.setBackground(new Color(0, 122, 0));
		pcardPanel.setBackground(new Color(0, 122, 0));

		winlosebox.setText(" ");
		winlosebox.setFont(new java.awt.Font("Times New Roman", 1, 20));

		dealbutton.setText("  Deal");
		dealbutton.addActionListener(new dealbutton());
		hitbutton.setText("  Hit");
		hitbutton.addActionListener(new hitbutton());
		hitbutton.setEnabled(false);
		staybutton.setText("  Stay");
		staybutton.addActionListener(new staybutton());
		staybutton.setEnabled(false);
		playagainbutton.setText("  Play Again");
		playagainbutton.addActionListener(new playagainbutton());
		playagainbutton.setEnabled(false);

		bet20.addActionListener(new bet());
		bet50.addActionListener(new bet());
		bet100.addActionListener(new bet());

		dealerlabel.setText("  Dealer:  ");
		playerlabel.setText("  Player:  ");
		betlabel.setText("           BET:   ");

		topPanel.add(winlosebox);
		topPanel.add(dealbutton);
		topPanel.add(hitbutton);
		topPanel.add(staybutton);
		topPanel.add(playagainbutton);

		topPanel.add(bet20);
		topPanel.add(bet50);
		topPanel.add(bet100);

		bet20.setText("20");
		bet50.setText("50");
		bet100.setText("100");

		pcardPanel.add(playerlabel);
		dcardPanel.add(dealerlabel);
		topPanel.add(betlabel);

		setLayout(new BorderLayout());
		add(topPanel, BorderLayout.NORTH);
		add(dcardPanel, BorderLayout.CENTER);
		add(pcardPanel, BorderLayout.SOUTH);

	}// end BlackjackGUI

	/**
	 * Shows the screen
	 */
	public void display() {

		JFrame m = new JFrame("BlackJack");

		m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m.setContentPane(this);
		m.setPreferredSize(new Dimension(1200, 700));

		m.setResizable(true);
		m.pack();
		m.setVisible(true);

		JMenuBar jmb = new JMenuBar();
		m.setJMenuBar(jmb);

		JMenu file = new JMenu("File");
		jmb.add(file);

		JMenuItem pause = new JMenuItem("Pause");
		file.add(pause);

		JMenu help = new JMenu("Help");
		jmb.add(help);

		JMenuItem instruction = new JMenuItem("Game rules");
		help.add(instruction);

		instruction.addActionListener(new menubutton());

		pause.addActionListener(new menubutton());

	}// end display

	/**
	 * DealButton
	 * 
	 * @param e
	 *            Deal button pressed
	 */

	private class dealbutton implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			dcardPanel.add(dealerlabel);
			pcardPanel.add(playerlabel);

			/**
			 * Get's dealer and player cards from Hand and the image associated
			 * with that random card and puts them on the screen.
			 */

			dealercard0 = new JLabel(new ImageIcon(getClass().getResource(
					"BACK2.jpg")));

			game.dealInitialCards();

			// to iterate set and get current dealer cards
			Card dcard = null;
			Iterator<Card> dscan = (dealer.Inhand()).iterator();
			int count = 0;
			while (dscan.hasNext()) {
				dcard = dscan.next();
				if (count == 0)
					dealercard1 = new JLabel(dcard.getimage());
				else
					dealercard2 = new JLabel(dcard.getimage());

				count++;
			}

			// to iterate set and get current player cards
			Iterator<Card> pscan = (player.Inhand()).iterator();
			count = 0;
			while (pscan.hasNext()) {
				Card pcard = pscan.next();
				if (count == 0)
					playercard1 = new JLabel(pcard.getimage());
				else
					playercard2 = new JLabel(pcard.getimage());

				count++;
			}

			dcardPanel.add(dealercard0);
			dcardPanel.add(dealercard2);
			pcardPanel.add(playercard1);
			pcardPanel.add(playercard2);

			dealerlabel.setText("  Dealer:  " + dcard.getvalue());
			playerlabel.setText("  Player:  " + game.handValue(player));

			hitbutton.setEnabled(true);
			staybutton.setEnabled(true);
			dealbutton.setEnabled(false);
			Betbutton.setEnabled(false);
			bet20.setEnabled(false);
			bet50.setEnabled(false);
			bet100.setEnabled(false);

			if (game.blackj()) {
				hitbutton.setEnabled(false);
				staybutton.setEnabled(false);
				dealbutton.setEnabled(false);
				playagainbutton.setEnabled(true);
				winlosebox.setText("BlackJack");
				sum += 40;
				betlabel.setText("  Bet:   " + sum);
			}

			add(dcardPanel, BorderLayout.CENTER);
			add(pcardPanel, BorderLayout.SOUTH);

		}
	}// end dealbutton

	/**
	 * HitButton every time the player wants another card until hand value is
	 * over 21.
	 * 
	 * @param e
	 *            Hit button pressed
	 */
	private class hitbutton implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			Card hitcard = game.hit(player);
			playercardhit = new JLabel(hitcard.getimage());
			pcardPanel.add(playercardhit);
			pcardPanel.repaint();

			if (game.bust(player)) {
				winlosebox.setText("Bust");
				sum -= 20;
				betlabel.setText("  Bet:   " + sum);
				hitbutton.setEnabled(false);
				dealbutton.setEnabled(false);
				staybutton.setEnabled(false);
				Betbutton.setEnabled(false);
				bet20.setEnabled(false);
				bet50.setEnabled(false);
				bet100.setEnabled(false);
				playagainbutton.setEnabled(true);
				/*if(sum<=0){
					System.exit(0);
				}*/
                               
			}

			playerlabel.setText("  Player:   " + game.handValue(player));

		}
	}// end hitbutton

	/**
	 * Betbutton 20,50,100
	 * 
	 * @param e
	 *            Bet buttons pressed
	 */
	private class bet implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (e.getActionCommand().equals("20")) {
				bet20.setEnabled(true);
				bet50.setEnabled(true);
				bet100.setEnabled(true);
				sum += 20;
			} else if (e.getActionCommand().equals("50")) {
				bet20.setEnabled(true);
				bet50.setEnabled(true);
				bet100.setEnabled(true);

				sum += 50;
			} else {

				bet20.setEnabled(true);
				bet50.setEnabled(true);
				bet100.setEnabled(true);

				sum += 100;
			}

			betlabel.setText("  Bet:   " + sum);

		}

	}// end Betbutton

	/**
	 * StayButton dealer must hit on 16 or lower. determines the winner, player
	 * wins if under 21 and above dealer.
	 * 
	 * @param e
	 *            Stay button pressed
	 */
	private class staybutton implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			dcardPanel.remove(dealercard0);
			dcardPanel.add(dealercard1);

			dealer = game.dealerPlays();
			dcardPanel.removeAll();
			dcardPanel.add(dealerlabel);
			dealerlabel.setText(" " + dealerlabel.getText());

			// iterate through cards and re-display
			Card dhitcard = null;
			Iterator<Card> scan = (dealer.Inhand()).iterator();
			while (scan.hasNext()) {
				dhitcard = scan.next();
				dealercardhit = new JLabel(dhitcard.getimage());
				dcardPanel.add(dealercardhit);
			}

			dealerlabel.setText("Dealer: " + game.handValue(dealer));
			playerlabel.setText("Player: " + game.handValue(player));

			winlosebox.setText(game.winner());
			if (game.winner() == "Win") {
				sum += 20;
				betlabel.setText("  Bet:   " + sum);
				if(sum<=0){
					System.exit(0);
				}
			} else if (game.winner() == "Lose") {
				sum -= 20;
				betlabel.setText("  Bet:   " + sum);
				if(sum<=0){
					System.exit(0);
				}

			} else {
				sum += 0;
				betlabel.setText("  Bet:   " + sum);
			}

			hitbutton.setEnabled(false);
			staybutton.setEnabled(false);
			Betbutton.setEnabled(false);
			bet20.setEnabled(false);

			playagainbutton.setEnabled(true);

		}
	}// end staybutton

	/**
	 * PlayAgainButton resets screen
	 * 
	 * @param e
	 *            Play Again button pressed
	 */
	private class playagainbutton implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			dealerlabel.setText("Dealer: ");
			playerlabel.setText("Player: ");
			winlosebox.setText("");
			dealer = new Hand();
			player = new Hand();
			game = new BlackJack(dealer, player);

			dcardPanel.removeAll();
			pcardPanel.removeAll();

			hitbutton.setEnabled(false);
			staybutton.setEnabled(false);
			playagainbutton.setEnabled(false);

			Betbutton.setEnabled(true);
			bet20.setEnabled(false);
			bet50.setEnabled(false);
			bet100.setEnabled(false);
			dealbutton.setEnabled(true);

		}
	}// end playagainbutton

	/**
	 * Choose menu option
	 * 
	 * @param e
	 *            Choose between Pause and Game rules
	 */

	private class menubutton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("Pause")) {
				JOptionPane.showMessageDialog(null,
						"You paused the game\n Press OK to resume");
			}

			else if (e.getActionCommand().equals("Game rules")) {
				JOptionPane.showMessageDialog(null,
								"  Black Jack is an interactive card game against the dealer (computer).\n"
										+ "							   In this game the player (user) has the option\n "
										+ "						       of dealing out cards and seeing those random\n"
										+ "							   cards from a set on the screen.\n "
										+ "							   The player can see both of his/her cards and only the face up card of the dealer.\n"
										+ "							   Next the player has the choice to hit or stay\n"
										+ "							   at an attempt to get the closest to\n "
										+ "							   twenty one without going over.\n"
										+ "							   Once the stay button is pressed\n "
										+ "							   the dealer makes his choice to\n "
										+ "							   hit or stay and the cards are revealed\n "
										+ "							   and the winner is chosen.\n "
										+ "							   The dealer must hit on sixteen\n"
										+ "							   or less and the player can hit until\n "
										+ "							   he/she goes over which automatically makes\n"
										+ "							   the dealer win.\n"
										+ "							   Aces are reduced to one if they\n"
										+ "							   make either hand go over twenty one.\n "
										+ "							   Incorrect choices are not allowed to be clicked\n "
										+ "							   and the correct buttons are highlighted when ready.\n");
			}

		}
	}

}