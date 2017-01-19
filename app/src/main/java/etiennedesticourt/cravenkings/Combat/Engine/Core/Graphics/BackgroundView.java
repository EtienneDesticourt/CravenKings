package etiennedesticourt.cravenkings.Combat.Engine.Core.Graphics;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import etiennedesticourt.cravenkings.Combat.CombatActivity;
import etiennedesticourt.cravenkings.R;

public class BackgroundView extends ImageView {
    private final int DEFAULT_DISTANCE = 1;
    private final int DEFAULT_OFFSET   = 0;
    private Background background;

    public BackgroundView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray array = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.BackgroundView,
                0, 0);

        int offset = array.getInteger(R.styleable.BackgroundView_offset, DEFAULT_OFFSET);
        int distance = array.getInteger(R.styleable.BackgroundView_distance, DEFAULT_DISTANCE);
        array.recycle();
        background = new Background(((CombatActivity) context).getCamera(), this, offset, distance);
    }

    public BackgroundView(Context context) {
        super(context);
        background = new Background(((CombatActivity) context).getCamera(), this, DEFAULT_OFFSET,
                DEFAULT_DISTANCE);
    }

    protected void onDraw(Canvas c){
        super.onDraw(c);
        if (background != null){
            background.draw(c, this);
        }
        invalidate();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b){
        super.onLayout(changed, l, t, r, b);
        background.calculateDimens(this);
    }

    public void setBackground(Background background){
        this.background = background;
    }
}
