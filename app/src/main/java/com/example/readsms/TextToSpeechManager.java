package com.example.readsms;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import java.util.Locale;

public class TextToSpeechManager implements TextToSpeech.OnInitListener {
    
    private TextToSpeech textToSpeech;
    private boolean isInitialized = false;
    
    public TextToSpeechManager(Context context) {
        textToSpeech = new TextToSpeech(context, this);
    }
    
    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = textToSpeech.setLanguage(Locale.getDefault());
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                // Xử lý khi ngôn ngữ không được hỗ trợ
            } else {
                isInitialized = true;
            }
        } else {
            // Xử lý khi không khởi động được Text-to-Speech
        }
    }
    
    public void speak(String text) {
        if (isInitialized) {
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }
    
    public void shutdown() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }
}

