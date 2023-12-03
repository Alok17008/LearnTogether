package com.collageproject.learntogether;

public class Userinfo {

    String Name;
    String Age;
    String Gender;
    String Homeadress;
    String Town;

    public Userinfo(String name, String age, String gender, String homeadress, String town) {
        Name = name;
        Age = age;
        Gender = gender;
        Homeadress = homeadress;
        Town = town;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getHomeadress() {
        return Homeadress;
    }

    public void setHomeadress(String homeadress) {
        Homeadress = homeadress;
    }

    public String getTown() {
        return Town;
    }

    public void setTown(String town) {
        Town = town;
    }

}