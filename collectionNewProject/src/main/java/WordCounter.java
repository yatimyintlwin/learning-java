import java.util.List;
import java.util.Map;

public class WordCounter {
    public Map<String, Integer> countingWordFromList(List<String> words) {
        Map<String, Integer> wordCounts = new java.util.HashMap<>();

        for (String word : words) {
            if (wordCounts.containsKey(word)) {
                wordCounts.put(word, wordCounts.get(word) + 1);
            } else {
                wordCounts.put(word, 1);
            }
        }

        return wordCounts;
    }
}
