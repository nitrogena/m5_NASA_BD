package mx.nitrogena.dadm.mod5.nasa.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
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
import mx.nitrogena.dadm.mod5.nasa.DetailActivity;
import mx.nitrogena.dadm.mod5.nasa.ListActivity;
import mx.nitrogena.dadm.mod5.nasa.R;
import mx.nitrogena.dadm.mod5.nasa.fragment.ListFavoriteApodFragment;
import mx.nitrogena.dadm.mod5.nasa.fragment.ListFavoriteMRFragment;
import mx.nitrogena.dadm.mod5.nasa.model.FavoriteAPODModel;
import mx.nitrogena.dadm.mod5.nasa.model.FavoriteMarsRoverModel;
import mx.nitrogena.dadm.mod5.nasa.sql.OperacionesDatos;

import static android.app.PendingIntent.getActivity;

/**
 * Created by USUARIO on 03/10/2016.
 */

public class FavoriteApodAdapter extends RecyclerView.Adapter<FavoriteApodAdapter.FavoriteApodViewHolder>{

    ArrayList<FavoriteAPODModel> arrLstFavoriteApMle;
    Activity activity;

    private OperacionesDatos operacionesDatos;


    public FavoriteApodAdapter(ArrayList<FavoriteAPODModel> arrLstFavoriteApMle, Activity activity) {
        //constructor, pasamos la lista de contactos que se coloca en el objeto global que esta arriba
        this.arrLstFavoriteApMle = arrLstFavoriteApMle;
        this.activity = activity;
    }

    @Override
    public FavoriteApodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //se pone el layout del cardview
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_itemlist_favoriteapod, parent, false);
        //mandamos la vista al constructor viewHolder
        return new FavoriteApodViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(FavoriteApodViewHolder holder, int position) {
        //se invoca cada que se va recorriendo la lista de  arrLstFavoriteMrMle
        //holder fue creado en la public static class de abajo
        final FavoriteAPODModel modelItem = arrLstFavoriteApMle.get(position);

        //se tiene acceso a los elementos view y lo asocia con el contenido
        Picasso.with(holder.img.getContext())
                .load(modelItem.url)
                .into(holder.img);

        holder.tvDate.setText(modelItem.date);
        holder.tvTitle.setText(modelItem.title);
        holder.tvCopy.setText(modelItem.copyright);

        holder.img.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(activity, modelItem.url, Toast.LENGTH_SHORT).show();
                Snackbar.make(v, R.string.qDelete, Snackbar.LENGTH_LONG)
                        .setAction("Yes", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Log.i("Snackbar", "hizo clic en snacbar favoritos APOD");
                                operacionesDatos = new OperacionesDatos(activity);
                                operacionesDatos.eliminarFavoriteApod(modelItem);
                                Toast.makeText(activity, R.string.msgEliminado, Toast.LENGTH_SHORT).show();


                            }

                                //getFragmentManager().beginTransaction().replace(R.id.fragmentHolder, ListFavoriteMRFragment.newInstance("names")).commit();

                        })
                        .show();
            }
        });


    }

    @Override
    public int getItemCount() {

        return arrLstFavoriteApMle.size();
    }

    public static class FavoriteApodViewHolder extends RecyclerView.ViewHolder {

        //se agregaron el 24 sept, declaramos las view
        @BindView(R.id.fitemlist_favoriteapod_tv_date)
        TextView tvDate;
        @BindView(R.id.fitemlist_favoriteapod_tv_title) TextView tvTitle;
        @BindView(R.id.fitemlist_favoriteapod_iv_img)
        ImageView img;
        @BindView(R.id.fitemlist_favoriteapod_tv_copy) TextView tvCopy;


        //constructor que tomara cada elemento que compone el layout
        public FavoriteApodViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }
}
