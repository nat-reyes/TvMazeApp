package my.nat.tvmaze.presenter;

import java.util.ArrayList;

import my.nat.tvmaze.data.entities.TvShow;
import my.nat.tvmaze.interactors.TvShowInteractor;
import my.nat.tvmaze.interfaces.TvShowContractInterface;

public class TvShowPresenter implements TvShowContractInterface.ItvShowPresenter {

    private TvShowContractInterface.ItvShowView view;
    private TvShowContractInterface.ItvShowInteractor interactor;

    public TvShowPresenter(TvShowContractInterface.ItvShowView view){
        this.view = view;
        interactor = new TvShowInteractor(this);
    }

    @Override
    public void onShowError(String error) {
        if(view==null){
            view.hideLoading(); // progressbar
            view.onErrorLoading(error);// showerror
        }

    }

    @Override
    public void getListShows(int page) {
        if(view!=null){
            view.showLoading();
            interactor.getShowListInteractor(page);
        }
    }
   @Override
    public void showList(ArrayList<TvShow> tvShows) {
        if (view != null) {
            view.hideLoading();
        }
       view.setDataReceived(tvShows);
    }

}
