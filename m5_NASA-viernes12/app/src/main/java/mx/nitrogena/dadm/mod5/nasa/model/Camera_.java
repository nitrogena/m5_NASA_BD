package mx.nitrogena.dadm.mod5.nasa.model;

/**
 * Created by Alumno on 05/08/2016.
 */



//        import javax.annotation.Generated;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

//@Generated("org.jsonschema2pojo")
public class Camera_ {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("full_name")
    @Expose
    private String fullName;

    /**
     *
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     *     The fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     *
     * @param fullName
     *     The full_name
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}
