package my.nat.tvmaze.data.API.database;

import java.util.List;

import my.nat.tvmaze.data.entities.TvShowFav;
import my.nat.tvmaze.interfaces.FavoriteDTOInterface;

public class FavoriteRepository implements FavoriteDTOInterface {

    private FavoriteDTOInterface favoriteDTOInterface;

    public FavoriteRepository(FavoriteDTOInterface favoriteDTOInterface) {
        this.favoriteDTOInterface = favoriteDTOInterface;
    }

    private static FavoriteRepository instance;

    public static FavoriteRepository getInstance(FavoriteDTOInterface favoriteDTOInterface){
        if(instance==null){
            instance  = new FavoriteRepository(favoriteDTOInterface);
        }
        return instance;
    }


    @Override
    public void insertFavoriteData(TvShowFav tvShowFav) {
     favoriteDTOInterface.insertFavoriteData(tvShowFav);
    }

    @Override
    public void updateFavoriteData(TvShowFav tvShowFav) {
     favoriteDTOInterface.updateFavoriteData(tvShowFav);
    }

    @Override
    public void deleteFavoriteData(TvShowFav tvShowFav) {
        favoriteDTOInterface.deleteFavoriteData(tvShowFav);

    }

    @Override
    public List<TvShowFav> getFavDBList() {
        return favoriteDTOInterface.getFavDBList();
    }

    @Override
    public int isFavorite(int showID) {
        return favoriteDTOInterface.isFavorite(showID);
    }
}
