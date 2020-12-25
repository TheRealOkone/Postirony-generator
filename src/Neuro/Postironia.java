package Neuro;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;

public class Postironia {
    public static void main(String[] args) throws IOException {
        Generator generator = new Generator();
        String pathToPicture = "KZ";
        String pathToResult = "ZN";
        String pathToMark = "ZN\\mark.txt";
        String pathToRequest = "KZ\\request.txt";
        File filePicture = new File(pathToPicture);
        filePicture.mkdirs();
        pathToPicture += "\\image.jpg";
        filePicture = new File(pathToPicture);
        File file = new File(pathToResult);
        file.mkdirs();
        File fileExit = new File("exit.txt");
        fileExit.createNewFile();
        File fileMark = new File(pathToMark);
        File fileRequest = new File(pathToRequest);
        try (RandomAccessFile fileExitReader = new RandomAccessFile(fileExit, "r")) {
            end:
            while (fileExitReader.readLine() == null) {
                while (!filePicture.exists() || !fileRequest.exists())
                    try {
                        Thread.sleep(100);
                        if (fileExitReader.readLine() != null)
                            break end;
                    } catch (InterruptedException e) {
                        break;
                    }
                String request;
                try (BufferedReader fileRequestAccess = new BufferedReader(new InputStreamReader(new FileInputStream(fileRequest), "UTF8"))) {
                    request = fileRequestAccess.readLine();
                }
                fileRequest.delete();
                String[] texts = new String[5];
                for (int i = 0; i < 5; i++)
                    texts[i] = "";
                String text = "";
                try {
                    texts = generator.getText(request);
                    text = texts[0];
                } catch (Exception e) {
                    e.printStackTrace();
                }
                BufferedImage read;
                try {
                    read = ImageIO.read(filePicture);
                    Graphics2D g = (Graphics2D) read.getGraphics();
                    g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
                    Font font = getFont("TimesRoman", text, read.getWidth());
                    g.setFont(font);
                    FontRenderContext frc = new FontRenderContext(null, true, true);
                    Rectangle2D r2D = font.getStringBounds(text, frc);
                    int rWidth = (int) Math.round(r2D.getWidth());
                    int x = (read.getWidth() / 2) - (rWidth / 2);
                    int y = read.getHeight();
                    if (read.getHeight() > 10)
                        y = read.getHeight() - 10;
                    g.setColor(getColor(read, x, y));
                    g.drawString(text, x, y);
                    g.dispose();
                } catch (Exception e) {
                    e.printStackTrace();
                    filePicture.delete();
                    continue;
                }
                String name = "ZN\\image.jpg";
                String name2 = "ZN\\text.txt";
                File fileText = new File(name2);
                File resultImage = new File(name);
                while (fileText.exists() || resultImage.exists())
                    try {
                        Thread.sleep(100);
                        if (fileExitReader.readLine() != null)
                            break end;
                    } catch (InterruptedException e) {
                        break;
                    }
                fileText.createNewFile();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileText), "UTF-8"));
                writer.write(texts[2]);
                writer.close();
                ImageIO.write(read, "jpg", resultImage);
                filePicture.delete();
                while (!fileMark.exists()) {
                    try {
                        Thread.sleep(100);
                        if (fileExitReader.readLine() != null)
                            break end;
                    } catch (InterruptedException e) {
                        break;
                    }
                }
                String mark;
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileMark), "UTF-8"))) {
                    for (mark = reader.readLine(); mark == null; mark = reader.readLine()) {
                        Thread.sleep(100);
                        if (fileExitReader.readLine() != null)
                            break end;
                    }
                } catch (InterruptedException e) {
                    break;
                }
                while (!fileMark.delete())
                    try {
                        Thread.sleep(100);
                        if (fileExitReader.readLine() != null)
                            break end;
                    } catch (InterruptedException e) {
                        break;
                    }
                String[] strings = new String[6];
                System.arraycopy(texts, 0, strings, 0, texts.length);
                strings[5] = mark;
                System.out.println(Arrays.toString(strings));
                generator.putWord(strings);
            }
        }
        deleteDirectory(file);
        file.mkdir();
        generator.close();
    }

    private static void deleteDirectory(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (String aChildren : children) {
                File f = new File(dir, aChildren);
                deleteDirectory(f);
            }
            dir.delete();
        } else dir.delete();
    }

    private static Color getColor(BufferedImage image, int x, int y) {
        int black = 0;
        int white = 0;
        int clr;
        int red;
        int green;
        int blue;
        for (int i = -10; i < 10; i++) {
            int newX = x + i;
            if (newX > 0 && newX < image.getWidth())
                for (int j = -10; j < 10; j++) {
                    int newY = y + j;
                    if (newY > 0 && newY < image.getHeight()) {
                        clr = image.getRGB(newX, newY);
                        red = (clr & 0x00ff0000) >> 16;
                        green = (clr & 0x0000ff00) >> 8;
                        blue = clr & 0x000000ff;
                        if (red >= 100 && green >= 100 && blue >= 100 && Math.abs(red - green) < 47 && Math.abs(red - blue) < 47 && Math.abs(blue - green) < 47)
                            black++;
                        else if (red >= 200 && green >= 200 && blue >= 200)
                            black++;
                        else if (red - blue > 100 && green - blue > 100)
                            black++;
                        else
                            white++;
                    }
                }
        }
        if (white >= black)
            return Color.WHITE;
        else
            return Color.BLACK;
    }


    private static Font getFont(String fontS, String text, int widthImage) {
        FontRenderContext frc = new FontRenderContext(null, true, true);
        Font font = null;
        for (int size = 200; size > 0; size -= 15) {
            font = new Font(fontS, Font.PLAIN, size);
            if (widthImage - 15 > (int) Math.round(font.getStringBounds(text, frc).getWidth()))
                return font;
        }
        return font;
    }
}

