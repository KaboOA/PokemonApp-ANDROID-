package eg.kabooo.pokemonapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import eg.kabooo.pokemonapp.R;
import eg.kabooo.pokemonapp.adapter.RecyclerAdapter;
import eg.kabooo.pokemonapp.databinding.ActivityFavouriteBinding;
import eg.kabooo.pokemonapp.viewmodel.PokemonViewModel;

@AndroidEntryPoint
public class FavouriteActivity extends AppCompatActivity {

    ActivityFavouriteBinding b;

    @Inject
    RecyclerAdapter adapter;

    PokemonViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityFavouriteBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        viewModel = new ViewModelProvider(this).get(PokemonViewModel.class);

        b.favRecyclerView.setAdapter(adapter);
        adapter.swipeDeletePokemon(viewModel, b.favRecyclerView);
        viewModel.getFavPokemons();
        viewModel.getLiveData().observe(this, pokemon -> adapter.setList(pokemon));

    }

}