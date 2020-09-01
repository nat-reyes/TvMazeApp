package my.nat.tvmaze.interfaces;

import java.util.ArrayList;

import my.nat.tvmaze.data.entities.Actor;
import my.nat.tvmaze.data.entities.mActor;

public interface ActorsContractInterface {

    interface IactorInteractor {

        void getActorsSearchList(String search);

    }

    interface IsearchActorView {

        void showLoading();
        void hideLoading();
        void setDataReceived(ArrayList<Actor> mActors);
        void onErrorLoading(String message);

    }

    interface IactorPresenter {

        void onShowError(String error);
        void getListActors(String search);
        void actorList(ArrayList<Actor>actorsArrayList);

    }

    interface actorItemClickListener {

        void onActorItemClick(int position);

    }


}
