package com.mygdx.ebbirari.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Align;
import com.mygdx.ebbirari.game.Ebbirari;

import com.mygdx.ebbirari.tools.CollisionRect;

public class MainScreen implements Screen{

    final Ebbirari game;

    //Ebbirari
    private final float kEbbiX = 525f;
    private final float kEbbiY = 175f;
    private final float kEbbiHeight = 550f;
    private final float kEbbiWidth = 550f;

    //Button drawing constants
    private final float kbuyFoodButtonX = 0f;
    private final float kbuyFoodButtonY = 500f;
    private final float kbuyFoodButtonHeight = 100f;
    private final float kbuyFoodButtonWidth = 200f;

    private final float kbuyWaterButtonX = 0f;
    private final float kbuyWaterButtonY = 350f;
    private final float kbuyWaterButtonHeight = 100f;
    private final float kbuyWaterButtonWidth = 200f;

    private final float ksaveButtonX = 1400f;
    private final float kSaveButtonY = 300f;
    private final float kSaveButtonHeight = 100f;
    private final float ksaveButtonWidth = 200f;

    private final float kloadButtonX = 1400f;
    private final float kloadButtonY = 150f;
    private final float kloadButtonHeight = 100f;
    private final float kloadButtonWidth =200f;

    private final float kfeedButtonX = 400f;
    private final float kfeedButtonY = 15f;
    private final float kfeedButtonHeight = 100f;
    private final float kfeedButtonWidth = 200f;

    private final float kquenchButtonX = 900f;
    private final float kquenchButtonY = 15f;
    private final float kquenchButtonHeight = 100f;
    private final float kquenchButtonWidth = 200f;

    private final float kminigameButtonX = 1400f;
    private final float kminigameButtonY = 500f;
    private final float kminigameButtonHeight = 200f;
    private final float kminigameButtonWidth = 200f;

    //Values of money, food, water, hunger, thirst
    float hungerFactor = 0;
    float thirstFactor = 0;
    int currency = 50;
    int food = 5;
    int water = 5;

    int energy = 3;

    int pastDay;
    int pastMonth;
    int pastHour;
    int pastMinute;

    int day;
    int month;
    int hour;
    int minute;

    float hourTemp;
    float minuteTemp;

    double hungerConstant;
    double waterConstant;

    //Various booleans
    boolean pong = false;
    boolean versionB = false;
    boolean ebbiIsDead = false;


    //Textures
    public static final Texture kPlaceholder = new Texture("badlogic.jpg");
    Texture ebbiHappy = new Texture("EbbiHappy.png");
    Texture ebbiNeutral = new Texture("EbbiNeutral.png");
    Texture ebbiSad = new Texture("EbbiSad.png");
    Texture ebbiDead= new Texture("EbbiDead.png");
    Texture ebbiTexture = ebbiNeutral;

    Texture background = new Texture("Background.png");
    Texture logo = new Texture("FixedLogo.png");

    Texture feedButton = new Texture("FeedButton.png");
    Texture drinkButton = new Texture("DrinkButton.png");
    Texture buyFoodButton = new Texture("BuyFood.png");
    Texture buyDrinkButton = new Texture("BuyDrink.png");
    Texture saveButton = new Texture("SaveButton.png");
    Texture loadButton = new Texture("LoadButton.png");
    Texture minigameButton = new Texture("MinigameButton.png");

    //Collision rectangles for buttons
    CollisionRect buyFoodRect;
    CollisionRect buyDrinkRect;
    CollisionRect feedRect;
    CollisionRect quenchRect;
    CollisionRect minigameRect;
    CollisionRect saveRect;
    CollisionRect loadRect;
    CollisionRect ebbirari;

    //Collision rectangle for mouse
    CollisionRect mouseRect;

    Preferences saveFile;

    BitmapFont font;

    String ebbiStatus;

    Music mainTune;


    public MainScreen(Ebbirari game){
        this.game = game;
        font = new BitmapFont(Gdx.files.internal("MerchantSerif.fnt"));
        saveFile = Gdx.app.getPreferences("Save File");

        mainTune = Gdx.audio.newMusic(Gdx.files.internal("MainScreenJams.mp3"));

        //Create all button collision rectangles
        ebbirari = new CollisionRect(kEbbiX, kEbbiY, kEbbiWidth, kEbbiHeight);

        buyFoodRect = new CollisionRect(kbuyFoodButtonX, kbuyFoodButtonY, kbuyFoodButtonWidth, kbuyFoodButtonHeight);
        buyDrinkRect = new CollisionRect(kbuyWaterButtonX, kbuyWaterButtonY, kbuyWaterButtonWidth, kbuyWaterButtonHeight);
        feedRect = new CollisionRect(kfeedButtonX, kfeedButtonY, kfeedButtonWidth, kfeedButtonHeight);
        quenchRect = new CollisionRect(kquenchButtonX, kquenchButtonY, kquenchButtonWidth, kquenchButtonHeight);

        minigameRect = new CollisionRect(kminigameButtonX, kminigameButtonY, kminigameButtonWidth, kminigameButtonHeight);

        saveRect = new CollisionRect(ksaveButtonX, kSaveButtonY, ksaveButtonWidth, kSaveButtonHeight);
        loadRect = new CollisionRect(kloadButtonX, kloadButtonY, kloadButtonWidth, kloadButtonHeight);
        if(versionB){
            hungerConstant = (.25);
            waterConstant = (.25);
        }
        else{
            hungerConstant = (.2);
            waterConstant = (.2);

        }
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        mainTune.play();

        if(Gdx.input.justTouched()) {
            Vector3 pos = game.viewport.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            mouseRect = new CollisionRect(pos.x, pos.y, 1, 1);

            //Write in here what all the buttons should be up to
            if(mouseRect.collidesWith(buyFoodRect)){
                if (!ebbiIsDead) {
                    if(currency >= 10){
                        food += 1;
                        currency -= 10;
                    }
                    else if(currency < 10){
                        food += 0;
                        currency -= 0;
                    }
                }
                else if(ebbiIsDead){
                    food += 0;
                    currency -= 0;
                }

            }

            if(mouseRect.collidesWith(buyDrinkRect)){
                if (!ebbiIsDead) {
                    if (currency >= 10) {
                        water += 1;
                        currency -= 10;
                    } else if (currency < 10) {
                        water += 0;
                        currency -= 0;
                    }
                }
                else if(ebbiIsDead){
                    water += 0;
                    currency -= 0;
                }

            }

            if(mouseRect.collidesWith(feedRect)){
                if(!ebbiIsDead){
                    if(food >= 1){
                        if(hungerFactor > 0){
                            hungerFactor -= 1;
                            food -= 1;
                        }
                        else{
                            food -= 0;
                            hungerFactor -= 0;
                        }

                    }
                    else if(food < 1){
                        food -= 0;
                    }
                }
                else if(ebbiIsDead){
                    food -= 0;
                    hungerFactor -= 0;
                }

            }

            if(mouseRect.collidesWith(quenchRect)){
                if(!ebbiIsDead){
                    if(water >= 1){
                        if(thirstFactor > 0){
                            thirstFactor -= 1;
                            water -= 1;
                        }
                        else{
                            thirstFactor -= 0;
                            water -= 0;
                        }
                    }
                    else if(water < 1){
                        water -= 0;
                    }
                }
                else if(ebbiIsDead){
                    thirstFactor -= 0;
                    water -= 0;
                }

            }

            if(mouseRect.collidesWith(saveRect)){
                String date = String.valueOf(java.time.LocalDate.now());
                String time = String.valueOf(java.time.LocalTime.now());

                String dateSep = "-";
                String dateArray[] = date.split(dateSep);

                String timeSep = ":";
                String timeArray[] = time.split(timeSep);

                month = Integer.parseInt(dateArray[1]);
                day = Integer.parseInt(dateArray[2]);

                hour = Integer.parseInt(timeArray[0]);
                minute = Integer.parseInt(timeArray[1]);

                saveFile.putInteger("Food count", food);
                saveFile.putInteger("Water count", water);
                saveFile.putInteger("Currency", currency);
                saveFile.putFloat("Hunger Factor", hungerFactor);
                saveFile.putFloat("Thirst Factor", thirstFactor);


                saveFile.putInteger("Hour", hour);
                saveFile.putInteger("Minute", minute);

                saveFile.putInteger("Day", day);
                saveFile.putInteger("Month", month);

                if(versionB){
                    saveFile.putInteger("Energy", energy);
                }
                
                saveFile.flush();


            }

            //Add in calculations to tick down stats

            if(mouseRect.collidesWith(loadRect)){
                if(ebbiIsDead){
                    food = 5;
                    water = 5;
                    currency = 50;
                    hungerFactor = 0;
                    thirstFactor = 0;
                    ebbiIsDead = false;
                }
                else{
                    String date = String.valueOf(java.time.LocalDate.now());
                    String time = String.valueOf(java.time.LocalTime.now());

                    String dateSep = "-";
                    String dateArray[] = date.split(dateSep);

                    String timeSep = ":";
                    String timeArray[] = time.split(timeSep);

                    day = Integer.parseInt(dateArray[2]);
                    month = Integer.parseInt(dateArray[1]);

                    hour = Integer.parseInt(timeArray[0]);
                    minute = Integer.parseInt(timeArray[1]);

                    food = saveFile.getInteger("Food count", 0);
                    water = saveFile.getInteger("Water count", 0);
                    currency = saveFile.getInteger("Currency", 0);
                    hungerFactor = saveFile.getFloat("Hunger Factor", 0);
                    thirstFactor = saveFile.getFloat("Thirst Factor", 0);

                    pastHour = saveFile.getInteger("Hour", 0);
                    pastMinute = saveFile.getInteger("Minute", 0);
                    pastDay = saveFile.getInteger("Day", 0);
                    pastMonth = saveFile.getInteger("Month", 0);

                    if(month - pastMonth == 1){
                        pastDay = 0;
                    }

                    if(day - pastDay == 0){
                        hourTemp = hour - pastHour;
                        hungerFactor += (hourTemp * hungerConstant);
                        thirstFactor += (hourTemp * waterConstant);
                    }
                    else if(day - pastDay == 1){
                        hourTemp = (hour + 24) - pastHour;
                        hungerFactor += (hourTemp * hungerConstant);
                        thirstFactor += (hourTemp * waterConstant);
                    }
                    else if(day - pastDay == 2){
                        hourTemp = (hour + 48) - pastHour;
                        hungerFactor += (hourTemp * hungerConstant);
                        thirstFactor += (hourTemp * waterConstant);
                    }
                    else if(day - pastDay == 3){
                        hourTemp = (hour + 72) - pastHour;
                        hungerFactor += (hourTemp * hungerConstant);
                        thirstFactor += (hourTemp * waterConstant);
                    }

                    if(versionB){
                        energy = saveFile.getInteger("Energy", 3);
                        if(minute - pastMinute >=  10 && minute - pastMinute < 20){
                            if(energy < 3){
                                energy +=1 ;
                            }
                        }
                        if(minute - pastMinute >= 20 && minute - pastMinute < 30){
                            if(energy < 2){
                                energy +=2 ;
                            }
                        }
                        if(minute - pastMinute >= 30){
                            if(energy < 1){
                                energy +=3 ;
                            }
                        }
                    }

                }
            }


            if(mouseRect.collidesWith(minigameRect)){
                if(versionB){
                    if(energy > 0){
                        pong = true;
                        energy -=1;
                    }
                    else{
                        pong = false;
                        energy -= 0;
                    }
                }
                else{
                    pong = true;
                }
            }
        }

        //Rendering
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.setProjectionMatrix(game.camera.combined);

        //Ebbirari emotion logic

        if(hungerFactor < 5){
            if(thirstFactor < 5){
                ebbiStatus = "happy";
                ebbiTexture = ebbiHappy;
            }
            else if(thirstFactor >= 5 && thirstFactor < 10){
                ebbiStatus = "thirsty";
                ebbiTexture = ebbiNeutral;
            }
            else if(thirstFactor >= 10){
                ebbiStatus = "dehydrated";
                ebbiTexture = ebbiSad;
            }
        }
        else if(hungerFactor >= 5 && hungerFactor < 10){
            if(thirstFactor < 5){
                ebbiStatus = "hungry";
                ebbiTexture = ebbiNeutral;
            }
            else if(thirstFactor >= 5 && thirstFactor < 10){
                ebbiStatus = "hungry and thirsty";
                ebbiTexture = ebbiNeutral;
            }
            else if(thirstFactor >= 10){
                ebbiStatus = "hungry and dehydrated";
                ebbiTexture = ebbiSad;
            }
        }
        else if(hungerFactor >= 10){
            if(thirstFactor < 5){
                ebbiStatus = "starving";
                ebbiTexture = ebbiSad;
            }
            else if(thirstFactor >= 5 && thirstFactor < 10){
                ebbiStatus = "starving and thirsty";
                ebbiTexture = ebbiSad;
            }
            else if(thirstFactor >= 10){
                ebbiStatus = "dead";
                ebbiTexture = ebbiDead;
                ebbiIsDead = true;
            }
        }

        //Draw the background and logo
        game.batch.draw(background, 0, 0 , 1600, 960);
        game.batch.draw(logo, 406, 764 , 788, 196);

        //Draw all of the buttons
        game.batch.draw(ebbiTexture, kEbbiX, kEbbiY, kEbbiWidth, kEbbiHeight);


        game.batch.draw(buyFoodButton, kbuyFoodButtonX, kbuyFoodButtonY, kbuyFoodButtonWidth, kbuyFoodButtonHeight);
        game.batch.draw(buyDrinkButton, kbuyWaterButtonX, kbuyWaterButtonY, kbuyWaterButtonWidth, kbuyWaterButtonHeight);
        game.batch.draw(feedButton, kfeedButtonX, kfeedButtonY, kfeedButtonWidth, kfeedButtonHeight);
        game.batch.draw(drinkButton, kquenchButtonX, kquenchButtonY, kquenchButtonWidth, kquenchButtonHeight);

        game.batch.draw(minigameButton, kminigameButtonX, kminigameButtonY, kminigameButtonWidth, kminigameButtonHeight);

        game.batch.draw(saveButton, ksaveButtonX, kSaveButtonY, ksaveButtonWidth, kSaveButtonHeight);
        game.batch.draw(loadButton, kloadButtonX, kloadButtonY, kloadButtonWidth, kloadButtonHeight);

        font.getData().setScale(2); //Scale of text
        font.draw(game.batch, "Food: "+ food, 10, 100, 30, Align.left,true);
//        font.getData().setScale(2); //Scale of text
//        font.draw(game.batch, "Hour: "+ hour + " Day: "+ day + " Minute: "+ minute, 10, 300, 30, Align.left,true);
//
//        font.getData().setScale(2); //Scale of text
//        font.draw(game.batch, "PastHour: "+ pastHour + " PastDay: "+ pastDay + " Pastminute: "+ pastMinute, 10, 350, 30, Align.left,true);

        font.getData().setScale(2); //Scale of text
        font.draw(game.batch, "Water: "+ water, 10, 50, 30, Align.left,true);

        font.getData().setScale(2); //Scale of text
        font.draw(game.batch, "Buckaroonos: "+ currency, 10, 150, 30, Align.left,true);

        font.getData().setScale(2); //Scale of text
        font.draw(game.batch, "Your ebbirari is "+ ebbiStatus + "!", 10, 200, 30, Align.left,true);

        if(versionB){
            font.getData().setScale(2); //Scale of text
            font.draw(game.batch, "Energy: "+ energy, 10, 250, 30, Align.left,true);
        }


        game.batch.end();

        if(pong == true){
            //game.camera.translate(MMGame.kWidth/2 - player.getX() - player.getWidth()/2, MMGame.kHeight/2 - player.getY() - player.getHeight()/2);
            //this.dispose();
           // game.camera.translate(Ebbirari.kWidth/2 , Ebbirari.kHeight/2);
            game.camera.update();
            game.setScreen(new MinigameScreen(game));
        }

    }

    @Override
    public void resize(int width, int height) {
        game.viewport.update(width,height);
        game.camera.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
