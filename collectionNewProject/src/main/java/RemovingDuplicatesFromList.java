import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class RemovingDuplicatesFromList {
    public List<Integer> removeDuplicates(List<Integer> numbers) {
        Set<Integer> uniqueNumbersSet = new LinkedHashSet<>(numbers);
        return new ArrayList<>(uniqueNumbersSet);
    }
}
