package my.nat.tvmaze.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tvmaze.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.textfield.TextInputEditText;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import my.nat.tvmaze.data.entities.Actor;
import my.nat.tvmaze.data.entities.mActor;
import my.nat.tvmaze.interfaces.ActorsContractInterface;
import my.nat.tvmaze.presenter.ActorPresenter;
import my.nat.tvmaze.views.adapters.ActorAdapter;
import my.nat.tvmaze.views.adapters.ContentAdapter;

import static my.nat.tvmaze.Constants.ACTOR;

public class ActorActivity extends AppCompatActivity implements ActorsContractInterface.IsearchActorView, ActorsContractInterface.actorItemClickListener {

    @BindView(R.id.input_actors)
    TextInputEditText search_actor;
    @BindView(R.id.recycle_actors)
    RecyclerView recycle;
    @BindView(R.id.progress_bar_actor)
    ProgressBar progress_bar;


    private ActorAdapter adapter;
    private ArrayList<Actor> mActors;
    private ActorPresenter presenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_actor);
        ButterKnife.bind(this);
        mActors = new ArrayList<>();
        presenter = new ActorPresenter(this);
        textChanged();
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
    public void setDataReceived(ArrayList<Actor> Actors) {
        cargarRecycleView(Actors);
        mActors.addAll(Actors);
    }

    public void textChanged(){
        search_actor.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (s.toString().contains("\n")){
                    presenter.getListActors(search_actor.getText().toString());
                    search_actor.setText("");
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
    public void onErrorLoading(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActorItemClick(int position) {
        if (position == -1) {
            return;
        }
        Intent detailIntent = new Intent(this,ActorDetailActivity.class);
        detailIntent.putExtra(ACTOR, mActors.get(position));
        startActivity(detailIntent);
    }

    public void cargarRecycleView(ArrayList<Actor> Actors){
        GridLayoutManager grid = new GridLayoutManager(this, 2);
        recycle.setItemAnimator(new DefaultItemAnimator());
        recycle.setLayoutManager(grid);
        adapter = new ActorAdapter(this,this,Actors);
        recycle.setAdapter(adapter);
        mActors.clear();
    }
}
