package controller;

import Neuro.Postironia;
import interface_.Gui;
import ru.parse.Parser;

import java.io.IOException;

public class Controller {
    public static void main(){
        Parser parser = new Parser();
        Gui gui = new Gui();
        Postironia post = new Postironia();
        Runnable task1 = new Runnable() {
            public void run() {
                try {
                    post.oldmain(parser,gui);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread1 = new Thread(task1);
        thread1.start();
    }
}
