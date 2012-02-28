package uk.co.oumu.orbit;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;


public class Simulation implements Screen, InputProcessor {
		
	public int PRECISION = 5;
	public float colours = 10;
	public Stage stage;
	public float[][] potential;
	public Pixmap pixmap;
	public Texture background;
	public Body target;
	public boolean following = true;
	
	public Simulation() {

		// Set up the UI camera
		Game.UI_CAM.translate(-Game.WIDTH/2, -Game.HEIGHT/2, 0);
		Game.UI_CAM.zoom = (float) (1.0/PRECISION);
		Game.UI_CAM.update();
		Game.BATCH.getProjectionMatrix().set(Game.UI_CAM.combined);
		
		// Set up the stage
		stage = new Stage(Game.WIDTH, Game.HEIGHT, false);
		stage.setCamera(Game.GAME_CAM);
		target = new Body(0,0);
		
		pixmap = new Pixmap(Game.WIDTH, Game.HEIGHT, Pixmap.Format.RGB888);
		background = new Texture(pixmap);
		
		// Populate the stage
		stage.addActor(target);
		stage.addActor(new Mass());
		stage.addActor(new Mass());
		stage.addActor(new Mass());
		stage.addActor(new Mass());
				
	}

	@Override
	public void render(float delta) {
				
		// Act
		
		if(Gdx.input.isKeyPressed(Input.Keys.D)) {
			Game.GAME_CAM.translate(delta*100, 0, 0);
			if(following == true) {
				following = false;
			}
		}
		if(Gdx.input.isKeyPressed(Input.Keys.A)) {
			Game.GAME_CAM.translate(-delta*100, 0, 0);
			if(following == true) {
				following = false;
			}
		}
		if(Gdx.input.isKeyPressed(Input.Keys.W)) {
			Game.GAME_CAM.translate(0, delta*100, 0);
			if(following == true) {
				following = false;
			}
		}
		if(Gdx.input.isKeyPressed(Input.Keys.S)) {
			Game.GAME_CAM.translate(0, -delta*100, 0);
			if(following == true) {
				following = false;
			}
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.J)) {
			colours /= 0.85;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.K)) {
			colours *= 0.85;
		}
		
		if(following == true) {
			Vector3 translation = new Vector3(target.x - (Game.GAME_CAM.position.x), target.y - (Game.GAME_CAM.position.y), 0);
			Game.GAME_CAM.translate(translation.x, translation.y, translation.z);
		}
		
		// This doesn't do anything, but it might if I were to properly synchronise the target body and the game camera
		stage.act(delta);

		List<Actor> actors = stage.getActors();
		potential = new float[Game.WIDTH/PRECISION][Game.HEIGHT/PRECISION];
		pixmap.dispose();
		pixmap = new Pixmap(Game.WIDTH, Game.HEIGHT, Pixmap.Format.RGB888);
		for(int j = 0; j < Game.HEIGHT/PRECISION; j++) {
			for(int i = 0; i < Game.WIDTH/PRECISION; i++) {
				for(int k = 0; k < actors.size(); k++) {
					if(actors.get(k).getClass().equals(Mass.class)) {
						Mass mass = (Mass) actors.get(k);
						potential[i][j] -= (float) (mass.mass / Math.hypot(mass.x - Game.GAME_CAM.position.x + Game.WIDTH/2 - i*PRECISION, mass.y - Game.GAME_CAM.position.y + Game.HEIGHT/2 - j*PRECISION));
					}
				}
				pixmap.setColor(0, 0, -potential[i][j]/colours, 1);
				pixmap.drawRectangle(i, pixmap.getHeight() - j, PRECISION, PRECISION);
			}
		}
		background.dispose();
		background = new Texture(pixmap);
		
		
		// Draw
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		Game.BATCH.disableBlending();
		Game.BATCH.begin();
		Game.BATCH.draw(background, (float) (-Game.WIDTH*(1 + (1.0/PRECISION))/2 + PRECISION/6), (float) (-Game.HEIGHT*(1 + (1.0/PRECISION))/2  + PRECISION));
		Game.BATCH.end();
		Game.BATCH.enableBlending();
		
		stage.draw();
		
	}

	@Override
	public void resize(int width, int height) {		
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(this);

	}

	@Override
	public void hide() {		
	}

	@Override
	public void pause() {		
	}

	@Override
	public void resume() {		
	}

	@Override
	public void dispose() {		
		stage.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Input.Keys.ESCAPE) {
			Gdx.app.exit();
			return true;
		}
		
		if(keycode == Input.Keys.SPACE) {
			Game.GAME.init();
			Game.GAME.setScreen(new Simulation());
			return true;
		}
		
		if(keycode == Input.Keys.H) {
			Game.GAME.setScreen(new Help(this));
			return true;
		}
		
		if(keycode == Input.Keys.N) {
			PRECISION += 1;
			
			Game.UI_CAM.zoom = (float) (1.0/PRECISION);
			Game.UI_CAM.update();
			Game.BATCH.getProjectionMatrix().set(Game.UI_CAM.combined);
			return true;
		}
		if(keycode == Input.Keys.M) {
			if(PRECISION > 1) {
				PRECISION -= 1;
			}
			
			Game.UI_CAM.zoom = (float) (1.0/PRECISION);
			Game.UI_CAM.update();
			Game.BATCH.getProjectionMatrix().set(Game.UI_CAM.combined);
			return true;
		}
		
		if(keycode == Input.Keys.F) {
			following = !following;
			return true;
		}
		
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		return false;
	}

	@Override
	public boolean touchMoved(int x, int y) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
