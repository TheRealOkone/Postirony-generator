package ru.parse;

public class Parser {
    static {
        System.loadLibrary("TNParser");
    }
    static native void activate();
    static native void deactivate();
    public static void main(String[] argv){
        activate();
    }
}
