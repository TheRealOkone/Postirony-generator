package partOfSpeech;


import java.util.*;

public abstract class Word {
    private Set<String> goodRequests = new HashSet<>();
    private Set<String> badRequests = new HashSet<>();
    private Set<String> goodWords = new HashSet<>();
    private Set<String> badWords = new HashSet<>();
    private String firstWord;
    int variant = 0;
    List<Integer> variantsToCheck = new ArrayList<>();
    String word;

    Word(Set<String> goodRequests, Set<String> badRequests, Set<String> goodWords, Set<String> badWords, String word, int variant) {
        this.goodRequests.addAll(goodRequests);
        this.badRequests.addAll(badRequests);
        this.goodWords.addAll(goodWords);
        this.badWords.addAll(badWords);
        firstWord = word;
        this.variant = variant;
    }

    public void deleteVariant(int variant) {
        System.out.println("eeee " + variant);
        for (int i = 0; i < variantsToCheck.size(); i++) {
            if (variantsToCheck.get(i) == variant) {
                variantsToCheck.remove(i);
                break;
            }
        }
    }

    Word(String word) {
        firstWord = word;
    }

    public int getVariant() {
        return variant;
    }

    public void setVariant(int type) {
        this.variant = type;
    }

    public boolean variantIsReady() {
        return variant != 0;
    }

    public int sizeTrialVariants() {
        return variantsToCheck.size();
    }

    public String getFirstWord() {
        return firstWord;
    }


    public int checkRequest(String request) {
        if (goodRequests.contains(request))
            return 1;
        else if (badRequests.contains(request))
            return 0;
        else
            return 2;
    }

    public int checkWord(String word) {
        if (goodWords.contains(word))
            return 1;
        else if (badWords.contains(word))
            return 0;
        else
            return 2;
    }

    public Set<String> getGoodRequests() {
        return goodRequests;
    }

    public Set<String> getBadRequests() {
        return badRequests;
    }

    public Set<String> getGoodWords() {
        return goodWords;
    }

    public Set<String> getBadWords() {
        return badWords;
    }

    public void putWord(String word, boolean pozitive) {
        if (pozitive)
            goodWords.add(word);
        else
            badWords.add(word);
    }

    public void putRequest(String request, boolean pozitive) {
        if (pozitive)
            goodRequests.add(request);
        else
            badWords.add(request);
    }

    public static List<Integer> getType(List<String[]> list, String word) {
        char[] chars = word.toCharArray();
        String end1 = Character.toString(chars[chars.length - 1]);
        String end2 = Character.toString(chars[chars.length - 2]) + Character.toString(chars[chars.length - 1]);
        String end3 = Character.toString(chars[chars.length - 3]) + Character.toString(chars[chars.length - 2]) + Character.toString(chars[chars.length - 1]);
        int[] types = new int[list.size()];
        List<Integer> listOfTypes = new ArrayList<>();
        List<Integer> spaceTypes = new ArrayList<>();
        int length = 0;
        for (int i = 0; i < list.size(); i++) {
            for (String ending : list.get(i)) {
                if (ending.equals(""))
                    spaceTypes.add(i + 1);
                if (end3.equals(ending)) {
                    types[i] = 3;
                    break;
                } else if (end2.equals(ending))
                    types[i] = 2;
                else if (end1.equals(ending) && types[i] != 2)
                    types[i] = 1;
            }
            if (types[i] > length) {
                length = types[i];
                listOfTypes = new ArrayList<>();
                listOfTypes.add(i + 1);
            } else if (types[i] == length && length != 0)
                listOfTypes.add(i + 1);
        }
        if (listOfTypes.size() == 0) {
            spaceTypes.add(0);
            return spaceTypes;
        }
        listOfTypes.add(length);
        return listOfTypes;
    }

    static String[] combiningArrays(String[] firstArray, String[] secondArray) {
        String[] result = new String[firstArray.length + secondArray.length];
        int i = 0;
        for (; i < firstArray.length; i++)
            result[i] = firstArray[i];
        for (int j = 0; j < secondArray.length; j++) {
            result[i] = secondArray[j];
            i++;
        }
        return result;
    }

    static boolean isVowel(char sumbol) {
        return sumbol == 'а' || sumbol == 'о' || sumbol == 'у' || sumbol == 'э' || sumbol == 'ы' || sumbol == 'я' || sumbol == 'е' || sumbol == 'ю' || sumbol == 'и';
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (String string : getGoodRequests()) {
            builder.append(string);
            builder.append("#");
        }
        if (getGoodRequests().size() != 0)
            builder = new StringBuilder(builder.substring(0, builder.length() - 1));
        else
            builder.append("^");
        builder.append("&");
        for (String string : getBadRequests()) {
            builder.append(string);
            builder.append("#");
        }
        if (getBadRequests().size() != 0)
            builder = new StringBuilder(builder.substring(0, builder.length() - 1));
        else
            builder.append("^");
        builder.append("&");
        for (String string : getGoodWords()) {
            builder.append(string);
            builder.append("#");
        }
        if (getGoodWords().size() != 0)
            builder = new StringBuilder(builder.substring(0, builder.length() - 1));
        else
            builder.append("^");
        builder.append("&");
        for (String string : getBadWords()) {
            builder.append(string);
            builder.append("#");
        }
        if (getBadWords().size() != 0)
            builder = new StringBuilder(builder.substring(0, builder.length() - 1));
        else
            builder.append("^");
        builder.append("&").append(firstWord);
        return builder.toString();
    }

    public String getWord() {
        return word;
    }
}