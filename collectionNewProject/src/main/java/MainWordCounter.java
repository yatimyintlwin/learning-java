import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MainWordCounter {
    public static void main(String[] args) {
        WordCounter wordCounter = new WordCounter();
        List<String> words = Arrays.asList("apple", "banana", "apple", "orange", "banana", "banana");
        Map<String, Integer> result = wordCounter.countingWordFromList(words);

        System.out.println("Counting Word result: " + result);
    }
}
