package ru.el.coordinatestracker.db
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.el.coordinatestracker.db.model.Track
import ru.el.coordinatestracker.db.model.TrackDBEntity
import ru.el.coordinatestracker.db.model.TrackInfoTuple


@Dao
interface TrackDAO {
    @Insert(entity = TrackDBEntity::class)
    fun insertNewData(statistic: TrackDBEntity)

    @Query("SELECT all_tracks.id, date, distance, track_detail_id FROM all_tracks\n" +
            "INNER JOIN track_details ON all_tracks.track_detail_id = track_details.id;")
    fun getAllData(): List<TrackInfoTuple>
    /*
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrack(track: Track)

    @Query("SELECT * FROM all_tracks ORDER BY date DESC")
    fun getAllTracksSortedByDate(): LiveData<List<Track>>



    @Delete
    suspend fun deleteRun(run: Track)

        @Query("SELECT * FROM running_table ORDER BY timeInMillis DESC")
        fun getAllRunsSortedByTimeInMillis(): LiveData<List<Run>>

        @Query("SELECT * FROM running_table ORDER BY caloriesBurned DESC")
        fun getAllRunsSortedByCaloriesBurned(): LiveData<List<Run>>

        @Query("SELECT * FROM running_table ORDER BY avgSpeedInKMH DESC")
        fun getAllRunsSortedByAvgSpeed(): LiveData<List<Run>>

        @Query("SELECT * FROM running_table ORDER BY distanceInMeters DESC")
        fun getAllRunsSortedByDistance(): LiveData<List<Run>>

        @Query("SELECT SUM(timeInMillis) FROM running_table")
        fun getTotalTimeInMillis(): LiveData<Long>

        @Query("SELECT SUM(caloriesBurned) FROM running_table")
        fun getTotalCaloriesBurned(): LiveData<Int>

        @Query("SELECT SUM(distanceInMeters) FROM running_table")
        fun getTotalDistance(): LiveData<Int>



        @Query("SELECT AVG(avgSpeedInKMH) FROM running_table")
        fun getTotalAvgSpeed(): LiveData<Float>

         */

}