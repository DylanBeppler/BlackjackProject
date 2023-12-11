package com.skilldistillery.blackjack.entities;

public class BlackJackHand extends Hand {
	public BlackJackHand() {
		super();
	}

	public int getHandValue() {
		int total = 0;
		for (Card card : cards) {
			total += card.getValue();
		}
		return total;
	}

	public boolean isBlackjack() {
		return getHandValue() == 21;
	}

	public boolean isBust() {
		return getHandValue() > 21;
	}
}