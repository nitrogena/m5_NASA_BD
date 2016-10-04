package mx.nitrogena.dadm.mod5.nasa.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.nitrogena.dadm.mod5.nasa.Adapter.FavoriteAdapter;
import mx.nitrogena.dadm.mod5.nasa.Adapter.FavoriteApodAdapter;
import mx.nitrogena.dadm.mod5.nasa.Adapter.FavoriteRMAdapter;
import mx.nitrogena.dadm.mod5.nasa.R;
import mx.nitrogena.dadm.mod5.nasa.model.FavoriteAPODModel;
import mx.nitrogena.dadm.mod5.nasa.model.FavoriteMarsRoverModel;
import mx.nitrogena.dadm.mod5.nasa.sql.OperacionesDatos;

/**
 * Created by USUARIO on 03/10/2016.
 */
public class ListFavoriteApodFragment extends Fragment{

    private OperacionesDatos operacionesDatos;

    //private ListView listView;

    @BindView(R.id.rvFavoriteAPOD)
    RecyclerView apodListRecycler;




    public static ListFavoriteApodFragment newInstance(String name)
    {
        ListFavoriteApodFragment f = new ListFavoriteApodFragment();
        Bundle b = new Bundle();
        b.putString("user2_key", name);
        f.setArguments(b);
        return f;
    }

    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        operacionesDatos = new OperacionesDatos(getActivity());

        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_favoriteapod, container, false);

        ButterKnife.bind(this, view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        apodListRecycler.setLayoutManager(linearLayoutManager);


        ArrayList<FavoriteAPODModel> arrLstFavAp = operacionesDatos.obtenerFavoritosApod();

        //listView.setAdapter(new FavoriteAdapter(getActivity(), arrLstFavMr));

        apodListRecycler.setAdapter(new FavoriteApodAdapter(arrLstFavAp, getActivity()));


        return view;


    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.menu_list_fragment, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuListFragment_share_today_apod:
                //Toast.makeText(getActivity(), "Hola", Toast.LENGTH_LONG ).show();

                Snackbar.make(getView(), "REFRESH", Snackbar.LENGTH_SHORT).show();


                return true;


            default:
                return super.onOptionsItemSelected(item);
        }

    }


}

