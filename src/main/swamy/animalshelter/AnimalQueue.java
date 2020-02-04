package main.swamy.animalshelter;
/**
 * An animal shelter holds only dogs and cats, and operates on a strictly "first in, first out" basis. 
 * People must adopt either the "oldest" (based on arrival time) of all animals at the shelter, 
 * or they can select whether they would prefer a dog or a cat (and will receive the oldest animal of that type).
 *  They cannot select which specific animal they would like. 
 *  Create the data structures to maintain this system and 
 *  implement operations such as enqueue, dequeueAny, dequeueDog and dequeueCat. 
 *  You may use the built-in Linked List data structure.
 * 
 */
import java.util.LinkedList;

public class AnimalQueue {
	
	LinkedList<Dog> dogs = new LinkedList<Dog>();
	LinkedList<Cat> cats = new LinkedList<Cat>();
	private int order = 0;//acts as timestamp

	public AnimalQueue() {
		
	}
	
	public void enque(Animal a){
		
		a.setOrder(order);
		order++;
		
		if(a instanceof Dog){
			dogs.addLast((Dog) a);
		}else if(a instanceof Cat){
			cats.addLast((Cat)a);
		}
	}

	
	public Animal dequeAny(){
		if(dogs.size() == 0){
			return dequeCat();
		}else if(cats.size() == 0){
			return dequeDog();
		}
		Dog dog = dogs.peek();
		Cat cat = cats.peek();
		if(dog.isOlderThan(cat)){
			return dequeDog();
		}else{
			return dequeCat();
		}
	}
	
	private Animal dequeCat() {
		
		return cats.poll();
	}

	private Animal dequeDog() {
		
		return dogs.poll();
	}

	public static void main(String[] args) {

		AnimalQueue aq = new AnimalQueue();
		aq.enque(new Dog("Dog"));
		aq.enque(new Dog("Dog"));
		aq.enque(new Cat("Cat"));
		aq.enque(new Dog("Dog"));
		aq.enque(new Cat("Cat"));
		Animal a = aq.dequeAny();
		System.out.println("Animal:"+a);
		a = aq.dequeAny();
		System.out.println("Animal:"+a);
		a = aq.dequeAny();
		System.out.println("Animal:"+a);
		a = aq.dequeCat();
		//a = aq.dequeAny();
		System.out.println("Animal:"+a);
		a = aq.dequeAny();
		System.out.println("Animal:"+a);
	}

}
