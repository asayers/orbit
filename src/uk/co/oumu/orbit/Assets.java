package uk.co.oumu.orbit;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {

	public static BitmapFont font;
	
	public static Texture planets;
	public static Texture ui;
	public static TextureRegion textbox;
		
	
	public static void loadAssets() {

		font = new BitmapFont();
		
		// Images
		planets = new Texture(Gdx.files.internal("data/img/planets.png"));
		ui = new Texture(Gdx.files.internal("data/img/ui.png"));
		textbox = new TextureRegion(ui, 0, 0, 16, 16);
		
	}
	
	public static void disposeAssets() {
		planets.dispose();
		ui.dispose();
	}
}
