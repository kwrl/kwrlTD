package com.kwrl.models.factories;

import com.badlogic.gdx.math.Vector2;
import com.kwrl.models.GameObject;

public abstract class GameObjectFactory {
	public abstract GameObject createInstance(Vector2 position);
}
