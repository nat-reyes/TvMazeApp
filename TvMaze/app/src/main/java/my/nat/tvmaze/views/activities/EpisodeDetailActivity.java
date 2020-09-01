package my.nat.tvmaze.views.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Layout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tvmaze.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import my.nat.tvmaze.data.entities.Episode;
import static my.nat.tvmaze.Constants.EPISODE;


public class EpisodeDetailActivity extends AppCompatActivity {


    private Episode episode;
    @BindView(R.id.tv_overview_episode)
    TextView overview_episode;
    @BindView(R.id.iv_backdrop_episode)
    ImageView poster_episode;
    @BindView(R.id.tv_episode_tittle)
    TextView episode_title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episode_detail);
        ButterKnife.bind(this);
        Intent mIntent = getIntent();
        episode = (Episode) mIntent.getSerializableExtra(EPISODE);
        setValuesUI();
    }

    public void setValuesUI(){
        episode_title.setText(episode.getSeason()+"x"+episode.getNumber()+"."+episode.getName());

        String s =episode.getSummary();
        overview_episode.setText(Html.fromHtml(s, Html.FROM_HTML_MODE_COMPACT));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            overview_episode.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        }
        if(episode.getImage()!=null){
            if(!episode.getImage().getOriginal().isEmpty()){
                Picasso.get().load(episode.getImage().getOriginal()).resize(300,300).into(poster_episode);
            }else if(!episode.getImage().getMedium().isEmpty()){
                Picasso.get().load(episode.getImage().getMedium()).resize(300,300).into(poster_episode);

            }
        }
    }
}
