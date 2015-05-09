package com.kwrl.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Timer;
import com.kwrl.models.factories.BallFactory;

public class SpawnPoint extends RectangleToken {

	protected BallFactory factory;
	protected float lastSpawn, spawnInterval;
	protected Timer timer;
	protected boolean spawning;

	public SpawnPoint(Vector2 position, Vector2 dimensions, BallFactory factory) {
		super(new Texture("stone.png"), position, dimensions, BodyType.StaticBody);
		this.factory = factory;
		
		startSpawning(5);
	}
	
	public void startSpawning(int interval) {
		timer = new Timer();
		spawning = true;
		timer.scheduleTask(new Timer.Task() {
			@Override
			public void run() {
				if(spawning) {
					factory.createInstance(body.getPosition());
					timer.scheduleTask(this, 5);
				}
			}
		}, 5);
	}
	
	public void stopSpawning() {
		spawning = false;
	}

	@Override
	public void draw(Batch batch, Body body) {
		super.draw(batch, body);
	}
}
