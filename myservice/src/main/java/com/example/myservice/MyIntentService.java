package com.example.myservice;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.Surface;
import android.widget.Toast;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_FOO = "com.example.myservice.action.FOO";
    private static final String ACTION_BAZ = "com.example.myservice.action.BAZ";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "com.example.myservice.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.example.myservice.extra.PARAM2";

    public MyIntentService() {
        super("MyIntentService");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        onHandleIntent(intent);
        return super.onBind(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                final Surface surface = intent.getExtras().getParcelable(EXTRA_PARAM1);
                handleActionFoo(surface);
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param1, param2);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(Surface surface) {
        // TODO: Handle action Foo
//        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();

        Canvas canvas = surface.lockCanvas(null);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(100);
//                      canvas.drawRGB(0, 0, 0);
        canvas.drawLine(0, 0, 500, 500, paint);
        canvas.drawLine(0, 500, 500, 0, paint);
        surface.unlockCanvasAndPost(canvas);
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
