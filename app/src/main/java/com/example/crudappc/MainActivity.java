package com.example.crudappc;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crudappc.interfaces.API;
import com.example.crudappc.model.Empleado;
import com.example.crudappc.utils.Conexion;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
{
    API apiEmpleado;

    private ImageButton imgBtnFacebook;
    private ImageButton imgBtnTwitter;
    private ImageButton imgBtnYoutube;
    private String URL_TO_YOUTUBE = "http://www.youtube.com";
    private String URL_TO_FACEBOOK = "http://www.facebook.com";
    private String URL_TO_TWITTER = "http://www.twitter.com";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView nombre = (TextView) findViewById(R.id.tvId);
        EditText txtNombre = (EditText) findViewById(R.id.txtNombre);
        TextView email = (TextView) findViewById(R.id.tvEmail);
        EditText txtEmail = (EditText) findViewById(R.id.txtEmail);
        TextView password = (TextView) findViewById(R.id.password);
        EditText txtPassword = (EditText) findViewById(R.id.txtPassword);

        Button btnIngresar = (Button) findViewById(R.id.btnIngresar);
        Button btnEliminar= (Button) findViewById(R.id.btnEliminar);

        Button btnBuscarUser = (Button) findViewById(R.id.btnConsultarId);


        imgBtnFacebook = (ImageButton) findViewById(R.id.imgBtnFacebook);
        imgBtnTwitter = (ImageButton) findViewById(R.id.imgBtnTwitter);
        imgBtnYoutube = (ImageButton) findViewById(R.id.imgBtnYoutube);


        btnIngresar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Empleado empleado = new Empleado();
                empleado.setNombre(txtNombre.getText().toString());
                empleado.setEmail(txtEmail.getText().toString());
                empleado.setPassword(txtPassword.getText().toString());
                addEmpleado(empleado);
            }
        });


        btnBuscarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EmpleadoConsultar.class);
                startActivity(intent);
            }
        });


        /**
         * Botones de redes sociales
         */
        imgBtnYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://www.youtube.com");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        imgBtnTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://twitter.com/BlackClover_EN");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        imgBtnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.facebook.com/blackclover.fans/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });


    }

    public void addEmpleado(Empleado empleado)
    {
        apiEmpleado = Conexion.getEmpleadoInterface(); // hacer la conexion

        Call<Empleado> call = apiEmpleado.addEmpleado(empleado);

        call.enqueue(new Callback<Empleado>() {
            @Override
            public void onResponse(Call<Empleado> call, Response<Empleado> response)
            {
                if( response != null )
                {
                    Empleado empleado = response.body();
                    //Toast.makeText(EmpleadoActivity.this, "Se agregó con éxito", Toast.LENGTH_LONG).show();
                    Toast.makeText(MainActivity.this, "Se agregó con éxito", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Empleado> call, Throwable t) {
                Log.e("Error: ", t.getMessage());
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_desplegable, menu);
        return super.onCreateOptionsMenu(menu);
    }
    /**
     * Agsignar mensaje a los botones
     */


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        int id = item.getItemId();
        Intent intent = new Intent(MainActivity.this, Bienvenida.class);
        switch (id)
        {
            case R.id.item1:
                Toast.makeText(this, "Bienvenido a la aplicación v1", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                break;

            case R.id.item2:
                Toast.makeText(this, "Bienvenido a la aplicación v2", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                break;

            case R.id.item3:
                Toast.makeText(this, "Bienvenida a la aplicación v3", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                break;

            case R.id.item4:
                Toast.makeText(this, "Bienvenida a la aplicación v4", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void activityEliminar(View view)
    {
        // Do something in response to button
        Intent intent = new Intent(MainActivity.this, EmpleadoEliminar.class);
        startActivity(intent);
    }
}
