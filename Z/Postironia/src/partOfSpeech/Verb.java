package partOfSpeech;

import java.util.*;

public class Verb extends Word {
    private String[] endingesForFirstConjugationFirstTypeSingle = {"у", "ешь", "ет"};
    private String[] endingesForFirstConjugationFirstTypeMultiple = {"ем", "ете", "ут"};
    private String[] endingesForFirstConjugationSecondTypeSingle = {"ю", "ешь", "ет"};
    private String[] endingesForFirstConjugationSecondTypeMultiple = {"ем", "ете", "ют"};
    private String[] endingesForSecondConjugationFirstTypeSingle = {"у", "ишь", "ит"};
    private String[] endingesForSecondConjugationFirstTypeMultiple = {"им", "ите", "ат"};
    private String[] endingesForSecondConjugationSecondTypeSingle = {"ю", "ишь", "ит"};
    private String[] endingesForSecondConjugationSecondTypeMultiple = {"им", "ите", "ят"};
    private String[] endingesForPast = {"л", "ла", "ло", "ли"};
    private String[] endingesForInfinitive = {"ть"};
    private Queue<Character> trialChars = new ArrayDeque<>();
    private Character charToPast = null;

    public Verb(String word) {
        super(word);
        List<String[]> list = new ArrayList<>();
        list.add(Word.combiningArrays(endingesForFirstConjugationFirstTypeSingle, endingesForFirstConjugationFirstTypeMultiple));
        list.add(Word.combiningArrays(endingesForFirstConjugationSecondTypeSingle, endingesForFirstConjugationSecondTypeMultiple));
        list.add(Word.combiningArrays(endingesForSecondConjugationFirstTypeSingle, endingesForSecondConjugationFirstTypeMultiple));
        list.add(Word.combiningArrays(endingesForSecondConjugationSecondTypeSingle, endingesForSecondConjugationSecondTypeMultiple));
        list.add(Word.combiningArrays(endingesForInfinitive, endingesForPast));
        List<Integer> result = Word.getType(list, word);
        this.word = word.substring(0, word.length() - result.get(result.size() - 1));
        result.remove(result.size() - 1);
        if (!Word.isVowel(this.word.toCharArray()[this.word.toCharArray().length - 1])) {
            trialChars.offer('а');
            trialChars.offer('я');
            trialChars.offer('ю');
            trialChars.offer('у');
            trialChars.offer('е');
            trialChars.offer('и');
        }
        if (result.size() == 1) {
            if (result.get(0) == 5) {
                variantsToCheck.add(1);
                variantsToCheck.add(2);
                variantsToCheck.add(3);
                variantsToCheck.add(4);
            } else
                variant = result.get(0);
        } else if (result.size() != 0) {
            variantsToCheck.addAll(result);
        }
    }

    public Verb(Set<String> goodRequests, Set<String> badRequests, Set<String> goodWords, Set<String> badWords, String word, int variant, List<Integer> variantsToCheck, Queue<Character> trialChars, Character charToPast, String firstWord) {
        super(goodRequests, badRequests, goodWords, badWords, firstWord, variant);
        this.word = word;
        this.variantsToCheck = variantsToCheck;
        this.trialChars = trialChars;
        this.charToPast = charToPast;
    }

    public String getTrialPast(String kind) throws Exception {
        if (trialChars.size() == 0)
            return null;
        this.charToPast = trialChars.peek();
        String result = getPast(kind);
        this.charToPast = null;
        return result;
    }

    public void deletePast() {
        trialChars.poll();
    }

    public void setCharToPast(String string) {
        if (string.toCharArray()[string.length() - 1] == 'л')
            string = string.substring(0, string.length() - 1);
        else
            string = string.substring(0, string.length() - 1);
        charToPast = string.toCharArray()[string.length() - 1];
    }

    public boolean withOutPast() {
        System.out.println(trialChars.size() == 0);
        System.out.println(!pastIsReady() && trialChars.size() == 0);
        return !pastIsReady() && trialChars.size() == 0;
    }

    public boolean pastIsReady() {
        return Word.isVowel(word.toCharArray()[word.toCharArray().length - 1]) || charToPast != null;
    }

    public String getPast(String kind) throws Exception {
        int position;
        switch (kind) {
            case "мужской":
                position = 0;
                break;
            case "средний":
                position = 2;
                break;
            case "женский":
                position = 1;
                break;
            case "множественный":
                position = 3;
                break;
            default:
                throw new Exception();
        }
        return word + endingesForPast[position];
    }

    public String getNewWord(boolean plural, int face) throws Exception {
        if (variant == 0)
            throw new Exception();
        switch (variant) {
            case 1:
                if (plural)
                    return word + endingesForFirstConjugationFirstTypeMultiple[face - 1];
                return word + endingesForFirstConjugationFirstTypeSingle[face - 1];
            case 2:
                if (plural)
                    return word + endingesForFirstConjugationSecondTypeMultiple[face - 1];
                return word + endingesForFirstConjugationSecondTypeSingle[face - 1];
            case 3:
                if (plural)
                    return word + endingesForSecondConjugationFirstTypeMultiple[face - 1];
                return word + endingesForSecondConjugationFirstTypeSingle[face - 1];
            case 4:
                if (plural)
                    return word + endingesForSecondConjugationSecondTypeMultiple[face - 1];
                else
                    return word + endingesForSecondConjugationSecondTypeSingle[face - 1];
            default:
                throw new Exception();
        }
    }

    public String getNewWord(boolean plural, int face, int trialVariant) throws Exception {
        int buf = this.variant;
        this.variant = trialVariant;
        String result = getNewWord(plural, face);
        this.variant = buf;
        return result;
    }

    public int[] trialVariant() {
        int[] result = new int[2];
        System.out.println("Варианты");
        System.out.println(variantsToCheck);
        if (variantsToCheck.size() == 0)
            return null;
        if (variantsToCheck.size() == 1) {
            result[0] = variantsToCheck.get(0);
            result[1] = 1;
            return result;
        }
        for (int i = 0; i < variantsToCheck.size(); i++)
            for (int j = i + 1; j < variantsToCheck.size(); j++)
                if (variantsToCheck.get(i) == 1 && variantsToCheck.get(j) == 3 || variantsToCheck.get(j) == 1 && variantsToCheck.get(i) == 3) {
                    result[0] = 3;
                    result[1] = 2;
                    return result;
                } else if (variantsToCheck.get(i) == 2 && variantsToCheck.get(j) == 4 || variantsToCheck.get(j) == 2 && variantsToCheck.get(i) == 4) {
                    result[0] = 4;
                    result[1] = 2;
                    return result;
                } else if (variantsToCheck.get(i) == 1 && variantsToCheck.get(j) == 2 || variantsToCheck.get(j) == 1 && variantsToCheck.get(i) == 2) {
                    result[0] = 2;
                    result[1] = 1;
                    return result;
                } else if (variantsToCheck.get(i) == 3 && variantsToCheck.get(j) == 4 || variantsToCheck.get(j) == 3 && variantsToCheck.get(i) == 4) {
                    result[0] = 4;
                    result[1] = 1;
                    return result;
                }
        result[0] = variantsToCheck.get(0);
        result[1] = 1;
        return result;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(word);
        builder.append("&");
        builder.append(super.toString());
        builder.append("&");
        if (charToPast == null)
            builder.append("null");
        else
            builder.append(charToPast);
        builder.append("&");
        for (Character character : trialChars) {
            builder.append(character);
            builder.append("#");
        }
        if (trialChars.size() != 0)
            builder = new StringBuilder(builder.substring(0, builder.length() - 1));
        else
            builder.append("^");
        builder.append("&");
        builder.append(variant);
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