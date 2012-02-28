package uk.co.oumu.orbit;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;


public class Body extends Actor {
	
	float x;
	float y;
	Vector2 v;
	Sprite sprite;
	
	public Body() {
		this((float) ((Math.random() - 0.5)*Game.WIDTH), (float) ((Math.random() - 0.5)*Game.HEIGHT));
		
	}
	
	public Body(float x, float y) {
		this.x = x;
		this.y = y;
		this.v = new Vector2();
		this.sprite = new Sprite(Assets.planets, 64, 0, 64, 64);
		
		System.out.println("Body created at ("+x+","+y+")");
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		
		Simulation sim = (Simulation) Game.GAME.getScreen();

		int i = (int) ((x - Game.GAME_CAM.position.x + Game.WIDTH/2) / sim.PRECISION);
		int j = (int) ((y - Game.GAME_CAM.position.y + Game.HEIGHT/2) / sim.PRECISION);

		if((i-1)>=0 && (i+1)<Game.WIDTH/sim.PRECISION && (j-1)>=0 && (j+1)<Game.HEIGHT/sim.PRECISION) {
			v.x += (sim.potential[i-1][j] - sim.potential[i+1][j]);
			v.y += (sim.potential[i][j-1] - sim.potential[i][j+1]);
			
			List<Actor> actors = sim.stage.getActors();
			for(int k = 0; k < actors.size(); k++) {
				if(actors.get(k).getClass().equals(Mass.class)) {
					Mass mass = (Mass) actors.get(k);
					if(Math.hypot(mass.x - x, mass.y - y) < 52) {
						// TODO: make bodies bounce off masses (tricky)
						v.x = 0;
						v.y = 0;
					}
				}
			}
			
			if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
				v.x -= Gdx.graphics.getDeltaTime()*20;
			}
			if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
				v.x += Gdx.graphics.getDeltaTime()*20;
			}
			if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
				v.y += Gdx.graphics.getDeltaTime()*20;
			}
			if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
				v.y -= Gdx.graphics.getDeltaTime()*20;
			}
			
			x += v.x/2;
			y += v.y/2;
		}
		
		sprite.setPosition(x - sprite.getWidth()/2, y - sprite.getHeight()/2);
		sprite.draw(batch);

	}

	@Override
	public boolean touchDown(float x, float y, int pointer) {
		return false;
	}

	@Override
	public void touchUp(float x, float y, int pointer) {
		
	}

	@Override
	public void touchDragged(float x, float y, int pointer) {
		
	}

	@Override
	public Actor hit(float x, float y) {
		return null;
	}

	
	
}
