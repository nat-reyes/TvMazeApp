package my.nat.tvmaze.presenter;

import java.util.ArrayList;
import java.util.List;

import my.nat.tvmaze.data.entities.TvShow;
import my.nat.tvmaze.data.entities.TvShowFav;
import my.nat.tvmaze.interactors.FavoriteInteractor;
import my.nat.tvmaze.interfaces.FavoriteShowContractInterface;

public class FavoritePresenter implements FavoriteShowContractInterface.IfavoritePresenter {

    private FavoriteShowContractInterface.IfavoriteView view;
    private FavoriteShowContractInterface.IfavoriteInteractor interactor;

    public FavoritePresenter(FavoriteShowContractInterface.IfavoriteView view) {
        this.view = view;
        this.interactor = new FavoriteInteractor(this);
    }

    @Override
    public void onShowError(String error) {
        if(view==null){
            view.hideLoading(); // progressbar
            view.onErrorLoading(error);// showerror
        }
    }

    @Override
    public void getFavoriteShowList(List<TvShowFav> favs) {
        if(view!=null){
            view.showLoading();
            interactor.getFavoriteShowListApi(favs);
        }
    }


    @Override
    public void showFavoriteList(ArrayList<TvShow> favShowsList) {
        if (view != null) {
            view.hideLoading();
        }
        view.setDataReceived(favShowsList);
    }
}
