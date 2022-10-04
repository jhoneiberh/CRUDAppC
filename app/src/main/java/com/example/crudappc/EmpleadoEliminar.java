package com.example.crudappc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crudappc.interfaces.API;
import com.example.crudappc.model.Empleado;
import com.example.crudappc.utils.Conexion;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmpleadoEliminar extends AppCompatActivity
{

    API apiUser;
    EditText edtId;
    TextView tvsendMessage;
    TextView tvEmail;
    TextView tvPassword;
    Button btnEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empleado_eliminar);


        Intent intent = getIntent();
        intent.getAction();


        edtId=findViewById(R.id.edtId);
        btnEliminar = findViewById(R.id.btnDeleteEmpleado);

        // se genera el evento del boton buscar producto

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // le pasamos al metodo el id que capturamos del usuario
                //find(edtId.getText().toString());
                findUserById(Long.parseLong(edtId.getText().toString()));

                /* Despues de ejecutado el metodo agregar hacer que se direccione al Main Activity
                 */

            }
        });
    }
    private void findUserById(Long id)
    {
        apiUser = Conexion.getEmpleadoInterface(); // hacer la conexion

        // hacer la llamada http
        Call<Empleado> call1 = apiUser.DeleteById(id);

        call1.enqueue(new Callback<Empleado>() {
            @Override
            public void onResponse(Call<Empleado> call, Response<Empleado> response) {
                try {
                    if(response != null)
                    {
                        // se debe capturar los datos de respuesta en el objeto empleado
                        Empleado empleado = response.body();
                        Toast.makeText(EmpleadoEliminar.this, "Se eliminó con éxito", Toast.LENGTH_SHORT).show();
                        /* asignar a cada uno de los controles los datos de cada uno
                         * de los datos obtenidos de la api*/
                    }
                }catch (Exception e) {
                    Toast.makeText(EmpleadoEliminar.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Empleado> call, Throwable t) {
                Toast.makeText(EmpleadoEliminar.this, "", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
