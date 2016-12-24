package com.erza.prizrencityguide.Accommodation;

import com.google.gson.annotations.SerializedName;

/**
 * Created by thaqibsm on 12/24/2016.
 */

public class AccommodationDB {

        @SerializedName("id")
        public int id;
        @SerializedName("emri")
        public String emri;
        @SerializedName("lloji")
        public String lloji;
        @SerializedName("cmimi")
        public Double cmimi;
        @SerializedName("lokacioni")
        public String lokacioni;
        @SerializedName("koordinatat")
        public String koordinatat;
        @SerializedName("pershkrimi")
        public String pershkrimi;
        @SerializedName("imazhi_link")
        public String imazhi_link;
    }

