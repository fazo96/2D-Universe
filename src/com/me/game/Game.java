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
	private boolean paused = false;
	public float lineStartX, lineStartY;
	private InputHandler inputHandler;
	private static Game game;

	@Override
	public void create() {
		game = this;
		camera = new OrthographicCamera(1, Gdx.graphics.getHeight()
				/ Gdx.graphics.getWidth());
		shapeRenderer = new ShapeRenderer();
		Gdx.input.setInputProcessor(inputHandler=new InputHandler());
	}

	@Override
	public void dispose() {
		shapeRenderer.dispose();
	}

	@Override
	public void render() {
		//logic
		if (!paused)
			GravityPoint.updateAll();
		camera.update();
		inputHandler.loop(); //some input code
		// clear
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		// actual render
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeType.Line);
		if (Gdx.input.isButtonPressed(Buttons.LEFT))
			shapeRenderer.line(
					lineStartX,
					lineStartY,
					Gdx.input.getX() + camera.position.x
							- Gdx.graphics.getWidth() / 2,
					Gdx.input.getY() + camera.position.y
							- Gdx.graphics.getHeight() / 2);
		GravityPoint.drawAll(shapeRenderer);
		shapeRenderer.end();
	}

	@Override
	public void resize(int width, int height) {
		camera.setToOrtho(true, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	public OrthographicCamera getCamera() {
		return camera;
	}

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	public static Game get() {
		return game;
	}
}
