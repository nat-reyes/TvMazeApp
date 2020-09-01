package my.nat.tvmaze.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tvmaze.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import my.nat.tvmaze.data.entities.Actor;
import my.nat.tvmaze.data.entities.TvShow;
import my.nat.tvmaze.interfaces.CastCreditContractInterface;
import my.nat.tvmaze.presenter.CastCreditPresenter;
import my.nat.tvmaze.views.adapters.ContentAdapter;

import static my.nat.tvmaze.Constants.ACTOR;
import static my.nat.tvmaze.Constants.TV_SHOW;

public class ActorDetailActivity extends AppCompatActivity implements CastCreditContractInterface.IcastCreditView, CastCreditContractInterface.castCreditItemClickListener{


    @BindView(R.id.tv_name_actor)
    TextView name;
    @BindView(R.id.iv_backdrop_actor)
    ImageView poster;
    @BindView(R.id.recycle_tvshows)
    RecyclerView recycle;
    private Actor actor;
    @BindView(R.id.progress_bar)
    ProgressBar progres_bar;
    ArrayList<TvShow> mShows;
    private CastCreditPresenter presenter;
    private ContentAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actor_detail);
        ButterKnife.bind(this);
        Intent mIntent = getIntent();
        actor = (Actor) mIntent.getSerializableExtra(ACTOR);
        mShows = new ArrayList<>();
        presenter = new CastCreditPresenter(this);
        setValues();
        presenter.getListShows(actor.getId());

    }

    public void setValues(){

        name.setText(actor.getName());

        if (actor.getImage()!= null) {
            if(actor.getImage().getMedium() != null) {
                Picasso.get().load(actor.getImage().getOriginal()).into(poster);
            } else if(actor.getImage().getOriginal() != null){
                Picasso.get().load(actor.getImage().getOriginal()).into(poster);
            }
        }
    }

    @Override
    public void showLoading() {
        this.progres_bar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
    this.progres_bar.setVisibility(View.GONE);
    }

    @Override
    public void setDataReceived(ArrayList<TvShow> shows) {


     mShows.clear();
     mShows.addAll(shows);
     cargarRecycle(shows);
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }


    public void cargarRecycle(ArrayList<TvShow>shows){

        GridLayoutManager grid = new GridLayoutManager(this, 2);
        recycle.setItemAnimator(new DefaultItemAnimator());
        recycle.setLayoutManager(grid);
        adapter = new ContentAdapter(this, this, shows);
        recycle.setAdapter(adapter);
    }

    @Override
    public void onCastCreditItemClick(int position) {
        if (position == -1) {
            return;
        }
        Intent detailIntent = new Intent(this,TvShowDetailActivity.class);
        detailIntent.putExtra(TV_SHOW, mShows.get(position));
        startActivity(detailIntent);
    }
}
