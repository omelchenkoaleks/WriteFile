package com.omelchenkoaleks.writer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private static final String COMMENTS_FILE = "comments.txt";
    private static final String TAG_FILE = "TAG_FILE";

    FileOutputStream fileOutputStream;
    FileInputStream fileInputStream;
    InputStreamReader inputStreamReader;
    BufferedReader stringBuilder;
    TextView tvComments;
    EditText etComments;
    String commentsWriteText;
    String commentRows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvComments = findViewById(R.id.comments_tv);
        etComments = findViewById(R.id.write_text_et);
    }

    // запись в файл
    public void writeFile(View view) {
        // инициализация переменной выходного потока файла для записи данных в файл
        fileOutputStream = null;
        try {
            // производим съем данных из поля ввода
            commentsWriteText = etComments.getText().toString();

            // валидация на пустое поле и запись если поле не пустое
            if (!commentsWriteText.isEmpty()) {

                // MODE_PRIVATE закрывает доступ приложений к данным файла
                fileOutputStream = openFileOutput(COMMENTS_FILE, MODE_PRIVATE);
                // конвертируем в поток байтов
                fileOutputStream.write(commentsWriteText.getBytes());

                // очищаем поле ввода после записи в файл
                etComments.getText().clear();

                // можно уведомить через Toast об успешной записи в файл
                Toast.makeText(this, "saved to file", Toast.LENGTH_SHORT).show();
            } else {
                // можно уведомить через Toast o заполении поля ввода
                Toast.makeText(this, "add information", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException exception) {
            // вывод исключений в Log
            Log.e(TAG_FILE, exception.getMessage(), exception);
        } finally {
            try {
                if (fileOutputStream != null)
                    // закрываем файл
                    fileOutputStream.close();
            } catch (IOException exception) {
                // вывод исключений в Log
                Log.e(TAG_FILE, exception.getMessage(), exception);
            }
        }
    }

}
