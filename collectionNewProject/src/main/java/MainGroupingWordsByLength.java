import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainGroupingWordsByLength {
    public static void main(String[] args) {
        GroupingWordsByLength wordsGroupByLength = new GroupingWordsByLength();
        List<String> words = Arrays.asList("cat", "dog", "elephant", "bat", "fish", "ant");
        Map<Integer, Set<String>> result = wordsGroupByLength.groupingWords(words);

        System.out.println(result);
    }
}
