package mx.nitrogena.dadm.mod5.nasa.Adapter;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;


import java.security.AccessController;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.nitrogena.dadm.mod5.nasa.R;
import mx.nitrogena.dadm.mod5.nasa.fragment.ListFavoriteMRFragment;
import mx.nitrogena.dadm.mod5.nasa.model.FavoriteMarsRoverModel;
import mx.nitrogena.dadm.mod5.nasa.sql.OperacionesDatos;

import static android.app.PendingIntent.getActivity;

/**
 * Created by USUARIO on 25/09/2016.
 */

public class FavoriteRMAdapter extends RecyclerView.Adapter<FavoriteRMAdapter.FavoriteRMViewHolder>{

    ArrayList<FavoriteMarsRoverModel> arrLstFavoriteMrMle;
    Activity activity;

    private OperacionesDatos operacionesDatos;


    public FavoriteRMAdapter(ArrayList<FavoriteMarsRoverModel> arrLstFavoriteMrMle, Activity activity) {
        //constructor, pasamos la lista de contactos que se coloca en el objeto global que esta arriba
        this.arrLstFavoriteMrMle = arrLstFavoriteMrMle;
        this.activity = activity;
    }

    @Override
    public FavoriteRMViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //se pone el layout del cardview
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_itemlist_favoritemr, parent, false);
        //mandamos la vista al constructor viewHolder
        return new FavoriteRMViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(FavoriteRMViewHolder holder, int position) {
        //se invoca cada que se va recorriendo la lista de  arrLstFavoriteMrMle
        //holder fue creado en la public static class de abajo
        final FavoriteMarsRoverModel modelItem = arrLstFavoriteMrMle.get(position);

        //se tiene acceso a los elementos view y lo asocia con el contenido
        Picasso.with(holder.img.getContext())
                .load(modelItem.imgSrc)
                .into(holder.img);

        holder.tvEarthDate.setText(modelItem.earthDate);
        holder.tvCameraFullName.setText(modelItem.cameraFullName);
        holder.tvRoverLanding.setText(modelItem.roverLandingDate);

        holder.img.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(activity, modelItem.imgSrc, Toast.LENGTH_SHORT).show();
                Snackbar.make(v, R.string.qDelete, Snackbar.LENGTH_LONG)
                        .setAction("Yes", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Log.i("Snackbar", "hizo clic en snacbar favoritos");
                                operacionesDatos = new OperacionesDatos(activity);
                                operacionesDatos.eliminarFavoriteMR(modelItem);

                                Toast.makeText(activity, R.string.msgEliminado, Toast.LENGTH_SHORT).show();
                                //getFragmentManager().beginTransaction().replace(R.id.fragmentHolder, ListFavoriteMRFragment.newInstance("names")).commit();
                            }
                        })
                        .show();
            }
        });


    }

    @Override
    public int getItemCount() {

        return arrLstFavoriteMrMle.size();
    }

    public static class FavoriteRMViewHolder extends RecyclerView.ViewHolder {

        //se agregaron el 24 sept, declaramos las view
        @BindView(R.id.fitemlist_favoritemr_tv_earthDate)
        TextView tvEarthDate;
        @BindView(R.id.fitemlist_favoritemr_tv_camera_fullName) TextView tvCameraFullName;
        @BindView(R.id.fitemlist_favoritemr_iv_img)
        ImageView img;
        @BindView(R.id.fitemlist_favoritemr_tv_roverLanding) TextView tvRoverLanding;


        //constructor que tomara cada elemento que compone el layout
        public FavoriteRMViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }
}
