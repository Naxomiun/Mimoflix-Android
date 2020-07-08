package com.nramos.mimoflix.ui.settings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.nramos.mimoflix.R;
import com.nramos.mimoflix.databinding.ActivitySettingsBinding;
import com.nramos.mimoflix.utils.StyleManager;

import java.util.Objects;

import static com.nramos.mimoflix.utils.Constants.RESULT_LOGOUT;

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
        if(styleManager.shouldRecreate())
            setResult(RESULT_OK);
        else
            setResult(RESULT_CANCELED);
        super.onBackPressed();
    }

    private void checkMode() {
        binding.tvLogout.setVisibility(GoogleSignIn.getLastSignedInAccount(this) != null ? View.VISIBLE : View.GONE);
        binding.swNightMode.setChecked(styleManager.getMode());
        binding.swNightMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            styleManager.saveMode(isChecked);
            styleManager.recreate(true);
            this.recreate();
        });
        binding.tvLogout.setOnClickListener(view -> {
            logOut();
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

    private void logOut() {
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().requestProfile().build());
        mGoogleSignInClient.signOut().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                setResult(RESULT_LOGOUT);
                SettingsActivity.this.finish();
            }
        });
    }

}