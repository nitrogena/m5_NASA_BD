package mx.nitrogena.dadm.mod5.nasa.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.nitrogena.dadm.mod5.nasa.R;
import mx.nitrogena.dadm.mod5.nasa.data.ApodService;
import mx.nitrogena.dadm.mod5.nasa.data.Data;
import mx.nitrogena.dadm.mod5.nasa.model.APOD;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Alumno on 12/08/2016.
 */
public class TodayApodFragment extends Fragment {

    //USANDO LA CLASE DE BUTTER KNIFE
    @BindView(R.id.amain_tv_copyright) TextView tvCopyright;
    @BindView(R.id.amain_tv_date) TextView tvDate;
    @BindView(R.id.amain_tv_explanation) TextView tvExplanation;
    // @BindView(R.id.amain_tv_hdurl) TextView tvHdurl;
    @BindView(R.id.amain_tv_mediaType) TextView tvMediaType;
    @BindView(R.id.amain_tv_serviceVersion) TextView tvServiceVersion;
    @BindView(R.id.amain_tv_title) TextView tvTitle;
    //@BindView(R.id.amain_tv_url) TextView tvUrl;
    @BindView(R.id.amain_iv_img) ImageView ivImg;


    public String strUrl;

    public static TodayApodFragment newInstance(String name)
    {
        TodayApodFragment f = new TodayApodFragment();
        Bundle b = new Bundle();
        b.putString("user_key", name);
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
        View view = inflater.inflate(R.layout.activity_main, container, false);

        ButterKnife.bind(this, view);

    /*Preguntar como se usa el toolbar en fragment

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);
*/


        ApodService apodService = Data.getRetrofitInstance().create(ApodService.class);
        //Call<APOD> callApodService = apodService.getTodayApod();
        //SE PUEDE USAR LA FORMA DE ARRIBA O LA DE ABAJO, ES LO MISMO
        Call<APOD> callApodService = apodService.getTodayApodWithQuery("sN0q0EGDwJer2FFc4ns7toCYkthNtvgmtuAjtEIV");
        callApodService.enqueue(new Callback<APOD>() {
            @Override
            public void onResponse(Call<APOD> call, Response<APOD> response) {
                //ESTAMOS OBTENIENDO LOS VALORES DEL JSON QUE USAMOS
                Log.d("LOG APOD: ", response.body().getTitle());

                tvCopyright.setText(response.body().getCopyright());
                tvDate.setText(response.body().getDate());
                tvExplanation.setText(response.body().getExplanation());
                //tvHdurl.setText(response.body().getHdurl());
                tvMediaType.setText(response.body().getMediaType());
                tvServiceVersion.setText(response.body().getServiceVersion());
                tvTitle.setText(response.body().getTitle());
                //tvUrl.setText(response.body().getUrl());


                strUrl = response.body().getUrl();


                Picasso.with(getActivity()).load(strUrl).into(ivImg);

            }

            @Override
            public void onFailure(Call<APOD> call, Throwable t) {

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
                Snackbar.make(getView(), "Share", Snackbar.LENGTH_SHORT).show();

                shareText("APP: " + strUrl);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void shareText(String text){
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, text);
        startActivity(Intent.createChooser(shareIntent, "Compartir"));
    }



}
