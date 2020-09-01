package my.nat.tvmaze.presenter;

import java.util.ArrayList;

import my.nat.tvmaze.data.entities.TvShow;
import my.nat.tvmaze.interactors.SearchShowInteractor;
import my.nat.tvmaze.interfaces.EpisodesContractInterface;
import my.nat.tvmaze.interfaces.SearchShowContractInterface;

public class SearchTvShowPresenter  implements SearchShowContractInterface.IsearchShowPresenter {

    private SearchShowContractInterface.IsearchShowView view;
    private SearchShowContractInterface.IsearchShowInteractor interactor;

    public SearchTvShowPresenter(SearchShowContractInterface.IsearchShowView view) {
        this.view = view;
        interactor = new SearchShowInteractor(this);
    }

    @Override
    public void onShowError(String error) {
        if(view==null){
            view.hideLoading(); // progressbar
            view.onErrorLoading(error);// showerror
        }
    }

    @Override
    public void getListShows(String search) {
        if(view!=null){
            view.showLoading();
            interactor.getShowsSearchList(search);
        }
    }

    @Override
    public void showSearchList(ArrayList<TvShow> showArrayList) {
        if (view != null) {
            view.hideLoading();
        }
        view.setDataReceived(showArrayList);
    }

}
