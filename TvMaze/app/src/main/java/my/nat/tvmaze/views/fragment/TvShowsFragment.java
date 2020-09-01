package my.nat.tvmaze.views.fragment;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tvmaze.R;
import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import my.nat.tvmaze.data.entities.TvShow;
import my.nat.tvmaze.interfaces.TvShowContractInterface;
import my.nat.tvmaze.presenter.TvShowPresenter;
import my.nat.tvmaze.views.activities.TvShowDetailActivity;
import my.nat.tvmaze.views.adapters.ContentAdapter;


import static my.nat.tvmaze.Constants.TV_SHOW;

//recycle con el listado de movies
//El recycle tiene un card view con la info que llevara cada item
//text de tittle

public class TvShowsFragment extends Fragment implements TvShowContractInterface.ItvShowView, TvShowContractInterface.TvShowItemClickListener {

    @BindView(R.id.progress_bar)
    ProgressBar progress_bar;
    @BindView(R.id.recycle_tvshows)
    RecyclerView showsRecycle;
    @BindView(R.id.first_number)
    TextView first_num;
    @BindView(R.id.second_number)
    TextView second_num;
    @BindView(R.id.third_number)
    TextView third_num;
    @BindView(R.id.btn_right)
    Button right;
    @BindView(R.id.btn_lefgt)
    Button left;

    int snaped;
    private TvShowPresenter presenter;
    private ArrayList<TvShow> shows;
    private ContentAdapter adapter;
    private static final int incr = 3;
    private static TextView text_snapped;
    private static final int lenght = 200;


    public TvShowsFragment() {

    }


    @Override
    public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container,
                                          Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.view.View view= inflater.inflate(R.layout.fragment_list_tvshows, container, false);
        ButterKnife.bind(this, view);
        shows = new ArrayList<>();
        presenter = new TvShowPresenter(this);
        presenter.getListShows(0);
        initListeners();
        return view;
    }

    @Override
    public void showLoading() {
            progress_bar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
            progress_bar.setVisibility(View.GONE);

    }

    @Override
    public void setDataReceived(ArrayList<TvShow> tvShows) {
        cargarRecycleView(tvShows);
        if(shows.isEmpty()){
            shows.addAll(tvShows);
        }

    }


    public void snapText(TextView text, TextView last){
        SpannableString mitextoU = new SpannableString(text.getText().toString());
        mitextoU.setSpan(new UnderlineSpan(), 0, mitextoU.length(), 0);
        text.setText(mitextoU);
        if(last!=null){
            String s =last.getText().toString();
            last.setText(s);
        }

    }

    public void changePage(int action){
        if(action == 0){
            first_num.setText(String.valueOf(Integer.parseInt(first_num.getText().toString())+incr));
            second_num.setText(String.valueOf(Integer.parseInt(second_num.getText().toString())+incr));
            third_num.setText(String.valueOf(Integer.parseInt(third_num.getText().toString())+incr));
        }else if (action==1){
            first_num.setText(String.valueOf(Integer.parseInt(first_num.getText().toString())-incr));
            second_num.setText(String.valueOf(Integer.parseInt(second_num.getText().toString())-incr));
            third_num.setText(String.valueOf(Integer.parseInt(third_num.getText().toString())-incr));
        }

    }


    public void cargarRecycleView(ArrayList<TvShow> tvShows){
        GridLayoutManager grid = new GridLayoutManager(getContext(), 2);
        showsRecycle.setLayoutManager(grid);
        adapter = new ContentAdapter(this,getContext(),tvShows);
        showsRecycle.setAdapter(adapter);
        shows.clear();
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
    }


    public void initListeners(){

        first_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.getListShows(Integer.parseInt(first_num.getText().toString())-1);
                snapText(first_num, text_snapped);
                text_snapped= first_num;

            }
        });
        second_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.getListShows(Integer.parseInt(second_num.getText().toString())-1);
                snapText(second_num, text_snapped);
                text_snapped= second_num;
            }
        });

        third_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.getListShows(Integer.parseInt(third_num.getText().toString())-1);
                snapText(third_num, text_snapped);
                text_snapped= third_num;
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!third_num.getText().toString().equalsIgnoreCase("201")){
                    changePage(0);
                }
            }
        });

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!first_num.getText().toString().equalsIgnoreCase("1")){
                    changePage(1);
                }
            }
        });

    }

    public void changeStateFav(){

    }


    @Override
    public void onShowItemClick(int position) {
        if (position == -1) {
            return;
        }
        Intent detailIntent = new Intent(getActivity(),TvShowDetailActivity.class);
        detailIntent.putExtra(TV_SHOW,shows.get(position));
        startActivity(detailIntent);
    }
}
