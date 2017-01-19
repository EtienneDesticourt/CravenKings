package etiennedesticourt.cravenkings.Combat.Engine.Core.Graphics;

import android.graphics.Canvas;

public interface Graphics {
    void draw(int x, int y, Canvas c);
    void draw(int x, int y, Canvas c, Camera camera);
}
