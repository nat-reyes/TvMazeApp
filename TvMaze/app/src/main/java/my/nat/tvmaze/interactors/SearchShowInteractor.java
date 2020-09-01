package my.nat.tvmaze.interactors;

import android.util.Log;

import androidx.annotation.NonNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import my.nat.tvmaze.data.API.TvMazeClient;
import my.nat.tvmaze.data.entities.TvShow;
import my.nat.tvmaze.data.entities.mShow;
import my.nat.tvmaze.interfaces.SearchShowContractInterface;
import my.nat.tvmaze.presenter.SearchTvShowPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchShowInteractor implements SearchShowContractInterface.IsearchShowInteractor {

    private SearchTvShowPresenter presenter;
    ArrayList<TvShow> shows;
    public SearchShowInteractor(SearchTvShowPresenter presenter) {
        this.presenter = presenter;
        shows = new ArrayList<>();
    }

    @Override
    public void getShowsSearchList(String search) {
        Call<List<mShow>> tvShowCall = TvMazeClient.getAPI().getSearchShow(search);
        tvShowCall.enqueue(new Callback<List<mShow>>() {
            @Override
            public void onResponse(Call<List<mShow>> call, Response<List<mShow>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<mShow> list = response.body();
                        for (mShow s : list) {
                            shows.add(s.getShow());
                        }
                        presenter.showSearchList(shows);
                    } else {
                        Log.e("Response", "is null");
                    }
                }

            @Override
            public void onFailure(Call<List<mShow>> call, Throwable t) {
                Log.e("Interact", t.getLocalizedMessage());
                presenter.onShowError(t.getLocalizedMessage());
            }

        });
    }
}

