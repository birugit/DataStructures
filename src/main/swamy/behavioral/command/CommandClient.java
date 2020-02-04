package main.swamy.behavioral.command;
/*
 * 
 * The Command pattern is known as a behavioural pattern,as it's used to manage algorithms,
 *  relationships and responsibilities between objects. 
 *  
 *  
 *  Encapsulate a request as an object, 
 *  thereby letting you parameterize clients with different requests, 
 *  queue or log requests, and support undoable operations
 */
public class CommandClient {

	public CommandClient() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Stock stock = new Stock();
		Broker broker = new Broker();
		
		BuyStock  buyStock = new BuyStock(stock);
		SellStock sellStock = new SellStock(stock);
		
		broker.takeOrder(buyStock);
		broker.takeOrder(sellStock);
		
		
		broker.placeorder();
				

	}

}
