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
import com.example.crudappc.utils.Conexion;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmpleadoConsultar extends AppCompatActivity
{

    API apiUser;
    Button btnConsultar;
    EditText txtId;
    TextView tvNombre;
    TextView showNombre;
    TextView tvtEmail;
    TextView showEmail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empleado_consultar);

        Intent intent = getIntent();
        intent.getAction();

        btnConsultar = (Button) findViewById(R.id.btnConsultar);
        showNombre = (TextView) findViewById(R.id.showNombre);
        showEmail = (TextView) findViewById(R.id.showEmail);
        txtId = (EditText) findViewById(R.id.txtId);


        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // para el id traido del Editext
                findByUser(Long.parseLong(txtId.getText().toString()));

                //showNombre.setText(empleado.getNombre());
                //showEmail.setText(empleado.getEmail());
            }
        });

    }

    private void findByUser(Long id)
    {
        apiUser = Conexion.getEmpleadoInterface(); /// hacer conexion

        Call<Empleado> call = apiUser.findUserById(id);

        call.enqueue(new Callback<Empleado>() {
            @Override
            public void onResponse(Call<Empleado> call, Response<Empleado> response) {
                if (response.isSuccessful())
                {
                    Empleado empleado = response.body();

                    Log.i(empleado.getNombre(), "MOSTRANDO VALOR DE NOMBRE EMPLEADO");




                    Toast.makeText(EmpleadoConsultar.this, "Get correcto", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Empleado> call, Throwable t) {
                Toast.makeText(EmpleadoConsultar.this, "Error de Conexion", Toast.LENGTH_SHORT).show();
                Log.e("Get no cumplido", t.getMessage());
            }
        });
    }

}
