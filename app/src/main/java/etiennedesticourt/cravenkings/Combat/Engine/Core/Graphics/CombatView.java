package etiennedesticourt.cravenkings.Combat.Engine.Core.Graphics;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import etiennedesticourt.cravenkings.Combat.Engine.Core.Graphics.Renderer;

public class CombatView extends View {
    private Renderer renderer;

    public CombatView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public CombatView(Context context) {
        super(context);
    }

    protected void onDraw(Canvas c){
        super.onDraw(c);
        if (renderer != null){
            renderer.draw(c);
        }
        invalidate();
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public void setRenderer(Renderer renderer) {
        this.renderer = renderer;
    }
}
