package com.kwrl.models.factories;

import com.badlogic.gdx.math.Vector2;
import com.kwrl.models.Ball;
import com.kwrl.models.Token;

public class BallFactory {
	protected float radius;
	
	public BallFactory(float radius) {
		this.radius = radius;
	}

	public Token createInstance(Vector2 position) {
		return new Ball(position, radius);
	}

}
