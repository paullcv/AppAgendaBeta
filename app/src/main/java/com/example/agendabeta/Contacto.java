package com.example.agendabeta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Contacto extends AppCompatActivity {

    EditText etIdContacto;
    EditText etNombreContacto;
    EditText etCorreoContacto;
    EditText etTelefonoContacto;
    EditText etDireccionContacto;

    Button btnAgregarContacto, btnMostrarContacto, btnBuscarContacto, btnEditarContacto, btnEliminarContacto, btnLimpiar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        //Asociamos nuestros elementos con los del formulario (vista)

        etIdContacto = findViewById(R.id.editTextId);
        etNombreContacto = findViewById(R.id.editTextNombreContacto);
        etCorreoContacto = findViewById(R.id.editTextCorreoContacto);
        etTelefonoContacto = findViewById(R.id.editTextTelefonoContacto);
        etDireccionContacto = findViewById(R.id.editTextDireccionContacto);

        btnAgregarContacto = findViewById(R.id.btnAddContacto);
        btnMostrarContacto = findViewById(R.id.btnShowContacto);
        btnBuscarContacto = findViewById(R.id.btnBuscarContacto);
        btnEditarContacto = findViewById(R.id.btnEditContacto);
        btnEliminarContacto = findViewById(R.id.btnDeleteContacto);
        btnLimpiar = findViewById(R.id.btnLimpiar);

        //CREAMOS INSTACIA DE NUESTRA BD
        final AgendaBD agendaBD = new AgendaBD(getApplicationContext());

        //GENERAR EVENTO DE AGREGAR CONTACTO
        btnAgregarContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //AGREGAR LOS ATRIBUTOS A NUESTRO OBJETO DE LA BD OBTENIDOS EN CADA EDIT TEXT

                agendaBD.agregarContacto(Integer.valueOf(etIdContacto.getText().toString()),etNombreContacto.getText().toString(), etCorreoContacto.getText().toString(), Integer.valueOf(etTelefonoContacto.getText().toString()), etDireccionContacto.getText().toString());


                    Toast.makeText(getApplicationContext(), "SE AGREGO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
            }
        });

        //GENERAR EVENTO DE MOSTRAR CONTACTOS
        btnMostrarContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mostrarContactos = new Intent(getApplicationContext(),ContactoMostar.class);
                startActivity(mostrarContactos);
            }
        });

        //GENERAR EVENTO DE BUSCAR CONTACTO
        btnBuscarContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              ContactoModelo contacto = new ContactoModelo();
              String textid = etIdContacto.getText().toString();
              int id = Integer.valueOf(textid);
              //agendaBD.buscarContacto(contacto, Integer.valueOf(etIdContacto.getText().toString()));
                agendaBD.buscarContacto(contacto, id);
              etNombreContacto.setText(contacto.getNombre());
              etCorreoContacto.setText(contacto.getCorreo());
              etTelefonoContacto.setText(String.valueOf(contacto.getTelefono()));
              etDireccionContacto.setText(contacto.getDireccion());

            }
        });

        //GENERAR EVENTO DE EDITAR CONTACTO
        btnEditarContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agendaBD.editarContacto(Integer.valueOf(etIdContacto.getText().toString()), etNombreContacto.getText().toString(), etCorreoContacto.getText().toString(), Integer.valueOf(etTelefonoContacto.getText().toString()),etDireccionContacto.getText().toString());
                Toast.makeText(getApplicationContext(), "Los Datos se Actualizaron Correctamente", Toast.LENGTH_SHORT).show();
            }
        });


        //GENERAR EVENTO DE ELIMINAR CONTACTO
        btnEliminarContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agendaBD.eliminarContacto(Integer.valueOf(etIdContacto.getText().toString()));
                Toast.makeText(getApplicationContext(), "Los Datos se Eliminaron Correctamente", Toast.LENGTH_SHORT).show();
            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiar();
            }
        });
    }

    private  void limpiar(){
        etIdContacto.setText("");
        etNombreContacto.setText("");
        etCorreoContacto.setText("");
        etTelefonoContacto.setText("");
        etDireccionContacto.setText("");
    }
}