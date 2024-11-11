package es.studium.myzodiac;// SecondaryActivity.java
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class SecondaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        TextView txtAge = findViewById(R.id.txtAge);
        TextView txtHoroscope = findViewById(R.id.txtHoroscope);
        TextView txtError = findViewById(R.id.txtError);
        ImageView imgError = findViewById(R.id.imageView);
        ImageView imgZodiac = findViewById(R.id.imgZodiac);


        //cogemos la fecha de nuestro Main
        Intent intent = getIntent();
        int dia = intent.getIntExtra("Dia", 1);
        int mes = intent.getIntExtra("Mes", 1);
        int ano = intent.getIntExtra("Año", 2000);

        //comprobamos la fecha
        if (isValidDate(dia, mes, ano)) {
            //calcula la edad y muestra la información
            int age = calcularEdad(dia, mes, ano);
            String horoscopo = obtenerHoroscopo(mes, dia);

            txtAge.setText("Edad: " + age + " años");
            txtHoroscope.setText("Horóscopo: " + horoscopo);


            int zodiacImageResource = obtenerImagenHoroscopo(horoscopo);
            imgZodiac.setImageResource(zodiacImageResource);
        } else {
            //muestra el mensaje de error si la fecha no es válida

            txtError.setVisibility(View.VISIBLE);
            imgError.setVisibility(View.VISIBLE);
            txtError.setText(getString(R.string.fecha_error));
        }
    }


    private boolean isValidDate(int day, int month, int ano) {
        Calendar currentDate = Calendar.getInstance();
        int anoActual = currentDate.get(Calendar.YEAR);
        int mesActual = currentDate.get(Calendar.MONTH) + 1;
        int diaActual = currentDate.get(Calendar.DAY_OF_MONTH);

        //la fecha de nacimiento no debe ser posterior a la fecha actual
        return ano <= anoActual &&
                (ano < anoActual || (month <= mesActual && day <= diaActual));
    }



    private int calcularEdad(int dia, int mes, int ano) {
        // Obtiene la fecha actual
        Calendar fechaActual = Calendar.getInstance();
        int anoActual = fechaActual.get(Calendar.YEAR);
        int mesActual = fechaActual.get(Calendar.MONTH) + 1;
        int diaActual = fechaActual.get(Calendar.DAY_OF_MONTH);

        int edad = anoActual - ano;

        if (mesActual < mes || (mesActual == mes && diaActual < dia)) {
            edad--;
        }

        return edad;
    }

    private String obtenerHoroscopo(int mes, int dia) {
        String horoscopo = "";

        switch (mes) {
            case 1:
                horoscopo = (dia <= 19) ? "Capricornio" : "Acuario";
                break;
            case 2:
                horoscopo = (dia <= 18) ? "Acuario" : "Piscis";
                break;
            case 3:
                horoscopo = (dia <= 20) ? "Piscis" : "Aries";
                break;

            case 4:
                horoscopo = (dia <= 19) ? "Aries" : "Tauro";
                break;
            case 5:
                horoscopo = (dia <= 20) ? "Tauro" : "Géminis";
                break;
            case 6:
                horoscopo = (dia <= 20) ? "Géminis" : "Cáncer";
                break;
            case 7:
                horoscopo = (dia <= 22) ? "Cáncer" : "Leo";
                break;
            case 8:
                horoscopo = (dia <= 22) ? "Leo" : "Virgo";
                break;
            case 9:
                horoscopo = (dia <= 22) ? "Virgo" : "Libra";
                break;
            case 10:
                horoscopo = (dia <= 22) ? "Libra" : "Escorpio";
                break;
            case 11:
                horoscopo = (dia <= 21) ? "Escorpio" : "Sagitario";
                break;
            case 12:
                horoscopo = (dia <= 21 ) ? "Sagitario" : "Capricornio";
                break;
        }

        return horoscopo;
    }
    private int obtenerImagenHoroscopo(String horoscopo) {
        switch (horoscopo) {
            case "Aries":
                return R.drawable.aries;
            case "Tauro":
                return R.drawable.tauro;
            case "Escorpio":
                return R.drawable.escorpio;
            case "Virgo":
                return R.drawable.virgo;
            case "Sagitario":
                return R.drawable.sagitario;
            case "Capricornio":
                return R.drawable.capricornio;
            case "Libra":
                return R.drawable.libra;
            case "Piscis":
                return R.drawable.piscis;
            case "Leo":
                return R.drawable.leo;
            case "Géminis":
                return R.drawable.geminis;
            case "Cáncer":
                return R.drawable.cancer;
            case "Acuario":
                return  R.drawable.acuario;

            default:
                return R.drawable.error;
        }
    }
}