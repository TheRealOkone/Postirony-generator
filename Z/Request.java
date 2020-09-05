import java.util.*;

public class Request {
    private Map<String, String> textsAndPartOfSpeech;
    private String request;

    public Request(Map<String, String> textsAndPartOfSpeech, String request) {
        this.textsAndPartOfSpeech = textsAndPartOfSpeech;
        this.request = request;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public Map<String, String> getTextsAndPartOfSpeech() {
        return textsAndPartOfSpeech;
    }

    public Set<String> getTexts() {
        return textsAndPartOfSpeech.keySet();
    }

    public void putTextAndPartOfSpeech(String text, String partsOfSpeech) {
        textsAndPartOfSpeech.putIfAbsent(text, partsOfSpeech);
    }

    public List<String> getPartOfSpeech() {
        List<String> result = new ArrayList<>();
        result.addAll(textsAndPartOfSpeech.values());
        return result;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder(request + "&");
        for (Map.Entry<String, String> entry : textsAndPartOfSpeech.entrySet()) {
            builder.append(entry.getKey()).append("#").append(entry.getValue()).append("&");
        }
        if (textsAndPartOfSpeech.size() > 0)
            builder = new StringBuilder(builder.substring(0, builder.length() - 1));
        else
            builder.append("^");
        return builder.toString();
    }
}