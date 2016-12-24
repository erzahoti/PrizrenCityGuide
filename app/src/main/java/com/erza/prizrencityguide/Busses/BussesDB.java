package com.erza.prizrencityguide.Busses;

import com.google.gson.annotations.SerializedName;

public class BussesDB {

        @SerializedName("id")
        public int id;
        @SerializedName("linja")
        public String linja;
        @SerializedName("orari")
        public String orari;
        @SerializedName("koment_shtese")
        public String koment_shtese;
    }

