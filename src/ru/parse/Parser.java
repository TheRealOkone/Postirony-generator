package ru.parse;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Parser {
    static {
        System.loadLibrary("TNParser");
    }
    static native void activate();
    static native void deactivate();
    public LinkedBlockingQueue<File> qjpg = new LinkedBlockingQueue<File>();
    public LinkedBlockingQueue<File> qtxt = new LinkedBlockingQueue<File>();
    public void checkout(){
        int i;
        Object[] bruh = qjpg.toArray();
        Object[] brruh = qjpg.toArray();
        List<File> jjj = new ArrayList<>();
        List<File> ttt = new ArrayList<>();
        for(int j =0; j < qjpg.size();j++){
            jjj.add((File)bruh[j]);
            ttt.add((File)brruh[j]);
        }

        for(i=0;i<500;i++){
            File filejpg = new File("KZ", "image" + String.valueOf(i) +".jpg");
            File filetxt = new File("KZ", "request" + String.valueOf(i) +".txt");
            if((!jjj.contains(filejpg)) && (!ttt.contains(filetxt)) && filejpg.isFile() && filetxt.isFile()){
                try {
                    Thread.sleep(4000);
                    qjpg.put(filejpg);
                    qtxt.put(filetxt);
                } catch (InterruptedException e) {
                    deactivate();
                }

            }
        }
    }

    public Parser(){

        Runnable task1 = Parser::activate;
        Runnable task2 = () -> {
            while (true) {
                checkout();
            }
        };
        Thread thread1 = new Thread(task1);
        thread1.setDaemon(true);
        thread1.start();
        Thread thread2 = new Thread(task2);
        thread2.setDaemon(true);
        thread2.start();

    }


}
