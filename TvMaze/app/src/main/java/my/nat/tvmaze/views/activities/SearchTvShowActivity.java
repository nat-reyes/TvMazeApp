package my.nat.tvmaze.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tvmaze.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import my.nat.tvmaze.data.entities.TvShow;
import my.nat.tvmaze.interfaces.SearchShowContractInterface;
import my.nat.tvmaze.presenter.SearchTvShowPresenter;
import my.nat.tvmaze.views.adapters.ContentAdapter;

import static my.nat.tvmaze.Constants.TV_SHOW;

public class SearchTvShowActivity extends AppCompatActivity implements SearchShowContractInterface.IsearchShowView, SearchShowContractInterface.searchShowItemClickListener,TextView.OnEditorActionListener {


    @BindView(R.id.input_show)
    TextInputEditText tvshow_name;
    @BindView(R.id.recycle_tvshows)
    RecyclerView recycle_tvshows;
    @BindView(R.id.progress_bar)
    ProgressBar progress_bar;
    private ContentAdapter adapter;
    SearchTvShowPresenter presenter;
    private ArrayList<TvShow> mShows;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_show);
        ButterKnife.bind(this);
        mShows = new ArrayList<>();
        presenter = new SearchTvShowPresenter(this);
        textChanged();
        hideLoading();
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
        cargarRecycleView(shows);
        mShows.addAll(shows);
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    public void cargarRecycleView(ArrayList<TvShow> tvShows){
        GridLayoutManager grid = new GridLayoutManager(this, 2);
        recycle_tvshows.setItemAnimator(new DefaultItemAnimator());
        recycle_tvshows.setLayoutManager(grid);
        adapter = new ContentAdapter(this, this,tvShows);
        recycle_tvshows.setAdapter(adapter);
        mShows.clear();
    }


    @Override
    public void onSearchesShowItemClick(int position) {
        if (position == -1) {
            return;
        }
        Intent detailIntent = new Intent(this,TvShowDetailActivity.class);
        detailIntent.putExtra(TV_SHOW, mShows.get(position));
        startActivity(detailIntent);

    }

    public void textChanged(){
        tvshow_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (s.toString().contains("\n")){
                    Toast.makeText(getApplicationContext(),tvshow_name.getText(), Toast.LENGTH_SHORT).show();
                    presenter.getListShows(tvshow_name.getText().toString());
                    tvshow_name.setText("");
                }

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        return true;
    }

}
