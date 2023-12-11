package com.skilldistillery.blackjack.entities;

public class Dealer extends Player {
	Deck deck = new Deck();

	public Dealer() {
		super();
	}

	public Card dealCard(Player recipient) {
		Card card = deck.dealCard();
		recipient.takeCard(card);

		if (recipient instanceof Dealer) {
			System.out.println("Dealer places " + card + ".");
		} else if (recipient instanceof Player) {
			System.out.println("You receive " + card + ".");
		}
		return card;

	}

	public Card dealCardFaceDown(Player p) {
		Card card = deck.dealCard();
		p.takeCard(card);
		System.out.println("Dealer places face down card.");

		return card;
	}

	public void dealerHand() {
		System.out.println("Dealers turn: ");
		if (getHand().getHandValue() > 17) {
			System.out.println("Dealer selects stand.");

		}
		while (getHand().getHandValue() < 17) {
			takeCard(deck.dealCard());
			System.out.println("Dealer selects hit.");
		}
	}

	public void resetDeck() {
		deck.reset();
	}

	public void shuffle() {
		deck.shuffle();
	}
}
