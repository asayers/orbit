package uk.co.oumu.orbit;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game extends com.badlogic.gdx.Game {
	
	public static final Game GAME = new Game();
	public static OrthographicCamera UI_CAM;
	public static OrthographicCamera GAME_CAM;
	public static SpriteBatch BATCH;
	public static float ZOOM = 1f;
	public static int WIDTH;
	public static int HEIGHT;
	
	
	public static void updateCameras() {
		WIDTH = (int) (Gdx.graphics.getWidth()*ZOOM);
		HEIGHT = (int) (Gdx.graphics.getHeight()*ZOOM);
		
		UI_CAM.zoom = ZOOM;
		UI_CAM.update();
		GAME_CAM.zoom = ZOOM;
		GAME_CAM.update();
		BATCH.getProjectionMatrix().set(UI_CAM.combined);
	}
	
	@Override
	public void create() {
		
		Assets.loadAssets();
		
		init();
		Simulation sim = new Simulation();
		setScreen(sim);
		sim.render(Gdx.graphics.getDeltaTime());
		setScreen(new Help(sim));
		
	}
	
	public void init() {

		ZOOM = 1f;
		UI_CAM = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		GAME_CAM = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		BATCH = new SpriteBatch();
		updateCameras();
		
//		Assets.music.play();
	}
	
	@Override
	public void dispose() {
		
		Assets.disposeAssets();
	}
}
