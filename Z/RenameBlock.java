import java.io.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class RenameBlock implements Runnable {
    private BlockingQueue<String> nextRenaming = new LinkedBlockingQueue<>();
    private BlockingQueue<String> questionsToRenaming = new LinkedBlockingQueue<>();
    private BlockingQueue<String[]> elements = new LinkedBlockingQueue<>();
    private File fileToSave = new File("C:\\Postironia\\Z\\renameBlock.txt");
    private Generator generator;

    public RenameBlock(Generator generator) {
        this.generator = generator;
        try {
            fileToSave.createNewFile();
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileToSave), "UTF8"));
            String readString = reader.readLine();
            if (readString != null)
                for (String string : readString.split("#"))
                    if (!string.equals("^"))
                        nextRenaming.offer(string);
            readString = reader.readLine();
            if (readString != null)
                for (String string : readString.split("#"))
                    if (!string.equals("^"))
                        questionsToRenaming.offer(string);
            for (String string = reader.readLine(); string != null; string = reader.readLine()) {
                String[] strings = new String[6];
                String[] elements = string.split("#");
                for (int i = 0; i < elements.length; i++)
                    if(elements[i].equals("^"))
                        elements[i] = "";
                System.arraycopy(elements, 0, strings, 0, elements.length);
                this.elements.offer(strings);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        System.out.println("Вход");
        File fileMark = new File("C:\\Postironia\\ZN\\mark.txt");
        File fileImage = new File("C:\\Postironia\\ZN\\image.jpg");
        File fileQuestions = new File("C:\\Postironia\\ZN\\text.txt");
        try {
            while (true) {
                while (fileImage.exists() || fileQuestions.exists())
                    Thread.sleep(100);
                System.out.println("я тута");
                System.out.println(nextRenaming.poll());
                File fileToRename = new File(nextRenaming.take());
                fileToRename.renameTo(fileImage);
                System.out.println(questionsToRenaming.poll());
                File fileToQuestionsRename = new File(questionsToRenaming.take());
                fileToQuestionsRename.renameTo(fileQuestions);
                try (RandomAccessFile fileMarkAccess = new RandomAccessFile(fileMark, "r")) {
                    String markS = fileMarkAccess.readLine();
                    while (markS == null) {
                        Thread.sleep(100);
                        markS = fileMarkAccess.readLine();
                    }
                    String[] result = elements.take();
                    result[5] = markS;
                    generator.putWord(result);
                }
                fileMark.delete();
                fileMark.createNewFile();

            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    public void putQuestions(String name) {
        questionsToRenaming.offer(name);
    }

    public void putName(String name) {
        nextRenaming.offer(name);
    }

    public void putElements(String[] elements) {
        this.elements.offer(elements);
    }

    public void close() {
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileToSave), "UTF8"));
            fileToSave.createNewFile();
            StringBuilder builder = new StringBuilder("");
            for (int i = 0; i < nextRenaming.size(); i++)
                builder.append(nextRenaming.poll()).append("#");
            if (builder.length() > 0)
                builder = new StringBuilder(builder.substring(0, builder.length() - 1));
            else
                builder.append("^");
            writer.write(builder.toString());
            writer.write("\r\n");
            builder = new StringBuilder("");
            for (int i = 0; i < questionsToRenaming.size(); i++)
                builder.append(questionsToRenaming.poll()).append("#");
            if (builder.length() > 0)
                builder = new StringBuilder(builder.substring(0, builder.length() - 1));
            else
                builder.append("^");
            writer.write(builder.toString());
            writer.write("\r\n");
            for (int i = 0; i < elements.size(); i++) {
                builder = new StringBuilder("");
                for (String element : elements.poll()) {
                    if (element.equals(""))
                        builder.append("^").append("#");
                    else
                    builder.append(element).append("#");
                }
                builder = new StringBuilder(builder.substring(0, builder.length() - 1));
                writer.write(builder.toString());
                writer.write("\r\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
