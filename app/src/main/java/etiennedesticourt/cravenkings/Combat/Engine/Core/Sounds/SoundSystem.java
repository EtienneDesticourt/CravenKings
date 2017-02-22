package etiennedesticourt.cravenkings.Combat.Engine.Core.Sounds;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;

import java.util.ArrayList;
import java.util.HashMap;

import etiennedesticourt.cravenkings.Combat.Engine.Core.Physics.Object2D;

public class SoundSystem extends Thread{
    private HashMap<Enum, MediaPlayer> tracks;
    private HashMap<Enum, Sound> sounds;
    private SoundPool pool;
    private Object2D player;
    private ArrayList<SoundEvent> soundEvents;
    private int maxAudibleDistance;
    private boolean stop = false;
    private final int delay = 1000;

    public SoundSystem(Object2D player, int maxAudibleDistance) {
        this.player = player;
        this.maxAudibleDistance = maxAudibleDistance;
        tracks = new HashMap<>();
        sounds = new HashMap<>();
        soundEvents = new ArrayList<>();
        pool = new SoundPool.Builder().build();
    }

    public void spawnSoundEvent(Enum soundType, int x, boolean looping) throws SoundNotLoadedException {
        Sound sound = sounds.get(soundType);
        if (sound == null) {
            throw new SoundNotLoadedException(soundType);
        }
        int duration = looping ? 0 : sound.duration;
        SoundEvent event = new SoundEvent(soundType, x, maxAudibleDistance, sound, duration);
        soundEvents.add(event);
        int distance = calcDistanceToEvent(event);
        event.play(pool, distance);
    }

    public int calcDistanceToEvent(SoundEvent event) {
        return event.getX() - player.getX();
    }

    public void play(Enum soundType) {
        tracks.get(soundType).start();
    }

    public void loadTrack(Enum soundType, int resourceId, Context context) {
        MediaPlayer mp = MediaPlayer.create(context, resourceId);
        tracks.put(soundType, mp);
    }

    public void loadSound(Enum soundType, int resourceId, Context context) {
        int duration = getSoundDuration(resourceId, context);
        int soundId = pool.load(context, resourceId, 1);
        Sound sound = new Sound(soundId, duration);
        sounds.put(soundType, sound);
    }

    public int getSoundDuration(int resourceId, Context context) {
        MediaPlayer player = MediaPlayer.create(context, resourceId);
        int duration = player.getDuration();
        player.release();
        return duration;
    }

    public void start() {
        stop = false;
    }

    public void end() {
        stop = true;
    }

    public void run() {
        while (!stop) {

            for (int i=0; i<soundEvents.size(); i++) {
                SoundEvent event = soundEvents.get(i);
                int distance = calcDistanceToEvent(event);
                event.update(pool, distance);
            }


            try {
                Thread.sleep(delay);
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}
