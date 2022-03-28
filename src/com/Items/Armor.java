package com.Items;

public class Armor extends Item{
    private long defence;
    public Armor(String name, String desc, long defence, long value) {
        super(name, desc, value);
        setDefence(defence);
    }

    public long getDefence() {
        return defence;
    }

    public void setDefence(long defence) {
        this.defence = defence;
    }
}
