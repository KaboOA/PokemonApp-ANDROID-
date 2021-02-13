package eg.kabooo.pokemonapp.pojo;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class PokemonResponse{

	@SerializedName("results")
	private List<Pokemon> results;

	public List<Pokemon> getResults(){
		return results;
	}
}