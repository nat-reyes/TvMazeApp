package my.nat.tvmaze.interfaces;

import java.util.ArrayList;
import java.util.List;

import my.nat.tvmaze.data.entities.Episode;
import my.nat.tvmaze.data.entities.TvShow;
import my.nat.tvmaze.data.entities.TvShowFav;

public interface FavoriteShowContractInterface {

    interface IfavoriteInteractor {
        void getFavoriteShowListApi(List<TvShowFav> favs);
    }

    interface IfavoriteView {

        void showLoading();
        void hideLoading();
        void setDataReceived(ArrayList<TvShow> shows);
        void onErrorLoading(String message);

    }

    interface IfavoritePresenter {

        void onShowError(String error);
        void getFavoriteShowList(List<TvShowFav>favs);
        void showFavoriteList(ArrayList<TvShow> favShowsList);

    }

    interface favItemClickListener {
        void onFavItemClick(int position);
    }

}
