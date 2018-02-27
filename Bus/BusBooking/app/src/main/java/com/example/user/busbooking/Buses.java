package com.example.user.busbooking;

import com.google.gson.annotations.SerializedName;

/**
 * Created by LENOVO on 25-09-2017.
 */


public class Buses {
    @SerializedName("busname")
    public String busname;
    @SerializedName("bustype")
    public String bustype;
    @SerializedName("depature")
    public String depature;
    @SerializedName("arrival")
    public String arrival;
    @SerializedName("seats")
    public String seats;
    @SerializedName("fare")
    public String fare;
    @SerializedName("journeytime")
    public String journeytime;
    @SerializedName("image_url")
    public String image_url;

}
