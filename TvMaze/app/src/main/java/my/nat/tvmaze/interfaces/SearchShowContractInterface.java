package my.nat.tvmaze.interfaces;
import java.util.ArrayList;
import my.nat.tvmaze.data.entities.Episode;
import my.nat.tvmaze.data.entities.TvShow;

public interface SearchShowContractInterface {

    interface IsearchShowInteractor {

        void getShowsSearchList(String search);

    }

    interface IsearchShowView {

        void showLoading();
        void hideLoading();
        void setDataReceived(ArrayList<TvShow> shows);
        void onErrorLoading(String message);

    }

    interface IsearchShowPresenter {

        void onShowError(String error);
        void getListShows(String search);
        void showSearchList(ArrayList<TvShow> showArrayList);

    }

    interface searchShowItemClickListener {

        void onSearchesShowItemClick(int position);

    }


}
