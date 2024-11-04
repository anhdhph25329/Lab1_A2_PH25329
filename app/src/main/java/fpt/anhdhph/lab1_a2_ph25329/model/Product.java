package fpt.anhdhph.lab1_a2_ph25329.model;

public class Product {
    int id;
    String name;
    double price;
    int id_cat;

    public String toString (){
        return "ID catgory: " + id + ", name: " + name + ", price: " + price + ", id_cat: " + id_cat;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId_cat() {
        return id_cat;
    }

    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }
}
