package com.texttospeech;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextToSpeech temp;
    EditText text;
    Button speak;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (EditText) findViewById(R.id.editText);
        speak = (Button) findViewById(R.id.button);
        temp = new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    temp.setLanguage(Locale.US);
                }
            }
        });
        speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String speech = text.getText().toString();
                temp.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
    }
    public void onPause(){
        if(temp!=null){
            temp.stop();
            temp.shutdown();
        }
        super.onPause();
    }
}
