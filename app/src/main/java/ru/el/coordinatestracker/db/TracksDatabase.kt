package ru.el.coordinatestracker.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.el.coordinatestracker.db.entities.TrackCoordinates
//import ru.el.coordinatestracker.db.entities.TrackWithCoordinates
import ru.el.coordinatestracker.db.entities.Tracks

import ru.el.coordinatestracker.utils.Constants.Keys.NOTE_DATABASE

/*
@Database(
    entities = [
        Track::class,
        TrackDetail::class
    ],
    version = 1
)
abstract class TracksDatabase: RoomDatabase() {
    abstract fun getTrackDao(): TrackDAO






    companion object {
        @Volatile
        private var INSTANCE: TracksDatabase? = null

        fun getInstance(context: Context): TracksDatabase
        {
            return if (INSTANCE == null)
            {
                INSTANCE = Room.databaseBuilder(context,TracksDatabase::class.java,NOTE_DATABASE).build()
                INSTANCE as TracksDatabase
            }else INSTANCE as TracksDatabase
        }
    }*/
@Database(
    entities = [
        Tracks::class,
        TrackCoordinates::class
    ],
    version = 1
)
abstract class ADatabase : RoomDatabase(){

    abstract fun getTrackDao(): TrackDAO
}

object TracksDatabase{
    //abstract val trackDAO: TrackDAO
   // abstract fun getTrackDao(): TrackDAO
   // companion object {
        //@Volatile
     //   private var INSTANCE: TracksDatabase? = null

        /*fun getInstance(context: Context): TracksDatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    TracksDatabase::class.java,
                    "tracks_db"
                ).build().also {
                    INSTANCE = it
                }
            }
        }*/
        private lateinit var db: TrackDAO
        fun getDao(applicationContext: Context) = if (!::db.isInitialized){
            db = Room.databaseBuilder(
                applicationContext,
                ADatabase::class.java,
                "tracks_db"
            ).build().getTrackDao()
            db
        } else db





    }
//}