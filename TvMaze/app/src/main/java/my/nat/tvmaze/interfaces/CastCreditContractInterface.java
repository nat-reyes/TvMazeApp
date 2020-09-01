package my.nat.tvmaze.interfaces;
import java.util.ArrayList;
import my.nat.tvmaze.data.entities.Episode;
import my.nat.tvmaze.data.entities.TvShow;

public interface CastCreditContractInterface {

        interface IcastCreditInteractor {
            void getShowsList(int idActor);
        }

        interface IcastCreditView {
            void showLoading();
            void hideLoading();
            void setDataReceived(ArrayList<TvShow> showes);
            void onErrorLoading(String message);
        }

        interface IcastCreditPresenter {
            void onShowError(String error);
            void getListShows(int idActor);
            void showList(ArrayList<TvShow> showArrayList);

        }

        interface castCreditItemClickListener {
            void onCastCreditItemClick(int position);
        }


    }


