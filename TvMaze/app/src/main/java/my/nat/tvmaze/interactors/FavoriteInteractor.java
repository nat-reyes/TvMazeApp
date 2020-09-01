package my.nat.tvmaze.interactors;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import my.nat.tvmaze.data.API.TvMazeClient;
import my.nat.tvmaze.data.entities.TvShow;
import my.nat.tvmaze.data.entities.TvShowFav;
import my.nat.tvmaze.data.entities.mShow;
import my.nat.tvmaze.interfaces.FavoriteShowContractInterface;
import my.nat.tvmaze.presenter.FavoritePresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriteInteractor implements FavoriteShowContractInterface.IfavoriteInteractor {

     private ArrayList<TvShow> mListShows;
     private FavoritePresenter presenter;
     private int end;

    public FavoriteInteractor( FavoritePresenter presenter) {
        this.presenter = presenter;
        mListShows = new ArrayList<>();
    }


    @Override
    public void getFavoriteShowListApi(List<TvShowFav> favs) {
        end = favs.size();
        for (TvShowFav mListFav: favs) {
            searchFavShows(mListFav.getIdTvShow());
            end--;
        }
    }


    public void searchFavShows(int idShow){

        Call<TvShow> tvShowCall = TvMazeClient.getAPI().getShow(idShow);
        tvShowCall.enqueue(new Callback<TvShow>() {
            @Override
            public void onResponse(Call<TvShow> call, Response<TvShow> response) {
                if (response.isSuccessful() && response.body() != null) {
                    TvShow tvShow = response.body();
                     mListShows.add(tvShow);
                     if(end==0){
                        presenter.showFavoriteList(mListShows);
                     }
                } else {
                    Log.e("Response", "is null");
                }
            }

            @Override
            public void onFailure(Call<TvShow> call, Throwable t) {
                Log.e("Interact", t.getLocalizedMessage());
                presenter.onShowError(t.getLocalizedMessage());
            }

        });


    }
}
