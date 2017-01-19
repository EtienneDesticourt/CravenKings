package etiennedesticourt.cravenkings.Combat.Engine.Core.Graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

public class Background{
    private int offset, width;
    private float distance;
    private boolean repeating;
    private Camera camera;
    private Bitmap background;
    private boolean dimensionsCalculated = false;

    public Background(Camera camera, ImageView v, int offset,  float distance) {
        this(camera, v, offset, distance, true);
    }
    public Background(Camera camera, ImageView v, int offset,  float distance, boolean repeating) {
        this.camera = camera;
        this.offset = offset;
        this.distance = distance;
        this.repeating = repeating;
    }


    public void calculateDimens(ImageView v){
        if (dimensionsCalculated){ return; }
        BitmapDrawable bitmapDrawable = (BitmapDrawable) v.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        float height = v.getHeight();
        float percent = height / bitmap.getHeight();
        int width = (int) (percent * bitmap.getWidth());
        background = Bitmap.createScaledBitmap(bitmap, width, (int) height, false);
        this.width = width;
        v.setImageResource(0);
        dimensionsCalculated = true;
    }

    public void draw(Canvas canvas, View v){
        int adjustedCameraX = (int) (camera.getX() / distance);
        int adjustedX = offset - (adjustedCameraX - (adjustedCameraX / width  * width));
        int adjustedY = 0 - camera.getY();


        canvas.drawBitmap(background, adjustedX, adjustedY, null);
        if (repeating){
            canvas.drawBitmap(background, adjustedX - width, adjustedY, null);
            canvas.drawBitmap(background, adjustedX + width, adjustedY, null);
        }
    }

    /*
    public void draw(Canvas c){
        draw(0, y, c);
    }

    public void draw(Canvas c, Camera camera){
        draw(0, y, c, camera);
    }

    @Override
    public void draw(int x, int y, Canvas c) {
        c.drawBitmap(background, x + offset, y, null);
    }

    @Override
    public void draw(int x, int y, Canvas canvas, Camera camera) {
        //If the camera is multiple background widths away from the original background
        //It's like being one width away since the background is repeating
        int adjustedCameraX = (int) (camera.getX() / distance);
        int adjustedX = x + offset - (adjustedCameraX - (adjustedCameraX / width  * width));
        int adjustedY = y - camera.getY();


        canvas.drawBitmap(background, adjustedX, adjustedY, null);
        if (repeating){
            canvas.drawBitmap(background, adjustedX - width + 10, adjustedY, null);
            canvas.drawBitmap(background, adjustedX + width - 10, adjustedY, null);
        }
    }
    */
}
