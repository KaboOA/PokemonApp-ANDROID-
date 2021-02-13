package eg.kabooo.pokemonapp.api;

import eg.kabooo.pokemonapp.pojo.PokemonResponse;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("pokemon")
    Single<PokemonResponse> getPokemon();

}
