package com.erza.prizrencityguide.FoodDrink;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Donika on 12/24/2016.
 */

public class Food_Drink_DB {


    @SerializedName("id")
    public int id;
    @SerializedName("emri")
    public String emri;
    @SerializedName("lloji")
    public String lloji;
    @SerializedName("lokacioni")
    public String lokacioni;
    @SerializedName("koordinantat")
    public String koordinantat;
    @SerializedName("pershkrimi")
    public String pershkrimi;
    @SerializedName("imazhi_link")
    public String imazhi_link;
}
