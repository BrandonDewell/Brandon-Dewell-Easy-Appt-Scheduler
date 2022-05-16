package model;

public class Country {
    private int id;
    private String name;

    public Country(int id, String name) {  // constructor
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
