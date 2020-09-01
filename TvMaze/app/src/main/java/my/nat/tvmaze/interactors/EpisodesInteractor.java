package my.nat.tvmaze.interactors;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import my.nat.tvmaze.data.API.TvMazeClient;
import my.nat.tvmaze.data.entities.Episode;
import my.nat.tvmaze.data.entities.TvShow;
import my.nat.tvmaze.interfaces.EpisodesContractInterface;
import my.nat.tvmaze.presenter.EpisodesPresenter;
import my.nat.tvmaze.presenter.TvShowPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EpisodesInteractor implements EpisodesContractInterface.IepisodesInteractor {


    private EpisodesPresenter presenter;

    public EpisodesInteractor(EpisodesPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getEpisodesList(int idTvShow) {

        Call<List<Episode>> episodeCall= TvMazeClient.getAPI().getEpisodes(idTvShow);
        Log.e("Interact", episodeCall.toString());
        episodeCall.enqueue(new Callback<List<Episode>>() {
            @Override
            public void onResponse(@NonNull Call<List<Episode>> call, @NonNull Response<List<Episode>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Episode> episodes=  response.body();
                    if(episodes!=null){
                        presenter.showEpisodesList((ArrayList<Episode>) episodes);
                    }
                } else {
                    Log.e("Response", "is null");
                }
            }

            @Override
            public void onFailure(Call<List<Episode>> call, Throwable t) {
                presenter.onShowError(t.getLocalizedMessage());
            }
        });

    }


}
