import partOfSpeech.*;

import java.io.*;
import java.util.*;

class Generator {
    private File fileToVerbs = new File("Z\\verbs.txt");
    private File fileToNouns = new File("Z\\nouns.txt");
    private File fileToAdjectives = new File("Z\\adjectives.txt");
    private File fileToIndefiniteWords = new File("Z\\indefiniteWords.txt");
    private File fileToGarbage = new File("Z\\garbage.txt");
    private File fileToRequests = new File("Z\\requests.txt");
    private File fileToGenerator = new File("Z\\generator1.txt");
    private Set<Verb> verbs = new HashSet<>();
    private Set<Verb> verbsWithOutPast = new HashSet<>();
    private Set<Verb> verbsProblemPast = new HashSet<>();
    private Set<Verb> verbsProblemVariant = new HashSet<>();
    private Set<Noun> nouns = new HashSet<>();
    private Set<Noun> nounsProblemVariant = new HashSet<>();
    private Set<Noun> nounsProblemKind = new HashSet<>();
    private Set<Noun> nounsWithoutKind = new HashSet<>();
    private Set<Adjective> adjectives = new HashSet<>();
    private Set<Adjective> adjectivesProblemType = new HashSet<>();
    private Set<IndefiniteWord> indefiniteWords = new HashSet<>();
    private Set<String> garbage = new HashSet<>();
    private List<Request> requests = new ArrayList<>();
    private int savedNoun = -1;
    private int savedVerb = -1;
    private int savedAdjectives = -1;
    private int savedVerbPast = -1;
    private int savedNounKind = -1;

    Generator() {
        try {
            File dir = new File("Z");
            dir.mkdirs();
            fileToAdjectives.createNewFile();
            fileToNouns.createNewFile();
            fileToVerbs.createNewFile();
            fileToIndefiniteWords.createNewFile();
            fileToGarbage.createNewFile();
            fileToRequests.createNewFile();
            fileToGenerator.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileToVerbs), "UTF8"));
            String text = reader.readLine();
            while (text != null) {
                if (text.equals("") || text.equals(" ")) {
                    text = reader.readLine();
                    continue;
                }
                String[] parts = text.split("&");
                String word = parts[0];
                Set<String> goodRequests = new HashSet<>();
                if (!parts[1].equals("^"))
                    goodRequests.addAll(Arrays.asList(parts[1].split("#")));
                Set<String> badRequests = new HashSet<>();
                if (!parts[2].equals("^"))
                    badRequests.addAll(Arrays.asList(parts[2].split("#")));
                Set<String> goodWords = new HashSet<>();
                if (!parts[3].equals("^"))
                    goodWords.addAll(Arrays.asList(parts[3].split("#")));
                Set<String> badWords = new HashSet<>();
                if (!parts[4].equals("^"))
                    badWords.addAll(Arrays.asList(parts[4].split("#")));
                String firstWord = parts[5];
                Character charToPast = null;
                if (!parts[6].equals("null"))
                    charToPast = parts[6].toCharArray()[0];
                Queue<Character> trialChars = new ArrayDeque<>();
                if (!parts[7].equals("^"))
                    for (String trialChar : parts[7].split("#")) {
                        if (!trialChar.equals(""))
                            trialChars.offer(trialChar.toCharArray()[0]);
                    }
                int variant = Integer.parseInt(parts[8]);
                List<Integer> variantsToCheck = new ArrayList<>();
                if (!parts[9].equals("^"))
                    for (String trialVariant : parts[9].split("#")) {
                        if (!trialVariant.equals(""))
                            variantsToCheck.add(Integer.parseInt(trialVariant));
                    }
                Verb verb = new Verb(goodRequests, badRequests, goodWords, badWords, word, variant, variantsToCheck, trialChars, charToPast, firstWord);
                addToSet(verb);
                text = reader.readLine();
            }
            reader.close();
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileToNouns), "UTF8"));
            text = reader.readLine();
            while (text != null) {
                if (text.equals("") || text.equals(" ")) {
                    text = reader.readLine();
                    continue;
                }
                String[] parts = text.split("&");
                String word = parts[0];
                Set<String> goodRequests = new HashSet<>();
                if (!parts[1].equals("^"))
                    goodRequests.addAll(Arrays.asList(parts[1].split("#")));
                Set<String> badRequests = new HashSet<>();
                if (!parts[2].equals("^"))
                    badRequests.addAll(Arrays.asList(parts[2].split("#")));
                Set<String> goodWords = new HashSet<>();
                if (!parts[3].equals("^"))
                    goodWords.addAll(Arrays.asList(parts[3].split("#")));
                Set<String> badWords = new HashSet<>();
                if (!parts[4].equals("^"))
                    badWords.addAll(Arrays.asList(parts[4].split("#")));
                String firstWord = parts[5];
                int variant = Integer.parseInt(parts[6]);
                String kind = parts[7];
                if (kind.equals("null"))
                    kind = null;
                boolean male = Boolean.parseBoolean(parts[8]);
                boolean female = Boolean.parseBoolean(parts[9]);
                boolean middle = Boolean.parseBoolean(parts[10]);
                List<Integer> variantsToCheck = new ArrayList<>();
                if (!parts[11].equals("^"))
                    for (String trialVariant : parts[11].split("#"))
                        if (!trialVariant.equals(""))
                            variantsToCheck.add(Integer.parseInt(trialVariant));
                Noun noun = new Noun(goodRequests, badRequests, goodWords, badWords, word, variant, kind, male, female, middle, variantsToCheck, firstWord);
                addToSet(noun);
                text = reader.readLine();
            }
            reader.close();
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileToAdjectives), "UTF8"));
            text = reader.readLine();
            while (text != null) {
                if (text.equals("") || text.equals(" ")) {
                    text = reader.readLine();
                    continue;
                }
                String[] parts = text.split("&");
                String word = parts[0];
                Set<String> goodRequests = new HashSet<>();
                if (!parts[1].equals("^"))
                    goodRequests.addAll(Arrays.asList(parts[1].split("#")));
                Set<String> badRequests = new HashSet<>();
                if (!parts[2].equals("^"))
                    badRequests.addAll(Arrays.asList(parts[2].split("#")));
                Set<String> goodWords = new HashSet<>();
                if (!parts[3].equals("^"))
                    goodWords.addAll(Arrays.asList(parts[3].split("#")));
                Set<String> badWords = new HashSet<>();
                if (!parts[4].equals("^"))
                    badWords.addAll(Arrays.asList(parts[4].split("#")));
                String firstWord = parts[5];
                int type = Integer.parseInt(parts[6]);
                Adjective adjective = new Adjective(goodRequests, badRequests, goodWords, badWords, word, type, firstWord);
                addToSet(adjective);
                text = reader.readLine();
            }
            reader.close();
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileToIndefiniteWords), "UTF8"));
            text = reader.readLine();
            while (text != null) {
                if (text.equals("") || text.equals(" ")) {
                    text = reader.readLine();
                    continue;
                }
                String[] parts = text.split("&");
                String word = parts[0];
                Queue<String> partOfSpeech = new ArrayDeque<>();
                if (!parts[1].equals("^"))
                    for (String speech : parts[1].split("#"))
                        if (!speech.equals(""))
                            partOfSpeech.offer(speech);
                indefiniteWords.add(new IndefiniteWord(word, partOfSpeech));
                text = reader.readLine();
            }
            reader.close();
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileToGarbage), "UTF8"));
            for (text = reader.readLine(); text != null; text = reader.readLine()) {
                if (text.equals("") || text.equals(" ")) {
                    continue;
                }
                garbage.add(text);
            }
            reader.close();
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileToRequests), "UTF8"));
            for (text = reader.readLine(); text != null; text = reader.readLine()) {
                if (text.equals("") || text.equals(" ")) {
                    continue;
                }
                String[] parts = text.split("&");
                String request = parts[0];
                Map<String, String> texts = new HashMap<>();
                if (!parts[1].equals("^"))
                    for (int i = 1; i < parts.length; i++) {
                        String[] elements = parts[i].split("#");
                        if (elements.length == 2)
                            texts.put(elements[0], elements[1]);
                    }
                requests.add(new Request(texts, request));
            }
            reader.close();
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileToGenerator), "UTF8"));
            String first = reader.readLine();
            System.out.println(first);
            if (first != null) {
                savedNoun = Integer.parseInt(first);
                savedVerb = Integer.parseInt(reader.readLine());
                savedAdjectives = Integer.parseInt(reader.readLine());
                savedVerbPast = Integer.parseInt(reader.readLine());
                savedNounKind = Integer.parseInt(reader.readLine());
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addToSet(Noun noun) {
        if (!noun.variantIsReady()) {
            if (noun.sizeTrialVariants() > 0)
                nounsProblemVariant.add(noun);
            else
                garbage.add(noun.getFirstWord());
        } else if (!noun.kindIsReady()) {
            if (noun.trialKind().equals("нет"))
                nounsWithoutKind.add(noun);
            else
                nounsProblemKind.add(noun);
        } else
            nouns.add(noun);
    }

    private void addToSet(Verb verb) {
        if (!verb.variantIsReady()) {
            if (verb.sizeTrialVariants() > 0)
                verbsProblemVariant.add(verb);
            else
                garbage.add(verb.getFirstWord());
        } else if (!verb.pastIsReady()) {
            if (verb.withOutPast())
                verbsWithOutPast.add(verb);
            else
                verbsProblemPast.add(verb);
        } else
            verbs.add(verb);
    }

    private void addToSet(Adjective adjective) {
        if (!adjective.variantIsReady()) {
            if (adjective.sizeTrialVariants() > 0)
                adjectivesProblemType.add(adjective);
            else
                garbage.add(adjective.getFirstWord());
        } else
            adjectives.add(adjective);
    }

    String[] getText(String request) throws Exception {
        a:
        for (String word : request.split(" ")) {
            List<String> partOfSpeech = partOfSpeech(word);
            if (partOfSpeech.get(0).equals("Предлог"))
                continue;
            String shortWord = word.substring(0, word.length() - Integer.parseInt(partOfSpeech.get(partOfSpeech.size() - 1)));
            partOfSpeech.remove(partOfSpeech.size() - 1);
            Set<Noun> nounSum = new HashSet<>();
            nounSum.addAll(nouns);
            nounSum.addAll(nounsWithoutKind);
            nounSum.addAll(nounsProblemKind);
            nounSum.addAll(nounsProblemVariant);
            Set<Adjective> adjectiveSum = new HashSet<>();
            adjectiveSum.addAll(adjectives);
            adjectiveSum.addAll(adjectivesProblemType);
            Set<Verb> verbSum = new HashSet<>();
            verbSum.addAll(verbs);
            verbSum.addAll(verbsWithOutPast);
            verbSum.addAll(verbsProblemPast);
            verbSum.addAll(verbsProblemVariant);
            for (Noun noun : nounSum)
                if (noun.getWord().equals(shortWord))
                    continue a;
            for (Verb verb : verbSum)
                if (verb.getWord().equals(shortWord))
                    continue a;
            for (Adjective adjective : adjectiveSum)
                if (adjective.getWord().equals(shortWord))
                    continue a;
            for (String garbage : this.garbage)
                if (garbage.equals(word))
                    continue a;
            for (IndefiniteWord indefiniteWord : indefiniteWords)
                if (indefiniteWord.equals(word))
                    continue a;
            if (partOfSpeech.size() == 0)
                garbage.add(word);
            else if (partOfSpeech.size() == 1) {
                switch (partOfSpeech.get(0)) {
                    case "Существительное":
                        Noun noun = new Noun(word);
                        addToSet(noun);
                        break;
                    case "Глагол":
                        Verb verb = new Verb(word);
                        addToSet(verb);
                        break;
                    case "Прилагательное":
                        Adjective adjective = new Adjective(word);
                        addToSet(adjective);
                        break;
                }
            } else {
                Queue<String> queue = new ArrayDeque<>();
                queue.addAll(partOfSpeech);
                indefiniteWords.add(new IndefiniteWord(word, queue));
            }
        }
        Set<Request> pastRequests = find(request);
        if (savedNoun == -1 && savedAdjectives == -1 && savedVerb == -1 && savedVerbPast == -1 && savedNounKind == -1) {
            Map<String, Integer> count = new HashMap<>();
            count.put("сгс", 0);
            count.put("сгп", 0);
            count.put("пгп", 0);
            count.put("пгс", 0);
            count.put("спп", 0);
            count.put("спг", 0);
            count.put("гсп", 0);
            count.put("псг", 0);
            count.put("гпс", 0);
            int number;
            if (requests.size() > 20)
                number = requests.size() - 20;
            else
                number = 0;
            for (; number < requests.size(); number++) {
                List<String> partsOfSpeech = requests.get(number).getPartOfSpeech();
                int i = 0;
                count.computeIfPresent("" + partsOfSpeech.get(i).toCharArray()[0] + partsOfSpeech.get(i).toCharArray()[2] + partsOfSpeech.get(i).toCharArray()[4], (key, value) -> ++value);
            }
            String text = null;
            int bestVariant = Integer.MAX_VALUE;
            String partOfSpeech = null;
            String question = null;
            for (Map.Entry<String, Integer> entry : count.entrySet()) {
                if (entry.getValue() <= 5) {
                    String newQuestion = null;
                    Map<String, Integer> variant = bestVariant(entry.getKey(), pastRequests);
                    for (Map.Entry<String, Integer> entry2 : variant.entrySet()) {
                        if (entry2.getValue() == -1) {
                            if (question == null) {
                                question = entry2.getKey();
                            }
                            newQuestion = entry2.getKey();
                        } else if (!entry2.getKey().equals("") && entry2.getValue() < bestVariant) {
                            bestVariant = entry2.getValue();
                            text = entry2.getKey();
                            partOfSpeech = entry.getKey();
                            if (newQuestion != null)
                                question = newQuestion;
                            else
                                question = null;
                        }
                    }
                }
            }
            if (text != null) {
                Set<Noun> nounSum = new HashSet<>();
                nounSum.addAll(nouns);
                nounSum.addAll(nounsWithoutKind);
                nounSum.addAll(nounsProblemKind);
                Set<Verb> verbSum = new HashSet<>();
                verbSum.addAll(verbs);
                verbSum.addAll(verbsWithOutPast);
                verbSum.addAll(verbsProblemPast);
                StringBuilder result = new StringBuilder("");
                if (partOfSpeech.toCharArray()[1] == 'г') {
                    String[] words = text.split(" ");
                    for (int i = 0; i < words.length; i++) {
                        if (partOfSpeech.toCharArray()[i] == 'г') {
                            for (Verb verb : verbSum) {
                                if (verb.getWord().equals(words[i])) {
                                    result.append(verb.getNewWord(false, 3)).append(" ");
                                    break;
                                }
                            }
                        } else if (partOfSpeech.toCharArray()[i] == 'с') {
                            for (Noun noun : nounSum) {
                                if (noun.getWord().equals(words[i])) {
                                    if (i == 0)
                                        result.append(noun.getNewWord(false, "именительный")).append(" ");
                                    else
                                        result.append(noun.getNewWord(false, "винительный"));
                                    break;
                                }
                            }
                        } else if (partOfSpeech.toCharArray()[i] == 'п') {
                            for (Adjective adjective : adjectives)
                                if (adjective.getWord().equals(words[i])) {
                                    if (i == 0)
                                        result.append(adjective.getNewWord("именительный", "мужской")).append(" ");
                                    else
                                        result.append(adjective.getNewWord("винительный", "женский"));
                                    break;
                                }
                        }
                    }
                } else {
                    String[] words = text.split(" ");
                    Noun noun = null;
                    for (int i = 0; i < partOfSpeech.toCharArray().length; i++) {
                        if (partOfSpeech.toCharArray()[i] == 'с') {
                            for (Noun noun2 : nounSum)
                                if (noun2.getWord().equals(words[i])) {
                                    noun = noun2;
                                    break;
                                }
                        }
                    }
                    for (int i = 0; i < words.length; i++) {
                        if (partOfSpeech.toCharArray()[i] == 'с') {
                            result.append(noun.getNewWord(false, "именительный"));
                            if (i != 2)
                                result.append(" ");
                        } else if (partOfSpeech.toCharArray()[i] == 'г') {
                            for (Verb verb : verbSum)
                                if (verb.getWord().equals(words[i])) {
                                    result.append(verb.getNewWord(false, 3));
                                    if (i != 2)
                                        result.append(" ");
                                    break;
                                }
                        } else if (partOfSpeech.toCharArray()[i] == 'п') {
                            for (Adjective adjective : adjectives) {
                                if (adjective.getWord().equals(words[i])) {
                                    result.append(adjective.getNewWord("именительный", noun.getKind()));
                                    if (i != 2)
                                        result.append(" ");
                                    break;
                                }
                            }
                        }
                    }
                }
                String[] realResult = new String[5];
                realResult[0] = result.toString();
                realResult[1] = partOfSpeech.toCharArray()[0] + " " + partOfSpeech.toCharArray()[1] + " " + partOfSpeech.toCharArray()[2];
                realResult[2] = question;
                realResult[3] = "gg";
                realResult[4] = request;
                return realResult;
            } else {
                savedNoun = 0;
                savedVerb = 0;
                savedAdjectives = 0;
                savedVerbPast = 0;
                savedNounKind = 0;
            }
        }
        if (savedNoun != -1 && savedVerb != -1 && savedAdjectives != -1 && savedVerbPast != -1 && savedNounKind != -1) {
            for (int i = 0; i < 2; i++) {
                Set<TrialVariant> problemVariants = new HashSet<>();
                int saved;
                Set<Word> sum = new HashSet<>();
                Set<Verb> sumVerb = new HashSet<>();
                sumVerb.addAll(verbs);
                sumVerb.addAll(verbsWithOutPast);
                sumVerb.addAll(verbsProblemPast);
                if (i == 0) {
                    saved = savedNoun;
                    problemVariants.addAll(nounsProblemVariant);
                    sum.addAll(adjectives);
                } else {
                    saved = savedAdjectives;
                    problemVariants.addAll(adjectivesProblemType);
                    sum.addAll(nouns);
                    sum.addAll(nounsProblemKind);
                    sum.addAll(nounsWithoutKind);
                }
                if (saved != 10 && problemVariants.size() != 0 && sum.size() != 0 && sumVerb.size() != 0) {
                    String question = "txt1";
                    TrialVariant trialVariant = problemVariants.iterator().next();
                    Verb verb = choose(sumVerb, pastRequests);
                    if (chooseUtility(verb, pastRequests))
                        question += "#txt-img2";
                    Word word = choose(sum, pastRequests);
                    if (chooseUtility(word, pastRequests))
                        question += "#txt-img3";
                    if (!verb.getGoodWords().contains(word.getWord()) && !verb.getBadWords().contains(word.getWord()))
                        question += "#stxt2-3";
                    String[] result = new String[5];
                    StringBuilder builder = new StringBuilder("");
                    if (Noun.class.isInstance(trialVariant)) {
                        result[1] = "с г п";
                        Noun noun = (Noun) trialVariant;
                        builder.append(noun.getNewWord(false, "именительный", trialVariant.trialVariant())).append(" ");
                        builder.append(verb.getNewWord(false, 3)).append(" ");
                        Adjective adjective = (Adjective) word;
                        builder.append(adjective.getNewWord("винительный", "мужской"));
                        result[3] = "nv" + trialVariant.trialVariant();
                    } else {
                        result[1] = "п г с";
                        Adjective adjective = (Adjective) trialVariant;
                        builder.append(adjective.getNewWord(trialVariant.trialVariant(), "именительный", "средний")).append(" ");
                        builder.append(verb.getNewWord(false, 3)).append(" ");
                        Noun noun = (Noun) word;
                        builder.append(noun.getNewWord(false, "винительныйй"));
                        result[3] = "av" + trialVariant.trialVariant();
                    }
                    result[0] = builder.toString();
                    result[2] = question;
                    result[4] = request;
                    return result;
                }
            }
            Set<Noun> sumNoun = new HashSet<>();
            sumNoun.addAll(nouns);
            sumNoun.addAll(nounsWithoutKind);
            sumNoun.addAll(nounsProblemKind);
            if (savedVerb != 10 && verbsProblemVariant.size() != 0 && sumNoun.size() != 0 && adjectives.size() != 0) {
                String question = "txt2";
                Verb verb = verbsProblemVariant.iterator().next();
                Noun noun = choose(sumNoun, pastRequests);
                if (chooseUtility(noun, pastRequests))
                    question += "#txt-img3";
                Adjective adjective = choose(adjectives, pastRequests);
                if (chooseUtility(adjective, pastRequests))
                    question += "#txt-img1";
                String[] result = new String[5];
                StringBuilder builder = new StringBuilder("");
                int[] elements = verb.trialVariant();
                builder.append(adjective.getNewWord("именительный", "средний")).append(" ");
                builder.append(verb.getNewWord(false, elements[1], elements[0])).append(" ");
                builder.append(noun.getNewWord(false, "винительный"));
                result[0] = builder.toString();
                result[1] = "п г с";
                result[2] = question;
                result[3] = "vv" + elements[0];
                result[4] = request;
                return result;
            }
            if ((savedVerb != 10 || savedNoun != 10 || savedAdjectives != 10) && indefiniteWords.size() != 0) {
                StringBuilder text = new StringBuilder("");
                StringBuilder question = new StringBuilder("");
                int count = 0;
                for (IndefiniteWord indefiniteWord : indefiniteWords) {
                    count++;
                    text.append(indefiniteWord.getWord()).append(" ");
                    question.append("part").append(count).append("#");
                    if (count == 3)
                        break;
                }
                text.substring(0, text.length() - 1);
                question.substring(0, question.length() - 1);
                String[] result = new String[5];
                result[0] = text.toString();
                result[1] = "";
                result[2] = question.toString();
                result[3] = "unknown";
                result[4] = request;
                return result;
            }
            if (savedVerbPast != 5 && verbsProblemPast.size() != 0 && sumNoun.size() != 0 && adjectives.size() != 0) {
                String question = "txt2";
                Verb verb = verbsProblemPast.iterator().next();
                Noun noun = choose(sumNoun, pastRequests);
                if (chooseUtility(noun, pastRequests))
                    question += "#txt-img1";
                Adjective adjective = choose(adjectives, pastRequests);
                if (chooseUtility(adjective, pastRequests))
                    question += "#txt-img3";
                String builder = noun.getNewWord(false, "именительный") + " " +
                        verb.getTrialPast("мужской") + " " +
                        adjective.getNewWord("винительный", "женский");
                String[] result = new String[5];
                result[0] = builder;
                result[1] = "с г п";
                result[2] = question;
                result[3] = "vp";
                result[4] = request;
                return result;
            }
            if (savedNounKind != 5 && nounsProblemKind.size() != 0 && verbs.size() != 0 && adjectives.size() != 0) {
                String question = "otxt1-2";
                Noun noun = nounsProblemKind.iterator().next();
                Verb verb = choose(verbs, pastRequests);
                if (chooseUtility(verb, pastRequests))
                    question += "#txt-img2";
                Adjective adjective = choose(adjectives, pastRequests);
                if (chooseUtility(adjective, pastRequests))
                    question += "#txt-img3";
                if (!verb.getBadWords().contains(adjective.getWord()) && !verb.getGoodWords().contains(adjective.getWord()))
                    question += "#stxt2-3";
                String text = noun.getNewWord(false, "именительный") + " " + verb.getPast(noun.trialKind()) + " " + adjective.getNewWord("винительный", "множественный");
                String[] result = new String[5];
                result[0] = text;
                result[1] = "с г п";
                result[2] = question;
                result[3] = "nk" + noun.trialKind();
                result[4] = request;
                return result;
            }
            savedNounKind = -1;
            savedNoun = -1;
            savedVerbPast = -1;
            savedVerb = -1;
            savedAdjectives = -1;
        }
        return getText(request);
    }

    private boolean chooseUtility(Word word, Set<Request> requests) {
        for (Request request : requests)
            for (String text : request.getTexts())
                if (Arrays.asList(text.split(" ")).contains(word.getWord()))
                    return false;
        return true;
    }

    private <T extends Word> T choose(Set<T> words, Set<Request> requests) {
        Random random = new Random();
        T result = null;
        for (T word : words) {
            boolean good = true;
            a:
            for (Request request : requests)
                for (String text : request.getTexts())
                    if (Arrays.asList(text.split(" ")).contains(word.getWord())) {
                        good = false;
                        break a;
                    }
            if (good && !contains(this.requests, ".*" + word.getWord() + ".*", 10))
                return word;
            else if (good)
                result = word;
        }
        for (T word : words)
            for (Request request : requests)
                if (word.getGoodRequests().contains(request.getRequest())) {
                    if (!contains(this.requests, ".*" + word.getWord() + ".*", 10))
                        return word;
                    else if (result == null)
                        result = word;
                }
        if (result != null)
            return result;
        int count = random.nextInt(words.size());
        int i = 0;
        for (T word : words) {
            if (i == count)
                return word;
            i++;
        }
        return null;
    }

    private Map<String, Integer> bestVariant(String partOfSpeech, Set<Request> pastRequest) throws Exception {
        Set<Word> words = new HashSet<>();
        int wordsPosition = -1;
        Set<Word> middleWords = new HashSet<>();
        int middleWordsPosition = -1;
        Set<Word> dependentWords = new HashSet<>();
        int dependentWordsPosition = -1;
        if (partOfSpeech.toCharArray()[1] == 'г') {
            char mainChar = partOfSpeech.toCharArray()[0];
            if (mainChar == 'с') {
                words.addAll(nouns);
                words.addAll(nounsProblemKind);
                words.addAll(nounsWithoutKind);
            } else if (mainChar == 'п')
                words.addAll(adjectives);
            else
                throw new Exception();
            middleWords.addAll(verbs);
            middleWords.addAll(verbsProblemPast);
            middleWords.addAll(verbsWithOutPast);
            char dependentChar = partOfSpeech.toCharArray()[2];
            if (dependentChar == 'с') {
                dependentWords.addAll(nouns);
                dependentWords.addAll(nounsProblemKind);
                dependentWords.addAll(nounsWithoutKind);
            } else if (dependentChar == 'п')
                dependentWords.addAll(adjectives);
            else
                throw new Exception();
        } else {
            words.addAll(nouns);
            Set<Word> set = middleWords;
            boolean first = true;
            for (int i = 0; i < partOfSpeech.length(); i++) {
                if (partOfSpeech.toCharArray()[i] != 'с') {
                    if (partOfSpeech.toCharArray()[i] == 'п')
                        set.addAll(adjectives);
                    else {
                        set.addAll(verbs);
                        set.addAll(verbsProblemPast);
                        set.addAll(verbsWithOutPast);
                    }
                    if (first)
                        middleWordsPosition = i;
                    else
                        dependentWordsPosition = i;
                    first = false;
                    set = dependentWords;
                } else wordsPosition = i;
            }
        }
        int bestVariant = Integer.MAX_VALUE;
        String text = "";
        String realQuestion = "";
        for (Word word : words) {
            StringBuilder question = new StringBuilder("");
            boolean goodWord1 = false;
            String goodWord = goodWord(word, pastRequest);
            if (goodWord.equals("b"))
                continue;
            else if (goodWord.equals("g"))
                goodWord1 = true;
            for (Word middleWord : middleWords) {
                if (contains(pastRequest, ".*" + word.getWord() + ".*" + middleWord.getWord() + ".*") || contains(pastRequest, ".*" + middleWord.getWord() + ".*" + word.getWord() + ".*") || word.getBadWords().contains(middleWord.getWord()))
                    continue;
                if (contains(requests, ".*" + word.getWord() + ".*" + middleWord.getWord() + ".*", requests.size()) || contains(requests, ".*" + middleWord.getWord() + ".*" + word.getWord() + ".*", requests.size()) || word.getWord().equals(middleWord.getWord()))
                    continue;
                boolean goodWord2 = false;
                goodWord = goodWord(middleWord, pastRequest);
                if (goodWord.equals("b"))
                    continue;
                else if (goodWord.equals("g"))
                    goodWord2 = true;
                for (Word dependentWord : dependentWords) {
                    if (contains(pastRequest, ".*" + word.getWord() + ".*" + dependentWord.getWord() + ".*") || contains(pastRequest, ".*" + dependentWord.getWord() + ".*" + word.getWord() + ".*") || contains(pastRequest, ".*" + dependentWord.getWord() + ".*" + middleWord.getWord() + ".*") || contains(pastRequest, ".*" + middleWord.getWord() + ".*" + dependentWord.getWord() + ".*") || dependentWord.getWord().equals(middleWord.getWord()) || dependentWord.getWord().equals(word.getWord()))
                        continue;
                    if (contains(requests, ".*" + word.getWord() + ".*" + dependentWord.getWord() + ".*", requests.size()) || contains(requests, ".*" + dependentWord.getWord() + ".*" + word.getWord() + ".*", requests.size()) || contains(requests, ".*" + dependentWord.getWord() + ".*" + middleWord.getWord() + ".*", requests.size()) || contains(requests, ".*" + middleWord.getWord() + ".*" + dependentWord.getWord() + ".*", requests.size()))
                        continue;
                    if (partOfSpeech.toCharArray()[1] == 'г' && middleWord.getBadWords().contains(dependentWord.getWord()))
                        continue;
                    else if (word.getBadWords().contains(dependentWord.getWord()))
                        continue;
                    boolean goodWord3 = false;
                    goodWord = goodWord(word, pastRequest);
                    if (goodWord.equals("b"))
                        continue;
                    else if (goodWord.equals("g"))
                        goodWord3 = true;
                    boolean link12 = word.getGoodWords().contains(middleWord.getWord());
                    if (!link12) {
                        if (partOfSpeech.toCharArray()[1] == 'г')
                            question.append("stxt1-2");
                        else
                            question.append("stxt").append(wordsPosition + 1).append("-").append(middleWordsPosition + 1);
                        question.append("#");
                    }
                    boolean link03;
                    if (partOfSpeech.toCharArray()[1] == 'г') {
                        link03 = middleWord.getGoodWords().contains(dependentWord.getWord());
                        if (!link03)
                            question.append("stxt2-3#");
                    } else {
                        link03 = word.getGoodWords().contains(dependentWord.getWord());
                        if (!link03)
                            question.append("stxt").append(wordsPosition + 1).append("-").append(dependentWordsPosition + 1).append("#");
                    }
                    if (!goodWord1)
                        question.append("txt-img1#");
                    if (!goodWord2)
                        question.append("txt-img2#");
                    if (!goodWord3)
                        question.append("txt-img3#");
                    if (question.length() > 0)
                        question = new StringBuilder(question.substring(0, question.length() - 1));
                    int result = 12;
                    if (goodWord1 && goodWord2 && goodWord3 && link12 && link03)
                        result = 1;
                    else if (goodWord1 && goodWord2 && !goodWord3 && link12 && link03 || goodWord1 && !goodWord2 && goodWord3 && link12 && link03 || !goodWord1 && goodWord2 && goodWord3 && link12 && link03)
                        result = 2;
                    else if (goodWord1 && goodWord2 && goodWord3 && link12 && !link03 || goodWord1 && goodWord2 && goodWord3 && !link12 && link03)
                        result = 3;
                    else if (goodWord1 && !goodWord2 && !goodWord3 && link12 && link03 || !goodWord1 && !goodWord2 && goodWord3 && link12 && link03 || !goodWord1 && goodWord2 && !goodWord3 && link12 && link03)
                        result = 4;
                    else if (goodWord1 && goodWord2 && goodWord3 && !link12 && !link03)
                        result = 5;
                    else if (goodWord1 && goodWord2 && !goodWord3 && link12 && !link03 || goodWord1 && !goodWord2 && goodWord3 && link12 && !link03 || !goodWord1 && goodWord2 && goodWord3 && link12 && !link03 || goodWord1 && goodWord2 && !goodWord3 && !link12 && link03 || goodWord1 && !goodWord2 && goodWord3 && !link12 && link03 || !goodWord1 && goodWord2 && goodWord3 && !link12 && link03)
                        result = 6;
                    else if (!goodWord1 && !goodWord2 && !goodWord3 && link12 && link03)
                        result = 7;
                    else if (goodWord1 && !goodWord2 && !goodWord3 && link12 && !link03 || !goodWord1 && goodWord2 && !goodWord3 && link12 && !link03 || !goodWord1 && !goodWord2 && goodWord3 && link12 && !link03 || goodWord1 && !goodWord2 && !goodWord3 && !link12 && link03 || !goodWord1 && goodWord2 && !goodWord3 && !link12 && link03 || !goodWord1 && !goodWord2 && goodWord3 && !link12 && link03)
                        result = 8;
                    else if (!goodWord1 && goodWord2 && goodWord3 && !link12 && !link03 || goodWord1 && !goodWord2 && goodWord3 && !link12 && !link03 || goodWord1 && goodWord2 && !goodWord3 && !link12 && !link03)
                        result = 9;
                    else if (!goodWord1 && !goodWord2 && goodWord3 && !link12 && !link03 || !goodWord1 && goodWord2 && !goodWord3 && !link12 && !link03 || goodWord1 && !goodWord2 && !goodWord3 && !link12 && !link03)
                        result = 10;
                    else if (!goodWord1 && !goodWord2 && !goodWord3 && link12 && !link03 || !goodWord1 && !goodWord2 && !goodWord3 && !link12 && link03)
                        result = 11;
                    if (result < bestVariant) {
                        bestVariant = result;
                        StringBuilder newText = new StringBuilder();
                        if (partOfSpeech.toCharArray()[1] == 'г')
                            newText.append(word.getWord()).append(" ").append(middleWord.getWord()).append(" ").append(dependentWord.getWord());
                        else {
                            if (wordsPosition < dependentWordsPosition && wordsPosition < middleWordsPosition)
                                newText.append(word.getWord());
                            else if (dependentWordsPosition < wordsPosition && dependentWordsPosition < middleWordsPosition)
                                newText.append(dependentWord.getWord());
                            else
                                newText.append(middleWord.getWord());
                            newText.append(" ");
                            if (wordsPosition > dependentWordsPosition && wordsPosition < middleWordsPosition || wordsPosition < dependentWordsPosition && wordsPosition > middleWordsPosition)
                                newText.append(word.getWord());
                            else if (dependentWordsPosition < wordsPosition && dependentWordsPosition > middleWordsPosition || dependentWordsPosition > wordsPosition && dependentWordsPosition < middleWordsPosition)
                                newText.append(dependentWord.getWord());
                            else
                                newText.append(middleWord.getWord());
                            newText.append(" ");
                            if (wordsPosition > dependentWordsPosition && wordsPosition > middleWordsPosition)
                                newText.append(word.getWord());
                            else if (dependentWordsPosition > wordsPosition && dependentWordsPosition > middleWordsPosition)
                                newText.append(dependentWord.getWord());
                            else
                                newText.append(middleWord.getWord());
                        }
                        text = newText.toString();
                        realQuestion = question.toString();
                        if (bestVariant == 1) {
                            Map<String, Integer> map = new HashMap<>();
                            map.put(text, bestVariant);
                            map.put(realQuestion, -1);
                            return map;
                        }
                    }
                    question = new StringBuilder("");
                }
            }
        }
        Map<String, Integer> map = new HashMap<>();
        map.put(text, bestVariant);
        map.put(realQuestion, -1);
        return map;
    }


    private String goodWord(Word word, Set<Request> pastRequest) {
        for (Request request : pastRequest) {
            if (word.getBadRequests().contains(request.getRequest()))
                return "b";
            if (word.getGoodRequests().contains(request.getRequest())) {
                return "g";
            }
        }
        return "n";
    }

    private boolean contains(Set<Request> pastRequest, String string) {
        for (Request request : pastRequest) {
            for (String text : request.getTexts()) {
                if (text.matches(string))
                    return true;
            }
        }
        return false;
    }

    private boolean contains(List<Request> requests, String string, int number) {
        int i = 0;
        if (requests.size() > number)
            i = requests.size() - number;
        for (; i < requests.size(); i++) {
            for (String text : requests.get(i).getTexts())
                if (text.matches(string))
                    return true;
        }
        return false;
    }

    private static List<String> partOfSpeech(String string) {
        String[] adjectives = {"ого", "его", "ому", "ему", "ими", "ыми", "ое", "ее", "ый", "ий", "ым", "им", "ом", "ем", "ая", "яя", "ую", "юю", "ой", "ей", "ые", "йе", "ых", "их"};
        String[] nouns = {"ами", "ями", "ой", "ей", "ом", "ем", "ам", "ям", "ах", "ях", "ов", "ев", "ы", "и", "е", "у", "ю", "а", "я", "о"};
        String[] verbs = {"ешь", "ете", "ишь", "ите", "ать", "ять", "ють", "ить", "еть", "уть", "ся", "сь", "ет", "ем", "ут", "ют", "ит", "им", "ат", "ят", "ла", "ло", "ли", "ть", "у", "ю", "а", "о", "и", "л"};
        List<String> result1 = new ArrayList<>();
        List<String> result2 = new ArrayList<>();
        List<String> result3 = new ArrayList<>();
        if (string.length() < 4) {
            result1.add("Предлог");
            return result1;
        }
        char[] chars = string.toCharArray();
        String end1 = Character.toString(chars[chars.length - 1]);
        String end2 = Character.toString(chars[chars.length - 2]) + Character.toString(chars[chars.length - 1]);
        String end3 = Character.toString(chars[chars.length - 3]) + Character.toString(chars[chars.length - 2]) + Character.toString(chars[chars.length - 1]);
        for (String endVariant : adjectives) {
            if (end1.equals(endVariant)) {
                result1.add("Прилагательное");
                break;
            } else if (end3.equals(endVariant)) {
                result3.add("Прилагательное");
                break;
            } else if (end2.equals(endVariant)) {
                result2.add("Прилагательное");
                break;
            }
        }
        if (result3.size() == 0)
            for (String endVariant : nouns) {
                if (end3.equals(endVariant)) {
                    result3.add("Существительное");
                    break;
                } else if (end2.equals(endVariant)) {
                    result2.add("Существительное");
                    break;
                } else if (end1.equals(endVariant)) {
                    result1.add("Существительное");
                    break;
                }
            }
        if (result3.size() == 0)
            for (String endVariant : verbs) {
                if (end1.equals(endVariant)) {
                    result1.add("Глагол");
                    break;
                } else if (end2.equals(endVariant)) {
                    result2.add("Глагол");
                    break;
                } else if (end3.equals(endVariant)) {
                    result3.add("Глагол");
                    break;
                }
            }
        if (result3.size() > 0) {
            result3.add("3");
            return result3;
        }
        if (result2.size() > 0) {
            result2.add("2");
            return result2;
        } else if (result1.size() > 0) {
            result1.add("1");
            return result1;
        } else {
            List<String> result = new ArrayList<>();
            if (end2.equals("ся") || end2.equals("сь") || end2.equals("ть"))
                result.add("Предлог");
            else
                result.add("Существительное");
            result.add("0");
            return result;
        }
    }

    private Set<Request> find(String request) {
        Set<Request> result = new HashSet<>();
        a:
        for (Request request1 : requests) {
            if (request1.getRequest().equals(request))
                result.add(request1);
            else {
                for (String text1 : request.split(" "))
                    for (String text2 : request1.getRequest().split(" "))
                        if (text1.equals(text2)) {
                            result.add(request1);
                            continue a;
                        }
            }
        }
        return result;
    }

    void putWord(String[] elements) {
        if (elements[3].equals("unknown")) {
            String[] words = elements[0].split(" ");
            String[] answers = elements[5].split("#");
            for (int i = 0; i < words.length; i++)
                for (IndefiniteWord indefiniteWord : indefiniteWords)
                    if (words[i].equals(indefiniteWord.getWord())) {
                        indefiniteWords.remove(indefiniteWord);
                        switch (answers[i].toLowerCase()) {
                            case "существительное":
                                addToSet(new Noun(words[i]));
                                break;
                            case "прилагательное":
                                addToSet(new Adjective(words[i]));
                                break;
                            case "глагол":
                                addToSet(new Verb(words[i]));
                                break;
                            case "нет":
                                garbage.add(indefiniteWord.getWord());
                        }
                        break;
                    }
            return;
        }
        String[] strings = elements[0].split(" ");
        String[] partsOfSpeech = elements[1].split(" ");
        for (int i = 0; i < strings.length; i++) {
            List<String[]> list = new ArrayList<>();
            switch (partsOfSpeech[i]) {
                case "п":
                    list.add(new String[]{"ого", "его", "ому", "ему", "ими", "ыми", "ое", "ее", "ый", "ий", "ым", "им", "ом", "ем", "ая", "яя", "ую", "юю", "ой", "ей", "ые", "йе", "ых", "их"});
                    break;
                case "с":
                    list.add(new String[]{"ами", "ями", "ой", "ей", "ом", "ем", "ам", "ям", "ах", "ях", "ов", "ев", "ы", "и", "е", "у", "ю", "а", "я", "о"});
                    break;
                case "г":
                    list.add(new String[]{"ешь", "ете", "ишь", "ите", "ать", "ять", "ють", "ить", "еть", "уть", "ся", "сь", "ет", "ем", "ут", "ют", "ит", "им", "ат", "ят", "ла", "ло", "ли", "ть", "у", "ю", "а", "о", "и", "л"});
            }
            List<Integer> list2 = Word.getType(list, strings[i]);
            strings[i] = strings[i].substring(0, strings[i].length() - list2.get(list2.size() - 1));
        }
        StringBuilder newString = new StringBuilder();
        for (String string : strings)
            newString.append(string).append(" ");
        newString = new StringBuilder(newString.substring(0, newString.length() - 1));
        boolean find = false;
        for (Request request : requests) {
            if (request.getRequest().equals(elements[4])) {
                request.putTextAndPartOfSpeech(newString.toString(), elements[1]);
                find = true;
                break;
            }
        }
        if (!find) {
            Map<String, String> map = new HashMap<>();
            map.put(newString.toString(), elements[1]);
            requests.add(new Request(map, elements[4]));
        }

        String[] questions = elements[2].split("#");
        String[] answers = elements[5].split("#");
        Word[] words = new Word[3];
        for (int i = 0; i < partsOfSpeech.length; i++) {
            switch (partsOfSpeech[i]) {
                case "с":
                    Set<Noun> nounSum = new HashSet<>();
                    nounSum.addAll(nouns);
                    nounSum.addAll(nounsProblemKind);
                    nounSum.addAll(nounsWithoutKind);
                    for (Noun noun : nounSum)
                        if (noun.getWord().equals(strings[i])) {
                            imgTxt(noun, (i + 1), answers, questions, elements[4]);
                            words[i] = noun;
                            break;
                        }
                    if (words[i] == null) {
                        for (Noun noun : nounsProblemVariant)
                            if (noun.getWord().equals(strings[i])) {
                                System.out.println("Батя в здании");
                                imgTxt(noun, (i + 1), answers, questions, elements[4]);
                                words[i] = noun;
                                if (elements[3].matches("nv.*")) {
                                    int count = 0;
                                    for (String question : questions)
                                        if (question.equals("txt" + (i + 1))) {
                                            nounsProblemVariant.remove(noun);
                                            System.out.println(Integer.parseInt(elements[3].toCharArray()[elements[3].toCharArray().length - 1] + ""));
                                            noun.deleteVariant(Integer.parseInt(elements[3].toCharArray()[elements[3].toCharArray().length - 1] + ""));
                                            switch (answers[count]) {
                                                case "1":
                                                    noun.setVariant(Integer.parseInt(elements[3].toCharArray()[elements[3].toCharArray().length - 1] + ""));
                                                    savedNoun++;
                                                case "2":
                                                    addToSet(noun);
                                            }
                                            break;
                                        } else
                                            count++;
                                }
                                break;
                            }
                    }
                    break;
                case "п":
                    for (Adjective adjective : adjectives)
                        if (adjective.getWord().equals(strings[i])) {
                            imgTxt(adjective, (i + 1), answers, questions, elements[4]);
                            words[i] = adjective;
                            break;
                        }
                    if (words[i] == null) {
                        for (Adjective adjective : adjectivesProblemType)
                            if (adjective.getWord().equals(strings[i])) {
                                imgTxt(adjective, (i + 1), answers, questions, elements[4]);
                                words[i] = adjective;
                                if (elements[3].matches("av.*")) {
                                    int count = 0;
                                    for (String question : questions)
                                        if (question.equals("txt" + (i + 1))) {
                                            adjectivesProblemType.remove(adjective);
                                            adjective.deleteVariant(Integer.parseInt(elements[3].toCharArray()[elements[3].toCharArray().length - 1] + ""));
                                            switch (answers[count]) {
                                                case "1":
                                                    adjective.setVariant(Integer.parseInt(elements[3].toCharArray()[elements[3].toCharArray().length - 1] + ""));
                                                    savedAdjectives++;
                                                case "2":
                                                    addToSet(adjective);
                                            }
                                            break;
                                        } else
                                            count++;
                                }
                                break;
                            }
                    }
                    break;
                case "г":
                    Set<Verb> verbSum = new HashSet<>();
                    verbSum.addAll(verbs);
                    verbSum.addAll(verbsWithOutPast);
                    for (Verb verb : verbSum)
                        if (verb.getWord().equals(strings[i])) {
                            imgTxt(verb, (i + 1), answers, questions, elements[4]);
                            words[i] = verb;
                            break;
                        }
                    if (words[i] == null) {
                        for (Verb verb : verbsProblemVariant)
                            if (verb.getWord().equals(strings[i])) {
                                imgTxt(verb, (i + 1), answers, questions, elements[4]);
                                words[i] = verb;
                                if (elements[3].matches("vv.*")) {
                                    int count = 0;
                                    for (String question : questions)
                                        if (question.equals("txt" + (i + 1))) {
                                            verbsProblemVariant.remove(verb);
                                            verb.deleteVariant(Integer.parseInt(elements[3].toCharArray()[elements[3].toCharArray().length - 1] + ""));
                                            switch (answers[count]) {
                                                case "1":
                                                    verb.setVariant(Integer.parseInt(elements[3].toCharArray()[elements[3].toCharArray().length - 1] + ""));
                                                    savedVerb++;
                                                case "2":
                                                    addToSet(verb);
                                            }
                                            break;
                                        } else
                                            count++;

                                }
                                break;
                            }
                    }
                    if (words[i] == null)
                        for (Verb verb : verbsProblemPast)
                            if (verb.getWord().equals(strings[i])) {
                                imgTxt(verb, i, answers, questions, elements[4]);
                                words[i] = verb;
                                if (elements[3].matches("vp.*")) {
                                    int count = 0;
                                    for (String question : questions)
                                        if (question.equals("txt" + (i + 1))) {
                                            verbsProblemPast.remove(verb);
                                            verb.deletePast();
                                            switch (answers[count]) {
                                                case "1":
                                                    verb.setCharToPast(elements[0].split(" ")[i]);
                                                    savedVerbPast++;
                                                case "2":
                                                    addToSet(verb);
                                            }
                                            break;
                                        } else
                                            count++;
                                }
                                break;
                            }
            }
        }
        for (int i = 0; i < questions.length; i++) {
            if (questions[i].matches("stxt\\d+-\\d+")) {
                String[] splitQuestion = questions[i].split("-");
                int number1 = Integer.parseInt(splitQuestion[0].substring(4, splitQuestion[0].length())) - 1;
                int number2 = Integer.parseInt(splitQuestion[1]) - 1;
                System.out.println("Объединяем " + number1 + " " + number2);
                switch (answers[i]) {
                    case "1":
                        words[number1].putWord(words[number2].getWord(), true);
                        words[number2].putWord(words[number1].getWord(), true);
                        break;
                    case "2":
                        words[number1].putWord(words[number2].getWord(), false);
                        words[number2].putWord(words[number1].getWord(), false);
                }
            }
        }
        if (elements[3].matches("nk.*")) {
            for (int i = 0; i < questions.length; i++) {
                if (questions[i].matches("otxt\\d+-\\d+")) {
                    String[] splitQuestion = questions[i].split("-");
                    int number1 = Integer.parseInt(questions[i].split("-")[0].substring(4, splitQuestion[0].length())) - 1;
                    Noun noun = (Noun) words[number1];
                    String kind = elements[3].substring(2, elements[3].length());
                    switch (answers[i]) {
                        case "1":
                            noun.setKind(kind, true);
                            savedNounKind++;
                            break;
                        case "2":
                            noun.setKind(kind, false);
                    }
                }
            }
        }
    }

    private void imgTxt(Word word, int number, String[] answers, String[] questions, String request) {
        for (int i = 0; i < answers.length; i++) {
            if (questions[i].equals("txt-img" + number))
                if (answers[i].equals("1"))
                    word.putRequest(request, true);
                else
                    word.putRequest(request, false);
        }
    }

    public void close() {
        fileToRequests.delete();
        fileToGarbage.delete();
        fileToIndefiniteWords.delete();
        fileToVerbs.delete();
        fileToNouns.delete();
        fileToAdjectives.delete();
        fileToGenerator.delete();
        try {
            fileToRequests.createNewFile();
            fileToGarbage.createNewFile();
            fileToIndefiniteWords.createNewFile();
            fileToVerbs.createNewFile();
            fileToNouns.createNewFile();
            fileToAdjectives.createNewFile();
            fileToGenerator.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileToRequests), "UTF8"));
            for (Request request : requests)
                writer.write(request.toString() + "\r\n");
            writer.close();
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileToGarbage), "UTF8"));
            for (String garbage : garbage)
                writer.write(garbage + "\r\n");
            writer.close();
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileToIndefiniteWords), "UTF8"));
            for (IndefiniteWord indefiniteWord : indefiniteWords)
                writer.write(indefiniteWord.toString() + "\r\n");
            writer.close();
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileToVerbs), "UTF8"));
            Set<Verb> sumVerb = new HashSet<>();
            sumVerb.addAll(verbs);
            sumVerb.addAll(verbsProblemVariant);
            sumVerb.addAll(verbsProblemPast);
            sumVerb.addAll(verbsWithOutPast);
            for (Verb verb : sumVerb)
                writer.write(verb.toString() + "\r\n");
            writer.close();
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileToNouns), "UTF8"));
            Set<Noun> sumNoun = new HashSet<>();
            sumNoun.addAll(nouns);
            sumNoun.addAll(nounsProblemKind);
            sumNoun.addAll(nounsWithoutKind);
            sumNoun.addAll(nounsProblemVariant);
            for (Noun noun : sumNoun)
                writer.write(noun.toString() + "\r\n");
            writer.close();
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileToAdjectives), "UTF8"));
            Set<Adjective> sumAdjective = new HashSet<>();
            sumAdjective.addAll(adjectives);
            sumAdjective.addAll(adjectivesProblemType);
            for (Adjective adjective : sumAdjective)
                writer.write(adjective + "\r\n");
            writer.close();
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileToGenerator), "UTF8"));
            System.out.println("Я тут");
            System.out.println(savedNoun);
            writer.write(savedNoun + "\r\n");
            writer.write(savedVerb + "\r\n");
            writer.write(savedAdjectives + "\r\n");
            writer.write(savedVerbPast + "\r\n");
            writer.write(savedNounKind + "\r\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}