package my.nat.tvmaze.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.fragment.app.Fragment;

import com.example.tvmaze.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.navigation.NavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import my.nat.tvmaze.Constants;
import my.nat.tvmaze.data.API.database.DataFavRoom;
import my.nat.tvmaze.data.API.database.FavoriteDTO;
import my.nat.tvmaze.data.API.database.FavoriteRepository;
import my.nat.tvmaze.views.fragment.TvShowsFragment;

public class HomeActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.appBar)
    AppBarLayout app_bar;
    @BindView(R.id.nav_view)
    NavigationView nav_view;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.toolbar)
    Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_nav);
        setFragment( new TvShowsFragment());
        ButterKnife.bind(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();
        nav_view.setItemIconTintList(null);
        nav_view.setNavigationItemSelectedListener(this);
        initDb();

    }


    private void setFragment(Fragment fragment) {

        getSupportFragmentManager().beginTransaction().replace(R.id.pager, fragment).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void initDb(){
        Constants.database = DataFavRoom.getInstance(this);
        Constants.favoriteRepository = FavoriteRepository.getInstance(FavoriteDTO.getInstance(Constants.database.getFavoriteDao()));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id = menuItem.getItemId();

        if (id == R.id.home_it) {
            setFragment( new TvShowsFragment());

        } else if (id == R.id.search_it) {

            Intent detailIntent = new Intent(this,SearchTvShowActivity.class);
            startActivity(detailIntent);

        } else if (id == R.id.actors_it) {
            Intent detailIntent = new Intent(this, ActorActivity.class);
            startActivity(detailIntent);

        } else if (id == R.id.fav_it) {

            Intent detailIntent = new Intent(this, FavoriteShows.class);
            startActivity(detailIntent);

        } else {
            //Configuracion
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }



    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
