package HashMapTest;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Main {
	public static void main(String[] args) {

		// Create a HashMap object called people
		Map<Person, Integer> people = new HashMap<Person, Integer>();

//		hashcode
		Person p = new Person("a", 1,"aa");
		Person p2 = p;

		if(p == p2) {
			System.out.println("== true");
		}
		if (p.equals(p2)) {
			System.out.println("equals true");
		}
		
		// Add keys and values (Name, Age)
		people.put(new Person("a", 1,"aa"), 1);
		people.put(new Person("a", 1,"aa"), 2);

		people.put(new Person("b", 1, "bb"), 3);
		people.put(new Person("c", 1, "cc"), 4);

		System.out.println("people size" + people.size());

//		Map<Person, Integer> peopleMap = new TreeMap<Person, Integer>(new Sortbyroll());
//
//		// Add keys and values (Name, Age)
//		peopleMap.put(new Person("a", 1,"aa"), 1);
//		peopleMap.put(new Person("a", 2,"aa"), 1);
//
//		peopleMap.put(new Person("b", 3, "bb"), 3);
//		peopleMap.put(new Person("c", 4, "cc"), 4);
//
//		System.out.println("people size" + peopleMap.size());
	}
}