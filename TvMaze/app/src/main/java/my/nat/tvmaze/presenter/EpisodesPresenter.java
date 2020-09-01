package my.nat.tvmaze.presenter;

import java.util.ArrayList;

import my.nat.tvmaze.data.entities.Episode;
import my.nat.tvmaze.interactors.EpisodesInteractor;
import my.nat.tvmaze.interactors.TvShowInteractor;
import my.nat.tvmaze.interfaces.EpisodesContractInterface;
import my.nat.tvmaze.interfaces.TvShowContractInterface;

public class EpisodesPresenter implements EpisodesContractInterface.IepisodesPresenter{


    private EpisodesContractInterface.IepisodeswView view;
    private EpisodesContractInterface.IepisodesInteractor interactor;

    public EpisodesPresenter(EpisodesContractInterface.IepisodeswView view) {
        this.view = view;
        interactor = new EpisodesInteractor(this);
    }

    @Override
    public void onShowError(String error) {
        if(view==null){
            view.hideLoading(); // progressbar
            view.onErrorLoading(error);// showerror
        }
    }

    @Override
    public void getListEpisodes(int idShow) {
        if(view!=null){
            view.showLoading();
            interactor.getEpisodesList(idShow);
        }
    }

    @Override
    public void showEpisodesList(ArrayList<Episode> episodeArrayList) {
        if (view != null) {
            view.hideLoading();
        }
        view.setDataReceived(episodeArrayList);
    }
}



