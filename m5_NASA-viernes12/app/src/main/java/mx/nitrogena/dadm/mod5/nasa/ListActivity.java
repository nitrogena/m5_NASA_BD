package mx.nitrogena.dadm.mod5.nasa;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.nitrogena.dadm.mod5.nasa.fragment.ListFragment;
import mx.nitrogena.dadm.mod5.nasa.fragment.TodayApodFragment;

/**
 * Created by Alumno on 05/08/2016.
 */
public class ListActivity extends AppCompatActivity {

    /*
    @BindView(R.id.nasaapodlayout_rv_recyclerv)
    RecyclerView marsRoverListRecycler;
*/
    @BindView(R.id.navigationActivity_toolbar) Toolbar toolbar;
    @BindView(R.id.navigationActivity_navigationView)
    NavigationView navigationView;
    @BindView(R.id.navigation_drawer)
    DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Se cambio el viernes 12 de agosto, porque estamos usando un menu
        //se crearon los archivos navigation header y navigation activity
        //setContentView(R.layout.nasa_apod_layout);


        setContentView(R.layout.navigation_activity);

        ButterKnife.bind(this);

        //Fresco.initialize(this);

        // Find the toolbar view inside the activity layout
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);

/*

        try {
            PackageInfo info = getPackageManager().getPackageInfo("mx.nitrogena.dadm.mod5.nasa",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }

*/


        replaceFragment(TodayApodFragment.newInstance("name"));


        /*https://guides.codepath.com/android/Using-the-App-ToolBar*/

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                drawerLayout.closeDrawers();
                switch (item.getItemId()){
                    case R.id.menuuno_opcionUno:
                        replaceFragment(TodayApodFragment.newInstance("name"));
                        return true;


                    case R.id.menuuno_opcionDos:
                        replaceFragments(ListFragment.newInstance("names"));
                        return true;


                    case R.id.menuuno_opcionTres:
                        Snackbar.make(findViewById(android.R.id.content), "Opcion tres", Snackbar.LENGTH_SHORT).show();
                        return true;

                }
                return true;        //se supone que no va

            }
        });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name){
            @Override
            public void onDrawerClosed(View drawerView){
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView){
                super.onDrawerOpened(drawerView);
            }

        };

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        //sincronizar cuando abre o cuando lo cerramos
        actionBarDrawerToggle.syncState();





        /*Hecho el sabado 6 de agosto se quitó el 12 de agosto
       LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        marsRoverListRecycler.setLayoutManager(linearLayoutManager);


        //6-8-16 Creamos adaptador
        final NasaApodAdapter nasaApodAdapter = new NasaApodAdapter();
        nasaApodAdapter.setOnItemClickListener(new NasaApodAdapter.OnItemClickListener(){

            @Override
            public void onItemClick(Photo photo) {
                Log.d("log - titulo: ", photo.getCamera().getFullName());
                Log.d("log - earthDATE: ", photo.getEarthDate());


                Intent intent = new Intent(ListActivity.this, DetailActivity.class);
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
                //ESTAMOS OBTENIENDO LOS VALORES DEL JSON QUE USAMOS

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

        */

        getFBUserInfo();
    }

    private void replaceFragment(TodayApodFragment f){

        getFragmentManager().beginTransaction().replace(R.id.fragmentHolder, f).commit();
    }

    private void replaceFragments(ListFragment f){

        getFragmentManager().beginTransaction().replace(R.id.fragmentHolder, f).commit();
    }


    private void getFBUserInfo() {
        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(),
        new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    Log.d("name", object.getString("name"));
                    Log.d("id", object.getString("id"));

                    SimpleDraweeView userImae = (SimpleDraweeView) findViewById(R.id.user_image);
                    userImae.setImageURI("http://graph.facebook.com/"+ object.getString("id") + "/picture?type=large");
                    TextView userName = (TextView) findViewById(R.id.user_name);
                    userName.setText(object.getString("name"));




                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });

        request.executeAsync();
    }

        /*
       interface UserDataCallback{
           void userData(FBUser user);
       }
    */


    /*https://developers.facebook.com/quickstarts/297876327242282/?platform=android*/
}
