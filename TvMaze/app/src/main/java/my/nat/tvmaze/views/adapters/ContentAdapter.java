package my.nat.tvmaze.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
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
import my.nat.tvmaze.Constants;
import my.nat.tvmaze.data.API.database.FavoriteDao;
import my.nat.tvmaze.data.entities.Actor;
import my.nat.tvmaze.data.entities.TvShowFav;
import my.nat.tvmaze.data.entities.mActor;
import my.nat.tvmaze.data.entities.TvShow;
import my.nat.tvmaze.views.activities.ActorActivity;
import my.nat.tvmaze.views.activities.ActorDetailActivity;
import my.nat.tvmaze.views.activities.FavoriteShows;
import my.nat.tvmaze.views.activities.SearchTvShowActivity;
import my.nat.tvmaze.views.fragment.TvShowsFragment;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ViewHolder> {

    private TvShowsFragment TvShowsFragment;
    private ArrayList<TvShow> mData;
    private LayoutInflater mInflater;
    private SearchTvShowActivity mActivity;
    private ActorDetailActivity mDetailAc;
    private FavoriteShows mFavShows;

    public ContentAdapter(TvShowsFragment fragment, Context context, ArrayList<TvShow> mData) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = mData;
        TvShowsFragment = fragment;
    }

    public ContentAdapter(FavoriteShows favoriteShows, Context context, ArrayList<TvShow> mData) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = mData;
        this.mFavShows = favoriteShows;
    }

    public ContentAdapter(SearchTvShowActivity searchShow, Context context, ArrayList<TvShow>mDataLis) {
        mActivity = searchShow;
        this.mInflater= LayoutInflater.from(context);
        this.mData = mDataLis;

    }

    public ContentAdapter(ActorDetailActivity mDetailAc, Context context, ArrayList<TvShow>mDataLis) {
        this.mDetailAc = mDetailAc;
        this.mInflater= LayoutInflater.from(context);
        this.mData = mDataLis;

    }

    @Override
    public ContentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.fragment_cardview_tvshow, parent, false);
     return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContentAdapter.ViewHolder holder, int position) {

        TvShow Item = mData.get(position);
        holder.tittle.setText(Item.getName());
        if(Item.getImage()!=null){
            if(!Item.getImage().getOriginal().isEmpty()){
                Picasso.get().load(Item.getImage().getMedium()).into(holder.poster);
            }else if(!Item.getImage().getMedium().isEmpty()){
                Picasso.get().load(Item.getImage().getMedium()).resize(200,250).into(holder.poster);

            }
        }


        if(Constants.favoriteRepository.isFavorite(Item.getId())==1){
            holder.fav.setButtonDrawable(R.drawable.ic_favorite);
        }else{
            holder.fav.setButtonDrawable(R.drawable.ic_favorite_down);
        }



        holder.fav.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
             if(Constants.favoriteRepository.isFavorite(Item.getId())!=1){
                 changeStateFavorite(position, true);
                 holder.fav.setButtonDrawable(R.drawable.ic_favorite);
             }else {
                 changeStateFavorite(position, false);
                 holder.fav.setButtonDrawable(R.drawable.ic_favorite_down);
             }
            }
        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TvShowsFragment!=null){
                    TvShowsFragment.onShowItemClick(position);
                }else if(mActivity!=null) {
                    mActivity.onSearchesShowItemClick(position);
                }else if (mDetailAc!=null){
                    mDetailAc.onCastCreditItemClick(position);
                }else{
                    mFavShows.onFavItemClick(position);
                }
            }
        });

    }

    private void changeStateFavorite(int position, boolean state) {
        //true si es fav
           TvShowFav fav = new TvShowFav(mData.get(position).getId(), state);
           if(state){
              Constants.favoriteRepository.insertFavoriteData(fav);

           }else{
               Constants.favoriteRepository.deleteFavoriteData(fav);
           }
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tvTitleShow)
        TextView tittle;
        @BindView(R.id.imageViewShow)
        ImageView poster;
        @BindView(R.id.LinearPoster)
        RelativeLayout listen;
        @BindView(R.id.favorite)
        CheckBox fav;
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
