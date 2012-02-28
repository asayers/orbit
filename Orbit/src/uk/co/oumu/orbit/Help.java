package uk.co.oumu.orbit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Help implements Screen, InputProcessor {
	
	private Simulation sim;
	private SpriteBatch batch;
	private String text = "Orbit\n" +
			"====\n" +
			"\n" +
			"Simulates the motion of a free body in the classical gravitational field of fixed masses\n" +
			"\n" +
			"Controls:\n" +
			"  Press Space to restart the simulation (randomises planet locations). Use the arrow keys to impart a force to the free body. Use w,a,s,d to move the camera independantly of the body. Press f to toggle whether the camera follows the body. Use n, m to decrement and increment the precision (warning: framerate drops if you go too high). Press h to view this help screen. Press Escape to quit.\n" +
			"  Press any key to return to the simulation.\n" +
			"\n" +
			"Author:\n" +
			"  Alex Sayers (alex.sayers@gmail.com)";
	
	
	public Help(Simulation sim) {
		this.sim = sim;
		
		batch = new SpriteBatch();
	}
	
	public void drawText() {
		
	}
	
	
	// Screen methods:
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render(float delta) {
		batch.begin();
		
		float w = Assets.font.getWrappedBounds(text, 600).width;
		float h = Assets.font.getWrappedBounds(text, 600).height;
		int x = (int)(Gdx.graphics.getWidth()-w)/2;
		int y = (int)(Gdx.graphics.getHeight()+h)/2;
		
		batch.draw(Assets.textbox, x-16, y-h-16, w+32, h+32);
		
		Assets.font.setColor(0,0,0,1);
		Assets.font.setScale(1);
		Assets.font.drawWrapped(batch, text, x, y, 600);
		
		batch.end();
	}
	
	@Override
	public void hide() {
		
	}
	
	@Override
	public void dispose() {
		batch.dispose();
	}
	
	
	// Input processor methods:

	public boolean keyDown(int keycode) {

		Game.GAME.setScreen(sim);
		dispose();

		return true;
	}


	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}


	

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchMoved(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
