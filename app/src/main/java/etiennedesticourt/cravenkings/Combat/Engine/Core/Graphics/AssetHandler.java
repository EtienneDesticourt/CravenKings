package etiennedesticourt.cravenkings.Combat.Engine.Core.Graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.GradientDrawable;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.HashMap;

import etiennedesticourt.cravenkings.R;

//TODO: abstract implement in Game package
public enum AssetHandler {
    INSTANCE;

    private HashMap<Integer, Bitmap> bitmaps;

    private AssetHandler(){
        bitmaps = new HashMap<>();
    }

    public Bitmap get(int drawableId){
        if (!bitmaps.containsKey(drawableId)){
            throw new RuntimeException("Trying to use an asset that wasn't loaded.");
        }
        return bitmaps.get(drawableId);
    }

    public void loadAllAssets(Context context){
        BitmapFactory.Options options = new BitmapFactory.Options();
        //options.inSampleSize = 2;

        loadAsset(context, R.drawable.knight_walk_frames, options);
        loadAsset(context, R.drawable.knight_attack_frames, options);
        loadAsset(context, R.drawable.knight_death_frames, options);
        loadAsset(context, R.drawable.archer_walk_frames, options);
        loadAsset(context, R.drawable.archer_attack_frames, options);
        loadAsset(context, R.drawable.archer_death_frames, options);
        loadAsset(context, R.drawable.mage_walk_frames, options);
        loadAsset(context, R.drawable.mage_attack_frames, options);
        loadAsset(context, R.drawable.mage_death_frames, options);
        loadAsset(context, R.drawable.knight_walk_frames_enemy, options);
        loadAsset(context, R.drawable.knight_attack_frames_enemy, options);
        loadAsset(context, R.drawable.knight_death_frames_enemy, options);
        loadAsset(context, R.drawable.archer_walk_frames_enemy, options);
        loadAsset(context, R.drawable.archer_attack_frames_enemy, options);
        loadAsset(context, R.drawable.archer_death_frames_enemy, options);
        loadAsset(context, R.drawable.mage_walk_frames_enemy, options);
        loadAsset(context, R.drawable.mage_attack_frames_enemy, options);
        loadAsset(context, R.drawable.mage_death_frames_enemy, options);
    }

    public void loadAsset(Context context, int id, BitmapFactory.Options options){
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), id, options);
        bitmaps.put(id, bitmap);
    }

    public void loadAllAssetsForCurrentDevice(Context context){
        //Create matrix to scale down assets for current resolution
        float resPercentage = getScreenResolutionPercentage(context);

        BitmapDrawable d = (BitmapDrawable) context.getResources().getDrawable(R.drawable.knight_walk_frames);
        Bitmap b = scaleBitmap(d.getBitmap(), resPercentage);
        bitmaps.put(R.drawable.knight_walk_frames, b);

        d = (BitmapDrawable) context.getResources().getDrawable(R.drawable.background_backmountains);
        b = scaleBitmap(d.getBitmap(), resPercentage);
        bitmaps.put(R.drawable.background_backmountains, b);

        d = (BitmapDrawable) context.getResources().getDrawable(R.drawable.background_frontmountains);
        b = scaleBitmap(d.getBitmap(), resPercentage);
        bitmaps.put(R.drawable.background_frontmountains, b);

        d = (BitmapDrawable) context.getResources().getDrawable(R.drawable.background_grass);
        b = scaleBitmap(d.getBitmap(), resPercentage);
        bitmaps.put(R.drawable.background_grass, b);

        d = (BitmapDrawable) context.getResources().getDrawable(R.drawable.background_clouds);
        b = scaleBitmap(d.getBitmap(), resPercentage);
        bitmaps.put(R.drawable.background_clouds, b);
    }

    public void freeAllAssets(){
        bitmaps = new HashMap<>();
    }

    public static int getScreenHeightPercentage(Context context, int percentage, Orientation orientation){
        Resources res = context.getResources();
        DisplayMetrics metrics = res.getDisplayMetrics();
        int height = metrics.heightPixels;
//        if (orientation == Orientation.LANDSCAPE){
//            height = metrics.widthPixels;
//        }
//        else{
//            height = metrics.heightPixels;
//        }
        return (int) (height * (percentage / 100.) );//* getScreenResolutionPercentage(context));
    }

    private static float getScreenResolutionPercentage(Context context){
        //DEFAULT METRICS
        Resources res = context.getResources();
        float defaultWidth  = res.getDimension(R.dimen.engine_default_resolution_width);
        float defaultHeight = res.getDimension(R.dimen.engine_default_resolution_height);

        //ACTUAL DEVICE METRICS
        DisplayMetrics metrics = res.getDisplayMetrics();
        int width  = metrics.widthPixels;
        int height = metrics.heightPixels;

        float widthPercent  = width  / defaultWidth;
        float heightPercent = height / defaultHeight;

        return Math.min(widthPercent, heightPercent);
    }

    private Bitmap scaleBitmap(Bitmap bitmap, float sizePercentage){
        int scaledWidth  = (int) (bitmap.getWidth()  * sizePercentage);
        int scaledHeight = (int) (bitmap.getHeight() * sizePercentage);
        return Bitmap.createScaledBitmap(bitmap, scaledWidth, scaledHeight, false);
    }
}