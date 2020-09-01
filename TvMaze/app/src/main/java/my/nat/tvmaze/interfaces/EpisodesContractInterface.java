package my.nat.tvmaze.interfaces;

import java.util.ArrayList;

import my.nat.tvmaze.data.entities.Episode;
import my.nat.tvmaze.data.entities.TvShow;

public interface EpisodesContractInterface {

    interface IepisodesInteractor {

        void getEpisodesList(int idShow);

    }

    interface IepisodeswView {

        void showLoading();
        void hideLoading();
        void setDataReceived(ArrayList<Episode>episodes);
        void onErrorLoading(String message);

    }

    interface IepisodesPresenter {

        void onShowError(String error);
        void getListEpisodes(int idShow);
        void showEpisodesList(ArrayList<Episode> episodeArrayList);

    }

     interface EpisodeItemClickListener {

        void onEpisodeItemClick(int position);

    }


}
