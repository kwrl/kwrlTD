package com.kwrl.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Ball extends CircleToken {
	public Ball(Vector2 position, float radius) {
		super(new Texture("ball.png"), position, radius);
	}
}
