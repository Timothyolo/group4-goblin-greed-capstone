package com.Items;

public class Item {
    private String name;
    private String desc;
    private long attack;


    public Item(String name, String desc, long attack) {
        setName(name);
        setDesc(desc);
        setAttack(attack);
    }

    public Item(String name, String desc) {
        setName(name);
        setDesc(desc);
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public long getAttack() {
        return attack;
    }

    public void setAttack(long attack) {
        this.attack = attack;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
