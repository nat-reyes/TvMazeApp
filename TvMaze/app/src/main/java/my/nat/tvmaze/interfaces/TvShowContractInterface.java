package my.nat.tvmaze.interfaces;

import java.util.ArrayList;

import my.nat.tvmaze.data.entities.TvShow;

public interface TvShowContractInterface {


    interface ItvShowInteractor {

        void getShowListInteractor(int page);

    }

     interface ItvShowView {

        void showLoading();
        void hideLoading();
        void setDataReceived(ArrayList<TvShow> tvShows);
        void onErrorLoading(String message);

    }

    interface ItvShowPresenter {

        void onShowError(String error);
        void getListShows(int page);
        void showList(ArrayList<TvShow> showArrayList);

    }

    interface TvShowItemClickListener {

        void onShowItemClick(int position);

    }
}
