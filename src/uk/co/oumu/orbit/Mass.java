package uk.co.oumu.orbit;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;


public class Mass extends Actor {

	float x;
	float y;
	float mass;
	Sprite sprite;
	
	public Mass() {
		this((float) ((Math.random() - 0.5)*Game.WIDTH), (float) ((Math.random() - 0.5)*Game.HEIGHT), (float) (Math.random()*1000));
		
	}
	
	public Mass(float x, float y, float mass) {
		this.x = x;
		this.y = y;
		this.mass = mass;
		this.sprite = new Sprite(Assets.planets, 0, 0, 64, 64);
		
		System.out.println("Planet created at ("+x+","+y+") with mass "+mass);
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		
		// TODO: Make size proportional to mass
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
