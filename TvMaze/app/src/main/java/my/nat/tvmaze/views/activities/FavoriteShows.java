package my.nat.tvmaze.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tvmaze.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import my.nat.tvmaze.Constants;
import my.nat.tvmaze.data.entities.TvShow;
import my.nat.tvmaze.data.entities.TvShowFav;
import my.nat.tvmaze.interfaces.FavoriteShowContractInterface;
import my.nat.tvmaze.presenter.FavoritePresenter;
import my.nat.tvmaze.presenter.SearchTvShowPresenter;
import my.nat.tvmaze.views.adapters.ContentAdapter;

import static my.nat.tvmaze.Constants.TV_SHOW;

public class FavoriteShows extends AppCompatActivity implements FavoriteShowContractInterface.IfavoriteView, FavoriteShowContractInterface.favItemClickListener {


    @BindView(R.id.recycle_tvshows)
    RecyclerView recycle_tvshows;
    @BindView(R.id.progress_bar)
    ProgressBar progress_bar;
    private ContentAdapter adapter;
    private ArrayList<TvShow> mShows;
    private List<TvShowFav> favs;
    private FavoritePresenter presenter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appBar)
    AppBarLayout appbar;
    @BindView(R.id.empty_fav)
    TextView empty_favs;
    @BindView(R.id.iv_empty_favs)
    ImageView box_empty;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_list);
        ButterKnife.bind(this);
        mShows = new ArrayList<>();
        favs = new ArrayList<>();
        presenter = new FavoritePresenter(this);
        loadItems();
        loadToolbar();
    }

    private void loadToolbar() {
        toolbar.setNavigationIcon(R.drawable.ic_espalda);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
            }
        });
    }
    public  void loadItems(){
        favs = Constants.favoriteRepository.getFavDBList();
        if(favs.isEmpty()){
            hideLoading();
            empty_favs.setVisibility(View.VISIBLE);
            box_empty.setVisibility(View.VISIBLE);
        }else{
            presenter.getFavoriteShowList(favs);
        }
    }


    @Override
    public void showLoading() {
        this.progress_bar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
      this.progress_bar.setVisibility(View.GONE);
    }

    @Override
    public void setDataReceived(ArrayList<TvShow> shows) {
     mShows.clear();
     mShows.addAll(shows);
     cargarRecycle(mShows);
    }

    private void cargarRecycle(ArrayList<TvShow> mShows) {
        GridLayoutManager grid = new GridLayoutManager(this, 2);
        recycle_tvshows.setItemAnimator(new DefaultItemAnimator());
        recycle_tvshows.setLayoutManager(grid);
        adapter = new ContentAdapter(this, this, mShows);
        recycle_tvshows.setAdapter(adapter);

    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onFavItemClick(int position) {
        if (position == -1) {
            return;
        }
        Intent detailIntent = new Intent(this,TvShowDetailActivity.class);
        detailIntent.putExtra(TV_SHOW, mShows.get(position));
        startActivity(detailIntent);
    }
}
