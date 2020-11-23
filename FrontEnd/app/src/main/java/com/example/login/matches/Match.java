package com.example.login.matches;
public class Match {
    private String name;
    private int age;

    /**
     * constructor
     * @param name
     * @param age
     */
    public Match(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
