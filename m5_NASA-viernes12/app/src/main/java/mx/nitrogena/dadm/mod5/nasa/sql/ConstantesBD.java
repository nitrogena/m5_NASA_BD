package mx.nitrogena.dadm.mod5.nasa.sql;

import java.lang.ref.SoftReference;

/**
 * Created by USUARIO on 18/08/2016.
 */
public final class ConstantesBD {

    //FINAL PARA QUE NADIE LOS ALTERE

    public static final String DATABASE_NAME = "nasa";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_FAV_MR = "favorite_mr";
    public static final String COLUMNE_FAV_MR_ID = "id";
    public static final String COLUMNE_FAV_MR_CAMERA_FULL_NAME = "camera_full_name";
    public static final String COLUMNE_FAV_MR_EARTH_DATE = "earth_date";
    public static final String COLUMNE_FAV_MR_IMG_SRC = "img_src";
    public static final String COLUMNE_FAV_MR_ROVER_LANDING_DATE = "rover_landing_date";

    public static final String TABLE_FAV_APOD = "favorite_apod";
    public static final String COLUMNE_FAV_APOD_ID = "id";
    public static final String COLUMNE_FAV_APOD_COPYRIGHT = "copyryght";
    public static final String COLUMNE_FAV_APOD_DATE = "date";
    public static final String COLUMNE_FAV_APOD_EXPLANATION = "explanation";
    public static final String COLUMNE_FAV_APOD_MEDIATYPE = "media_type";
    public static final String COLUMNE_FAV_APOD_SERVICEVERSION = "service_version";
    public static final String COLUMNE_FAV_APOD_URL = "url";
    public static final String COLUMNE_FAV_APOD_TITLE = "title";
}
