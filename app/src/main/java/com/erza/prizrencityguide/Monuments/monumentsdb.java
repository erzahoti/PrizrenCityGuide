package com.erza.prizrencityguide.Monuments;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Erza on 12/23/2016.
 */

public class monumentsdb {

    @SerializedName("id")
    public int id;
    @SerializedName("emri")
    public String emri;
    @SerializedName("lokacioni")
    public String lokacioni;
    @SerializedName("pershkrimi")
    public String pershkrimi;
    @SerializedName("imazhi_link")
    public String imazhi_link;
    @SerializedName("read_more")
    public String read_more = "Read more...";

}
