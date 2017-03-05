package mhci.teamsix.ugs.incampus.util;

import java.util.HashMap;

/**
 * Created by Vincey on 6/3/2017.
 */

public class FoodStore {
    private String name;
    private int likes;
    private int lastPosted;
    private String[] imageurl;
    private String phone;
    private int startPrice;
    private int endPrice;
    private HashMap<String, String> userComments = new HashMap<String, String>();
    private int reviews;

    private int image;
    private String author;
    public FoodStore(String name, String author, int image){
        this.name = name;
        this.author = author;
        this.image = image;
    }

    public FoodStore() {

    }

    public FoodStore(String name, int likes, int lastPosted, String[] imageurl, String phone, int startPrice, int endPrice, HashMap<String, String> userComments, int reviews) {
        this.name = name;
        this.likes = likes;
        this.lastPosted = lastPosted;
        this.imageurl = imageurl;
        this.phone = phone;
        this.startPrice = startPrice;
        this.endPrice = endPrice;
        this.userComments = userComments;
        this.reviews = reviews;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getLastPosted() {
        return lastPosted;
    }

    public void setLastPosted(int lastPosted) {
        this.lastPosted = lastPosted;
    }

    public String[] getImageurl() {
        return imageurl;
    }

    public void setImageurl(String[] imageurl) {
        this.imageurl = imageurl;
    }

    public int getImage(){return image;}
    public void setImage(int image){this.image = image;}

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(int startPrice) {
        this.startPrice = startPrice;
    }

    public int getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(int endPrice) {
        this.endPrice = endPrice;
    }

    public HashMap<String, String> getUserComments() {
        return userComments;
    }

    public void setUserComments(HashMap<String, String> userComments) {
        this.userComments = userComments;
    }

    public int getReviews() {
        return reviews;
    }

    public void setReviews(int reviews) {
        this.reviews = reviews;
    }
}
