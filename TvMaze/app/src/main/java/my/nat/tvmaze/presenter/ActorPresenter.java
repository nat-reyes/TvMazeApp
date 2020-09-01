package my.nat.tvmaze.presenter;

import java.util.ArrayList;

import my.nat.tvmaze.data.entities.Actor;
import my.nat.tvmaze.data.entities.mActor;
import my.nat.tvmaze.interactors.ActorInteractor;
import my.nat.tvmaze.interfaces.ActorsContractInterface;

public class ActorPresenter implements ActorsContractInterface.IactorPresenter {

    private ActorsContractInterface.IsearchActorView view;
    private ActorsContractInterface.IactorInteractor interactor;

    public ActorPresenter(ActorsContractInterface.IsearchActorView view) {
        this.view = view;
        interactor = new ActorInteractor(this);
    }

    @Override
    public void onShowError(String error) {
        if(view==null){
            view.hideLoading(); // progressbar
            view.onErrorLoading(error);// showerror
        }
    }

    @Override
    public void getListActors(String search) {
        if(view!=null){
            view.showLoading();
            interactor.getActorsSearchList(search);
        }
    }

    @Override
    public void actorList(ArrayList<Actor> actorsArrayList) {
        if (view != null) {
            view.hideLoading();
        }
        view.setDataReceived(actorsArrayList);
    }





}
