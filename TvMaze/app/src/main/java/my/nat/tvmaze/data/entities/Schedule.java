package my.nat.tvmaze.data.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

@Entity(foreignKeys = {
        @ForeignKey(
                entity = Schedule.class,
                parentColumns = "id",
                childColumns = "big_object_fk"
        )})
       public class Schedule implements Serializable {

        @PrimaryKey(autoGenerate = true)
        private int id;
        @SerializedName("time")
        @Expose
        private String time;
        @SerializedName("days")
        @Expose
        private List<String> days = null;
        @ColumnInfo(name = "big_object_fk")
        private int big_object_fk;

    public int getId() {
        return id;
    }

    public int getBig_object_fk() {
        return big_object_fk;
    }

    public void setBig_object_fk(int big_object_fk) {
        this.big_object_fk = big_object_fk;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public List<String> getDays() {
            return days;
        }

        public void setDays(List<String> days) {
            this.days = days;
        }

        @Override
        public String toString() {
            return "Schedule{" +
                    "time='" + time + '\'' +
                    ", days=" + days +
                    '}';
        }
}
