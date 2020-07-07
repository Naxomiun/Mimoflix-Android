package com.nramos.mimoflix.ui.settings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.CompoundButton;

import com.nramos.mimoflix.R;
import com.nramos.mimoflix.databinding.ActivitySettingsBinding;
import com.nramos.mimoflix.utils.StyleManager;

import java.util.Objects;

public class SettingsActivity extends AppCompatActivity {

    private ActivitySettingsBinding binding;
    private StyleManager styleManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setMode();
        super.onCreate(savedInstanceState);
        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        binding.setLifecycleOwner(this);
        setContentView(binding.getRoot());
        setSupportActionBar(binding.settingsToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        checkMode();
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
             onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Log.e("SETTINGS", "BACK PRESSED");
        if(styleManager.shouldRecreate()) {
            setResult(RESULT_OK);
            Log.e("SETTINGS", "OK");
        }
        else {
            setResult(RESULT_CANCELED);
            Log.e("SETTINGS", "CANCELED");
        }
        super.onBackPressed();
    }

    private void checkMode() {
        binding.swNightMode.setChecked(styleManager.getMode());
        binding.swNightMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            styleManager.saveMode(isChecked);
            styleManager.recreate(true);
            this.recreate();
        });
    }

    private void setMode() {
        styleManager = new StyleManager(this);
        if (styleManager.getMode()) {
            setTheme(R.style.Mimoflix_Dark);
        } else {
            setTheme(R.style.Mimoflix_Light);
        }
    }

}