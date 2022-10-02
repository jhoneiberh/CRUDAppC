package com.example.crudappc;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.crudappc.interfaces.API;
import com.example.crudappc.model.Empleado;
import com.example.crudappc.utils.Cliente;
import com.example.crudappc.utils.Conexion;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EmpleadoIngresar extends AppCompatActivity
{

    API apiEmpleado;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empleado_ingresar);


        TextView nombre = (TextView) findViewById(R.id.nombre);
        EditText txtNombre = (EditText) findViewById(R.id.txtNombre);
        TextView email = (TextView) findViewById(R.id.email);
        EditText txtEmail = (EditText) findViewById(R.id.txtEmail);
        TextView password = (TextView) findViewById(R.id.password);
        EditText txtPassword = (EditText) findViewById(R.id.txtPassword);

        Button btnIngresar = (Button) findViewById(R.id.btnIngresar);
        Button btnRegresar = (Button) findViewById(R.id.btnRegresar);


        btnIngresar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Empleado empleado = new Empleado();
                empleado.setNombre(txtNombre.getText().toString());
                empleado.setEmail(txtEmail.getText().toString());
                empleado.setPassword(txtPassword.getText().toString());
                addEmpleado(empleado);

                /* Despues de ejecutado el metodo agregar hacer que se direccione al Main Activity
                 */
                Intent intent = new Intent(EmpleadoIngresar.this, MainActivity.class);
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
            public void onResponse(Call<Empleado> call, Response<Empleado> response) {
                if( response != null )
                {
                    //Toast.makeText(EmpleadoActivity.this, "Se agregó con éxito", Toast.LENGTH_LONG).show();
                    Toast.makeText(EmpleadoIngresar.this, "Se agregó con éxito", Toast.LENGTH_SHORT).show();


                }
            }

            @Override
            public void onFailure(Call<Empleado> call, Throwable t) {
                Log.e("Error: ", t.getMessage());
            }
        });

        /* Despues de ejecutado el metodo agregar hacer que se direccione al Main Activity
         */
        // Por si la instancia de arriba no funciona
        Intent intent = new Intent(EmpleadoIngresar.this, MainActivity.class);
        startActivity(intent);
    }


}
