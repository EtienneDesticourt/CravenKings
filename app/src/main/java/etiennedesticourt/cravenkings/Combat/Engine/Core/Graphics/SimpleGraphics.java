package etiennedesticourt.cravenkings.Combat.Engine.Core.Graphics;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class SimpleGraphics implements Graphics{
    private int bitmapId;

    public SimpleGraphics(int bitmapId) {
        this.bitmapId = bitmapId;
    }

    @Override
    public void draw(int x, int y, Canvas c) {
        Bitmap bitmap = AssetHandler.INSTANCE.get(bitmapId);
        c.drawBitmap(bitmap, x, y, null);

    }

    @Override
    public void draw(int x, int y, Canvas c, Camera camera) {
        draw(x-camera.getX(), y-camera.getY(), c);
    }
}
