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

public class EmpleadoIngresar extends AppCompatActivity
{

    API apiUser;
    EditText edtId;
    TextView tvsendMessage;
    TextView tvEmail;
    TextView tvPassword;
    Button btnBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empleado_eliminar);


        Intent intent = getIntent();
        intent.getAction();


        edtId=findViewById(R.id.edtId);

        tvEmail=findViewById(R.id.email);
        tvPassword=findViewById(R.id.password);
        btnBuscar = findViewById(R.id.btnEliminar);

        // se genera el evento del boton buscar producto

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // le pasamos al metodo el id que capturamos del usuario
                //find(edtId.getText().toString());
                findUserById(Long.parseLong(edtId.getText().toString()));

                /* Despues de ejecutado el metodo agregar hacer que se direccione al Main Activity
                 */
                Intent intent = new Intent(EmpleadoIngresar.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void findUserById(Long id)
    {

        apiUser = Conexion.getEmpleadoInterface(); // hacer la conexion

        // hacer la llamada http
        //Call<Empleado> call = api.findById(id);

        Call<Empleado> call1 = apiUser.DeleteById(id);

        call1.enqueue(new Callback<Empleado>() {
            @Override
            public void onResponse(Call<Empleado> call, Response<Empleado> response) {
                try {
                    if(response.isSuccessful())
                    {
                        // se debe capturar los datos de respuesta en el objeto empleado
                        Empleado empleado = response.body();
                        /* asignar a cada uno de los controles los datos de cada uno
                         * de los datos obtenidos de la api*/
                        Toast.makeText(EmpleadoIngresar.this, "Usuario eliminado", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e) {
                    Toast.makeText(EmpleadoIngresar.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Empleado> call, Throwable t) {
                Toast.makeText(EmpleadoIngresar.this, "Error de conexion", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
