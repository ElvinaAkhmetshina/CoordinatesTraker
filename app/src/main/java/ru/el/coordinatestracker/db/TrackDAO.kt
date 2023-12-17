package ru.el.coordinatestracker.db
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import ru.el.coordinatestracker.db.entities.TrackCoordinates
import ru.el.coordinatestracker.db.entities.TrackWithCoordinates
import ru.el.coordinatestracker.db.entities.Tracks


@Dao
interface TrackDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrack(tracks: Tracks)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrackCoordinates(tracksCoordinates: TrackCoordinates)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrackAndCoordinates(tracks: Tracks, tracksCoordinates: TrackCoordinates)


    @Transaction
    @Query("SELECT coordinates FROM TrackCoordinates WHERE trackDate = :date")
    suspend fun getTrackWithCoordinates(date: String): List<String>

    @Query("SELECT * FROM Tracks")
    fun getTracks(): Flow<List<Tracks>>


    @Query("SELECT coordinates FROM TrackCoordinates WHERE trackDate = :trackDate")
    fun getTrackCoordinates(trackDate: String): Flow<List<String>>
/*
    @Query("SELECT * FROM TrackCoordinates WHERE trackDate = :trackDate")
    fun getTrackCoordinates(trackDate: String): Flow<List<Tracks>>*/



/*

 @Transaction
    @Query("SELECT * FROM Tracks")
    fun getTracksWithCoordinates(): LiveData<List<TrackWithCoordinates>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrack(track: Track)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrackDetail(trackDetail: TrackDetail)*/





    /*

 @Query("SELECT * FROM all_tracks ORDER BY date DESC")
    fun getAllTracksSortedByDate(): List<Track>>

    @Query("SELECT * FROM all_tracks ORDER BY date DESC")
    fun getAllTrackDetailsSortedByDate(): LiveData<List<Track>>


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




         @Insert(entity = TrackDBEntity::class)
    fun insertNewData(statistic: TrackDBEntity)

    @Query("SELECT all_tracks.id, date, distance, track_detail_id FROM all_tracks\n" +
            "INNER JOIN track_details ON all_tracks.track_detail_id = track_details.id;")
    fun getAllData(): List<TrackInfoTuple>

         */

}