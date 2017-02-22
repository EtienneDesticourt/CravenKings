package etiennedesticourt.cravenkings.Combat.Engine.Core.Sounds;

public class SoundNotLoadedException extends Exception{
    private Enum soundType;

    public SoundNotLoadedException(Enum soundType) {
        this.soundType = soundType;
    }

    public Enum getSoundType() {
        return soundType;
    }
}
