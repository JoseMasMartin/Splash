package splashjosemas.splash;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.nio.channels.Channel;
import java.util.PrimitiveIterator;

public class HomeActivity extends AppCompatActivity {


    Button botonUno;

    private final static String CHANNEL_ID = "NOTIFICACION";
    private final static int NOTIFICATION_ID =0;
    private PendingIntent pendingIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        botonUno = findViewById(R.id.btn_boton);

        botonUno.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDialog();
                }
            });



    }

    public void showToast()
    {
        Toast.makeText(HomeActivity.this, "No tienes cupones disponibles", Toast.LENGTH_SHORT).show();
    }
    public void showDialog(){
        new AlertDialog.Builder(this)
                .setTitle("Sin Cupones")
                .setMessage("No tienes cupones, ¿deseas agregar cupones")
                .setPositiveButton("si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(HomeActivity.this,CuponesActivity.class);
                        startActivity(i);
                    }
                }).setNegativeButton("No", null).show();

    }

public void  launchNotification()
{
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
    {
        CharSequence name = "Notificacion";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }
    NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
    builder.setSmallIcon(R.drawable.demosplash);
    builder.setContentTitle("Notificacion android");
    builder.setContentText("Quieres Mas Cupones");
    builder.setColor(Color.RED);
    builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
    builder.setLights(Color.YELLOW,1000,1000);
    builder.setVibrate(new long[]{1000,1000,1000,1000,1000});
    builder.setDefaults(Notification.DEFAULT_SOUND);

    NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
    notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());
}

}