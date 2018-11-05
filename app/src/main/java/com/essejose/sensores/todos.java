package com.essejose.sensores;

import android.app.usage.UsageEvents;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class todos extends AppCompatActivity implements SensorEventListener{

    private TextView sGyro,sProx,sMag,sLuz,sBar;
    private LinearLayout datosView;
    private SensorManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todos);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        sGyro=(TextView)findViewById(R.id.sGyro);
        sProx=(TextView)findViewById(R.id.sProx);
        sMag=(TextView)findViewById(R.id.sMag);
        sLuz=(TextView)findViewById(R.id.sLuz);
        sBar=(TextView)findViewById(R.id.sBar);

        // Activando controlador de sensores
        sm =(SensorManager)getSystemService(SENSOR_SERVICE);

        // Gyroscopio
        if(sm.getSensorList(Sensor.TYPE_GYROSCOPE).size()==0){
            sGyro.setText("Dispositivo no cuenta con Gyroscopio");
        }else{
            // activando Sensor
            sm.registerListener(this,sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE),SensorManager.SENSOR_DELAY_NORMAL);
        }
        //proximidad
        if(sm.getSensorList(Sensor.TYPE_PROXIMITY).size()==0){
            sProx.setText("Dispositivo no cuenta con Proxymidad");
        }else{
            // activando Sensor
            sm.registerListener(this,sm.getDefaultSensor(Sensor.TYPE_PROXIMITY),SensorManager.SENSOR_DELAY_NORMAL);
        }
        //Magnetómetro
        if(sm.getSensorList(Sensor.TYPE_MAGNETIC_FIELD).size()==0){
            sMag.setText("Dispotivo no cuenta con Magnetómetro");
        }else{
            // activando Sensor
            sm.registerListener(this,sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),SensorManager.SENSOR_DELAY_NORMAL);
        }
        // ALS
        if(sm.getSensorList(Sensor.TYPE_LIGHT).size()==0){
            sLuz.setText("Dispotivo no cuenta con ALS (Sensor de Luz Ambiental)");
        }else{
            // activando Sensor
            sm.registerListener(this,sm.getDefaultSensor(Sensor.TYPE_LIGHT),SensorManager.SENSOR_DELAY_NORMAL);
        }
        // Barómetro
        if(sm.getSensorList(Sensor.TYPE_PRESSURE).size()==0){
            sBar.setText("Dispotivo no cuenta con Barómetro");
        }else{
            // activando Sensor
            sm.registerListener(this,sm.getDefaultSensor(Sensor.TYPE_PRESSURE),SensorManager.SENSOR_DELAY_NORMAL);
        }


    }

    @Override
    public void onStop(){
        super.onStop();
        //Para todos los sensores que se estan utilizando
        sm.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        synchronized (this){
            switch (event.sensor.getType()){
                case Sensor.TYPE_GYROSCOPE:
                    // obtener valor de sensor
                    float gyro_x= event.values[SensorManager.DATA_X];
                    float gyro_y= event.values[SensorManager.DATA_Y];
                    float gyro_z= event.values[SensorManager.DATA_Z];
                    // guardar dato en Textview
                    sGyro.setText(
                            "Gyscopio:"+
                                    "\nX:"+String.valueOf(gyro_x)+
                                    "\nY:"+String.valueOf(gyro_y)+
                                    "\nz:"+String.valueOf(gyro_z)
                    );
                    break;
                case Sensor.TYPE_PROXIMITY:
                    // obtener valor de sensor
                    float prox=event.values[0];
                    // guardar dato en Textview
                    sProx.setText("Proximidad: "+String.valueOf(prox));
                    break;
                case Sensor.TYPE_MAGNETIC_FIELD:
                    // obtener valor de sensor
                    float mag_x=event.values[0];
                    float mag_y=event.values[1];
                    float mag_z=event.values[2];
                    // guardar dato en Textview
                    sMag.setText("Magnetómetro: "+
                            "\nX:"+String.valueOf(mag_x)+
                            "\nY:"+String.valueOf(mag_y)+
                            "\nz:"+String.valueOf(mag_z)
                    );
                    break;
                case Sensor.TYPE_LIGHT:
                    // obtener valor de sensor
                    float luz = event.values[0];
                    // guardar dato en Textview
                    sLuz.setText("ALS (Sensor de Luz Ambiental): "+String.valueOf(luz));
                    break;
                case Sensor.TYPE_PRESSURE:
                    // obtener valor de sensor
                    float mbar=event.values[0];
                    // guardar dato en Textview
                    sBar.setText("Barómetro: "+String.valueOf(mbar));
                    break;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
