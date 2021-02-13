package eg.kabooo.pokemonapp.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import eg.kabooo.pokemonapp.pojo.Pokemon;

@Database(entities = Pokemon.class, version = 1, exportSchema = false)
public abstract class PokemonDatabase extends RoomDatabase {

    public abstract PokemonDao pokemonDao();
}
