package com.example.crudappc.interfaces;

import com.example.crudappc.model.Empleado;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface API
{
    // se declaran todas las operaciones que se quieren consumir

    @GET("/consultar/{id}")
    public Call<Empleado> findById(@Path("id") String id);

    @DELETE("/user/{id}")
    public Call<Empleado> DeleteById(@Path("id") Long id);

    @POST("/guardar")
    public Call<Empleado> addEmpleado(@Body Empleado empleado);
}
