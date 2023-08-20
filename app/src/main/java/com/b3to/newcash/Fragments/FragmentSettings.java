package com.b3to.newcash.Fragments;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.b3to.newcash.Db.RoomDb;
import com.b3to.newcash.MainActivity;
import com.b3to.newcash.R;
import com.b3to.newcash.Tools.Widgets;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.File;
import java.util.List;

import de.raphaelebner.roomdatabasebackup.core.RoomBackup;
import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;

public class FragmentSettings extends Fragment {

    private boolean enableLog;
    private boolean useMaxFileCount;
    private int storageLocation;

    private boolean encryptBackup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        initBlur(view);
        return view;
    }

    private void initBlur(View view) {
        MaterialCardView btnBackup = view.findViewById(R.id.btn_backup);
        MaterialCardView btnRestore = view.findViewById(R.id.btn_restore);

        BlurView blurView = view.findViewById(R.id.blur_settings);

        float radius = 5f;
        View decorView = requireActivity().getWindow().getDecorView();
        ViewGroup rootView = (ViewGroup) decorView.findViewById(android.R.id.content);
        Drawable windowBackground = decorView.getBackground();

        blurView.setupWith(rootView, new RenderScriptBlur(getContext())) // or RenderEffectBlur
                .setFrameClearDrawable(windowBackground) // Optional
                .setBlurRadius(radius);

        btnBackup.setOnClickListener(v -> {
            checkPermission(result -> {
                //setBackupLocation();
                backup();
            });
        });
        btnRestore.setOnClickListener(v -> {
            checkPermission(result -> {
                restore();
            });
        });
    }

    private void checkPermission(Widgets.IExecute listener) {
        Dexter.withContext(getContext()).withPermissions(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        ).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                if (!multiplePermissionsReport.areAllPermissionsGranted()) {
                    Toast.makeText(getContext(), getResources().getString(R.string.permissions_are_required), Toast.LENGTH_LONG).show();
                } else {
                    listener.exec(true);
                }
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).withErrorListener(err -> {
            Toast.makeText(getContext(), err.name(), Toast.LENGTH_SHORT).show();
        }).check();
    }

    private void backup() {
        RoomBackup roomBackup = MainActivity.roomBackup;
        roomBackup.backupLocation(RoomBackup.BACKUP_FILE_LOCATION_CUSTOM_DIALOG);//storageLocation
        roomBackup.backupLocationCustomFile(new File(getContext().getFilesDir() + "/databasebackup/geilesBackup.sqlite3"));
        roomBackup.database(RoomDb.getInstance(MainActivity.requireActivity().getApplicationContext()));
        roomBackup.enableLogDebug(enableLog);
        roomBackup.backupIsEncrypted(encryptBackup);
        roomBackup.customEncryptPassword(MainActivity.SECRET_KEY);
        if (useMaxFileCount) roomBackup.maxFileCount(5);
        roomBackup.onCompleteListener((success, message, exitCode) -> {
            Log.d(TAG, "oncomplete: " + success + ", message: " + message + ", exitCode: " + exitCode);
            if (success) {
                Toast.makeText(getContext(), getResources().getString(R.string.backup_success), Toast.LENGTH_SHORT).show();
                roomBackup.restartApp(new Intent(getContext(), MainActivity.class));
            }
        });
        roomBackup.backup();
    }

    private void restore() {
        RoomBackup roomBackup = MainActivity.roomBackup;
        roomBackup.backupLocation(RoomBackup.BACKUP_FILE_LOCATION_CUSTOM_DIALOG);//storageLocation
        roomBackup.backupLocationCustomFile(new File(getContext().getFilesDir() + "/databasebackup/geilesBackup.sqlite3"));
        roomBackup.database(RoomDb.getInstance(MainActivity.requireActivity().getApplicationContext()));
        roomBackup.enableLogDebug(enableLog);
        roomBackup.backupIsEncrypted(encryptBackup);
        roomBackup.customEncryptPassword(MainActivity.SECRET_KEY);
        roomBackup.onCompleteListener((success, message, exitCode) -> {
            Log.d(TAG, "oncomplete: " + success + ", message: " + message + ", exitCode: " + exitCode);
            if (success) {
                Toast.makeText(getContext(), getResources().getString(R.string.restore_success), Toast.LENGTH_SHORT).show();
                roomBackup.restartApp(new Intent(getContext(), MainActivity.class));
            }
        });
        roomBackup.restore();
    }


    void setBackupLocation() {

        String SHARED_PREFS = "sampleBackup";
        final String spEncryptBackup = "encryptBackup";
        final String spStorageLocation = "storageLocation";
        final String spEnableLog = "enableLog";
        final String spUseMaxFileCount = "useMaxFileCount";
        final SharedPreferences sharedPreferences = getContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        encryptBackup = sharedPreferences.getBoolean(spEncryptBackup, true);
        storageLocation = sharedPreferences.getInt(spStorageLocation, 1);
        enableLog = sharedPreferences.getBoolean(spEnableLog, true);
        useMaxFileCount = sharedPreferences.getBoolean(spUseMaxFileCount, false);

        final String[] multiItems = new String[]{"Encrypt Backup", "enable Log", "use maxFileCount = 5"};
        final boolean[] checkedItems = new boolean[]{encryptBackup, enableLog, useMaxFileCount};
        final String[] storageItems = new String[]{"Internal", "External", "Custom Dialog", "Custom File"};

        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(getContext());
        materialAlertDialogBuilder.setTitle("Change Storage");
        materialAlertDialogBuilder.setPositiveButton("Ok", null);

        materialAlertDialogBuilder.setSingleChoiceItems(storageItems, storageLocation - 1, (dialog, which) -> {
            switch (which) {
                case 0:
                    storageLocation = RoomBackup.BACKUP_FILE_LOCATION_INTERNAL;
                    sharedPreferences.edit().putInt(spStorageLocation, storageLocation).apply();
                    break;
                case 1:
                    storageLocation = RoomBackup.BACKUP_FILE_LOCATION_EXTERNAL;
                    sharedPreferences.edit().putInt(spStorageLocation, storageLocation).apply();
                    break;
                case 2:
                    storageLocation = RoomBackup.BACKUP_FILE_LOCATION_CUSTOM_DIALOG;
                    sharedPreferences.edit().putInt(spStorageLocation, storageLocation).apply();
                    break;
                case 3:
                    storageLocation = RoomBackup.BACKUP_FILE_LOCATION_CUSTOM_FILE;
                    sharedPreferences.edit().putInt(spStorageLocation, storageLocation).apply();
                    break;
            }
        });
        materialAlertDialogBuilder.show();
    }
}