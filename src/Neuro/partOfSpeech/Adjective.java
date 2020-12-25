package Neuro.partOfSpeech;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Adjective extends TrialVariant{
    private String[] endingesForMale1 = {"ый", "ого", "ому", "ого", "ым", "ом"};
    private String[] endingesForFemale1 = {"ая", "ой", "ой", "ую", "ой", "ой"};
    private String[] endingesForMiddle1 = {"ое", "ого", "ому", "ое", "ым", "ом"};
    private String[] endingesForMultiple1 = {"ые", "ых", "ым", "ых", "ыми", "ых"};
    private String[] endingesForMale2 = {"ий", "его", "ему", "его", "им", "ем"};
    private String[] endingesForFemale2 = {"яя", "ей", "ей", "юю", "ей", "ей"};
    private String[] endingesForMiddle2 = {"ее", "его", "ему", "ее", "им", "ем"};
    private String[] endingesForMultiple2 = {"ие", "их", "им", "их", "ими", "их"};

    public Adjective(String word) {
        super(word);
        List<String[]> list = new ArrayList<>();
        list.add(Word.combiningArrays(Word.combiningArrays(Word.combiningArrays(endingesForMale1, endingesForFemale1), endingesForMiddle1), endingesForMultiple1));
        list.add(Word.combiningArrays(Word.combiningArrays(Word.combiningArrays(endingesForMale2, endingesForFemale2), endingesForMiddle2), endingesForMultiple2));
        List<Integer> elements = Word.getType(list, word);
        int endingLength = elements.get(elements.size() - 1);
        elements.remove(elements.size() - 1);
        this.word = word.substring(0, word.length() - endingLength);
        if (elements.size() > 1) {
            variantsToCheck.addAll(elements);
        }
        else if (elements.size() == 1)
            variant = elements.get(0);
    }

    public Adjective(Set<String> goodRequests, Set<String> badRequests, Set<String> goodWords, Set<String> badWords, String word, int variant, String firstWord) {
        super(goodRequests, badRequests, goodWords, badWords, firstWord, variant);
        this.word = word;
    }

    public String getNewWord(String mortality, String kind) throws Exception {
        if (variant == 0)
            throw new Exception();
        mortality = mortality.toLowerCase();
        int position;
        switch (mortality) {
            case "именительный":
                position = 0;
                break;
            case "родительный":
                position = 1;
                break;
            case "дательный":
                position = 2;
                break;
            case "винительный":
                position = 3;
                break;
            case "творительный":
                position = 4;
                break;
            case "предложный":
                position = 5;
                break;
            default:
                throw new Exception();
        }
        kind = kind.toLowerCase();
        switch (kind) {
            case "мужской":
                if (variant == 1)
                    return word + endingesForMale1[position];
                else
                    return word + endingesForMale2[position];
            case "средний":
                if (variant== 1)
                    return word + endingesForMiddle1[position];
                else
                    return word + endingesForMiddle2[position];
            case "женский":
                if (variant == 1)
                    return word + endingesForFemale1[position];
                else
                    return word + endingesForFemale2[position];
            case "множественный":
                if (variant == 1)
                    return word + endingesForMultiple1[position];
                else
                    return word + endingesForMultiple2[position];
            default:
                throw new Exception();
        }
    }

    public String getNewWord(int variant, String mortality, String kind) throws Exception {
        int realType = this.variant;
        this.variant = variant;
        String result = getNewWord(mortality, kind);
        this.variant = realType;
        return result;
    }

    public String toString() {
        return word +
                "&" +
                super.toString() +
                "&" +
                variant;
    }
}