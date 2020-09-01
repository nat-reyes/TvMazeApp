package my.nat.tvmaze.data.API;

import java.util.List;

import my.nat.tvmaze.data.entities.CastCredit;
import my.nat.tvmaze.data.entities.mActor;
import my.nat.tvmaze.data.entities.Episode;
import my.nat.tvmaze.data.entities.TvShow;
import my.nat.tvmaze.data.entities.mShow;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TvMazeAPI {

    @GET("show")
    Call<List<TvShow>> getShows(@Query("page") int page);
    @GET("shows/{id}/episodes")
    Call<List<Episode>> getEpisodes(@Path("id") int id);
    @GET("search/shows")
    Call<List<mShow>> getSearchShow(@Query("q") String query);
    @GET("search/people")
    Call<List<mActor>> getPeople(@Query("q") String query);
    @GET("people/{id}/castcredits?embed=show")
    Call<List<CastCredit>> getCastCredits(@Path("id") int id);
    @GET("shows/{id}")
    Call<TvShow> getShow(@Path("id") int id);

}
