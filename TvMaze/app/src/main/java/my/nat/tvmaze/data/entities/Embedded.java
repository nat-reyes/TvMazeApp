package my.nat.tvmaze.data.entities;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Embedded implements Serializable {

    @SerializedName("show")
    @Expose
    private TvShow show;

    public TvShow getShow() {
        return show;
    }

    public void setShow(TvShow show) {
        this.show = show;
    }

    @Override
    public String toString() {
        return "Embedded{" +
                "show=" + show +
                '}';
    }
}