package uk.co.oumu.orbit;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;


public class Body extends Actor {
	
	float x;
	float y;
	Vector2 v;
	float r;
	float mass;
	Sprite sprite;
	Simulation sim;
	
	public Body() {
		this((float) ((Math.random() - 0.5)*Game.WIDTH) + Game.GAME_CAM.position.x, (float) ((Math.random() - 0.5)*Game.HEIGHT) + Game.GAME_CAM.position.y);
	}
	
	public Body(float x, float y) {
		this(x, y, new Vector2((float) (Math.random()-0.5), (float) (Math.random()-0.5)), (float) (Math.random()*500 + 100));
	}
	
	public Body(Vector2 v) {
		this((float) ((Math.random() - 0.5)*Game.WIDTH) + Game.GAME_CAM.position.x, (float) ((Math.random() - 0.5)*Game.HEIGHT) + Game.GAME_CAM.position.y, v, (float) (Math.random()*500 + 100));
	}
	
	public Body(float x, float y, Vector2 v, float mass) {
		this.x = x;
		this.y = y;
		this.v = v;
		this.mass = mass;
		this.r = (float) Math.cbrt(mass/800);
		this.sprite = new Sprite(Assets.planets, 0, 0, 64, 64);		
		sprite.setScale(r);
				
		System.out.println("Planet created at ("+x+","+y+") with mass "+mass);
	}
	
	@Override
	public void act(float delta) {

		// Calculate the local potential
		sim = (Simulation) Game.GAME.getScreen();
		float[][] pot = new float[3][3];
		List<Actor> actors = sim.stage.getActors();

		for(int k = 0; k < actors.size(); k++) {
			if(actors.get(k).getClass().equals(Body.class) && !actors.get(k).equals(this)) {
				Body other = (Body) actors.get(k);
				
				if((Math.hypot(other.x - x, other.y - y) > (r + other.r)*8)) {
					pot[0][1] -= (float) (other.mass / Math.hypot(other.x - x + sim.PRECISION, other.y - y));
					pot[2][1] -= (float) (other.mass / Math.hypot(other.x - x - sim.PRECISION, other.y - y));
					pot[1][0] -= (float) (other.mass / Math.hypot(other.x - x, other.y - y + sim.PRECISION));
					pot[1][2] -= (float) (other.mass / Math.hypot(other.x - x, other.y - y - sim.PRECISION));
				} else {
					// Coalesce
					sim.stage.removeActor(this);
					sim.stage.removeActor(other);
					Body coalescance = new Body((r*x + other.r*other.x)/(r + other.r), (r*y+other.r*other.y)/(r + other.r), new Vector2((mass*v.x + other.mass*other.v.x)/(mass + other.mass), (mass*v.y + other.mass*other.v.y)/(mass + other.mass)), this.mass+other.mass);
					sim.stage.addActor(coalescance);
					if(sim.target.equals(this) || sim.target.equals(other)) {
						sim.target = coalescance;
					}
				}
			}
		}
		
		// Accelerate
		v.x += (pot[0][1] - pot[2][1])*sim.SPEED/sim.PRECISION;
		v.y += (pot[1][0] - pot[1][2])*sim.SPEED/sim.PRECISION;

		// Move
		x += v.x * delta * sim.SPEED;
		y += v.y * delta * sim.SPEED;			
		
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		
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
