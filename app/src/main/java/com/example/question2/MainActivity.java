package com.example.question2;



import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.bottomsheet.BottomSheetDialog;



public class MainActivity extends AppCompatActivity {

    private Switch switchSound, switchVibration, switchLED, switchBanners, switchContent, switchLockScreen;
    private Button btnSave;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        switchSound = findViewById(R.id.switchSound);
        switchVibration = findViewById(R.id.switchVibration);
        switchLED = findViewById(R.id.switchLED);
        switchBanners = findViewById(R.id.switchBanners);
        switchContent = findViewById(R.id.switchContent);
        switchLockScreen = findViewById(R.id.switchLockScreen);
        btnSave = findViewById(R.id.btnSave);


        preferences = getSharedPreferences("NotificationPrefs", MODE_PRIVATE);
        loadPreferences();


        btnSave.setOnClickListener(view -> showBottomSheet());
    }

    private void loadPreferences() {
        switchSound.setChecked(preferences.getBoolean("sound", false));
        switchVibration.setChecked(preferences.getBoolean("vibration", false));
        switchLED.setChecked(preferences.getBoolean("led", false));
        switchBanners.setChecked(preferences.getBoolean("banners", false));
        switchContent.setChecked(preferences.getBoolean("content", false));
        switchLockScreen.setChecked(preferences.getBoolean("lock_screen", false));
    }

    private void savePreferences() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("sound", switchSound.isChecked());
        editor.putBoolean("vibration", switchVibration.isChecked());
        editor.putBoolean("led", switchLED.isChecked());
        editor.putBoolean("banners", switchBanners.isChecked());
        editor.putBoolean("content", switchContent.isChecked());
        editor.putBoolean("lock_screen", switchLockScreen.isChecked());
        editor.apply();

        Toast.makeText(this, "Preferences Saved!", Toast.LENGTH_SHORT).show();
    }

    private void showBottomSheet() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View bottomSheetView = getLayoutInflater().inflate(R.layout.bottom_sheet_confirmation, null);
        bottomSheetDialog.setContentView(bottomSheetView);

        Button btnConfirm = bottomSheetView.findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(v -> {
            savePreferences();
            bottomSheetDialog.dismiss();
        });

        bottomSheetDialog.show();
    }
}
