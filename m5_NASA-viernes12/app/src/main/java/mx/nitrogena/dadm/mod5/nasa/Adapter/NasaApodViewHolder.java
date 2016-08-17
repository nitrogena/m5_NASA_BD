package mx.nitrogena.dadm.mod5.nasa.Adapter;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mx.nitrogena.dadm.mod5.nasa.R;
import mx.nitrogena.dadm.mod5.nasa.model.Photo;

/**
 * Created by Alumno on 05/08/2016.
 */
public class NasaApodViewHolder extends RecyclerView.ViewHolder{

    //CON BIND VIEW YA NO TENGO QUE USAR EL METODO A MANITA  5-8-16
   /* @BindView(R.id.alistcv_tv_id) TextView tvId;
    @BindView(R.id.alistcv_tv_sol) TextView tvSol;
    @BindView(R.id.alistcv_tv_earthDate) TextView tvEarthDate;*/

    @BindView(R.id.alistcv_tv_camera_fullName) TextView tvCameraFullName;


    //@BindView(R.id.alistcv_iv_img) ImageView ivImg;
    //se cambio el tipo porque usamos fresco 6-8-16
    @BindView(R.id.alistcv_iv_img) SimpleDraweeView ivImg;

    //para crear el itemcliclistener
    private  NasaApodAdapter.OnItemClickListener onItemListener;
    private Photo photo;


    //HACER RECICLAJE Y HACER ASOCIACION DE VISTAS
    //NO DEBE HABER MAS
    public NasaApodViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    //Creamos mensajero para mandar mensaje, o los objetos que se usan en esa interfaz
    //de referencia para obtener objetos que tenemos en el adapter
    public void setItemClick(Photo photo, NasaApodAdapter.OnItemClickListener onItemListener){
        this.photo = photo;
        this.onItemListener = onItemListener;
    }

    @OnClick(R.id.alistcv_iv_img)
    public void onViewClick(View view){
        if (onItemListener != null)
            onItemListener.onItemClick(photo);
    }

}