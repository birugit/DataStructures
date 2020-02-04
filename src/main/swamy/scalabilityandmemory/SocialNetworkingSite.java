package main.swamy.scalabilityandmemory;
/**
 * How would you design the data structures for a very large social network like Facebook or Linked/n? 
 * Describe how you would design an algorithm to show the connection, or path, 
 * between two people (e.g., Me -> Bob -> Susan -> Jason -> You).
 * @author swamy
 *
 */
/*
 * 1)Consider each Person as a Node
 * 2) Do Breadth First Search to find the edge(connection)
 * 3)Now think about millions of users, 
 * 		Friends may not live on same machine, 
 *        Give each person a ID, 
 *        Find the machine ID  for PersonID
 *        Go to machine with machineID
 *        Find the Person with PersonID
 *          Take Server class with Machines
 *          Person class
 *          
 *     //optimization
 *     		Reduce machine jumps --> Instead of random jumps from machine to machine , search for groups of frinds on same achine
 *     		Smart Division of People and Machines --> mostly frineds live in same country, state, city,,,. 
 *     				so divide this way can reduce machine jumps.
 *     		BFS marks visted node --> instead of marking our node with visited, editing data while doing multiple searchs is a bad.
 *     			instead mimic the marking of nodes with hashtable to lookup node id and whether or not been visited.
 *     
 *     more..
 *     
 *     If server fails, how does it affect you ?
 *     how do you take advantage of caching?
 *     Do you search until end of graph(infinete)? how do you end up?
 *     
 *     
 */
public class SocialNetworkingSite {

	public static void main(String[] args) {
		

	}

}
