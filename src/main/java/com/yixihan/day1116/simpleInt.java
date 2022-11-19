package com.yixihan.day1116;

public class simpleInt {
    private int value;
    private String name;

    public simpleInt(int value, String name){
        this.value = value;
        this.name = name;
    }

    public int getValue(){
        return this.value;
    }

    public String getName(){
        return this.name;
    }

    public void changeValue(int value){
        this.value = value;
    }

    public void changeName(String name){
        this.name = name;
    }
}
