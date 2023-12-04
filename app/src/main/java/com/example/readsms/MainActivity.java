package com.example.readsms;

import static com.example.readsms.MySmsReceive.message;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    public static TextToSpeech textToSpeech;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.RECEIVE_SMS) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.RECEIVE_SMS,
                    android.Manifest.permission.READ_SMS},1);
            return;
        }
        
        // Khởi tạo TextToSpeech
        textToSpeech = new TextToSpeech(getApplicationContext(), status -> {
            if (status == TextToSpeech.SUCCESS) {
                int result = textToSpeech.setLanguage(Locale.US);
                Toast.makeText(MainActivity.this, "Read OK", Toast.LENGTH_SHORT).show();
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(MainActivity.this, "Ngôn ngữ không được hỗ trợ", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(MainActivity.this, "Không thể khởi động Text-to-Speech", Toast.LENGTH_SHORT).show();
            }
        });
    
    }
    
    
    @Override
    protected void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }
    

}
