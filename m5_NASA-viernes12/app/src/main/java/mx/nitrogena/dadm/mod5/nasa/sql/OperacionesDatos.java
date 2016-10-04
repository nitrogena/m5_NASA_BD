package mx.nitrogena.dadm.mod5.nasa.sql;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import mx.nitrogena.dadm.mod5.nasa.model.FavoriteAPODModel;
import mx.nitrogena.dadm.mod5.nasa.model.FavoriteMarsRoverModel;

/**
 * Created by USUARIO on 18/08/2016.
 */
public class OperacionesDatos {

    private final SQLiteDatabase db;

    public OperacionesDatos(Context contexto) {
        BaseDatos helper = new BaseDatos(contexto);
        db = helper.getWritableDatabase();
    }

    public ArrayList<FavoriteMarsRoverModel> obtenerFavoritosMR(){
        ArrayList<FavoriteMarsRoverModel> arrLstFavMr = new ArrayList<>();
        Cursor cursor = db.query(ConstantesBD.TABLE_FAV_MR, null, null, null, null, null, null);

        while (cursor.moveToNext())
        {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantesBD.COLUMNE_FAV_MR_ID));
            String cameraFullName = cursor.getString(cursor.getColumnIndexOrThrow(ConstantesBD.COLUMNE_FAV_MR_CAMERA_FULL_NAME));
            String earthDate = cursor.getString(cursor.getColumnIndexOrThrow(ConstantesBD.COLUMNE_FAV_MR_EARTH_DATE));
            String imgSrc = cursor.getString(cursor.getColumnIndexOrThrow(ConstantesBD.COLUMNE_FAV_MR_IMG_SRC));
            String roverLandingDate = cursor.getString(cursor.getColumnIndexOrThrow(ConstantesBD.COLUMNE_FAV_MR_ROVER_LANDING_DATE));

            FavoriteMarsRoverModel favMrModel = new FavoriteMarsRoverModel();
            favMrModel.id = id;
            favMrModel.cameraFullName = cameraFullName;
            favMrModel.earthDate = earthDate;
            favMrModel.imgSrc = imgSrc;
            favMrModel.roverLandingDate = roverLandingDate;

            arrLstFavMr.add(favMrModel);
        }
        db.close();
        return arrLstFavMr;

    }

    public void insertarFavoriteMR(ContentValues contentValues){
        db.insert(ConstantesBD.TABLE_FAV_MR, null, contentValues);
        db.close();

    }

    public void insertarFavoriteAPOD(ContentValues contentValues){
        db.insert(ConstantesBD.TABLE_FAV_APOD, null, contentValues);
        db.close();

    }

    public void eliminarFavoriteMR(FavoriteMarsRoverModel favoriteMRModel){
        db.delete(ConstantesBD.TABLE_FAV_MR, ConstantesBD.COLUMNE_FAV_MR_ID+"=?",
                new String[]{String.valueOf(favoriteMRModel.id)});



    }

    public void eliminarFavoriteApod(FavoriteAPODModel favoriteApModel){
        db.delete(ConstantesBD.TABLE_FAV_APOD, ConstantesBD.COLUMNE_FAV_APOD_ID+"=?",
                new String[]{String.valueOf(favoriteApModel.id)});
    }

    public ArrayList<FavoriteAPODModel> obtenerFavoritosApod(){
        ArrayList<FavoriteAPODModel> arrLstFavApod = new ArrayList<>();
        Cursor cursor = db.query(ConstantesBD.TABLE_FAV_APOD, null, null, null, null, null, null);

        while (cursor.moveToNext())
        {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(ConstantesBD.COLUMNE_FAV_APOD_ID));
            String copyright = cursor.getString(cursor.getColumnIndexOrThrow(ConstantesBD.COLUMNE_FAV_APOD_COPYRIGHT));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(ConstantesBD.COLUMNE_FAV_APOD_DATE));
            String imgSrc = cursor.getString(cursor.getColumnIndexOrThrow(ConstantesBD.COLUMNE_FAV_APOD_URL));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(ConstantesBD.COLUMNE_FAV_APOD_TITLE));
            String media = cursor.getString(cursor.getColumnIndexOrThrow(ConstantesBD.COLUMNE_FAV_APOD_MEDIATYPE));

            FavoriteAPODModel favApModel = new FavoriteAPODModel();
            favApModel.id = id;
            favApModel.copyright = copyright;
            favApModel.date = date;
            favApModel.url = imgSrc;
            favApModel.title = title;
            favApModel.mediaType = media;



            arrLstFavApod.add(favApModel);
        }
        db.close();
        return arrLstFavApod;

    }
}
