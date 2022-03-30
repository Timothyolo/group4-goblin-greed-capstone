package com.Items;

public class Weapons extends Item{
    private long attack;
    public Weapons(String name, String desc, long attack, long value) {
        super(name, desc, value);
        setAttack(attack);
    }

    public long getAttack() {
        return attack;
    }

    public void setAttack(long attack) {
        this.attack = attack;
    }
}
