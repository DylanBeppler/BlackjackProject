package com.skilldistillery.blackjack.entities;

public class Player {

	private Hand cards = new BlackJackHand();

	public Player() {

	}

	public boolean mayBust() {
		return ((BlackJackHand) cards).isBust();
	}

	public boolean mayBlackjack() {
		return ((BlackJackHand) cards).isBlackjack();
	}

	public Hand getHand() {
		return cards;
	}

	public int getHandValue() {
		return cards.getHandValue();
	}

	@Override
	public String toString() {
		return "" + cards;
	}

	public void takeCard(Card card) {
		cards.addCard(card);

	}

}
