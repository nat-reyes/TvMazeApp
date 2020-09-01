package my.nat.tvmaze.data.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class mShow implements Serializable {

    @SerializedName("score")
    @Expose
    private String score;
    @SerializedName("show")
    @Expose
    private TvShow show;

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public TvShow getShow() {
        return show;
    }

    public void setShow(TvShow show) {
        this.show = show;
    }

    @Override
    public String toString() {
        return "Query{" +
                "score='" + score + '\'' +
                ", show=" + show +
                '}';
    }

}
