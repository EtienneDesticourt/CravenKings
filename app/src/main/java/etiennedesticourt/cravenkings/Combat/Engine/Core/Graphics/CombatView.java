package etiennedesticourt.cravenkings.Combat.Engine.Core.Graphics;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

import etiennedesticourt.cravenkings.Combat.Engine.Core.Graphics.Renderer;

public class CombatView extends View {
    private ArrayList<Renderer> renderers;

    public CombatView(Context context, AttributeSet attrs) {
        super(context, attrs);
        renderers = new ArrayList<>();
    }


    public CombatView(Context context) {
        super(context);
    }

    protected void onDraw(Canvas c){
        super.onDraw(c);
        for (int i=0; i<renderers.size(); i++) {
            Renderer renderer = renderers.get(i);
            if (renderer != null){
                renderer.draw(c);
            }
        }
        invalidate();
    }

    public void addRenderer(Renderer renderer) {
        renderers.add(renderer);
    }
}
