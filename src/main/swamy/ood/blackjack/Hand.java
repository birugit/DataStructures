package main.swamy.ood.blackjack;

import java.util.Vector;

/*
 * Maximum number of cards in hand, by default 5.
 * 
 */
public class Hand {
	
	private Vector hand; //The cards in the hand
	
	public Hand() {
		hand = new Vector();
	}
	
	public void clear() {
		//discard all the cards from the hand
		hand.removeAllElements();		
	}
	
	public void addCard(Card c) {
		//Add the card c to the hand. c should be non-null
		if(c != null)
			hand.addElement(c);
	}
	
	public void removeCard(Card c) {
		//if the specified card is in the hand , it is removed
		hand.removeElement(c);
	}
	
	public void removeCard(int position) {
		//if the specified position is a valid position in the hand,
		//then the card in that position is removed.
		if(position >= 0 && position < hand.size())
			hand.removeElementAt(position);
	}
	
	public Card getCard(int position) {
		//Get the card from the hand in given position,
		//where positions are numbered starting from 0.
		//if the specified position is not the position number of a card  inthe hand,
		//then null is returned
		
		if(position >= 0 && position < hand.size())
			return (Card)hand.elementAt(position);
		else
			return null;
	}
	
	public void sortBySuit() {
		//Sorts the card in the hand
		Vector newHand = new Vector();
		while(hand.size() > 0) {
			int pos = 0;//position of minimal card.
			Card c = (Card) hand.elementAt(0);//Minimal card
			for(int i = 1; i < hand.size(); i++) {
				Card c1 = (Card) hand.elementAt(i);
					if(c1.getSuit() < c.getSuit() ||
					(c1.getSuit() == c.getSuit() && c1.getValue() < c.getValue())){
						pos = i;
						c = c1;
					}
			}
			hand.removeElementAt(pos);
			newHand.addElement(c);
			
		}
		hand = newHand;
	}
	
	public void sortByValue() {
		
	}
}
