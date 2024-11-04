package fpt.anhdhph.lab1_a2_ph25329.model;

public class Category {
    int id;
    String name;

    public String toString (){
        return "ID catgory: " + id + ", name: " + name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
