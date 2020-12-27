package interface_;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ArrayBlockingQueue;

public class Gui extends JFrame {
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JButton TIKButton;
    private JLabel label1;
    private JLabel label2;
    private JButton yesButton;
    private JButton noButton;
    private JButton nounButton;
    private JButton verbButton;
    private JButton adjectiveButton;
    private JButton noOneButton;
    private BufferedImage kart;
    private static String line1;
    private static String mark = "";


    public static ArrayBlockingQueue<String> pictures = new ArrayBlockingQueue<String>(1);
    public static ArrayBlockingQueue<String> questions = new ArrayBlockingQueue<String>(1);
    public static ArrayBlockingQueue<String> marks = new ArrayBlockingQueue<String>(1);

    public void cons() {
        setupUi();

        setMinimumSize(new Dimension(720, 550));
        setContentPane(panel1);
        setVisible(true);
        TIKButton.setCursor(new Cursor(Cursor.HAND_CURSOR));



        setTitle("Postironia");


        addComponentListener(new ComponentAdapter() {
                                 public void componentResized(ComponentEvent evt) {
                                     ImageIcon ic = null;
                                     try {
                                         ic = new ImageIcon(ScaledImage(kart, panel1.getWidth(), panel1.getHeight() - TIKButton.getHeight() - 41));
                                         label1.setIcon(ic);
                                     }
                                     catch (NullPointerException ex) {

                                     }
                                 }
                             }
        );

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


    public void otsenka() {
        String l = "";
        int i = 0;

        if(!line1.equals("")) {
            while ((line1.charAt(i) != '#') && (i < line1.length() - 1)) {
                i++;
            }
            if (i < line1.length() - 1)
                l = line1.substring(0, i);
            else
                l = line1;
        }

        if (i == 0)
        {
            marks.add(mark);
            mark = "";
            TIKButton.setEnabled(true);
            panel2.setVisible(false);
            panel3.setVisible(false);
            panel4.setVisible(false);

            return;
        }
        else if (l.charAt(3) == '-')
        {
            String l1 = "Связано ли слово \"" + l.charAt(7) + "\" с картинкой?";
            label2.setText(l1);
            panel2.setVisible(true);
            panel3.setVisible(true);
            panel4.setVisible(false);
            TIKButton.setEnabled(false);
        }
        else if(l.charAt(0) == 's')
        {
            String l1 = "Связаны ли по смыслу слова \"" + l.charAt(4) + "\" и \"" + l.charAt(6) + "\"?";

            label2.setText(l1);
            panel2.setVisible(true);
            panel3.setVisible(true);
            panel4.setVisible(false);
            TIKButton.setEnabled(false);
        }
        else if (l.charAt(0) == 'o')
        {
            String l1 = "Связаны ли орфографически слова \"" + l.charAt(4) + "\" и \"" + l.charAt(6) + "\"?";
            label2.setText(l1);
            panel2.setVisible(true);
            panel3.setVisible(true);
            panel4.setVisible(false);
            TIKButton.setEnabled(false);
        }
        else if (l.charAt(0) == 'p')
        {
            String l1 = "К какой части речи относится слово \"" + l.charAt(4) + "\"?";
            label2.setText(l1);
            panel2.setVisible(true);
            panel3.setVisible(false);
            panel4.setVisible(true);
            TIKButton.setEnabled(false);

        }
        else
        {
            String l1 = "Правильно ли орфографически написано слово \"" + l.charAt(3) + "\"?";
            label2.setText(l1);
            panel2.setVisible(true);
            panel3.setVisible(true);
            panel4.setVisible(false);
            TIKButton.setEnabled(false);
        }
        if(i < line1.length() - 1)
            line1 = line1.substring(l.length() + 1);
        else
            line1 = "";
    }

    public void setupUi() {
        panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(6, 4, new Insets(0, 0, 0, 0), 0, -1));
        panel1.setAlignmentX(0.5f);
        panel1.setAlignmentY(0.5f);
        label1 = new JLabel();
        label1.setHorizontalTextPosition(0);
        label1.setText("");
        panel1.add(label1, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        TIKButton = new JButton();
        File f = new File("qt.jpg");
        ImageIcon ic = null;
        try {
            ic = new ImageIcon(ImageIO.read(f));
        } catch (IOException e) {
            e.printStackTrace();
        }
        TIKButton.setIcon(ic);
        TIKButton.setText("");
        TIKButton.setMargin(new Insets(0, 0, 0, 0));
        panel1.add(TIKButton, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTH, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(220, 63), new Dimension(220, 63), new Dimension(220, 63), 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        panel2 = new JPanel();
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        label2 = new JLabel();
        label2.setText("");
        panel2.add(label2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panel3 = new JPanel();
        panel3.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel3, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        yesButton = new JButton();
        yesButton.setText("Да");
        panel3.add(yesButton, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        noButton = new JButton();
        noButton.setText("Нет");
        panel3.add(noButton, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panel4 = new JPanel();
        panel4.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel4, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        nounButton = new JButton();
        nounButton.setText("Существительное");
        panel4.add(nounButton, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        verbButton = new JButton();
        verbButton.setText("Глагол");
        panel4.add(verbButton, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        adjectiveButton = new JButton();
        adjectiveButton.setText("Прилагательное");
        panel4.add(adjectiveButton, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        noOneButton = new JButton();
        noOneButton.setText("Ничто из перечиленного");
        panel4.add(noOneButton, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));


        TIKButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                try {
                    String pic = pictures.poll();
                    String txt = questions.poll();
                    try {
                        line1 = Files.readString(Paths.get(txt));
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    kart = null;
                    File file = new File(pic);
                    kart = ImageIO.read(file);
                    ImageIcon ic = new ImageIcon(ScaledImage(kart, panel1.getWidth(), panel1.getHeight() - TIKButton.getHeight() - 41));
                    label1.setIcon(ic);
                    label1.setText("");
                    otsenka();
                }catch (NullPointerException | IOException ex) {
                    label1.setIcon(null);
                    label1.setText("Loading... Press one more time on \"ТЫК!\"");
                }

            }
        });
        yesButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                if (line1.length() == 0)
                    mark += "1";
                else
                    mark += "1#";

                otsenka();

            }
        });
        noButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (line1.length() == 0)
                    mark += "2";
                else
                    mark += "2#";

                otsenka();
            }
        });
        nounButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (line1.length() == 0)
                    mark += "существительное";
                else
                    mark += "существительное#";

                otsenka();
            }
        });
        verbButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (line1.length() == 0)
                    mark += "глагол";
                else
                    mark += "глагол#";

                otsenka();
            }
        });
        adjectiveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (line1.length() == 0)
                    mark += "прилагательное";
                else
                    mark += "прилагательное#";

                otsenka();
            }
        });
        noOneButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (line1.length() == 0)
                    mark += "нет";
                else
                    mark += "нет#";

                otsenka();
            }
        });
        panel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(kart != null) {
                    FileDialog dlg = new FileDialog(new Frame(),"Save pictures", FileDialog.SAVE);
                    dlg.setFile("Untitled.jpg");
                    dlg.setVisible(true);
                    String path = dlg.getDirectory() + dlg.getFile();
                    if (path.lastIndexOf(".jpg") == -1) {
                        path += ".jpg";
                    }
                    File file = new File(path);
                    try {
                        ImageIO.write(kart, "jpg", file);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        });
        panel2.setVisible(false);
    }


    private Image ScaledImage(Image img, int w, int h) {
        if(img != null) {
            BufferedImage resizedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = resizedImage.createGraphics();

            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage(img, 0, 0, w, h, null);
            g2.dispose();
            return resizedImage;
        }
        else {
            return null;
        }
    }

    // если все сломается, то cons - старый конструктор
    public void Gui(){
        Runnable task1 = new Runnable() {
            public void run() {
                cons();
            }
        };
        Thread thread1 = new Thread(task1);
        thread1.start();
    }


    /*public static void main(String[] args) {
        Gui f = new Gui();
    }*/
}