package com.cipolat.surfaceview_tutorial_part1;

import com.cipolat.surfaceview_tutorial_part1.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MySurfaceView extends SurfaceView implements
		SurfaceHolder.Callback {
  
	//Referencia a un thread usaremos para dibujar
	public MySurfaceThread thread;
	
	public MySurfaceView(Context context) {
		super(context);
		//usaremos esta clase como manejador
		getHolder().addCallback(this);

	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		thread = new MySurfaceThread(getHolder(), this);
		thread.setRunning(true);
		thread.start();

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		Log.e("surfaceDestroyed ", "Hilo detenido ");

		boolean retry = true;
		thread.setRunning(false);
		while (retry) {
			try {
				thread.join();
				retry = false;
			} catch (InterruptedException e) {
			}
		}
	}

	@Override
	public void onDraw(Canvas canvas) {

		
		int ancho = canvas.getWidth();
		int alto = canvas.getHeight();

		// mitad de ancho
		int mitadW = ancho / 2;
		// mitad de largo
		int mitadH = alto / 2;

		// pinto fondo de blanco
		canvas.drawColor(Color.WHITE);

		// definimos pincel
		Paint pcirculo = new Paint();
		pcirculo.setColor(Color.BLACK);
		pcirculo.setStyle(Paint.Style.FILL);
		//dibujamor circulo usando ese pincel en la mitad de la pantalla
		canvas.drawCircle(mitadW, mitadH, 60, pcirculo);

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub

		//Lo veremos en la siguiente entrega
		return true;

	}
}