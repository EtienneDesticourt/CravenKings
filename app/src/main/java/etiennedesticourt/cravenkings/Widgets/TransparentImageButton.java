package etiennedesticourt.cravenkings.Widgets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;


/**
 * An image button that's clickable only where the image is not transparent.
 * We extend ImageView instead of Button because events are not dispatched to underlying buttons
 * when multiple buttons overlap (even if onTouch returns false for the top button), whereas
 * they're dispatched for overlapping ImageViews.
 */
public class TransparentImageButton extends ImageView {

    public TransparentImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Overrides the default function to instead set a touch listener that will behave the same
     * way as the given click listener only if the touch event was performed over a non-transparent
     * part of the button's background image.
     * It was made this way because the event is not passed to the onClickListener so it was
     * necessary to use an onTouchListener to get the event's position and we can still use the
     * android:onclick property to define the function to use in xml.
     * @param listener
     */
    @Override
    public void setOnClickListener(final View.OnClickListener listener){
        setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //If the event was a click
                if (MotionEventCompat.getActionMasked(motionEvent) == MotionEvent.ACTION_DOWN){
                    int xOnView = (int) motionEvent.getX();
                    int yOnView = (int) motionEvent.getY();

                    //If you try to change this later ... good luck, drawing cache always returns null
                    //all the other ways are shite.
                    Bitmap bmp1 = ((BitmapDrawable) view.getBackground()).getBitmap();
                    Bitmap bmp2 = Bitmap.createScaledBitmap(bmp1, view.getWidth(), view.getHeight(), false);
                    int color = bmp2.getPixel(xOnView, yOnView);
                    //We only keep clicks over non alpha regions
                    if (color != Color.TRANSPARENT){
                        listener.onClick(view);
                        return true;
                    }
                }
                return false;
            }
        });
    }
}
