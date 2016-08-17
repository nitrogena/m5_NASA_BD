package mx.nitrogena.dadm.mod5.nasa.data;

import mx.nitrogena.dadm.mod5.nasa.model.APOD;
import mx.nitrogena.dadm.mod5.nasa.model.MarsRoverResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Alumno on 30/07/2016.
 */
public interface ApodService {

    //NOS AYUDA A REALIZAR LA PETICION A LA PAGINA HTTP
    //SE PONEN LOS ENDPOINTS A UTILIZAR
    //DONDE SE USARNA METODOS CON EL TIPO DE RESPUESTA QUE DESEAMOS OBTENER

    //nomenclatura es @TIPO_REQUEST(DIRECCION DEL JSON OBTENIDO CON LA API KEY)
    @GET("planetary/apod?api_key=sN0q0EGDwJer2FFc4ns7toCYkthNtvgmtuAjtEIV")
    Call<APOD> getTodayApod();
    //Call es del retrofit, el json se vuelve un objeto APOD que es regresado por el metodo getTodayApod

    @GET("planetary/apod")
    Call<APOD> getTodayApodWithQuery(@Query("api_key") String apiKey);
    //PARAMETRO QUE NECESITA EL ENDPOINT: "api_key"

    @GET("mars-photos/api/v1/rovers/curiosity/photos")
    Call<MarsRoverResponse> getTodayMarsRoverResponseWithQuery(@Query("sol") int sol, @Query("api_key") String apiKey);


}
