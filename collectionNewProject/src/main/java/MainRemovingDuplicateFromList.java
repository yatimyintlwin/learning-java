import java.util.List;

public class MainRemovingDuplicateFromList {
    public static void main(String[] args) {
        RemovingDuplicatesFromList remover = new RemovingDuplicatesFromList();
        List<Integer> numbers = List.of(1, 2, 2, 3, 4, 4, 5);
        List<Integer> result = remover.removeDuplicates(numbers);

        System.out.println("Unique Numbers List are: " + result);
        System.out.println("List size is: " + result.size());
    }
}
