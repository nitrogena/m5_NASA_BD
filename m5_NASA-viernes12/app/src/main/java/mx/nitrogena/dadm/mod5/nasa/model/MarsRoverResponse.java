package mx.nitrogena.dadm.mod5.nasa.model;

/**
 * Created by Alumno on 05/08/2016.
 */
import java.util.ArrayList;
import java.util.List;
//import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//@Generated("org.jsonschema2pojo")
public class MarsRoverResponse {

    @SerializedName("photos")
    @Expose
    private List<Photo> photos = new ArrayList<Photo>();

    /**
     *
     * @return
     *     The photos
     */
    public List<Photo> getPhotos() {
        return photos;
    }

    /**
     *
     * @param photos
     *     The photos
     */
    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

}
