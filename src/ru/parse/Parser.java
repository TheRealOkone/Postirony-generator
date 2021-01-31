package ru.parse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
    private List<File> bank = new ArrayList<>();
    public void checkout(){
        int i;
        boolean che = true;
        boolean prev = true;
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
            try {
                BufferedReader a;
                System.out.println(a = new BufferedReader(new FileReader(filejpg)));
                a.close();
                System.out.println(a = new BufferedReader(new FileReader(filetxt)));
                a.close();
            }catch(Exception e){
                che = false;
            }
            if(bank.contains(filejpg) || bank.contains(filetxt)){
                prev = false;
            }
            bank.add(filejpg);
            bank.add(filetxt);
            if((!jjj.contains(filejpg)) && (!ttt.contains(filetxt)) && filejpg.isFile() && filetxt.isFile() && che && prev){

                try {
                    qjpg.put(filejpg);
                    qtxt.put(filetxt);

                } catch (InterruptedException e) {
                    deactivate();
                }

            }
            che = true;
            prev = true;
        }
    }

    public Parser(){

        Runnable task1 = Parser::activate;
        Runnable task2 = () -> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
