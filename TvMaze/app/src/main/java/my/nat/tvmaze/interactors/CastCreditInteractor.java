package my.nat.tvmaze.interactors;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import my.nat.tvmaze.data.API.TvMazeClient;
import my.nat.tvmaze.data.entities.CastCredit;
import my.nat.tvmaze.data.entities.Embedded;
import my.nat.tvmaze.data.entities.TvShow;
import my.nat.tvmaze.data.entities.mShow;
import my.nat.tvmaze.interfaces.CastCreditContractInterface;
import my.nat.tvmaze.presenter.CastCreditPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CastCreditInteractor implements CastCreditContractInterface.IcastCreditInteractor {

    private CastCreditPresenter presenter;
    ArrayList<TvShow> shows;
    public CastCreditInteractor(CastCreditPresenter presenter) {
        this.presenter = presenter;
        shows = new ArrayList<>();
    }

    @Override
    public void getShowsList(int idActor) {
        Call<List<CastCredit>> tvShowCall = TvMazeClient.getAPI().getCastCredits(idActor);
        tvShowCall.enqueue(new Callback<List<CastCredit>>() {
            @Override
            public void onResponse(Call<List<CastCredit>> call, Response<List<CastCredit>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<CastCredit> cast = response.body();
                    for (CastCredit mCast : cast) {
                        shows.add(mCast.getEmbedded().getShow());
                        presenter.showList(shows);

                    }
                } else {
                    Log.e("Response", "is null");
                }
            }


            @Override
            public void onFailure(Call<List<CastCredit>> call, Throwable t) {
                Log.e("Interact", t.getLocalizedMessage());
                presenter.onShowError(t.getLocalizedMessage());
            }

        });
    }
    }


