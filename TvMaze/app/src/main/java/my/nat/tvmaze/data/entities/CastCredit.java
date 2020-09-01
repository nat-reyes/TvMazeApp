package my.nat.tvmaze.data.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CastCredit {

    @SerializedName("_embedded")
    @Expose
    private Embedded embedded;

    public Embedded getEmbedded() {
        return embedded;
    }

    public void setEmbedded(Embedded embedded) {
        this.embedded = embedded;
    }

    @Override
    public String toString() {
        return "CastCredit{" +
                "embedded=" + embedded +
                '}';
    }

}
