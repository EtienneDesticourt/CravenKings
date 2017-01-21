package etiennedesticourt.cravenkings.Combat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import etiennedesticourt.cravenkings.Combat.Engine.Core.Graphics.AssetHandler;
import etiennedesticourt.cravenkings.Combat.Engine.Core.Graphics.Camera;
import etiennedesticourt.cravenkings.Combat.Engine.Core.Graphics.CombatView;
import etiennedesticourt.cravenkings.Combat.Engine.Core.Graphics.Renderer;
import etiennedesticourt.cravenkings.Combat.Engine.Core.Physics.Entity;
import etiennedesticourt.cravenkings.Combat.Engine.Game.AnimationManager;
import etiennedesticourt.cravenkings.Combat.Engine.Game.EntityFactory;
import etiennedesticourt.cravenkings.Combat.Engine.Game.Player;
import etiennedesticourt.cravenkings.Map.Allegiance;
import etiennedesticourt.cravenkings.R;

//GAMEPLAY
//TODO: Add Projectiles
//TODO: Add miners
//TODO: Add money
//TODO: Add skill effects
//TODO: Add castle unit
//TODO: Add victory state
//TODO: Add return from activity
//TODO: Add spells
//TODO: Add on touch damage enemies
//TODO: Add AI

//RENDER
//TODO: Set right position for units
//TODO: Add resized frames
//TODO: Add reverse bitmaps for other team
//TODO: Add list of assets to be loaded
//TODO: Add camera bounds
//TODO: Fix mountains size to make back more imposing
//TODO: Resize buttons for each density

//SOUNDS
//TODO: Add battle soundtrack
//TODO: Add coin effect sound
//TODO: Add button sound for each unit
//TODO: Add spell sound
//TODO: Add victory sound
//TODO: Add loss sound
//TODO: Add unit attack sounds
//TODO: Add unit spawn sounds
//TODO: Add unit death sounds
//TODO: Add unit damage sounds
//TODO: Add sound positioning system

//POLISH
//TODO: Add camera shakes
//TODO: Add button feedback
//TODO: Add damage feedback
//TODO: Add unit lifebar (only in combat)
//TODO: Add particle system
//TODO: Add particles on hit
//TODO: Add particles on spawn


public class CombatActivity extends AppCompatActivity {
    private Renderer renderer;
    private AnimationManager animationManager;
    private Camera camera;
    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        player = new Player(this, Allegiance.PLAYER);
        player.startEntityHandlers();

        //CREATE CAMERA
        camera = new Camera(this);

        //START RENDERING ENTITIES
        setContentView(R.layout.activity_combat);
        renderer = new Renderer(player.getEntities(), camera);
        CombatView combatView = (CombatView) findViewById(R.id.combatView);
        combatView.setRenderer(renderer);
        combatView.setOnTouchListener(camera);

        AssetHandler.INSTANCE.loadAllAssets(this);

        animationManager = new AnimationManager(this);
        animationManager.startRunningAnimations();
    }

    protected void onStop(){
        player.stopEntityHandlers();
        animationManager.stopRunningAnimations();
        animationManager.freeAnimations();
        AssetHandler.INSTANCE.freeAllAssets();
        System.gc();
        super.onStop();
    }

    public void spawnKnight(View v){
        try {
            Entity entity = EntityFactory.genKnight(animationManager, player.getSpawn());
            player.addEntity(entity);
        }
        catch (Exception e){
        }
    }

    public void spawnArcher(View v){
        try {
            Entity entity = EntityFactory.genArcher(animationManager, player.getSpawn());
            player.addEntity(entity);
        }
        catch (Exception e){
        }
    }

    public void spawnMage(View v){
        try {
            Entity entity = EntityFactory.genMage(animationManager, player.getSpawn());
            player.addEntity(entity);
        }
        catch (Exception e){
        }
    }


    private void toggleSubMainMenu(){

    }

    private void toggleSubSpellMenu(){

    }

    public Camera getCamera(){
        return camera;
    }
}
