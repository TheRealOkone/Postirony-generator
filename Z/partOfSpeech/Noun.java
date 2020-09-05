package partOfSpeech;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Noun extends TrialVariant {
    private String[] endingesForFirstDeclinationFirstTypeSingle = {"а", "ы", "е", "у", "ой", "е"};
    private String[] endingesForFirstDeclinationFirstTypeMultiple = {"ы", "", "ам", "ы", "ами", "ах"};
    private String[] endingesForFirstDeclinationSecondTypeSingle = {"я", "и", "е", "ю", "ей", "е"};
    private String[] endingesForFirstDeclinationSecondTypeMultiple = {"и", "", "ям", "и", "ями", "ях"};
    private String[] endingesForSecondDeclinationFirstTypeSingle = {"", "а", "у", "", "ом", "е"};
    private String[] endingesForSecondDeclinationSecondTypeSingle = {"о", "а", "у", "о", "ом", "е"};
    private String[] endingesForSecondDeclinationThirdTypeSingle = {"е", "я", "ю", "е", "ем", "е"};
    private String[] endingesForThirdDeclinationSingle = {"", "и", "и", "", "ю", "и"};
    private String[] endingesForThirdDeclinationMultiple = {"и", "ей", "ям", "и", "ями", "ях"};
    private String[] endingesForSecondDeclinationMultiple = {"ы", "и", "а", "я", "ов", "ев", "ей", "ам", "ям", "ы", "и", "а", "я", "ов", "ев", "ев", "ей", "ами", "ями", "ах", "ях"};
    private boolean male = false;
    private boolean female = false;
    private boolean middle = false;
    private String kind;

    public Noun(String word) {
        super(word);
        List<String[]> list = new ArrayList<>();
        list.add(Word.combiningArrays(endingesForFirstDeclinationFirstTypeSingle, endingesForFirstDeclinationFirstTypeMultiple));
        list.add(Word.combiningArrays(endingesForFirstDeclinationSecondTypeSingle, endingesForFirstDeclinationSecondTypeMultiple));
        list.add(Word.combiningArrays(endingesForSecondDeclinationFirstTypeSingle, endingesForSecondDeclinationMultiple));
        list.add(Word.combiningArrays(endingesForSecondDeclinationSecondTypeSingle, endingesForSecondDeclinationMultiple));
        list.add(Word.combiningArrays(endingesForSecondDeclinationThirdTypeSingle, endingesForSecondDeclinationMultiple));
        list.add(Word.combiningArrays(endingesForThirdDeclinationSingle, endingesForThirdDeclinationMultiple));
        List<Integer> elements = Word.getType(list, word);
        this.word = word.substring(0, word.length() - elements.get(elements.size() - 1));
        if (elements.size() == 2) {
            variant = elements.get(0);
            initializeKind();
        } else if (elements.size() != 1){
            elements.remove(elements.size() - 1);
            variantsToCheck.addAll(elements);
        }
    }

    public Noun(Set<String> goodRequests, Set<String> badRequests, Set<String> goodWords, Set<String> badWords, String word, int variant, String kind, boolean male, boolean female, boolean middle, List<Integer> variantsToCheck, String firstWord) {
        super(goodRequests, badRequests, goodWords, badWords, firstWord, variant);
        this.word = word;
        this.kind = kind;
        this.male = male;
        this.female = female;
        this.middle = middle;
        this.variantsToCheck = variantsToCheck;
    }

    @Override
    public void setVariant(int type) {
        super.setVariant(type);
        initializeKind();
    }
    public void setKind(String kind, boolean good)  {
        if (good) {
            this.kind = kind;
        } else
            switch (kind) {
                case "мужской" :
                    male = true;
                    break;
                case "женский" :
                    female = true;
                    break;
                case "средний" :
                    middle = true;
            }
    }

    private void initializeKind() {
        if (variant == 6) {
            kind = "женский";
        } else if (variant == 1 || variant == 2)
            middle = true;
        else if (variant == 3 || variant == 4 || variant == 5)
            female = true;
    }

    public String getKind() {
        return kind;
    }

    public boolean kindIsReady() {
        return kind != null;
    }

    public String trialKind() {
        if (kind != null)
            return "нет";
        if (!male) {
            return "мужской";
        }
        if (!female) {
            return "женский";
        }
        if (!middle) {
            return "средний";
        }
        kind = "нет";
        return "нет";
    }

    public String getNewWord(boolean plural, String mortality) throws Exception {
        if (!variantIsReady() || (plural && (variant == 3 || variant == 4 || variant == 5)))
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
        switch (variant) {
            case 1:
                if (plural)
                    return word + endingesForFirstDeclinationFirstTypeMultiple[position];
                else
                    return word + endingesForFirstDeclinationFirstTypeSingle[position];
            case 2:
                if (plural)
                    return word + endingesForFirstDeclinationSecondTypeMultiple[position];
                else
                    return word + endingesForFirstDeclinationSecondTypeSingle[position];
            case 3:
                return word + endingesForSecondDeclinationFirstTypeSingle[position];
            case 4:
                return word + endingesForSecondDeclinationSecondTypeSingle[position];
            case 5:
                return word + endingesForSecondDeclinationThirdTypeSingle[position];
            case 6:
                if (plural)
                    return word + endingesForThirdDeclinationMultiple[position];
                else
                    return word + endingesForThirdDeclinationSingle[position];
            default:
                throw new Exception();
        }
    }

    public String getNewWord(boolean plural, String mortality, int variant) throws Exception {
        int buf = this.variant;
        this.variant = variant;
        String result = getNewWord(plural, mortality);
        this.variant = buf;
        return result;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(word);
        builder.append("&");
        builder.append(super.toString());
        builder.append("&");
        builder.append(variant);
        builder.append("&");
        if (kind == null)
            builder.append("null");
        else
            builder.append(kind);
        builder.append("&");
        builder.append(male);
        builder.append("&");
        builder.append(female);
        builder.append("&");
        builder.append(middle);
        builder.append("&");
        for (Integer integer : variantsToCheck) {
            builder.append(integer);
            builder.append("#");
        }
        if (variantsToCheck.size() != 0)
            builder = new StringBuilder(builder.substring(0, builder.length() - 1));
        else
            builder.append("^");
        return builder.toString();
    }
}