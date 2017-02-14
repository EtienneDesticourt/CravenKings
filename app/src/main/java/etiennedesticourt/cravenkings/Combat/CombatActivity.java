package etiennedesticourt.cravenkings.Combat;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import etiennedesticourt.cravenkings.Combat.Engine.Core.Graphics.Animation.AnimationManager;
import etiennedesticourt.cravenkings.Combat.Engine.Core.Graphics.AssetHandler;
import etiennedesticourt.cravenkings.Combat.Engine.Core.Graphics.Camera;
import etiennedesticourt.cravenkings.Combat.Engine.Core.Graphics.CombatView;
import etiennedesticourt.cravenkings.Combat.Engine.Core.Graphics.Renderer;
import etiennedesticourt.cravenkings.Combat.Engine.Core.Physics.EntityManager;
import etiennedesticourt.cravenkings.Combat.Engine.Game.AI.Spawner;
import etiennedesticourt.cravenkings.Combat.Engine.Game.Player;
import etiennedesticourt.cravenkings.Map.Allegiance;
import etiennedesticourt.cravenkings.R;
import etiennedesticourt.cravenkings.databinding.ActivityCombatBinding;

//GAMEPLAY
//TODO: Fix enemy frames
//TODO: Fix framerate issue
//TODO: Fix attacker logic
//TODO: Add Projectiles
//TODO: Add skill effects
//TODO: Add castle unit
//TODO: Add victory state
//TODO: Add return from activity
//TODO: Add spells
//TODO: Add on touch damage enemies
//TODO: Add AI
//TODO: Add enemies

//CODE QUALITY
//TODO: Add custom exceptions
//TODO: Add global time tracker

//RENDER
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
//TODO: Add particles on income
//TODO: Clean miner icon
//TODO: Use nicer font


public class CombatActivity extends AppCompatActivity {
    private AnimationManager animationManager;
    private Camera camera;
    private EntityManager entityManager;
    private Player player;
    private Player computer;
    private Spawner spawner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        entityManager = new EntityManager();
        entityManager.startEntityHandlers();

        player = new Player(this, entityManager, Allegiance.PLAYER);
        computer = new Player(this, entityManager, Allegiance.COMPUTER);

        //CREATE CAMERA
        camera = new Camera(this);

        ActivityCombatBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_combat);
        binding.setPlayer(player);

        //START RENDERING ENTITIES
        Renderer renderer = new Renderer(entityManager.getEntities(), camera);
        CombatView combatView = (CombatView) findViewById(R.id.combatView);
        combatView.addRenderer(renderer);
        combatView.setOnTouchListener(camera);

        AssetHandler.INSTANCE.loadAllAssets(this);

        animationManager = AnimationManager.INSTANCE;
        animationManager.setContext(this);
        animationManager.startRunningAnimations();

        spawner = new Spawner(computer);
        //spawner.start();

        Log.d("FUCKING HUUUUU", String.valueOf(getResources().getDisplayMetrics().density));
    }

    protected void onStop(){
        spawner.stop();
        entityManager.stopEntityHandlers();
        animationManager.stopRunningAnimations();
        animationManager.freeAnimations();
        AssetHandler.INSTANCE.freeAllAssets();
        System.gc();
        super.onStop();
    }

    public void spawnKnight(View v){ //TODO: Handle exceptions better
        try {
            player.spawnKnight();
            computer.spawnKnight();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void spawnArcher(View v){
        try {
            player.spawnArcher();
            computer.spawnArcher();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void spawnMage(View v){
        try {
            player.spawnMage();
            computer.spawnMage();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void spawnMiner(View v){
        player.spawnMiner();
    }


    private void toggleSubMainMenu(){

    }

    private void toggleSubSpellMenu(){

    }

    public Camera getCamera(){
        return camera;
    }
}
