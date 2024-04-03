package com.portafoliowebmariano.notasapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.portafoliowebmariano.notasapp.model.Note
import com.portafoliowebmariano.notasapp.model.Setting
import com.portafoliowebmariano.notasapp.utils.constans.NAME_DB

@Database(entities = [Note::class, Setting::class], version = 1, exportSchema = false)
abstract class DBNote: RoomDatabase(){

    abstract fun daoNotes() : DaoNotes

    companion object{
        @Volatile
        private var INSTANCE : DBNote? = null

        fun getDataBase(context: Context): DBNote{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DBNote::class.java,
                    NAME_DB
                )
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}