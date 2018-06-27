package com.example.musicophile.gom;

public class MoreProductInfo {

    public String PName;
    public String PSize;
    public String PPrice;
    public String PDate;
    public String FName;
    public String Flocation;
    public String FEmail;
    public String FPhonenumber;

    public String PImage;

    public MoreProductInfo(String name, String url, String amount, String price, String duration, String femail, String flocation, String fname, String fphonenumber ) {

        this.PName = name;
        this.PSize = amount;
        this.PPrice = price;
        this.PDate = duration;
        this.PImage= url;
        this.FEmail = femail;
        this.Flocation = flocation;
        this.FName = fname;
        this.FPhonenumber = fphonenumber;
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

    public String getFName() {
        return FName;
    }
    public String getFlocation() {
        return Flocation;
    }
    public String getFPhonenumber() {
        return FPhonenumber;
    }
    public String getFEmail(){
        return FEmail;
    }
    public String getImageURL() {
        return PImage;
    }
}


