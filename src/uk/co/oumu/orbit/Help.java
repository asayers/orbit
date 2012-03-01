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
			"  Press Space to restart the simulation (randomises planet locations and masses). Use the arrow keys or w,a,s,d to move the camera. Press Enter to add planets randomly, or use the mouse to place them manually. Press f to toggle whether the camera follows the target body. Use n, m to decrement and increment the precision (warning: framerate drops if you go too high). Use j, k to change the colour-depth of the field visualisation. Use comma to slow the simulation and dot and slash to speed it up, and press p to pause it (warning: at high speeds, approaching bodies tend to slingshot rather than coalesce). Press h to view this help screen. Press Escape to quit.\n" +
			"  Press any key to return to the simulation.\n" +
			"\n" +
			"Author:\n" +
			"  Alex Sayers (alex.sayers@gmail.com)\n"+
			"  https://github.com/asayers/orbit";
	
	
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
		
	}


	@Override
	public void pause() {
		
	}


	@Override
	public void resume() {
		
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
