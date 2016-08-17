package mx.nitrogena.dadm.mod5.nasa.login;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.nitrogena.dadm.mod5.nasa.ListActivity;
import mx.nitrogena.dadm.mod5.nasa.R;

public class FBLoginActivity extends AppCompatActivity implements FacebookCallback<LoginResult>{

    @BindView(R.id.fb_login_button)
    LoginButton loginButton;

    CallbackManager callbackManager;


    /*ESTA CLASE ES LA QUE ESTARA MANDANDO COMO LA PRINCIPAL EL MANIFEST*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fblogin);

        ButterKnife.bind(this);

        callbackManager = CallbackManager.Factory.create();

        loginButton.registerCallback(callbackManager, this);

        if (AccessToken.getCurrentAccessToken() != null){
            Snackbar.make(findViewById(android.R.id.content), "Login", Snackbar.LENGTH_SHORT).show();

            startActivity(new Intent(this, ListActivity.class));
        }
    }

    @Override
    public void onSuccess(LoginResult loginResult) {
        //Snackbar.make(findViewById(android.R.id.content), "Login", Snackbar.LENGTH_SHORT).show();

        startActivity(new Intent(this, ListActivity.class));
    }

    @Override
    public void onCancel() {
        Snackbar.make(findViewById(android.R.id.content), "Cancel Login", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onError(FacebookException error) {
        Snackbar.make(findViewById(android.R.id.content), error.getMessage(), Snackbar.LENGTH_SHORT).show();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }
}
