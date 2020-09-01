package my.nat.tvmaze.data.API.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

import my.nat.tvmaze.data.entities.Actor;
import my.nat.tvmaze.data.entities.TvShow;
import my.nat.tvmaze.data.entities.TvShowFav;

@Dao
public interface FavoriteDao {

    @Insert
    void insertFavoriteData(TvShowFav...fav);
    @Update
    void updateFavoriteData(TvShowFav tvShowFav);
    @Delete
    void deleteFavoriteData(TvShowFav tvShowFav);
    @Query("SELECT * FROM TvShowFav")
    List<TvShowFav> getFavDBList();
    @Query("SELECT EXISTS(SELECT 1 FROM TvShowFav WHERE id=:showId)")
    int isFavorite(int showId);

}
