package mx.nitrogena.dadm.mod5.nasa.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import mx.nitrogena.dadm.mod5.nasa.R;
import mx.nitrogena.dadm.mod5.nasa.model.FavoriteMarsRoverModel;
import mx.nitrogena.dadm.mod5.nasa.sql.OperacionesDatos;

/**
 * Created by USUARIO on 18/08/2016.
 */
public class ListFavoriteMRFragment extends Fragment{

    private OperacionesDatos operacionesDatos;

    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        operacionesDatos = new OperacionesDatos(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_favoritemr, container, false);


        ArrayList<FavoriteMarsRoverModel> arrLstFavMr = operacionesDatos.obtenerFavoritosMR();

        return view;
    }
}
