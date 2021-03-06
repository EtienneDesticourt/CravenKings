package etiennedesticourt.cravenkings.Combat.Engine.Core.Graphics.Animation;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;

import etiennedesticourt.cravenkings.Combat.Engine.Core.Graphics.AssetHandler;

public enum AnimationManager {
    INSTANCE;

    private HashMap<Integer, Animation> animations;
    private AnimationClocker clock;
    private Resources res;
    private String packageName;

    private AnimationManager(){
        animations = new HashMap<>();
        clock = new AnimationClocker();
    }

    public void setContext(Context context) { //TODO: Clarify unintianalized exception
        res = context.getResources();
        packageName = context.getPackageName();
    }

    public Animation get(int animationId)
            throws IOException, SAXException, ParserConfigurationException {
        if (animations.containsKey(animationId)){
            return animations.get(animationId);
        }
        //Get animation resources //TODO: put in different method
        InputStream animationFile = res.openRawResource(animationId);

        //Build it from file
        AnimationFileParser parser = AnimationFileParser.parse(animationFile);

        int drawableId = res.getIdentifier(parser.getDrawable(), "drawable", packageName);
        Bitmap bitmap = AssetHandler.INSTANCE.get(drawableId);

        int[] frameSize = calculateFrameSize(bitmap, parser.getNumFramesX(), parser.getNumFramesY());
        Animation animation = new Animation(frameSize[0], frameSize[1], parser.getNumFramesX(),
                parser.getNumFramesY(),
                parser.getNumFramesTotal(),
                parser.isLooping(),
                parser.isReversed(),
                drawableId,
                parser.getOffsetX(),
                parser.getOffsetY());
        animations.put(animationId, animation);
        clock.addAnimation(animation);
        return animation;
    }

    public void startRunningAnimations(){
        clock.start();
    }

    public void stopRunningAnimations(){
        clock.stop();
    }

    public void freeAnimations() {
        animations = new HashMap<>();
    }

    private static int[] calculateFrameSize(Bitmap bitmap, int numFramesX, int numFramesY){
        int[] frameSize = new int[2];
        frameSize[0] = bitmap.getWidth() / numFramesX;
        frameSize[1] = bitmap.getHeight() / numFramesY;
        return frameSize;
    }
}
