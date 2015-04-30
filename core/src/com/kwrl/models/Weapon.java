package com.kwrl.models;

import net.dermetfan.gdx.graphics.g2d.Box2DSprite;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class Weapon extends Box2DSprite {
	public static Weapon activeWeapon;
	protected Body rangeSector;
	
	public Weapon(Vector2 position, float range) {
	}
}
