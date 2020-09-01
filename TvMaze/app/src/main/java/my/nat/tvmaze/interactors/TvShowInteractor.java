package my.nat.tvmaze.interactors;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import my.nat.tvmaze.data.API.TvMazeClient;
import my.nat.tvmaze.data.entities.TvShow;
import my.nat.tvmaze.interfaces.TvShowContractInterface;
import my.nat.tvmaze.presenter.TvShowPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvShowInteractor implements TvShowContractInterface.ItvShowInteractor {
    private TvShowPresenter presenter;
    public TvShowInteractor(TvShowPresenter presenter) {
        this.presenter = presenter;
    }



    @Override
    public void getShowListInteractor(int page) {
        Call<List<TvShow>> tvShowCall = TvMazeClient.getAPI().getShows(page);
        tvShowCall.enqueue(new Callback<List<TvShow>>() {
            @Override
            public void onResponse(@NonNull Call<List<TvShow>> call, @NonNull Response<List<TvShow>> response) {

                if (response.isSuccessful() && response.body() != null) {
                    List<TvShow> shows =  response.body();
                    Log.e("Interactor", tvShowCall.toString());
                    if(shows!=null){
                        presenter.showList((ArrayList<TvShow>) shows);
                    }
                } else {
                    Log.e("Response", "is null");
                }
            }
            @Override
            public void onFailure(Call<List<TvShow>> call, Throwable t) {
            Log.e("Interact", t.getLocalizedMessage());
            presenter.onShowError(t.getLocalizedMessage());
        }
    });
    }

}
