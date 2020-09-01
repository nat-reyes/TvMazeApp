package my.nat.tvmaze.data.API.database;
import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import my.nat.tvmaze.data.entities.TvShowFav;

@Database(entities = {TvShowFav.class}, version = 1)

public abstract class DataFavRoom extends RoomDatabase {

    public abstract FavoriteDao getFavoriteDao();
    private static DataFavRoom instance;
    public static DataFavRoom getInstance(Context context){

        if(instance==null){
            instance = Room.databaseBuilder(context, DataFavRoom.class,"DataBaseFav")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

}
