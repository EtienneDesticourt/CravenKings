package etiennedesticourt.cravenkings.Map;

import android.app.ActivityOptions;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.IOException;

import etiennedesticourt.cravenkings.Combat.CombatActivity;
import etiennedesticourt.cravenkings.R;
import etiennedesticourt.cravenkings.databinding.ActivityMapBinding;

//TODO: Add click visual feedback
public class MapActivity extends AppCompatActivity {
    private ActivityMapBinding binding;
    private CountryManager countryManager;
    private static final int SKILLS_REQUEST_CODE = 0;
    private static final int COMBAT_REQUEST_CODE = 1;
    private MediaPlayer selectButtonMediaPlayer;
    private MediaPlayer attackButtonMediaPlayer;
    private MediaPlayer skillsButtonMediaPlayer;
    private MediaPlayer nextTurnButtonMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        countryManager = CountryManager.startNewGame(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_map);
        binding.setPresenter(countryManager);

        selectButtonMediaPlayer = MediaPlayer.create(this, R.raw.placeholder);
        attackButtonMediaPlayer = MediaPlayer.create(this, R.raw.placeholder);
        skillsButtonMediaPlayer = MediaPlayer.create(this, R.raw.placeholder);
        nextTurnButtonMediaPlayer = MediaPlayer.create(this, R.raw.placeholder);

    }

    public void onClickNextTurn(View v){
        nextTurnButtonMediaPlayer.start();
        countryManager.increaseMoney();
        binding.setPresenter(countryManager);
        //TODO: Add attack AI
    }

    public void onClickCountry(View v){
        int countryId = Integer.valueOf((String) v.getTag());
        //If we've already selected the country we're clicking on we want to start the fight
        //otherwise we select the new country
        if (countryManager.isSelected(countryId) && countryManager.isEnemy(countryId)){
            attackButtonMediaPlayer.start();
            startCombatActivity();
        }
        else {
            selectButtonMediaPlayer.start();
            countryManager.setSelectedCountry(countryId);
            binding.setPresenter(countryManager);
        }
    }

    public void onClickSkills(View v){
        skillsButtonMediaPlayer.start();
        startSkillsActivity();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        // Check which request we're responding to
        if (resultCode == RESULT_OK) {
            Bundle bundle = intent.getExtras();
            if (requestCode == SKILLS_REQUEST_CODE) {
                int money = bundle.getInt("money");
                Country country = (Country) bundle.getSerializable("country");
                countryManager.replaceCountry(country);
                countryManager.setTotalMoney(money);
            }
            else if (requestCode == COMBAT_REQUEST_CODE) {
                Boolean victory = bundle.getBoolean("victory");
                int countryId = bundle.getInt("countryId");
                if (victory){
                    countryManager.setFriendly(countryId);
                }
            }
            try {
                countryManager.saveGame(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void startSkillsActivity(){
        Intent intent = new Intent(this, SkillsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("money", countryManager.getTotalMoney());
        bundle.putSerializable("country", countryManager.getSelectedCountry());
        intent.putExtras(bundle);
        startActivityForResult(intent, SKILLS_REQUEST_CODE);
    }

    private void startCombatActivity(){
        Intent intent = new Intent(this, CombatActivity.class);
        //Extras
        Bundle extrasBundle = new Bundle();
        extrasBundle.putSerializable("country", countryManager.getSelectedCountry());
        intent.putExtras(extrasBundle);
        //Start options
        View backgroundLayout = findViewById(R.id.mapBackgroundLayout);
        BitmapDrawable swordsDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.swords);
        Bitmap thumbnail = swordsDrawable.getBitmap();
        int startX = (backgroundLayout.getWidth() -  thumbnail.getWidth()) / 2;
        int startY = (backgroundLayout.getHeight() -  thumbnail.getHeight()) / 2;
        ActivityOptions options = ActivityOptions.makeThumbnailScaleUpAnimation(backgroundLayout,
                thumbnail, startX, startY);
        Bundle optionsBundle = options.toBundle();

        startActivityForResult(intent, COMBAT_REQUEST_CODE, optionsBundle);
    }
}
