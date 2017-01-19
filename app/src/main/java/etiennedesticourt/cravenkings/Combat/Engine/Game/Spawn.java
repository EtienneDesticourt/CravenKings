package etiennedesticourt.cravenkings.Combat.Engine.Game;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;

import etiennedesticourt.cravenkings.Combat.Engine.Core.Graphics.Camera;
import etiennedesticourt.cravenkings.Combat.Engine.Core.Graphics.Graphics;
import etiennedesticourt.cravenkings.Combat.Engine.Core.Physics.Object2D;
import etiennedesticourt.cravenkings.Map.Allegiance;
import etiennedesticourt.cravenkings.R;

public class Spawn{
    private int[] coords;
    private Allegiance allegiance;

    public Spawn(Context context, Allegiance allegiance){
        coords = new int[2];
        Resources res = context.getResources();
        if (allegiance == Allegiance.PLAYER){
            coords[0] = res.getInteger(R.integer.friendly_spawn_x_pos);
        }
        else{
            coords[0] = res.getInteger(R.integer.enemy_spawn_x_pos);
        }
        coords[1] = (int) res.getDimension(R.dimen.combat_units_height);
        this.allegiance = allegiance;
    }

    public int[] getSpawn(){
        return coords;
    }

    public Allegiance getAllegiance(){
        return allegiance;
    }
}
