package mx.nitrogena.dadm.mod5.nasa.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.security.AccessController;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.nitrogena.dadm.mod5.nasa.R;
import mx.nitrogena.dadm.mod5.nasa.model.FavoriteMarsRoverModel;

import static java.security.AccessController.*;

/**
 * Created by USUARIO on 25/09/2016.
 */

public class FavoriteRMAdapter extends RecyclerView.Adapter<FavoriteRMAdapter.FavoriteRMViewHolder>{

    ArrayList<FavoriteMarsRoverModel> arrLstFavoriteMrMle;

    public FavoriteRMAdapter(ArrayList<FavoriteMarsRoverModel> arrLstFavoriteMrMle) {

        this.arrLstFavoriteMrMle = arrLstFavoriteMrMle;
    }

    @Override
    public FavoriteRMViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_itemlist_favoritemr, parent, false);
        //ButterKnife.bind(this, vista);
        return new FavoriteRMViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(FavoriteRMViewHolder holder, int position) {
        FavoriteMarsRoverModel modelItem = arrLstFavoriteMrMle.get(position);

        Picasso.with(holder.img.getContext())
                .load(modelItem.imgSrc)
                .into(holder.img);

        //Picasso.with(getContext()).load(modelItem.imgSrc).into(img);

        holder.tvEarthDate.setText(modelItem.earthDate);
        holder.tvCameraFullName.setText(modelItem.cameraFullName);
        holder.tvRoverLanding.setText(modelItem.roverLandingDate);

    }

    @Override
    public int getItemCount() {

        return arrLstFavoriteMrMle.size();
    }

    public static class FavoriteRMViewHolder extends RecyclerView.ViewHolder {

        //se agregaron el 24 sept
        @BindView(R.id.fitemlist_favoritemr_tv_earthDate)
        TextView tvEarthDate;
        @BindView(R.id.fitemlist_favoritemr_tv_camera_fullName) TextView tvCameraFullName;
        @BindView(R.id.fitemlist_favoritemr_iv_img)
        ImageView img;
        @BindView(R.id.fitemlist_favoritemr_tv_roverLanding) TextView tvRoverLanding;



        public FavoriteRMViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }
}
