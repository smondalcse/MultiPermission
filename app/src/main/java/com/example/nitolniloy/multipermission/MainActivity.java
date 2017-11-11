package com.example.nitolniloy.multipermission;


import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

// Import permission  files.
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.GET_ACCOUNTS;
import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.SEND_SMS;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {

    Button button;
    public static final int RequestPermissionCode = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Show all the permission first
        RequestMultiplePermission();

        // Assigning ID to button.
        button = (Button)findViewById(R.id.button1);

        // Adding Click listener to Button.
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Adding if condition inside button.

                // If All permission is enabled successfully then this block will execute.
                if(CheckingPermissionIsEnabledOrNot())
                {
                    Toast.makeText(MainActivity.this, "All Permissions Granted Successfully", Toast.LENGTH_LONG).show();
                }

                // If, If permission is not enabled then else condition will execute.
                else {
                    //Calling method to enable permission.
                    RequestMultiplePermission();
                }
            }
        });

    }

    //Permission function starts from here
    private void RequestMultiplePermission() {

        // Creating String Array with Permissions.
        ActivityCompat.requestPermissions(MainActivity.this, new String[]
                {
                        CAMERA,
                        RECORD_AUDIO,
                        SEND_SMS,
                        GET_ACCOUNTS,
                        ACCESS_FINE_LOCATION,
                        WRITE_EXTERNAL_STORAGE
                }, RequestPermissionCode);

    }

    // Calling override method.
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {

            case RequestPermissionCode:

                if (grantResults.length > 0) {

                    boolean CameraPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean RecordAudioPermission = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean SendSMSPermission = grantResults[2] == PackageManager.PERMISSION_GRANTED;
                    boolean GetAccountsPermission = grantResults[3] == PackageManager.PERMISSION_GRANTED;
                    boolean GetLocationPermission = grantResults[4] == PackageManager.PERMISSION_GRANTED;
                    boolean GetExternalStorage = grantResults[5] == PackageManager.PERMISSION_GRANTED;

                    if (CameraPermission && RecordAudioPermission && SendSMSPermission && GetAccountsPermission &&
                            GetLocationPermission && GetExternalStorage) {

                        Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(MainActivity.this,"Permission Denied",Toast.LENGTH_LONG).show();

                    }
                }

                break;
        }
    }

    // Checking permission is enabled or not using function starts from here.
    public boolean CheckingPermissionIsEnabledOrNot() {

        int FirstPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA);
        int SecondPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), RECORD_AUDIO);
        int ThirdPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), SEND_SMS);
        int ForthPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), GET_ACCOUNTS);
        int FifthPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_FINE_LOCATION);
        int SixthPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);

        return FirstPermissionResult == PackageManager.PERMISSION_GRANTED &&
                SecondPermissionResult == PackageManager.PERMISSION_GRANTED &&
                ThirdPermissionResult == PackageManager.PERMISSION_GRANTED &&
                ForthPermissionResult == PackageManager.PERMISSION_GRANTED &&
                FifthPermissionResult == PackageManager.PERMISSION_GRANTED &&
                SixthPermissionResult == PackageManager.PERMISSION_GRANTED;
    }

}