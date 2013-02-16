package com.bigerstaff.ActionRPG;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class LoadScreen implements Screen {
		ActionRPG game;
		OrthographicCamera camera;

       // constructor to keep a reference to the main Game class
        public LoadScreen(ActionRPG game){        
        	this.game = game;   
        }
        
        @Override
        public void render(float delta) {
            Gdx.gl.glClearColor(0, 1f, 0, 1);
            Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
            camera.update();
            Gdx.app.log("LoadScreen", "loadscreen");
            //Show assetManager progress bar, and then move on to MenuScreen
        }
 

       @Override
        public void resize(int width, int height) {
           float aspectRatio = (float) width / (float) height;
    	   camera = new OrthographicCamera();
           camera.setToOrtho(false, 10f*aspectRatio, 10f);
        }
 

       @Override
        public void show() {
             // called when this screen is set as the screen with game.setScreen();
        }
 

       @Override
        public void hide() {
             // called when current screen changes from this to a different screen
        }
 

       @Override
        public void pause() {
        }
 

       @Override
        public void resume() {
        }
 

       @Override
        public void dispose() {
                // never called automatically
        }
 }