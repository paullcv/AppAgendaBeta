package com.example.agendabeta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ContactoMostar extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ContactoAdaptador contactoAdaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto_mostar);

        recyclerView = (RecyclerView) findViewById(R.id.reciclerViewContacto);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        AgendaBD agendaBD = new AgendaBD(getApplicationContext());


        contactoAdaptador = new ContactoAdaptador(agendaBD.mostrarContactos());
        recyclerView.setAdapter(contactoAdaptador);

    }

}