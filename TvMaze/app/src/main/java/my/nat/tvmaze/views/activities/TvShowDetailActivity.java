package my.nat.tvmaze.views.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tvmaze.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import my.nat.tvmaze.data.entities.Episode;
import my.nat.tvmaze.data.entities.TvShow;
import my.nat.tvmaze.interfaces.EpisodesContractInterface;
import my.nat.tvmaze.presenter.EpisodesPresenter;
import my.nat.tvmaze.views.adapters.EpisodesAdapter;

import static my.nat.tvmaze.Constants.EPISODE;
import static my.nat.tvmaze.Constants.TV_SHOW;

public class TvShowDetailActivity extends AppCompatActivity implements  EpisodesContractInterface.IepisodeswView, EpisodesContractInterface.EpisodeItemClickListener {

    @BindView(R.id.iv_backdrop)
    ImageView poster;
    @BindView(R.id.pb_load_backdrop)
    ProgressBar progress_bar;
    @BindView(R.id.tv_runtime_value)
    TextView runtime;
    @BindView(R.id.tv_value_overview)
    TextView overview;
    @BindView(R.id.tv_genre_value)
    TextView genre;
    @BindView(R.id.tv_schedule_value)
    TextView schedule;
    @BindView(R.id.appbar_col)
     AppBarLayout appbar;
    @BindView(R.id.recycle_episodes)
    RecyclerView episodes_recycle;
    @BindView(R.id.collapsing_toolbar_detail)
            CollapsingToolbarLayout collap;
    EpisodesPresenter presenter;
    EpisodesAdapter episodesAdapter;
    ArrayList<Episode> mEpisodes;
    private TvShow TvShow;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvshow_detail);
        ButterKnife.bind(this);
        Intent mIntent = getIntent();
        TvShow = (TvShow) mIntent.getSerializableExtra(TV_SHOW);
        setValuesUI();
        mEpisodes = new ArrayList<>();
        presenter = new EpisodesPresenter(this);
        presenter.getListEpisodes(TvShow.getId());

    }



    public void setValuesUI() {
        String s =TvShow.getSummary();
        overview.setText(Html.fromHtml(s, Html.FROM_HTML_MODE_COMPACT));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            overview.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        }

        String days = "";
        if (TvShow.getSchedule().getDays().size() == 1) {
            days = "  " + TvShow.getSchedule().getDays().get(0);
        } else {
            for (int i = 0; i < TvShow.getSchedule().getDays().size(); i++) {
                days += "  " + TvShow.getSchedule().getDays().get(i);
            }
        }

        String sch =  days;
        this.schedule.setText(Html.fromHtml(sch, Html.FROM_HTML_MODE_COMPACT) + " at " + TvShow.getSchedule().getTime());
        this.runtime.setText(String.valueOf(TvShow.getRuntime()+" min "));
        if(TvShow.getGenres()!=null && !TvShow.getGenres().isEmpty()){
            for (int i = 0; i < TvShow.getGenres().size(); i++) {
                this.genre.setText(" "+TvShow.getGenres().get(i));
            }
        }else{
            this.genre.setText("Not categorized");
        }

        if (TvShow.getImage() != null) {
            if(TvShow.getImage().getMedium() != null) {
                Picasso.get().load(TvShow.getImage().getOriginal()).into(poster);
            } else if(TvShow.getImage().getOriginal() != null){
                Picasso.get().load(TvShow.getImage().getOriginal()).into(poster);
            }
        }
    }



    public void cargarRecycleEpisodes(ArrayList<Episode> episodes){
        GridLayoutManager grid = new GridLayoutManager(this, 1);
        episodes_recycle.setItemAnimator(new DefaultItemAnimator());
        episodes_recycle.setLayoutManager(grid);
        episodesAdapter = new EpisodesAdapter(this,this,episodes);
        episodes_recycle.setAdapter(episodesAdapter);

    }


    @Override
    public void showLoading() { progress_bar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() { progress_bar.setVisibility(View.GONE);
    }

    @Override
    public void setDataReceived(ArrayList<Episode> episodes) {
        mEpisodes.clear();
        mEpisodes.addAll(episodes);
        cargarRecycleEpisodes(episodes);
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onEpisodeItemClick(int position) {
        if (position == -1) {
            return;
        }
        Intent detailIntent = new Intent(this,EpisodeDetailActivity.class);
        detailIntent.putExtra(EPISODE,mEpisodes.get(position));
        startActivity(detailIntent);

    }
    }



