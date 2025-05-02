package HashMapTest;

import java.util.Comparator;

class Sortbyroll implements Comparator<Person> 
{
  public int compare(Person a, Person b)
  {
    return a.age - b.age;
  }
} 