package my.nat.tvmaze.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tvmaze.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import my.nat.tvmaze.data.entities.Episode;
import my.nat.tvmaze.data.entities.TvShow;
import my.nat.tvmaze.views.activities.TvShowDetailActivity;
import my.nat.tvmaze.views.fragment.TvShowsFragment;

public class EpisodesAdapter extends RecyclerView.Adapter<EpisodesAdapter.ViewHolder> {

    private ArrayList<Episode> mData;
    private LayoutInflater mInflater;
    private TvShowDetailActivity mActivity;

    public EpisodesAdapter(TvShowDetailActivity activity, Context context, ArrayList<Episode> mData) {
        this.mData = mData;
        this.mInflater=  LayoutInflater.from(context);
        this.mActivity = activity;
    }

    @NonNull
    @Override
    public EpisodesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.fragment_cardview_episode, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Episode Item = mData.get(position);
        holder.season.setText(String.valueOf(Item.getSeason()));
        holder.number.setText(String.valueOf(Item.getNumber())+"."+Item.getName());

        if(Item.getImage()!=null){
           if(!Item.getImage().getOriginal().isEmpty()){
               Picasso.get().load(Item.getImage().getOriginal()).resize(300,300).into(holder.poster);
           }else if(!Item.getImage().getMedium().isEmpty()){
               Picasso.get().load(Item.getImage().getMedium()).resize(300,300).into(holder.poster);

           }
        }

      holder.itemView.setOnClickListener(new View.OnClickListener() {
          @Override
         public void onClick(View view) {
              mActivity.onEpisodeItemClick(position);
         }
      });

    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tvSeason_value)
        TextView season;
        @BindView(R.id.imageViewEpisode)
        ImageView poster;
        @BindView(R.id.tvNumber_value)
        TextView number;
        @BindView(R.id.LinearPosterEpisode)
        LinearLayout listen;
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
