package com.example.musicophile.gom;

public class ImageUploadInfo {

    public String PName;
    public String PSize;
    public String PPrice;
    public String PDate;


    public String PImage;

    public ImageUploadInfo() {

    }

    public ImageUploadInfo(String name, String url, String amount, String price, String duration ) {

        this.PName = name;
        this.PSize = amount;
        this.PPrice = price;
        this.PDate = duration;
        this.PImage= url;

    }

    public String getName() {
        return PName;
    }

    public String getAmount() {
        return PSize;
    }

    public String getPrice() {
        return PPrice;
    }

    public String getDuration() {
        return PDate;
    }

    public String getImageURL() {
        return PImage;
}


}




/*
* package com.example.musicophile.gom;

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

/*
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

