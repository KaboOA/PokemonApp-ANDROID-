package eg.kabooo.pokemonapp.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import eg.kabooo.pokemonapp.pojo.Pokemon;

@Dao
public interface PokemonDao {

    @Insert
    void insertPokemon(Pokemon pokemon);

    @Query("SELECT * FROM fav_table")
    LiveData<List<Pokemon>> getPokemons();

    @Query("DELETE FROM fav_table WHERE name =:name")
    void deletePokemon(String name);

}
