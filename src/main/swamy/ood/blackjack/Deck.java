package main.swamy.ood.blackjack;

public class Deck {
	
	//Array of 52 cards
	private Card[] deck;
	//How many cards have been dealt from the deck
	private int cardsUsed;
	
	public Deck() {
		//Create an unshuffled deck of cards
		deck = new Card[52];
		//how many cards have been created so far.
		int cardCt = 0;
		for(int suit = 0; suit <= 3;suit++) {
			for(int value = 1; value <= 13; value++) {
				deck[cardCt] = new Card(value, suit);
				cardCt++;
			}
		}
		cardsUsed = 0;
	}
	
	public void shuffle() {
		//put all the used cards back into the deck,
		//and shuffle it into a random number
		for(int i = 51; i > 0; i--) {
			int rand = (int)(Math.random() * (i+1));
			Card temp = deck[rand];
			deck[i] = deck[rand];
			deck[rand] = temp;
		}
		cardsUsed = 0;
	}
	
	public int cardsLeft() {
		//Number of cards still left in deck
		return 52 - cardsUsed;
	}
	
	public Card dealCard() {
		if(cardsUsed == 52) 
			shuffle();
			cardsUsed++;
			return deck[cardsUsed - 1];
	}

}//end class Deck
