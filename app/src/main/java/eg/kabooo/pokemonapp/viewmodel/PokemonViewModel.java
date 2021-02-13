package eg.kabooo.pokemonapp.viewmodel;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import eg.kabooo.pokemonapp.pojo.Pokemon;
import eg.kabooo.pokemonapp.repository.Repo;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PokemonViewModel extends ViewModel {

    private MutableLiveData<List<Pokemon>> mutableLiveData = new MutableLiveData<>();
    private Repo repo;
    private LiveData<List<Pokemon>> liveData = null;

    public LiveData<List<Pokemon>> getLiveData() {
        return liveData;
    }

    @ViewModelInject
    public PokemonViewModel(Repo repo) {
        this.repo = repo;
    }

    public MutableLiveData<List<Pokemon>> getMutableLiveData() {
        return mutableLiveData;
    }

    @SuppressLint("CheckResult")
    public void getPokemons() {
        repo.getPokemons()
                .subscribeOn(Schedulers.io())
                .map(pokemonResponse -> {
                    List<Pokemon> list = pokemonResponse.getResults();

                    for (Pokemon pokemon : list) {
                        String url = pokemon.getUrl();
                        String[] pokemonIndex = url.split("/");

                        pokemon.setUrl("https://pokeres.bastionbot.org/images/pokemon/" + pokemonIndex[pokemonIndex.length - 1] + ".png");
                    }
                    return list;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> mutableLiveData.setValue(result), error -> Log.e("ViewModelError", error.getMessage()));
    }

    public void insertPokemon(Pokemon pokemon) {
        repo.insertPokemon(pokemon);
    }

    public void deletePokemon(String pokemonName) {
        repo.deletePokemon(pokemonName);
    }

    public void getFavPokemons() {
        liveData = repo.getPokemonList();
    }

}
