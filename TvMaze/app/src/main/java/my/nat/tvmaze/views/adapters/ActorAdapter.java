package my.nat.tvmaze.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tvmaze.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import my.nat.tvmaze.data.entities.Actor;
import my.nat.tvmaze.data.entities.TvShow;
import my.nat.tvmaze.views.activities.ActorActivity;

public class ActorAdapter extends RecyclerView.Adapter<ActorAdapter.ViewHolder>  {

    private ActorActivity mActivity;
    private ArrayList<Actor> mActors;
    private LayoutInflater mInflater;

    public ActorAdapter(ActorActivity mActivity, Context context, ArrayList<Actor> actors) {
        this.mActivity = mActivity;
        this.mActors = actors;
        this.mInflater= LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ActorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.fragment_cardview_actor, parent, false);
        return new ActorAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActorAdapter.ViewHolder holder, int position) {

        Actor Item = mActors.get(position);
        holder.tittle.setText(Item.getName());
        if(Item.getImage()!=null){
            if(!Item.getImage().getOriginal().isEmpty()){
                Picasso.get().load(Item.getImage().getOriginal()).resize(300,350).into(holder.poster);
            }else if(!Item.getImage().getMedium().isEmpty()){
                Picasso.get().load(Item.getImage().getMedium()).resize(300,350).into(holder.poster);

            }
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               mActivity.onActorItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mActors.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tvTitleActor)
        TextView tittle;
        @BindView(R.id.imageViewActor)
        ImageView poster;
        @BindView(R.id.LinearPoster)
        RelativeLayout listen;
        private Context context;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        void setOnClickListener(){
            listen.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
        }
    }
}
