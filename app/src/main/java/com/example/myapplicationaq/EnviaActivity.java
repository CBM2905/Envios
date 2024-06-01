package com.example.myapplicationaq;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.Calendar;

public class EnviaActivity extends AppCompatActivity {

    private CardView cardView1;
    private CardView cardView2;
    private CardView cardView3;
    private CardView cardView4;
    private CardView cardView5;
    private CardView cardView6;
    private TextView textViewDate;
    private TextView textViewDateCard4;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envia);

        // Obtén referencias a los CardView, botones y elementos de la vista
        cardView1 = findViewById(R.id.cardView1);
        cardView2 = findViewById(R.id.cardView2);
        cardView3 = findViewById(R.id.cardView3);
        cardView4 = findViewById(R.id.cardView4);
        cardView5 = findViewById(R.id.cardView5);
        cardView6 = findViewById(R.id.cardView6);

        Button button = findViewById(R.id.button);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button buttonCalendar = findViewById(R.id.button_calendar);
        Button buttonCalendarCard4 = findViewById(R.id.button_calendar_card4);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);

        textViewDate = findViewById(R.id.textView_date);
        textViewDateCard4 = findViewById(R.id.textView_date_card4);
        spinner = findViewById(R.id.spinner);

        // Asegúrate de que al iniciar la aplicación, cardView1 esté visible y cardView2, cardView3, cardView4 y cardView5 estén ocultos
        cardView1.setVisibility(View.VISIBLE);
        cardView2.setVisibility(View.GONE);
        cardView3.setVisibility(View.GONE);
        cardView4.setVisibility(View.GONE);
        cardView5.setVisibility(View.GONE);
        cardView6.setVisibility(View.GONE);

        // Configura el listener de clic para el botón
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Oculta el CardView1 y muestra el CardView2 cuando se hace clic en el botón, y viceversa
                if (cardView1.getVisibility() == View.VISIBLE) {
                    cardView1.animate().alpha(0.0f).setDuration(300).withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            cardView1.setVisibility(View.GONE);
                            cardView2.setAlpha(0.0f);
                            cardView2.setVisibility(View.VISIBLE);
                            cardView2.animate().alpha(1.0f).setDuration(300);
                        }
                    });
                } else {
                    cardView2.animate().alpha(0.0f).setDuration(300).withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            cardView2.setVisibility(View.GONE);
                            cardView1.setAlpha(0.0f);
                            cardView1.setVisibility(View.VISIBLE);
                            cardView1.animate().alpha(1.0f).setDuration(300);
                        }
                    });
                }
            }
        });

        // Configura el listener de clic para el button2
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Oculta el CardView2 y muestra el CardView3 cuando se hace clic en el botón
                cardView2.setVisibility(View.GONE);
                cardView3.setVisibility(View.VISIBLE);
            }
        });

        // Configura el listener de clic para el button3
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Oculta el CardView3 y muestra el CardView4 cuando se hace clic en el botón
                cardView3.setVisibility(View.GONE);
                cardView6.setVisibility(View.VISIBLE);
            }
        });

        // Configura el listener de clic para el botón de calendario en CardView2
        buttonCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Muestra un DatePickerDialog
                showDatePickerDialog(textViewDate);
            }
        });

        // Configura el listener de clic para el botón de calendario en CardView4
        buttonCalendarCard4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Muestra un DatePickerDialog
                showDatePickerDialog(textViewDateCard4);
            }
        });

        // Configura el Spinner (dropdown menu)


        // Configura el listener de clic para el button4
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtiene el índice seleccionado del Spinner
                int selectedItemPosition = spinner.getSelectedItemPosition();
                // Obtiene el valor seleccionado del Spinner
                String selectedItem = spinner.getSelectedItem().toString();
                // Comprueba si el elemento seleccionado es "Elemento 2"
                if (selectedItem.equals("TARJETA DE CREDITO")) {
                    // Oculta las CardView anteriores y muestra cardView5
                    cardView1.setVisibility(View.GONE);
                    cardView2.setVisibility(View.GONE);
                    cardView3.setVisibility(View.GONE);
                    cardView4.setVisibility(View.GONE);
                    cardView5.setVisibility(View.VISIBLE);
                }else{
                    cardView1.setVisibility(View.GONE);
                    cardView2.setVisibility(View.GONE);
                    cardView3.setVisibility(View.GONE);
                    cardView4.setVisibility(View.GONE);
                    cardView5.setVisibility(View.GONE);
                    cardView6.setVisibility(View.VISIBLE);
                }
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Oculta el CardView5 y muestra el CardView6 cuando se hace clic en el botón
                cardView5.setVisibility(View.GONE);
                cardView6.setVisibility(View.VISIBLE);
            }
        });
    }

    // Método para mostrar el DatePickerDialog
    private void showDatePickerDialog(final TextView textView) {
        // Obtén la fecha actual
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        // Crea y muestra el DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                // Maneja la fecha seleccionada, por ejemplo, mostrarla en el TextView
                String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                textView.setText(selectedDate);
            }
        }, year, month, dayOfMonth);

        datePickerDialog.show();
    }

    @Override
    public void onBackPressed() {
        if (cardView2.getVisibility() == View.VISIBLE) {
            // Si el CardView2 está visible, mostrar el CardView1 y ocultar el CardView2
            cardView2.setVisibility(View.GONE);
            cardView1.setVisibility(View.VISIBLE);
        } else if (cardView3.getVisibility() == View.VISIBLE) {
            // Si el CardView3 está visible, mostrar el CardView2 y ocultar el CardView3
            cardView3.setVisibility(View.GONE);
            cardView2.setVisibility(View.VISIBLE);
        } else if (cardView4.getVisibility() == View.VISIBLE) {
            // Si el CardView4 está visible, mostrar el CardView3 y ocultar el CardView4
            cardView4.setVisibility(View.GONE);
            cardView3.setVisibility(View.VISIBLE);
        } else if (cardView5.getVisibility() == View.VISIBLE) {
            // Si el CardView5 está visible, mostrar el CardView4 y ocultar el CardView5
            cardView5.setVisibility(View.GONE);
            cardView4.setVisibility(View.VISIBLE);
        } else if (cardView6.getVisibility() == View.VISIBLE) {
            // Si el CardView6 está visible, mostrar el CardView1 y ocultar el CardView6
            cardView6.setVisibility(View.GONE);
            cardView1.setVisibility(View.VISIBLE);
        } else {
            // Si ninguna de las condiciones anteriores se cumple, llama al comportamiento predeterminado
            super.onBackPressed();
        }
    }

}