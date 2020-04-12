package com.nmhung.coreanroid.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import com.google.gson.Gson;
import com.nmhung.coreanroid.exception.GetExtraException;

public class BaseActivity  extends AppCompatActivity {

    protected int layoutResID;
    protected Gson gson;
    TextView txtView;
    public BaseActivity(@LayoutRes int layoutResID) {
        this.layoutResID = layoutResID;
        gson = new Gson();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(this.layoutResID);

    }

    protected void startActivity(Class<? extends Activity> classActivity, Pair<String, Object>... pairs) {
        Intent intent = new Intent(this, classActivity);
        for (Pair<String, Object> pair : pairs) {
            String json = this.gson.toJson(pair.second);
            intent.putExtra(pair.first, json);
        }
        startActivity(intent);
    }

    protected <T> T getObjectExtra(String key, Class<T> tClass) {
        String json = this.getIntent().getStringExtra(key);
        if (json == null) {
            throw new NullPointerException("Get String Extra Null Pointer Exception");
        }
        try {
            T rs = this.gson.fromJson(json, tClass);
            return rs;
        } catch (Exception e) {
            throw new GetExtraException();
        }

    }
}
