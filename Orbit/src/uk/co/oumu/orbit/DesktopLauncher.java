package uk.co.oumu.orbit;


import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class DesktopLauncher {
	
	public static void main(String[] args) {

		new LwjglApplication(Game.GAME, "Orbit", 800, 600, true);
		
	}
}
