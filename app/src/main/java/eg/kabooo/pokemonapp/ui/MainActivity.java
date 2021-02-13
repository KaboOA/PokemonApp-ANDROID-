package eg.kabooo.pokemonapp.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import eg.kabooo.pokemonapp.adapter.RecyclerAdapter;
import eg.kabooo.pokemonapp.databinding.ActivityMainBinding;
import eg.kabooo.pokemonapp.viewmodel.PokemonViewModel;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    ActivityMainBinding b;

    @Inject
    RecyclerAdapter adapter;

    PokemonViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        viewModel = new ViewModelProvider(this).get(PokemonViewModel.class);

        b.homeRecyclerView.setAdapter(adapter);
        adapter.swipeAddPokemon(viewModel, b.homeRecyclerView);
        b.toFavBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, FavouriteActivity.class)));
        viewModel.getPokemons();
        viewModel.getMutableLiveData().observe(this, pokemon -> adapter.setList(pokemon));

    }

}