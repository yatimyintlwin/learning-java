import java.util.*;

public class GroupingWordsByLength {
    public Map<Integer, Set<String>> groupingWords(List<String> words) {
        Map<Integer, Set<String>> groupedWords = new HashMap<>();

        for (String word : words) {
            int length = word.length();

            if (!groupedWords.containsKey(length)) {
                groupedWords.put(length, new HashSet<>());
            }
            groupedWords.get(length).add(word);
        }
        return groupedWords;
    }
}
