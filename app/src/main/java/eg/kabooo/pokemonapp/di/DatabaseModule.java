package eg.kabooo.pokemonapp.di;

import android.app.Application;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import eg.kabooo.pokemonapp.db.PokemonDao;
import eg.kabooo.pokemonapp.db.PokemonDatabase;

@Module
@InstallIn(ApplicationComponent.class)
public class DatabaseModule {

    @Provides
    @Singleton
    public static PokemonDatabase pokemonDatabaseProvide(Application application) {
        return Room.databaseBuilder(application, PokemonDatabase.class, "fav_db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    @Singleton
    public static PokemonDao pokemonDaoProvide(PokemonDatabase pokemonDatabase) {
        return pokemonDatabase.pokemonDao();
    }

}
