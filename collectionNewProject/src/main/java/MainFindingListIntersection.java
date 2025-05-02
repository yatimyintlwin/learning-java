import java.util.Arrays;
import java.util.List;

public class MainFindingListIntersection {
    public static void main(String[] args) {
        FindingListIntersection finder = new FindingListIntersection();
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list2 = Arrays.asList(3, 4, 5, 6, 7);
        List<Integer> result = finder.findIntersection(list1, list2);

        System.out.println("Intersection of two lists: " + result);
    }
}
