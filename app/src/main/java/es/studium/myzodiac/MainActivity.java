package es.studium.myzodiac;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    DatePicker datePicker;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //actividad en primer plano

        datePicker = findViewById(R.id.datePicker);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //obtener la fecha seleccionada en el DatePicker
                int diaSeleccionado = datePicker.getDayOfMonth();
                int mesSeleccionado = datePicker.getMonth();
                int anoSeleccionado = datePicker.getYear();

                //obtener la fecha actual para comparar
                Calendar fechaActual = Calendar.getInstance();
                int diaActual = fechaActual.get(Calendar.DAY_OF_MONTH);
                int mesActual = fechaActual.get(Calendar.MONTH);
                int anoActual = fechaActual.get(Calendar.YEAR);

                //comparar la fecha seleccionada con la fecha actual
                if (diaSeleccionado == diaActual && mesSeleccionado == mesActual && anoSeleccionado == anoActual) {
                    //mensaje de error si no se ha cambiado la fecha
                    Toast.makeText(MainActivity.this, "Por favor, selecciona una fecha de nacimiento.", Toast.LENGTH_SHORT).show();
                } else {
                    //pasamos a la segunda actividad
                    Intent intent = new Intent(MainActivity.this, SecondaryActivity.class);
                    intent.putExtra("Dia", diaSeleccionado);
                    intent.putExtra("Mes", mesSeleccionado + 1); //el mes comienza desde 0
                    intent.putExtra("Año", anoSeleccionado);
                    startActivity(intent);
                }
            }
        });
    }
}