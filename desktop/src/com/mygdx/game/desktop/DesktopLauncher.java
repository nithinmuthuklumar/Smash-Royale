package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.SmashRoyale;

import static com.mygdx.game.SmashRoyale.screenH;
import static com.mygdx.game.SmashRoyale.screenW;
public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.foregroundFPS=60;
		config.width= screenW;
		config.height= screenH;
		config.resizable = false;

		new LwjglApplication(new SmashRoyale(), config);
	}
}
