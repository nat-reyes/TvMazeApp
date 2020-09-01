package my.nat.tvmaze.interfaces;

import java.util.List;

import my.nat.tvmaze.data.entities.TvShowFav;

public interface FavoriteDTOInterface {

    void insertFavoriteData(TvShowFav fav);

    void updateFavoriteData(TvShowFav tvShowFav);

    void deleteFavoriteData(TvShowFav tvShowFav);

    List<TvShowFav> getFavDBList();

    int isFavorite(int showID);

}
