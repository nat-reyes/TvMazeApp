package my.nat.tvmaze.data.entities;

import my.nat.tvmaze.data.entities.Image;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import java.util.List;


@Entity(tableName = "TvShowFav")
public class TvShowFav {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private Integer idTvShow;
    @ColumnInfo(name= "isFavorite")
    private boolean isFav;


    public TvShowFav(Integer idTvShow, boolean isFav) {
        this.idTvShow = idTvShow;
        this.isFav = isFav;
    }

    public boolean isFav() {
        return isFav;
    }

    public void setFav(boolean fav) {
        isFav = fav;
    }

    public Integer getIdTvShow() {
        return idTvShow;
    }

    public void setIdTvShow(Integer idTvShow) {
        this.idTvShow = idTvShow;
    }





}
