package etiennedesticourt.cravenkings.Combat.Engine.Core.Sounds;

import android.media.SoundPool;

import etiennedesticourt.cravenkings.Combat.Engine.Core.Graphics.Graphics;
import etiennedesticourt.cravenkings.Combat.Engine.Core.Physics.Object2D;

public class SoundEvent implements Object2D{
    private int x;
    private int maxAudibleDistance;
    private int duration;
    private boolean looping;
    private Enum soundType;
    private Sound sound;
    private int streamId;
    private long startTime;
    private int priority = 1;
    private float playbackRate = 1;

    public SoundEvent(Enum soundType, int x, int maxAudibleDistance, Sound sound, int duration) {
        this.soundType = soundType;
        this.x = x;
        this.maxAudibleDistance = maxAudibleDistance;
        this.sound = sound;
        this.streamId = -1;
        this.startTime = -1;
        setDuration(duration);
    }

    public void play(SoundPool pool, int distance) {
        float[] volume = calculateVolumes(distance);
        int id = pool.play(sound.id, volume[0], volume[1], priority, looping ? -1 : 0, playbackRate);
        setStreamId(id);
    }

    public void update(SoundPool pool, int distance) {
        if (isPlaying()) {
            float[] volume = calculateVolumes(distance);
            pool.setVolume(streamId, volume[0], volume[1]);
        }
    }

    public float[] calculateVolumes(int centerDistance) {
        int leftEarDistance = (int) (centerDistance - maxAudibleDistance / 10.);
        int rightEarDistance = (int) (centerDistance + maxAudibleDistance / 10.);

        float leftEarVolume = calculateVolume(leftEarDistance);
        float rightEarVolume = calculateVolume(rightEarDistance);

        return new float[]{leftEarVolume, rightEarVolume};
    }

    public float calculateVolume(int distance) {
        if (distance >= maxAudibleDistance) {
            return 0;
        }
        return 1 - Math.abs(distance / maxAudibleDistance);
    }

    public boolean isPlaying() {
        if (hasStream()) {
            if (isLooping()) {
                return true;
            }
            long currentTime = System.currentTimeMillis();
            if (startTime + duration < currentTime) {
                return true;
            }
        }
        return false;
    }

    public void setDuration(int duration){
        this.duration = duration;
        looping = duration == 0;
    }

    public void setStreamId(int streamId) {
        this.streamId = streamId;
        this.startTime = System.currentTimeMillis();
    }

    public int getStreamId() {
        return streamId;
    }

    public boolean hasStream() {
        return streamId != -1;
    }

    public Enum getSoundType() {
        return soundType;
    }

    public int getDuration() {
        return duration;
    }

    public boolean isLooping() {
        return looping;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public Graphics getGraphics() {
        return null;
    }
}
