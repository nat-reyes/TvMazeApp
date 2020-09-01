package my.nat.tvmaze.data.entities;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class mActor implements Serializable {

    @SerializedName("score")
    @Expose
    private Double score;
    @SerializedName("person")
    @Expose
    private Actor actor;

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    @Override
    public String toString() {
        return "SearchActor{" +
                "score=" + score +
                ", actor =" + actor +
                '}';
    }
}