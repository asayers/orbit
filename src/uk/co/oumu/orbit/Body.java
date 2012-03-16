package uk.co.oumu.orbit;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;


public class Body extends Actor {
	
	// Velocity will be between + and - def_v. Mass will be between def_m[0] and def_m[1]
	static final float def_v = 5;
	static final float[] def_m = {100,500};
	
	float x;
	float y;
	int i;		// The closest grid square, as numbered from the viewport position
	int j;
	Vector2 v;
	float r;
	float mass;
	Sprite sprite;
	Simulation sim;
	
	public Body() {
		this((float) ((Math.random() - 0.5)*Game.WIDTH) + Game.GAME_CAM.position.x, (float) ((Math.random() - 0.5)*Game.HEIGHT) + Game.GAME_CAM.position.y);
	}
	
	public Body(float x, float y) {
		this(x, y, new Vector2((float) (Math.random()*2 - 1)*def_v, (float) (Math.random()*2 - 1)*def_v), (float) (Math.random()*(def_m[1]-def_m[0]) + def_m[0]));
	}
	
	public Body(Vector2 v) {
		this((float) ((Math.random() - 0.5)*Game.WIDTH) + Game.GAME_CAM.position.x, (float) ((Math.random() - 0.5)*Game.HEIGHT) + Game.GAME_CAM.position.y, v, (float) (Math.random()*(def_m[1]-def_m[0]) + def_m[0]));
	}
	
	public Body(float x, float y, Vector2 v, float mass) {
		this.x = x;
		this.y = y;
		this.v = v;
		this.mass = mass;
		this.r = (float) Math.cbrt(mass/1000);
		this.sprite = new Sprite(Assets.planets, 0, 0, 64, 64);		
		sprite.setScale(r);
		sim = (Simulation) Game.GAME.getScreen();
				
		System.out.println("Body created at ("+x+","+y+") with mass "+mass);
	}
	
	@Override
	public void act(float delta) {
		
		// Update some variables
		sim = (Simulation) Game.GAME.getScreen();		
		i = (int) Math.floor((x	- Game.GAME_CAM.position.x + Game.WIDTH/2)/sim.PRECISION);
		j = (int) Math.floor((y - Game.GAME_CAM.position.y + Game.HEIGHT/2)/sim.PRECISION);

		
		// Calculate the local potential
		float[][] pot = new float[3][3];
		List<Actor> actors = sim.stage.getActors();

		for(int k = 0; k < actors.size(); k++) {
			if(actors.get(k).getClass().equals(Body.class) && !actors.get(k).equals(this)) {
				Body other = (Body) actors.get(k);
				
				if((Math.hypot(other.x - x, other.y - y) > (r + other.r)*8)) {

				} else {
					// Coalesce
					sim.stage.removeActor(this);
					sim.stage.removeActor(other);
					Body coalescance = new Body((r*x + other.r*other.x)/(r + other.r), (r*y+other.r*other.y)/(r + other.r), new Vector2((mass*v.x + other.mass*other.v.x)/(mass + other.mass), (mass*v.y + other.mass*other.v.y)/(mass + other.mass)), (mass+other.mass));
					sim.stage.addActor(coalescance);
					if(sim.target.equals(this) || sim.target.equals(other)) {
						sim.target = coalescance;
					}
				}
			}
		}
		
		
		if(i >= 0 && i < Game.WIDTH/sim.PRECISION && j >= 0 && j < Game.HEIGHT/sim.PRECISION) {
			pot[0][1] = sim.potential[i-1][j];
			pot[2][1] = sim.potential[i+1][j];
			pot[1][0] = sim.potential[i][j-1];
			pot[1][2] = sim.potential[i][j+1];
		} else {
			// Perhaps it would be better to kill off-screen entities?
			
			for(int k = 0; k < actors.size(); k++) {
				if(actors.get(k).getClass().equals(Body.class) && !actors.get(k).equals(this)) {
					Body other = (Body) actors.get(k);
					pot[0][1] -= (float) (other.mass / Math.hypot(other.x - x + sim.PRECISION, other.y - y));
					pot[2][1] -= (float) (other.mass / Math.hypot(other.x - x - sim.PRECISION, other.y - y));
					pot[1][0] -= (float) (other.mass / Math.hypot(other.x - x, other.y - y + sim.PRECISION));
					pot[1][2] -= (float) (other.mass / Math.hypot(other.x - x, other.y - y - sim.PRECISION));
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
//		sprite.draw(batch);
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
