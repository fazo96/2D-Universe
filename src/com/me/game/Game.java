package com.me.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Game implements ApplicationListener {
	
	private OrthographicCamera camera;
	private ShapeRenderer shapeRenderer;
	private boolean leftWasPressed=false,line=false,paused=false;
	private float startX,startY;
	
	@Override
	public void create() {
		camera = new OrthographicCamera(1, Gdx.graphics.getHeight()/Gdx.graphics.getWidth());
		shapeRenderer = new ShapeRenderer();
	}

	@Override
	public void dispose() {
		shapeRenderer.dispose();
	}
	public void handleInput(){
		if(Gdx.input.isKeyPressed(Keys.ESCAPE));
		if(Gdx.input.isKeyPressed(Keys.R))GravityPoint.destroyAll();
		if(Gdx.input.isKeyPressed(Keys.P))paused=!paused;
		if(Gdx.input.isKeyPressed(Keys.W)||Gdx.input.isKeyPressed(Keys.UP))camera.translate(0,-5);
		if(Gdx.input.isKeyPressed(Keys.S)||Gdx.input.isKeyPressed(Keys.DOWN))camera.translate(0,5);
		if(Gdx.input.isKeyPressed(Keys.A)||Gdx.input.isKeyPressed(Keys.LEFT))camera.translate(-5,0);
		if(Gdx.input.isKeyPressed(Keys.D)||Gdx.input.isKeyPressed(Keys.RIGHT))camera.translate(5,0);
		camera.update();
		if(!Gdx.input.isButtonPressed(Buttons.LEFT)&&leftWasPressed)
			new GravityPoint(startX, startY, 10, 10, -(startX-(Gdx.input.getX()+camera.position.x-Gdx.graphics.getWidth()/2))/70,-(startY-(Gdx.input.getY()+camera.position.y-Gdx.graphics.getHeight()/2))/70);
		if(!Gdx.input.isButtonPressed(Buttons.LEFT)){startX=Gdx.input.getX()+camera.position.x-Gdx.graphics.getWidth()/2; startY=Gdx.input.getY()+camera.position.y-Gdx.graphics.getHeight()/2;}
		leftWasPressed=Gdx.input.isButtonPressed(Buttons.LEFT);
	}
	@Override
	public void render() {
		//clear
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		//input
		handleInput();
		if(!paused)GravityPoint.updateAll();
		//actual render
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeType.Line);
		if(Gdx.input.isButtonPressed(Buttons.LEFT))
			shapeRenderer.line(startX, startY, Gdx.input.getX()+camera.position.x-Gdx.graphics.getWidth()/2, Gdx.input.getY()+camera.position.y-Gdx.graphics.getHeight()/2);
		GravityPoint.drawAll(shapeRenderer);
		shapeRenderer.end();
	}
	
	@Override
	public void resize(int width, int height) {
		camera.setToOrtho(true,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
