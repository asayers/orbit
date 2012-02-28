package uk.co.oumu.orbit;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {

	public static FileHandle tempdir;
	
	public static BitmapFont font;
	
	public static Music music;
	public static Sound ding;
	
	public static Texture planets;
	public static Texture ui;
	public static TextureRegion textbox;
		
	
	public static void loadAssets() {
		
//		tempdir = Gdx.files.external(".tmp");
//		tempdir.mkdirs();
				
		// Sound		
//		FileHandle path_music = tempdir.child("music.mp3");
//	    Gdx.files.internal("data/snd/music.mp3").copyTo(path_music);
//		music = Gdx.audio.newMusic(path_music);
//		music.setLooping(true);
//		music.setVolume(0.5f);
		

		font = new BitmapFont();
		
		// Images
		planets = new Texture(Gdx.files.internal("data/img/planets.png"));
		ui = new Texture(Gdx.files.internal("data/img/ui.png"));
		textbox = new TextureRegion(ui, 0, 0, 16, 16);
		
	}
	
	public static void disposeAssets() {
		planets.dispose();
//		tempdir.deleteDirectory();
	}
}
