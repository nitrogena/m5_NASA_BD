package mx.nitrogena.dadm.mod5.nasa.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.nitrogena.dadm.mod5.nasa.R;
import mx.nitrogena.dadm.mod5.nasa.model.FavoriteMarsRoverModel;

/**
 * Created by USUARIO on 19/08/2016.
 */
public class FavoriteAdapter extends ArrayAdapter<FavoriteMarsRoverModel>{

    //se agregaron el 24 sept
    @BindView(R.id.fitemlist_favoritemr_tv_earthDate) TextView tvEarthDate;
    @BindView(R.id.fitemlist_favoritemr_tv_camera_fullName) TextView tvCameraFullName;
    @BindView(R.id.fitemlist_favoritemr_iv_img) ImageView img;
    @BindView(R.id.fitemlist_favoritemr_tv_roverLanding) TextView tvRoverLanding;

    public FavoriteAdapter(Context context, List<FavoriteMarsRoverModel> objects) {
        super(context, 0, objects);


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
        {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_itemlist_favoritemr, parent, false);
        }
        //se agregaron el 24 sept
        ButterKnife.bind(this, convertView);

        //se quitaron el 24 sept

        //TextView tvEarthDate = (TextView) convertView.findViewById(R.id.fitemlist_favoritemr_tv_earthDate);
        //TextView tvCameraFullName = (TextView) convertView.findViewById(R.id.fitemlist_favoritemr_tv_camera_fullName);
        //ImageView img = (ImageView) convertView.findViewById(R.id.fitemlist_favoritemr_iv_img);
        //TextView tvRoverLanding = (TextView) convertView.findViewById(R.id.fitemlist_favoritemr_tv_roverLanding);

        FavoriteMarsRoverModel modelItem = getItem(position);

        Picasso.with(getContext()).load(modelItem.imgSrc).into(img);

        tvEarthDate.setText(modelItem.earthDate);
        tvCameraFullName.setText(modelItem.cameraFullName);
        tvRoverLanding.setText(modelItem.roverLandingDate);
        return convertView;
    }
}
