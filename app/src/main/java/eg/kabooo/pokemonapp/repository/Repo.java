package eg.kabooo.pokemonapp.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import eg.kabooo.pokemonapp.api.ApiInterface;
import eg.kabooo.pokemonapp.db.PokemonDao;
import eg.kabooo.pokemonapp.pojo.Pokemon;
import eg.kabooo.pokemonapp.pojo.PokemonResponse;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class Repo {

    private ApiInterface apiInterface;
    private PokemonDao pokemonDao;

    @Inject
    public Repo(ApiInterface apiInterface, PokemonDao pokemonDao) {
        this.apiInterface = apiInterface;
        this.pokemonDao = pokemonDao;
    }

    public Single<PokemonResponse> getPokemons() {
        return apiInterface.getPokemon();
    }

    public void insertPokemon(Pokemon pokemon) {
        pokemonDao.insertPokemon(pokemon);
    }

    public void deletePokemon(String pokemonName) {
        pokemonDao.deletePokemon(pokemonName);
    }

    public LiveData<List<Pokemon>> getPokemonList() {
        return pokemonDao.getPokemons();
    }

}
