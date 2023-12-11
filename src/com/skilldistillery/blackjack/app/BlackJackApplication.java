package com.skilldistillery.blackjack.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.blackjack.entities.Card;
import com.skilldistillery.blackjack.entities.Dealer;
import com.skilldistillery.blackjack.entities.Player;

public class BlackJackApplication {
	Dealer dealer = new Dealer();
	Player player = new Player();
	Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		BlackJackApplication game = new BlackJackApplication();
		game.playGame();
	}

	public void playGame() {
		boolean playAgain = true;

		while (playAgain) {
			player.getHand().clear();
			dealer.getHand().clear();
			dealer.resetDeck();
			run();

			System.out.print("Do you want to play again? (yes/no): ");
			String playAgainChoice = scanner.nextLine().toLowerCase();
			System.out.println("____________________________________________________________");
			if (!playAgainChoice.equals("yes")) {
				playAgain = false;
			}
		}
	}

	public void run() {
		dealer.shuffle();
		dealer.dealCard(player); // 1st card - face up
		dealer.dealCardFaceDown(dealer); // 2nd card - face down
		dealer.dealCard(player); // 3rd card - face up
		dealer.dealCard(dealer); // 4th card - face up
		displayHands();
		playerTurn();
		if (player.mayBust() || dealer.mayBust() || player.mayBlackjack() || dealer.mayBlackjack()) {
			return;
		}
		dealer.dealerHand();
		System.out.println("Dealer's hand: " + dealer.getHand());
		System.out.println("Your hand: " + player.getHand());
		determineWinner();

	}

	private void displayHands() {
		System.out.println("Your hand: " + player.getHand());
		List<Card> dealerCards = dealer.getHand().getCards();
		System.out.println("Dealer's hand: " + dealerCards.get(1) + " and face down card.");
	}

	private void playerTurn() {
		while (true) {
			System.out.println("Your turn: ");
			System.out.print("Do you want to Hit or Stand? ");
			String choice = scanner.nextLine().toLowerCase();

			if (choice.equals("hit")) {
				dealer.dealCard(player);
				System.out.println("Your hand: " + player.getHand());

				if (player.mayBust()) {
					System.out.println("Dealers hand: " + dealer.getHand());
					System.out.println("You busted! Your hand total is over 21.");

					break;
				}
			} else if (choice.equals("stand")) {
				break;
			} else {
				System.out.println("Invalid choice. Please enter Hit or Stand.");
			}

			if (dealer.mayBust()) {
				System.out.println("Dealer busted!");
				break;
			}

			if (player.mayBlackjack()) {
				System.out.println("Dealers hand: " + dealer.getHand());
				System.out.println("You hit Blackjack! You win!");

				break;
			}

			if (dealer.mayBlackjack()) {
				System.out.println("Dealer hit Blackjack! Dealer wins!");

				break;
			}
		}
	}

	private void determineWinner() {
		int playerTotal = player.getHandValue();
		int dealerTotal = dealer.getHandValue();

		if (player.mayBust()) {
			System.out.println("Dealer wins! You busted.");
		} else if (dealer.mayBust()) {
			System.out.println("You win! Dealer busted.");
		} else if (player.mayBlackjack()) {
			System.out.println("You hit Blackjack! You win!");
		} else if (dealer.mayBlackjack()) {
			System.out.println("Dealer hit Blackjack! Dealer wins!");
		} else if (playerTotal > dealerTotal) {
			System.out.println("You win!");
		} else if (dealerTotal > playerTotal) {
			System.out.println("Dealer wins!");
		} else {
			System.out.println("It's a tie!");
		}
	}
}
