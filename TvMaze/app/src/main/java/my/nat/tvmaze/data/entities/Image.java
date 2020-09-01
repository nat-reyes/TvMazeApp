package my.nat.tvmaze.data.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


@Entity(foreignKeys = {
        @ForeignKey(
                entity = Image.class,
                parentColumns = "id",
                childColumns = "idObjfk"
        )})

public class Image implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @SerializedName("medium")
    @Expose
    private String medium;
    @SerializedName("original")
    @Expose
    private String original;
    @ColumnInfo(name = "idObjfk")
    private int idObjfk;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdObjfk() {
        return idObjfk;
    }

    public void setIdObjfk(int idObjfk) {
        this.idObjfk = idObjfk;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }
    @Override
    public String toString() {
        return "Image{" +
                "medium='" + medium + '\'' +
                ", original='" + original + '\'' +
                '}';
    }

}
