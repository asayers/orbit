package uk.co.oumu.orbit;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;


public class Simulation implements Screen, InputProcessor {
		
	public int PRECISION = 5;
	public int SPEED = 2;
	public float colours = 10;
	public Stage stage;
	public float[][] potential;
	public Pixmap pixmap;
	public Texture background;
	public Body target;
	public boolean following = false;
	public boolean paused = false;
	
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
		stage.addActor(new Body());
		stage.addActor(new Body());
		stage.addActor(new Body());
		stage.addActor(new Body());
				
	}

	@Override
	public void render(float delta) {
				
		// Act
		
		// Input Handling
		handleInput(delta);
		
		// Update the bodies
		if(paused==false) {
			stage.act(delta);
		}
		
		// Move the camera to follow the target
		if(following == true) {
			Vector3 translation = new Vector3(target.x - (Game.GAME_CAM.position.x), target.y - (Game.GAME_CAM.position.y), 0);
			Game.GAME_CAM.translate(translation.x, translation.y, translation.z);
		}
		
		// Calculate the potential
		List<Actor> actors = stage.getActors();
		potential = new float[Game.WIDTH/PRECISION][Game.HEIGHT/PRECISION];
		pixmap.dispose();
		pixmap = new Pixmap(Game.WIDTH, Game.HEIGHT, Pixmap.Format.RGB888);
		for(int j = 0; j < Game.HEIGHT/PRECISION; j++) {
			for(int i = 0; i < Game.WIDTH/PRECISION; i++) {
				for(int k = 0; k < actors.size(); k++) {
					if(actors.get(k).getClass().equals(Body.class)) {
						Body body = (Body) actors.get(k);
						potential[i][j] -= (float) (body.mass / Math.hypot(body.x - Game.GAME_CAM.position.x + Game.WIDTH/2 - i*PRECISION, body.y - Game.GAME_CAM.position.y + Game.HEIGHT/2 - j*PRECISION));
					}
				}
				// Draw the pixmap
				pixmap.setColor(0, 0, -potential[i][j]/colours, 1);
				pixmap.drawRectangle(i, pixmap.getHeight() - j, PRECISION, PRECISION);
			}
		}
		background.dispose();
		background = new Texture(pixmap);
		
		
		// Draw
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		// Draw the background
		Game.BATCH.disableBlending();
		Game.BATCH.begin();
		Game.BATCH.draw(background, (float) (-Game.WIDTH*(1 + (1.0/PRECISION))/2 + PRECISION/6), (float) (-Game.HEIGHT*(1 + (1.0/PRECISION))/2  + PRECISION));
		Game.BATCH.end();
		Game.BATCH.enableBlending();

		// Draw the bodies
		stage.draw();
		
	}
	
	public void handleInput( float delta) {
		// Move the camera
				if(Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
					Game.GAME_CAM.translate(delta*200, 0, 0);
					if(following == true) {
						following = false;
					}
				}
				if(Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
					Game.GAME_CAM.translate(-delta*200, 0, 0);
					if(following == true) {
						following = false;
					}
				}
				if(Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) {
					Game.GAME_CAM.translate(0, delta*200, 0);
					if(following == true) {
						following = false;
					}
				}
				if(Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
					Game.GAME_CAM.translate(0, -delta*200, 0);
					if(following == true) {
						following = false;
					}
				}
				
				// Change colour depth
				if(Gdx.input.isKeyPressed(Input.Keys.J)) {
					colours /= 0.85;
				}
				if(Gdx.input.isKeyPressed(Input.Keys.K)) {
					colours *= 0.85;
				}
				
				// Change simulation speed
				if(Gdx.input.isKeyPressed(Input.Keys.COMMA)) {
					SPEED = 1;
				} else if(Gdx.input.isKeyPressed(Input.Keys.PERIOD)) {
					SPEED = 8;
				} else if(Gdx.input.isKeyPressed(Input.Keys.SLASH)) {
					SPEED = 16;
				} else {
					SPEED = 3;
				}
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
		if(keycode == Input.Keys.ENTER) {
			stage.addActor(new Body());
			return true;
		}
		if(keycode == Input.Keys.P) {
			paused = !paused;
		}
		
		if(keycode == Input.Keys.O) {
			stage.clear();
			stage.addActor(new Body(50, 0, new Vector2(0, 8f), 250));
			stage.addActor(new Body(-50, 0, new Vector2(0, -8f), 250));
			return true;
		}
		if(keycode == Input.Keys.I) {
			stage.clear();
			stage.addActor(new Body(new Vector2()));
			stage.addActor(new Body(new Vector2()));
			stage.addActor(new Body(new Vector2()));
			stage.addActor(new Body(new Vector2()));
			stage.addActor(new Body(new Vector2()));
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
		Vector3 pos = new Vector3(x, y, 0);
		Game.GAME_CAM.unproject(pos);
		stage.addActor(new Body(pos.x, pos.y));
		return true;
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
