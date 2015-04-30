package com.kwrl;

import screens.GameScreen;

import com.badlogic.gdx.Game;

public class KwrlTD extends Game {
	
	@Override
	public void create () {
		this.setScreen(new GameScreen());
	}

	@Override
	public void render () {
		super.render();
	}
}
