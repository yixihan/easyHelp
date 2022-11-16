package com.yixihan;

public class Variable<T> {
    //should we use another two class extends from Variable to represent INT and BOOL?
    /**
     * The attributes of variable
     * (Including value, name and label)
     */
    public T value;
    public String name;
    public String lab;              // is this label for statement or variable?
    public String type;

    /**
     * The constructors of class
     * (Different parameter)
     */
    public Variable(){
    }

    public Variable(T value,String type){
        this.value = value;
        this.type = type;
    }

    //    /**
    //     * Set and get methods
    //     */


    @Override
    public String toString() {
        return null;
    }
}

