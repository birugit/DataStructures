package main.swamy.ood.blackjack;

//adds the method getBlackjackHand(), which return the value
//of the hand for the game of BlackJack.
public class BlackjackHand extends Hand {
	
	public int getBlackjackValue() {
		//Returns the vlaue of this hand for the 
		//game of Blackjack.
		
		int val;
		boolean ace;
		int cards;
		
		val = 0;
		ace = false;
		cards = getCardCount();
		
		for(int i=0; i< cards; i++) {
			//add the value of the i-th card in the hand
			Card card;
			int cardVal;
			card = getCard(i);
			cardVal = card.getValue();//normal value 1 to 13
			if(cardVal > 10) {
				cardVal = 10;// for a jack, queen , or king
			}
			if(cardVal == 1) {
				ace = true;
			}
			val = val + cardVal;
			
		}
		//Now, val is the value of the hand, counting any ace as 1.
		//if there is an ace, and if changing its value from 1 to
		//11 would leave the score less than or equal to 21,
		//then  do so by adding the extra 10 points to val.
		
		if(ace == true && val + 10 <= 21)
			val = val +10;
			
			return val;
	}
	

}
