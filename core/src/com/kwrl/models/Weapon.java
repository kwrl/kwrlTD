package com.kwrl.models;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Timer;

public class Weapon extends CircleToken {
	protected DangerZone dangerZone;
	protected Body rangeSector;
	protected Timer timer;
	protected float range, radius;
	protected boolean firing;
	
	@Override
	public void setFixtureValues() {
		this.density = 1.0f;
		this.friction = 1.0f;
		this.restitution = 1.0f;
	}

	public Weapon(Vector2 position, float radius, float range) {
		super(new Texture("dangerzone.png"), position, radius, BodyType.StaticBody);
		this.range = range;
		this.radius = radius;

		this.dangerZone = new DangerZone(this);
		this.startFiring(1);
	}

	public void startFiring(int interval) {
		timer = new Timer();
		firing = true;

		timer.scheduleTask(new Timer.Task() {
			@Override
			public void run() {
				if (firing) {
					if (!dangerZone.getTargets().isEmpty()) {
						dangerZone.getTargets().get(0).takeDamage(20);
					}
					timer.scheduleTask(this, 1);
				}
			}
		}, 1);
	}

	public class DangerZone extends CircleToken {
		protected ArrayList<Ball> targets = new ArrayList<Ball>();

		public DangerZone(Weapon weapon) {
			super(new Texture("dangerzone.png"), weapon.body.getPosition(), weapon.range, BodyType.StaticBody, true);
		}

		public void addTarget(Ball ball) {
			if (!targets.contains(ball)) {
				this.targets.add(ball);
			}
		}

		public void removeTarget(Ball ball) {
			this.targets.remove(ball);
		}

		public ArrayList<Ball> getTargets() {
			return this.targets;
		}
	}
}
