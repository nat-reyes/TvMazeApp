package my.nat.tvmaze.interactors;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import my.nat.tvmaze.data.API.TvMazeClient;
import my.nat.tvmaze.data.entities.Actor;
import my.nat.tvmaze.data.entities.mActor;
import my.nat.tvmaze.interfaces.ActorsContractInterface;
import my.nat.tvmaze.presenter.ActorPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActorInteractor implements ActorsContractInterface.IactorInteractor {


    private ActorPresenter presenter;
    ArrayList<Actor> actorsList;

    public ActorInteractor(ActorPresenter presenter) {
        this.presenter = presenter;
        actorsList = new ArrayList<>();
    }

    @Override
    public void getActorsSearchList(String search) {


        Call<List<mActor>> episodeCall = TvMazeClient.getAPI().getPeople(search);
        episodeCall.enqueue(new Callback<List<mActor>>() {
            @Override
            public void onResponse(@NonNull Call<List<mActor>> call, @NonNull Response<List<mActor>> response) {

                if (response.isSuccessful()&& response.body()!=null) {
                    List<mActor> mlist = response.body();
                        for (mActor actors : mlist) {
                            actorsList.add(actors.getActor());
                        }
                        presenter.actorList(actorsList);
                    } else {
                        Log.e("onResponseSearch", "Response is null");
                    }

                }

            @Override
            public void onFailure(Call<List<mActor>> call, Throwable t) {

            }


        });
    }
}