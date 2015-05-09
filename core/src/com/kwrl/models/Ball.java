package com.kwrl.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.kwrl.models.abstracts.CircleToken;

public class Ball extends CircleToken {

	@Override
	public void setFixtureValues() {
		this.friction = 1f;
		this.restitution = 1f;
		this.density = 1f;
	}
	
	public Ball(Vector2 position, float radius) {
		super(new Texture("ball.png"), position, radius);
	}
}
