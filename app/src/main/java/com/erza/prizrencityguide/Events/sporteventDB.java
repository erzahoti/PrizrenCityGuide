package com.erza.prizrencityguide.Events;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Egzi on 16.1.2017.
 */

public class sporteventDB {

    @SerializedName("id")
    public int id;
    @SerializedName("emri")
    public String emri;
    @SerializedName("kundershtari")
    public String kundershtari;
    @SerializedName("ora")
    public String ora;
    @SerializedName("lokacioni")
    public String lokacioni;
    @SerializedName("pershkrimi")
    public String pershkrimi;
    @SerializedName("imazhiEkipit")
    public String imazhiEkipit;
    @SerializedName("imazhiKundershtarit")
    public String imazhiKundershtarit;
    @SerializedName("cover")
    public String cover;
    @SerializedName("info")
    public String info = "INFO";


}
