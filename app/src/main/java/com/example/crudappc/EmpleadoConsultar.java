package com.example.crudappc;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.crudappc.interfaces.API;
import com.example.crudappc.model.Empleado;
import com.example.crudappc.utils.Conexion;

import java.util.List;

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
    List<Empleado> empleado;
private String rt;
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
                //findBy(Long.parseLong(txtId.getText().toString()));
                //showNombre.setText(rt);
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
                try {

                    if (response.isSuccessful())
                    {
                        Empleado empleado = response.body();
                        //showNombre.setText(empleado.getNombre().toString());
                        showNombre.setText(String.valueOf(empleado.getNombre()));
                        Log.i("MENSAJE",empleado.getEmail().toString());
                        //showEmail.setText(empleado.getEmail().toString());
                        showEmail.setText(String.valueOf(empleado.getEmail()));
                    }
                } catch (Exception e) {
                    Toast.makeText(EmpleadoConsultar.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Empleado> call, Throwable t) {
                Toast.makeText(EmpleadoConsultar.this, "Error de Conexion", Toast.LENGTH_SHORT).show();
                Log.e("Get no cumplido", t.getMessage());
            }
        });
    }

    private void findBy(Long id)
    {
        apiUser = Conexion.getEmpleadoInterface();
        Call<List<Empleado>> call = apiUser.findUserId(id);

        call.enqueue(new Callback<List<Empleado>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<Empleado>> call, Response<List<Empleado>> response) {
                if (!response.isSuccessful())
                {
                    Toast.makeText(EmpleadoConsultar.this, "Erro al mostrar usuario", Toast.LENGTH_SHORT).show();
                    Log.e("Response err: ",response.message());
                    return;
                }
                empleado = response.body();
                empleado.forEach(p -> {
                showNombre.setText(p.getNombre().toString());
                showEmail.setText(p.getEmail().toString());
                });
            }

            @Override
            public void onFailure(Call<List<Empleado>> call, Throwable t) {
                Toast.makeText(EmpleadoConsultar.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("Throw err: ",t.getMessage());
            }
        });
    }

}
