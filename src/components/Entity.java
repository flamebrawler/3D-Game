package components;

import java.util.ArrayList;

import javafx.geometry.Point3D;

public class Entity {

	public double gravity = .17;
	public HitBox3D hitBox;
	public Point3D Loc3D;
	public double vSpeed = 0;
	public boolean grounded = false;

	public void fall(ArrayList<HitBox3D> hitBoxes) {
		boolean collided = false;
		HitBox3D keyhb = new HitBox3D(null, 0, 0, 0, null);
		for (HitBox3D hb : hitBoxes) {
			if (hb != null) {
				if (grounded) {
					if (hitBox.moved(0, -gravity, 0).collidesWith(hb)) {
						collided = true;
						keyhb = hb;
					}
				} else if (hitBox.moved(0, -vSpeed, 0).collidesWith(hb)) {
					collided = true;
					keyhb = hb;
				}
			}
		}
		if (collided) {
			vSpeed = 0;
			grounded = true;
			for (HitBox3D y = hitBox; y.moved(0, -gravity, 0).collidesWith(keyhb) == false; y
					.updatePos(y.Origin.add(new Point3D(0, -gravity, 0)), y.direction)) {
				hitBox = y;
				Loc3D = y.Origin;
			}

		} else {
			grounded = false;
			Loc3D = Loc3D.subtract(new Point3D(0, vSpeed, 0));
			if (vSpeed + gravity < hitBox.height)
				vSpeed += gravity;
		}
	}

	public void jump(double force) {
		if (grounded) {
			vSpeed = -force;
			grounded = false;
		}
	}
}
