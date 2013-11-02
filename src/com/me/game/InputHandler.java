package com.me.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;

public class InputHandler implements InputProcessor {
	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.ESCAPE)
			;
		if (keycode == Keys.R)
			GravityPoint.destroyAll();
		if (keycode == Keys.P)
			Game.get().setPaused(!Game.get().isPaused());
		if (keycode == Keys.W || keycode == Keys.UP)
			Game.get().getCamera().translate(0, -5);
		if (Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.DOWN))
			Game.get().getCamera().translate(0, 5);
		if (Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT))
			Game.get().getCamera().translate(-5, 0);
		if (Gdx.input.isKeyPressed(Keys.D)
				|| Gdx.input.isKeyPressed(Keys.RIGHT))
			Game.get().getCamera().translate(5, 0);
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {

		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (button == Buttons.LEFT)
			new GravityPoint(Game.get().lineStartX, Game.get().lineStartY,
					Math.random() * 25 + 10, Math.random() * 25 + 10,
					-(Game.get().lineStartX - (screenX
							+ Game.get().getCamera().position.x - Gdx.graphics
							.getWidth() / 2)) / 70,
					-(Game.get().lineStartY - (screenY
							+ Game.get().getCamera().position.y - Gdx.graphics
							.getHeight() / 2)) / 70);
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		if (!Gdx.input.isButtonPressed(Buttons.LEFT)) {
			Game.get().lineStartX = screenX + Game.get().getCamera().position.x
					- Gdx.graphics.getWidth() / 2;
			Game.get().lineStartY = screenY + Game.get().getCamera().position.y
					- Gdx.graphics.getHeight() / 2;
		}
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
