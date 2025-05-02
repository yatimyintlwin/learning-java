import java.util.HashMap;
import java.util.Map;

public class MainStudent {
    public static void main(String[] args) {
        Map<Integer, Student> studentGrades = new HashMap<>();
        studentGrades.put(101, new Student("Alice", "A"));
        studentGrades.put(102, new Student("Bob", "B"));
        studentGrades.put(103, new Student("Charlie", "A"));

        System.out.println("Alice's grade: " + studentGrades.get(101).getGrade());
        System.out.println("Bob's grade: " + studentGrades.get(102).getGrade());
        System.out.println("Charlie's grade: " + studentGrades.get(103).getGrade());
    }
}
