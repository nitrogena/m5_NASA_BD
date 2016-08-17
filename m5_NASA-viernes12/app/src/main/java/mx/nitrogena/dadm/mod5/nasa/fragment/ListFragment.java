package mx.nitrogena.dadm.mod5.nasa.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.nitrogena.dadm.mod5.nasa.Adapter.NasaApodAdapter;
import mx.nitrogena.dadm.mod5.nasa.DetailActivity;
import mx.nitrogena.dadm.mod5.nasa.R;
import mx.nitrogena.dadm.mod5.nasa.data.ApodService;
import mx.nitrogena.dadm.mod5.nasa.data.Data;
import mx.nitrogena.dadm.mod5.nasa.model.MarsRoverResponse;
import mx.nitrogena.dadm.mod5.nasa.model.Photo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Alumno on 12/08/2016.
 */
public class ListFragment extends Fragment {

    @BindView(R.id.nasaapodlayout_rv_recyclerv)
    RecyclerView marsRoverListRecycler;

    public static ListFragment newInstance(String name)
    {
        ListFragment f = new ListFragment();
        Bundle b = new Bundle();
        b.putString("user2_key", name);
        f.setArguments(b);
        return f;
    }

    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nasa_apod_layout, container, false);

        ButterKnife.bind(this, view);



        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        marsRoverListRecycler.setLayoutManager(linearLayoutManager);


        //6-8-16 Creamos adaptador
        final NasaApodAdapter nasaApodAdapter = new NasaApodAdapter();
        nasaApodAdapter.setOnItemClickListener(new NasaApodAdapter.OnItemClickListener(){

            @Override
            public void onItemClick(Photo photo) {
                Log.d("log - titulo: ", photo.getCamera().getFullName());
                Log.d("log - earthDATE: ", photo.getEarthDate());


                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("cameraFullname", photo.getCamera().getFullName());
                intent.putExtra("earthdate", photo.getEarthDate());
                intent.putExtra("img", photo.getImgSrc());
                intent.putExtra("roverLandingdate", photo.getRover().getLandingDate());

                startActivity(intent);
            }

        });


        ApodService apodService = Data.getRetrofitInstance().create(ApodService.class);
        //Call<APOD> callApodService = apodService.getTodayApod();
        //SE PUEDE USAR LA FORMA DE ARRIBA O LA DE ABAJO, ES LO MISMO
        Call<MarsRoverResponse> callMarsService = apodService.getTodayMarsRoverResponseWithQuery(1000, "sN0q0EGDwJer2FFc4ns7toCYkthNtvgmtuAjtEIV");
        callMarsService.enqueue(new Callback<MarsRoverResponse>() {
            @Override
            public void onResponse(Call<MarsRoverResponse> call, Response<MarsRoverResponse> response) {
                //ESTAMOS OBTENIENDO LOS VALORES DEL JSON QUE USAMOS, es el callback de retrofit

                //se quito el 6-8-16
                //marsRoverListRecycler.setAdapter(new NasaApodAdapter(response.body().getPhotos()));

                //se agrego el 6-8-16

                nasaApodAdapter.setMarsPhotos(response.body().getPhotos());
                marsRoverListRecycler.setAdapter(nasaApodAdapter);
            }

            @Override
            public void onFailure(Call<MarsRoverResponse> call, Throwable t) {

            }
        });









        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.menu_redes, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuRedes_share_today_apod:
                //Toast.makeText(getActivity(), "Hola", Toast.LENGTH_LONG ).show();

                Snackbar.make(getView(), "Favoritos", Snackbar.LENGTH_SHORT).show();


                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
