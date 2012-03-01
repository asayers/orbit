/* 
 * 									Orbit
 * 							by Alex Sayers, 29/2/12
 * 
 * This program simulates the motion of massive bodies using a numerical
 * approximation to Newton's gravitational field equations. Usage notes
 * can be in README.md, Help.java, or on the web.
 * 
 * Webpage: https://github.com/asayers/orbit
 * Contact: alex.sayers@gmail.com
 * 
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program. If not, see http://www.gnu.org/licenses/
 * 
 */

package uk.co.oumu.orbit;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class DesktopLauncher {
	
	public static void main(String[] args) {

		new LwjglApplication(Game.GAME, "Orbit", 800, 600, true);
		
	}
}
