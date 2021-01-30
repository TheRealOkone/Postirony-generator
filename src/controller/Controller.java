package controller;

import Neuro.Postironia;
import interface_.Gui;
import ru.parse.Parser;

import java.io.IOException;

public class Controller {
    public static void main(String[] args){
        Parser parser = new Parser();
        Gui gui = new Gui();
        Postironia post = new Postironia();
        Runnable task1 = () -> post.oldmain(parser,gui);
        Thread thread1 = new Thread(task1);
        thread1.start();
    }
}
