import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

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
        Map<String, Integer> countMap = new HashMap<>();
        for (String word : words) {
            countMap.put(word, countMap.getOrDefault(word, 0) + 1);
        }
        List<WordFrequency> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            result.add(new WordFrequency(entry.getKey(), entry.getValue()));
        }
        return result;
    }


    // 按出现次数降序排序
    private List<WordFrequency> sortByFrequency(List<WordFrequency> list) {
        list.sort((w1, w2) -> Integer.compare(w2.getWordCount(), w1.getWordCount()));
        return list;
    }
    



    private Map<String,List<WordFrequency>> getListMap(List<WordFrequency> wordFrequencyList) {
        Map<String, List<WordFrequency>> map = new HashMap<>();
        for (WordFrequency wordFrequency : wordFrequencyList){
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(wordFrequency.getWord())){
                ArrayList arr = new ArrayList<>();
                arr.add(wordFrequency);
                map.put(wordFrequency.getWord(), arr);
            }

            else {
                map.get(wordFrequency.getWord()).add(wordFrequency);
            }
        }


        return map;
    }



