package com.example.musicophile.gom;

public class Prouct {


  private int id;
    private String name;
   // private String price;
    private String image;


 /*public Prouct(){}

 public Prouct(String name, String image ){

     this.name = name;
     this.image = image;

 }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;

    }

*/
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

   /*public String getPrice() {
        return price;
   }

   public void setPrice(String price) {
        this.price = price;
    }

*/
    public String getImage() {
        return image;

    }

    public void setImage(String image) {
        this.image = image;
    }
}

