package com.kwrl.models.factories;

import com.badlogic.gdx.math.Vector2;
import com.kwrl.models.Ball;
import com.kwrl.models.GameObject;

public class BallFactory extends GameObjectFactory {
	protected float radius;
	
	public BallFactory(float radius) {
		this.radius = radius;
	}

	@Override
	public GameObject createInstance(Vector2 position) {
		return new Ball(position, radius);
	}

}
