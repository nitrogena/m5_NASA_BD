package mx.nitrogena.dadm.mod5.nasa.app;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Alumno on 06/08/2016.
 */
public class NasaAppplication extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        Fresco.initialize(this);
        FacebookSdk.sdkInitialize(this);
    }
}
