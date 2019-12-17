package com.example.hw22;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()) {

                    String netType = "";
                    if(networkInfo.getTypeName().matches("WIFI")){
                        netType  = "當前網路狀態:WIFI";
                    }
                    if(networkInfo.getTypeName().matches("MOBILE")){
                        netType = "當前網路狀態:基地台";
                    }
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("目前有網路")
                                .setMessage(netType)
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .show();
                }
                else {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("目前沒有網路")
                            .setMessage("是否要前往設定")
                            .setPositiveButton("設定WIFI", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    startActivity(new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS));
                                    dialog.cancel();
                                }
                            })
                            .setNegativeButton("設定行動網路", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    startActivity(new Intent(Settings.ACTION_NETWORK_OPERATOR_SETTINGS));
                                    dialog.cancel();
                                }
                            })
                            .setNeutralButton("不用設定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .show();        
                }
            }
        });
    }
}
