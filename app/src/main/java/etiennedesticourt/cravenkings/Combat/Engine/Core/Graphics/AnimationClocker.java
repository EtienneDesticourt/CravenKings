package etiennedesticourt.cravenkings.Combat.Engine.Core.Graphics;

import java.util.ArrayList;

public class AnimationClocker implements Runnable{
    private ArrayList<Animation> animations;
    private boolean run;
    private final int DELAY = 500;

    public AnimationClocker(){
        animations = new ArrayList<>();
    }

    public void addAnimation(Animation animation){
        animations.add(animation);
    }

    @Override
    public void run() {
        Animation animation;

        while (run){
            for (int i=0; i<animations.size(); i++){
                animation = animations.get(i);
                animation.next();
            }

            try {
                Thread.sleep(DELAY);
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void start(){
        run = true;
        new Thread(this).start();
    }

    public void stop(){
        run = false;
    }
}
