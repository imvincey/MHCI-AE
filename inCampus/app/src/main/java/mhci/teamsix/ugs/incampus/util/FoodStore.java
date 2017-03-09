package mhci.teamsix.ugs.incampus.util;

import java.io.Serializable;

/**
 * Created by Vincey on 6/3/2017.
 */

public class FoodStore implements Serializable {
    private String sid;
    private String name;
    private String price_range;
    private String desc;
    private String[] img;
    private String location;
    private String coupon;

    public FoodStore() {
    }

    public FoodStore(String sid, String name, String price_range, String desc, String img[], String location, String coupon) {
        this.sid = sid;
        this.name = name;
        this.price_range = price_range;
        this.desc = desc;
        this.img = img;
        this.location = location;
        this.coupon = coupon;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice_range() {
        return price_range;
    }

    public void setPrice_range(String price_range) {
        this.price_range = price_range;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String[] getImg() {
        return img;
    }

    public void setImg(String[] img) {
        this.img = img;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }
}
