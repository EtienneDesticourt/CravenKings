package etiennedesticourt.cravenkings.Combat;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import etiennedesticourt.cravenkings.Combat.Engine.Core.Graphics.AssetHandler;
import etiennedesticourt.cravenkings.Combat.Engine.Core.Graphics.Camera;
import etiennedesticourt.cravenkings.Combat.Engine.Core.Graphics.CombatView;
import etiennedesticourt.cravenkings.Combat.Engine.Core.Graphics.Renderer;
import etiennedesticourt.cravenkings.Combat.Engine.Game.AnimationManager;
import etiennedesticourt.cravenkings.Combat.Engine.Game.Player;
import etiennedesticourt.cravenkings.Map.Allegiance;
import etiennedesticourt.cravenkings.R;
import etiennedesticourt.cravenkings.databinding.ActivityCombatBinding;

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

//CODE QUALITY
//TODO: Move animations to package
//TODO: Add custom exceptions

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
//TODO: Add particles on income
//TODO: Clean miner icon


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

        ActivityCombatBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_combat);
        binding.setPlayer(player);

        //START RENDERING ENTITIES
        renderer = new Renderer(player.getEntities(), camera);
        CombatView combatView = (CombatView) findViewById(R.id.combatView);
        combatView.setRenderer(renderer);
        combatView.setOnTouchListener(camera);

        AssetHandler.INSTANCE.loadAllAssets(this);

        animationManager = AnimationManager.INSTANCE;
        animationManager.setContext(this);
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
            player.spawnKnight();
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
