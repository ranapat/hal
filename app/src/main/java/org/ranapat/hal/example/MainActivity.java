package org.ranapat.hal.example;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.ranapat.hal.Hal;
import org.ranapat.hal.HalException;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("Example ", Hal.parse("https://domain.com/{key}", new HashMap<String, Object>() {{
            put("key", "value");
        }}));
        Log.d("Example ", Hal.parse("https://domain.com/{?key}", new HashMap<String, Object>() {{
            put("key", "value");
        }}));
        Log.d("Example ", Hal.parse("https://domain.com/{?key1,key2}", new HashMap<String, Object>() {{
            put("key1", "value1");
            put("key2", "value2");
        }}));
        Log.d("Example ", Hal.parse("https://domain.com/{?key}", new HashMap<String, Object>() {{
            put("_key", "value");
        }}));
        Log.d("Example ", Hal.parse("https://domain.com/{@key}", new HashMap<String, Object>() {{
            put("key", "value");
        }}));
        Log.d("Example ", Hal.parse("https://domain.com/{@key}", new HashMap<String, Object>() {{
            put("_key", "value");
        }}));
        try {
            Log.d("Example ", Hal.parse("https://domain.com/{key}", new HashMap<String, Object>() {{
                put("_key", "value");
            }}));
        } catch (final HalException e) {
            e.printStackTrace();
        }
        Log.d("Example ", Hal.safe("https://domain.com/{key}", new HashMap<String, Object>() {{
            put("_key", "value");
        }}));
    }
}
