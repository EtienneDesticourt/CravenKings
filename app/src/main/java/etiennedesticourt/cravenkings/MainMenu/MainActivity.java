package etiennedesticourt.cravenkings.MainMenu;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;

import etiennedesticourt.cravenkings.Map.MapActivity;
import etiennedesticourt.cravenkings.R;
import etiennedesticourt.cravenkings.Tutorial.StoryActivity;

public class MainActivity extends AppCompatActivity{
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mp = MediaPlayer.create(this, R.raw.placeholder); //TODO: replace

        Button continueButton = (Button) findViewById(R.id.continueButton); //TODO: Fix null ref sometimes (hdpi only)
        if (!saveFileExists()){
            continueButton.setVisibility(View.GONE);
        }
    }

    public boolean saveFileExists() {
        String fileName = getString(R.string.save_file_name);
        File file = getFileStreamPath(fileName);
        return file.exists();
    }

    public void onClickContinue(View view){
        mp.start();
        Intent intent = new Intent(this, StoryActivity.class);
        startActivity(intent);
    }

    public void onClickNewGame(View view){
        mp.start();
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }

    public void onClickOptions(View view){
        Intent intent = new Intent(this, OptionsActivity.class);
        startActivity(intent);
    }

    public void onClickQuit(View view){
        finish();
    }
}
