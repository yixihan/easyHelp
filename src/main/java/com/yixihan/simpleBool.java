package com.yixihan;

public class simpleBool {
    private boolean value;
    private String name;

    public simpleBool(boolean value, String name){
        this.value = value;
        this.name = name;
    }

    public boolean getValue(){
        return this.value;
    }

    public String getName(){
        return this.name;
    }

    public void changeValue(boolean value){
        this.value = value;
    }

    public void changeName(String name){
        this.name = name;
    }
}
