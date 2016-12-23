package com.erza.prizrencityguide.Entertainment;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Egzi on 22.12.2016.
 */

public class entertainmentDB {

    @SerializedName("id")
    public int id;
    @SerializedName("emri")
    public String emri;
    @SerializedName("website")
    public String website;
    @SerializedName("lloji")
    public String lloji;
    @SerializedName("pershkrimi")
    public String pershkrimi;
    @SerializedName("imazhi_link")
    public String imazhi_link;
}
