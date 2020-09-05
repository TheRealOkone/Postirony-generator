package partOfSpeech;

import java.util.Set;

public class TrialVariant extends Word {

    TrialVariant(Set<String> goodRequests, Set<String> badRequests, Set<String> goodWords, Set<String> badWords, String word, int variant) {
        super(goodRequests, badRequests, goodWords, badWords, word, variant);
    }

    TrialVariant(String word) {
        super(word);
    }

    public int trialVariant() {
        if (variantsToCheck.size() == 0)
            return 0;
        return variantsToCheck.get(variantsToCheck.size() - 1);

    }
}