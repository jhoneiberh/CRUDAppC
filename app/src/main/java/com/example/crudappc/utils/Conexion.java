package com.example.crudappc.utils;

import com.example.crudappc.EmpleadoIngresar;
import com.example.crudappc.interfaces.API;

public class Conexion
{
    private static final String URL_001 = "http://192.168.0.50:8080";

    public static API getEmpleadoInterface()
    {
        //return Cliente.getCliente(URL_001).create(EmpleadoInterface.class);
        return Cliente.getCliente(URL_001).create(API.class);
    }
}
