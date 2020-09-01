package my.nat.tvmaze.data.API.database;

import java.util.List;
import my.nat.tvmaze.data.entities.TvShowFav;
import my.nat.tvmaze.interfaces.FavoriteDTOInterface;

public class FavoriteDTO implements FavoriteDTOInterface {

    private FavoriteDao favDao;
    private  static FavoriteDTO instance;

    public FavoriteDTO(FavoriteDao favDao) {
        this.favDao = favDao;
    }

    public static FavoriteDTO getInstance(FavoriteDao favDao){
        if(instance==null){
            instance = new  FavoriteDTO(favDao);
        }
      return  instance;
    }
    @Override
    public void insertFavoriteData(TvShowFav fav) {
        favDao.insertFavoriteData(fav);
    }

    @Override
    public void updateFavoriteData(TvShowFav tvShowFav) {
    favDao.updateFavoriteData(tvShowFav);
    }

    @Override
    public void deleteFavoriteData(TvShowFav tvShowFav) {
        favDao.deleteFavoriteData(tvShowFav);
    }

    @Override
    public List<TvShowFav> getFavDBList() {
        return favDao.getFavDBList();
    }

    @Override
    public int isFavorite(int showID) {
       return favDao.isFavorite(showID);
    }


}
