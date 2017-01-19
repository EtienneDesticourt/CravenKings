package etiennedesticourt.cravenkings.Map;

import android.content.Context;
import android.content.res.Resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import etiennedesticourt.cravenkings.R;

public class CountryManager implements Serializable{
    private ArrayList<Country> countries;
    private Country selectedCountry;
    private int totalIncome = 0;
    private int totalMoney = 0;

    public CountryManager(ArrayList<Country> countries){
        if (countries.size() == 0){
            throw new RuntimeException("Initializing CountryManager with empty list of countries");
        }
        this.countries = countries;
        selectedCountry = countries.get(0);
        recalculateIncome();
    }

    public static CountryManager startNewGame(Context context){
        //Fetch country definition arrays from resources
        Resources res = context.getResources();
        int numCountries = res.getInteger(R.integer.num_countries);
        String[] names = res.getStringArray(R.array.country_names_array);
        int[] incomes = res.getIntArray(R.array.country_income_array);
        int[] troops = res.getIntArray(R.array.country_max_troops_array);
        String[] allegiances = res.getStringArray(R.array.country_allegiances_array);
        //TypedArray viewIds = res.obtainTypedArray(R.array.country_button_id_array);


        //Init countries
        ArrayList<Country> countries = new ArrayList<Country>();
        for (int i=0; i < numCountries; i++){
            Country country = new Country(i, names[i], troops[i], incomes[i], Allegiance.valueOf(allegiances[i]));
            countries.add(country);
            //View countryButton = findViewById(viewIds.getResourceId(i, 0));
            //countryButton.setTag(i);
        }

        CountryManager countryManager = new CountryManager(countries);
        return countryManager;
    }

    public static CountryManager loadGame(Context context) throws IOException, ClassNotFoundException{
        String fileName = context.getString(R.string.save_file_name);
        FileInputStream fis = context.openFileInput(fileName);
        ObjectInputStream is = new ObjectInputStream(fis);
        CountryManager loadedManager = (CountryManager) is.readObject();
        is.close();
        fis.close();
        return loadedManager;
    }

    public void saveGame(Context context) throws IOException {
        String fileName = context.getString(R.string.save_file_name);
        FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
        ObjectOutputStream os = new ObjectOutputStream(fos);
        os.writeObject(this);
        os.close();
        fos.close();
    }

    public void increaseMoney(){
        for (int i=0; i<countries.size(); i++){
            Country country = countries.get(i);
            if (country.isFriendly()){
                totalMoney += country.getIncome();
            }
        }
    }

    public int getSelectedCountryId() {
        return selectedCountry.getId();
    }
    public void setSelectedCountry(int countryId){
        Country country = countries.get(countryId);
        selectedCountry = country;
    }

    public Boolean isSelected(int countryId){
        if (selectedCountry.getId() == countryId) {
            return true;
        }
        return false;
    }

    public Boolean isEnemy(int countryId){
        if (!countries.get(countryId).isFriendly()){
            return true;
        }
        return false;
    }

    public void setFriendly(int countryId){
        countries.get(countryId).setAllegiance(Allegiance.PLAYER);
        recalculateIncome();
    }

    public void replaceCountry(Country country){
        int id = country.getId();
        countries.set(id, country);
    }

    public Country getCountry(int countryId){
        return countries.get(countryId);
    }

    public Country getSelectedCountry() {
        return selectedCountry;
    }

    public int getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(int totalIncome) {
        this.totalIncome = totalIncome;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

    private void recalculateIncome(){
        totalIncome = 0;
        for (int i=0; i<countries.size(); i++){
            Country country = countries.get(i);
            if (country.isFriendly()){
                totalIncome += country.getIncome();
            }
        }
    }
}
