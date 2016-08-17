package mx.nitrogena.dadm.mod5.nasa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.nitrogena.dadm.mod5.nasa.data.ApodService;
import mx.nitrogena.dadm.mod5.nasa.data.Data;
import mx.nitrogena.dadm.mod5.nasa.model.APOD;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    /*AL PARECER ESTA ACTIVIDAD YA NO SE USA*/

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        // Find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);

        //Log.d("build config", BuildConfig.URLDEBUG);
        /*
        final TextView tvCopyright = (TextView) findViewById(R.id.amain_tv_copyright);
        final TextView tvDate = (TextView) findViewById(R.id.amain_tv_date);
        final TextView tvExplanation = (TextView) findViewById(R.id.amain_tv_explanation);
        final TextView tvHdurl = (TextView) findViewById(R.id.amain_tv_hdurl);
        final TextView tvMediaType = (TextView) findViewById(R.id.amain_tv_mediaType);
        final TextView tvServiceVersion = (TextView) findViewById(R.id.amain_tv_serviceVersion);
        final TextView tvTitle = (TextView) findViewById(R.id.amain_tv_title);
        final TextView tvUrl = (TextView) findViewById(R.id.amain_tv_url);

        final ImageView ivImg = (ImageView) findViewById(R.id.amain_iv_img);
        */

        findViewById(R.id.amain_btn_ir).setOnClickListener(this);



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


                String url1 = response.body().getUrl();


                Picasso.with(MainActivity.this).load(url1).into(ivImg);

            }

            @Override
            public void onFailure(Call<APOD> call, Throwable t) {

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.amain_btn_ir:
                irLista();
                break;

        }
    }



    private void irLista() {
        Intent intent = new Intent(MainActivity.this, ListActivity.class);
        startActivity(intent);
    }

}
