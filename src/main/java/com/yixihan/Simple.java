package com.yixihan;

public class Simple {
    private String label;
    private simpleInt theInt;
    private simpleBool bool;

    public Simple(String label, simpleInt theInt, simpleBool bool) {
        this.label = label;
        this.theInt = theInt;
        this.bool = bool;
    }

    public String getLabel() {
        return this.label;
    }

    public simpleInt getInt() {
        return this.theInt;
    }

    public simpleBool getBool() {
        return this.bool;
    }

    public void changeLabel(String label) {
        this.label = label;
    }
}
