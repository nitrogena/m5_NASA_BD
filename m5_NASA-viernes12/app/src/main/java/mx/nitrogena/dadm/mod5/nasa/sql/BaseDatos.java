package mx.nitrogena.dadm.mod5.nasa.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by USUARIO on 18/08/2016.
 */
public class BaseDatos extends SQLiteOpenHelper{

    private Context contexto;

    public BaseDatos(Context contexto) {

        //Se construye la bd, si no existe la crea, sino la abre
        super(contexto, ConstantesBD.DATABASE_NAME, null, ConstantesBD.DATABASE_VERSION);
        this.contexto = contexto;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qryCrearTabFavoritoMR = "CREATE TABLE " + ConstantesBD.TABLE_FAV_MR + "(" +
                ConstantesBD.COLUMNE_FAV_MR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBD.COLUMNE_FAV_MR_CAMERA_FULL_NAME + " TEXT, " +
                ConstantesBD.COLUMNE_FAV_MR_EARTH_DATE + " TEXT, " +
                ConstantesBD.COLUMNE_FAV_MR_IMG_SRC + " TEXT, " +
                ConstantesBD.COLUMNE_FAV_MR_ROVER_LANDING_DATE + " TEXT " +
                ")";

        String qryCrearTabFavoritoApod = "CREATE TABLE " + ConstantesBD.TABLE_FAV_APOD+ "(" +
                ConstantesBD.COLUMNE_FAV_APOD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBD.COLUMNE_FAV_APOD_TITLE + " TEXT, " +
                ConstantesBD.COLUMNE_FAV_APOD_COPYRIGHT + " TEXT, " +
                ConstantesBD.COLUMNE_FAV_APOD_DATE + " TEXT, " +
                ConstantesBD.COLUMNE_FAV_APOD_EXPLANATION + " TEXT, " +
                ConstantesBD.COLUMNE_FAV_APOD_MEDIATYPE + " TEXT, " +
                ConstantesBD.COLUMNE_FAV_APOD_SERVICEVERSION + " TEXT, " +
                ConstantesBD.COLUMNE_FAV_APOD_URL + " TEXT " +
                ")";

        db.execSQL(qryCrearTabFavoritoMR);
        db.execSQL(qryCrearTabFavoritoApod);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + ConstantesBD.TABLE_FAV_MR);
        db.execSQL("DROP TABLE IF EXIST " + ConstantesBD.TABLE_FAV_APOD);
        onCreate(db);
    }


}
