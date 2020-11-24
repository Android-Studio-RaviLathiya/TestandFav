package com.test.Fav;

public class FavItem {

    String id,username, restaurantname;

    public FavItem(String id, String username, String restaurantname) {
        this.id = id;
        this.username = username;
        this.restaurantname = restaurantname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRestaurantname() {
        return restaurantname;
    }

    public void setRestaurantname(String restaurantname) {
        this.restaurantname = restaurantname;
    }
}
