package my.nat.tvmaze.presenter;

import java.util.ArrayList;

import my.nat.tvmaze.data.entities.CastCredit;
import my.nat.tvmaze.data.entities.TvShow;
import my.nat.tvmaze.interactors.CastCreditInteractor;
import my.nat.tvmaze.interfaces.CastCreditContractInterface;

public class CastCreditPresenter implements CastCreditContractInterface.IcastCreditPresenter {

    private CastCreditContractInterface.IcastCreditView view;
    private CastCreditContractInterface.IcastCreditInteractor interactor;

    public CastCreditPresenter(CastCreditContractInterface.IcastCreditView view) {
        this.view = view;
        interactor = new CastCreditInteractor(this);
    }

    @Override
    public void onShowError(String error) {
        if(view==null){
            view.hideLoading(); // progressbar
            view.onErrorLoading(error);// showerror
        }
    }

    @Override
    public void getListShows(int idActor) {
        if(view!=null){
            view.showLoading();
            interactor.getShowsList(idActor);
        }
    }

    @Override
    public void showList(ArrayList<TvShow> showArrayList) {
        if (view != null) {
            view.hideLoading();
        }
        view.setDataReceived(showArrayList);
    }
}
