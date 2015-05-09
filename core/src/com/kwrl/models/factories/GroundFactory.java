package com.kwrl.models.factories;

import com.badlogic.gdx.math.Vector2;
import com.kwrl.models.Ground;

public class GroundFactory {
	public static Ground createInstance(Vector2 start, Vector2 stop, float width) {
		Vector2 vec = stop.sub(start);
		Ground instance = new Ground(start, new Vector2(vec.len()/2f, width));
		instance.getBody().setTransform(start.add(vec.scl(0.5f)), vec.angleRad());
		return instance;
	}
}
