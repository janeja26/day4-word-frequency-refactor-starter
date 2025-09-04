import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    public String getResult(String inputStr){

        try {
            String[] words = splitInput(inputStr);
            List<WordFrequency> countedList = countWords(words);
            List<WordFrequency> sortedList = sortByFrequency(countedList);
            return formatResult(sortedList);
        } catch (Exception e) {
            return "Calculate Error";
        }
    }


    private String[] splitInput(String inputStr) {
        return inputStr.split("\\s+");
    }


    private List<WordFrequency> countWords(String[] words) {
        return Arrays.stream(words)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))  // 按单词分组统计
                .entrySet().stream()
                .map(entry -> new WordFrequency(entry.getKey(), entry.getValue().intValue()))  // 转换为WordFrequency
                .collect(Collectors.toList());
    }




    private List<WordFrequency> sortByFrequency(List<WordFrequency> list) {
        return list.stream()
                .sorted((w1, w2) -> Integer.compare(w2.getWordCount(), w1.getWordCount()))
                .collect(Collectors.toList());
    }


    private String formatResult(List<WordFrequency> list) {
        StringJoiner joiner = new StringJoiner("\n");
        for (WordFrequency w : list) {
            joiner.add(w.getWord() + " " + w.getWordCount());
        }
        return joiner.toString();
    }




    }



