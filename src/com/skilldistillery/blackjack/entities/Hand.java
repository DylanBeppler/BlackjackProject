package com.skilldistillery.blackjack.entities;

import java.util.ArrayList;
import java.util.List;

public abstract class Hand {
	protected List<Card> cards;

	public Hand() {
		cards = new ArrayList<>();
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	public List<Card> getCards() {
		return cards;
	}

	@Override
	public String toString() {
		return "" + cards;
	}

	public void clear() {
		cards.clear();

	}

	public abstract int getHandValue();

}
