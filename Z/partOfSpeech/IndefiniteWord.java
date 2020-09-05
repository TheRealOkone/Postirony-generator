package partOfSpeech;

import java.util.ArrayDeque;
import java.util.Queue;

public class IndefiniteWord {
    private String word;
    private Queue<String> partOfSpeech = new ArrayDeque<>();

    public String getWord() {
        return word;
    }

    public IndefiniteWord(String word, Queue<String> partOfSpeech) {
        this.word = word;
        this.partOfSpeech = partOfSpeech;
    }

    public String toString() {
        StringBuilder result = new StringBuilder(word + "&");
        for (String speech : partOfSpeech)  {
            result.append(speech).append("#");
        }
        if (partOfSpeech.size() > 0)
            result = new StringBuilder(result.substring(0, result.length() - 1));
        else
            result.append("^");
        return result.toString();
    }
}