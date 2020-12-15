package com.mygdx.ebbirari.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.ebbirari.game.Ebbirari;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = 960;
		config.width = 1600;
		config.foregroundFPS = 120;
		new LwjglApplication(new Ebbirari(), config);
	}
}
