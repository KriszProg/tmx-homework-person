package hu.tmx.model;

import hu.tmx.customException.BigamyException;
import hu.tmx.customException.InvalidAgeException;
import hu.tmx.customException.SameSexException;

public class Person {
    private String name;
    private boolean isMan;
    private int age;
    private boolean isMarried = false;

    public Person(String name, boolean isMan, int age) {
        this.name = name;
        this.isMan = isMan;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMan() {
        return isMan;
    }

    public void setIsMan(boolean man) {
        this.isMan = man;
    }

    public int getAge() {
        return age;
    }

    public boolean isMarried() {
        return isMarried;
    }

    private void setIsMarried(boolean married) {
        isMarried = married;
    }

    public boolean marry(Person personWantToMarry) {
        if (this.isMan == personWantToMarry.isMan()) {
            throw new SameSexException("Same sex marriage is not allowed");
        }
        if (this.age < 18 || personWantToMarry.getAge() < 18) {
            throw new InvalidAgeException("Marriage is not allowed under age 18");
        }
        if (this.isMarried || personWantToMarry.isMarried) {
            throw new BigamyException("Bigamy is not allowed");
        }
        this.setIsMarried(true);
        personWantToMarry.setIsMarried(true);
        return true;
    }
}
