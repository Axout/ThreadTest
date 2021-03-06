package ru.axout.threadtest;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // определяем объект Runnable
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        // получаем текущее время
                        final String currentTime = getFormatDate();
                        // отображаем в текстовом поле
                        textView.post(new Runnable() {
                            @Override
                            public void run() {
                                textView.setText(currentTime);
                            }
                        });
                    }
                };
                // определяем новый поток
                Thread thread = new Thread(runnable);
                // запускаем новый поток
                thread.start();
            }
        });
    }

    // Форматирование даты под следующий вид: "hh:mm:ss"
    private String getFormatDate() {
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("hh:mm:ss");
        return formatForDateNow.format(dateNow);
    }
}