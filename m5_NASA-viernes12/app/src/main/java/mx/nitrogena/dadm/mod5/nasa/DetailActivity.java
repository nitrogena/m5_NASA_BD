package mx.nitrogena.dadm.mod5.nasa;

import android.content.ContentValues;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.nitrogena.dadm.mod5.nasa.sql.ConstantesBD;
import mx.nitrogena.dadm.mod5.nasa.sql.OperacionesDatos;

public class DetailActivity extends AppCompatActivity  implements View.OnClickListener{

    /*PARA FAVORITOS MR*/
    String cameraFullname;
    String earthDate;
    String imgSrc;
    String roverLandingdate;


    //USANDO LA CLASE DE BUTTER KNIFE
    @BindView(R.id.adetail_tv_cameraFullName)
    TextView tvCameraFullName;
    @BindView(R.id.adetail_tv_earthDate) TextView tvEarthDate;
    @BindView(R.id.adetail_tv_roverLandingdate) TextView tvRoverLandingdate;

    //SimpleDraweeView
    @BindView(R.id.adetail_iv_img)
    ImageView ivImg;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);


        // Find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);

        //BOTON SUBIR
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bdlExtras = getIntent().getExtras();
        /*
        String cameraFullname = bdlExtras.getString("cameraFullname");
        String earthDate = bdlExtras.getString("earthdate");
        String imgSrc = bdlExtras.getString("img");
        String roverLandingdate = bdlExtras.getString("roverLandingdate");
*/


        cameraFullname = bdlExtras.getString("cameraFullname");
        earthDate = bdlExtras.getString("earthdate");
        imgSrc = bdlExtras.getString("img");
        roverLandingdate = bdlExtras.getString("roverLandingdate");


        Log.d("logDetail - titulo: ", cameraFullname);
        Log.d("logDetail - imgSrc: ", imgSrc);

        //String cameraFullname = getIntent().getExtras().getString("cameraFullname");
        //String earthDate = getIntent().getExtras().getString("earthdate");
        //String strClass = getIntent().getExtras().getString("class");



/**/
        //tvCameraFullName.setText("camera full name: "+cameraFullname);
        //tvEarthDate.setText("earth date: " + earthDate);

        tvCameraFullName.setText(cameraFullname);
        tvEarthDate.setText(earthDate);

        Picasso.with(DetailActivity.this).load(imgSrc).into(ivImg);

        //tvRoverLandingdate.setText("rover landing date: " + roverLandingdate);
        tvRoverLandingdate.setText(roverLandingdate);

        findViewById(R.id.adetail_btn_regresar).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.adetail_btn_regresar:
                irLista();
                break;

        }

    }

    private void irLista() {
        Intent intent = new Intent(DetailActivity.this, ListActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_redes, menu);
        //super.onCreateOptionsMenu(menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuRedes_share_today_apod:
                //Toast.makeText(getActivity(), "Hola", Toast.LENGTH_LONG ).show();
                //Snackbar.make(getView(), "Share", Snackbar.LENGTH_SHORT).show();

                shareText("APP: en detail activity" );
                return true;
            case R.id.menuRedes_favorites:

                //Agregar a favoritos a la base de datos
                Snackbar.make(findViewById(android.R.id.content), R.string.addFavorites, Snackbar.LENGTH_SHORT).show();

                OperacionesDatos db = new OperacionesDatos(DetailActivity.this);
                agregarFavoritos(db);

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

    private void agregarFavoritos(OperacionesDatos db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBD.COLUMNE_FAV_MR_CAMERA_FULL_NAME, cameraFullname);
        contentValues.put(ConstantesBD.COLUMNE_FAV_MR_EARTH_DATE, earthDate);
        contentValues.put(ConstantesBD.COLUMNE_FAV_MR_IMG_SRC, imgSrc);
        contentValues.put(ConstantesBD.COLUMNE_FAV_MR_ROVER_LANDING_DATE, roverLandingdate);

        db.insertarFavoriteMR(contentValues);


        /*PARA VER BD
         eN ANDROID DEVICE MONITOR, SELECCIONAR EL EMULADOR DE LADO IZQ, LUEGO
         FILE EXPLORER DE LA DERECHA, LUEGO DATA/DATA/ PAQUETE DE LA APLICACION*/

    }


}
