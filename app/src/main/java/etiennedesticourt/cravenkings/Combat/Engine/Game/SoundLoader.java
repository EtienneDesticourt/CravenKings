package etiennedesticourt.cravenkings.Combat.Engine.Game;

import android.content.Context;

import etiennedesticourt.cravenkings.Combat.Engine.Core.Sounds.SoundSystem;
import etiennedesticourt.cravenkings.R;

public class SoundLoader {

    public static void loadAllSounds(SoundSystem system, Context context) {
        system.loadSound(SoundType.COINS_SPENT, R.raw.placeholder, context);
        system.loadSound(SoundType.COINS_RECEIVED, R.raw.placeholder, context);
        system.loadSound(SoundType.ENTITY_SPAWNED, R.raw.placeholder, context);
    }
}
