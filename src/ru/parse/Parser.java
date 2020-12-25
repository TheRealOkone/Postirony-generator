package ru.parse;

import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;

public class Parser {
    static {
        System.loadLibrary("TNParser");
    }
    public static ArrayBlockingQueue<File> qjpg;
    public static ArrayBlockingQueue<File> qtxt;
    public void checkout(){
        int i;
        ArrayBlockingQueue<File> dropjpg = new ArrayBlockingQueue<File>(500);
        ArrayBlockingQueue<File> droptxt = new ArrayBlockingQueue<File>(500);
        for(i=0;i<500;i++){
            File filejpg = new File("KZ", "image" + String.valueOf(i) +".jpg");
            File filetxt = new File("KZ", "request" + String.valueOf(i) +".txt");
            if(filejpg.isFile() && filetxt.isFile()){
                dropjpg.add(filejpg);
                droptxt.add(filetxt);
            }
        }
        qjpg = dropjpg;
        qtxt = droptxt;

    }



    static native void activate();
    static native void deactivate();
    public void Parser(){
        Runnable task1 = new Runnable() {
            public void run() {
                activate();
            }
        };
        Runnable task2 = new Runnable() {
            public void run() {
                while (true) {
                    checkout();
                }
            }
        };
        Thread thread1 = new Thread(task1);
        thread1.start();
        Thread thread2 = new Thread(task2);
        thread2.start();

    }


}
