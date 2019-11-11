package components;

import Models.Model;
import framework.Vector;
import javafx.geometry.Point3D;

public class Object implements Comparable<Object>{

	public HitBox3D hitBox;
	public Model model;
	public Vector direction;
	public Point3D pos;
	public boolean solid;
	public boolean cylandrical;
	Point3D playerPos= new Point3D(0,0,0);
	public boolean accessory;

	public Object(double x, double y, double z, Model model, boolean solid,
			boolean cylandrical,boolean accessory, Vector direction) {
		this.model = model;
		this.direction = direction;
		pos = new Point3D(x, y, z);
		this.solid = solid;
		this.cylandrical = cylandrical;
		this.accessory = accessory;
		
		if (solid) {
			if (cylandrical) {
				hitBox = new HitBox3D(pos,
						Math.abs(model.highest * model.scale) + Math.abs(model.lowest * model.scale),
						Math.abs(model.rightest * model.scale) + Math.abs(model.leftest * model.scale), 0.0, new Vector(0, 0, 1));
			} else {
				hitBox = new HitBox3D(new Point3D(model.scale * (model.rightest - model.leftest) / 2,
						model.scale * (model.highest - model.lowest) / 2, model.scale * (model.forwardest - model.backest) / 2),
						Math.abs(model.highest * model.scale) + Math.abs(model.lowest * model.scale),
						Math.abs(model.rightest * model.scale) + Math.abs(model.leftest * model.scale),
						Math.abs(model.forwardest * model.scale) + Math.abs(model.backest * model.scale), new Vector(0, 0, 1));
			} // model.scale * (rightest - leftest) / 2, model.scale * (highest - lowest) /
				// 2,
				// scale * (forwardest - backest) / 2
		}
	}
	public void update(Point3D pos){
		playerPos= pos;
	}

	@Override
	public int compareTo(Object o) {
		int pos = (int)Math.sqrt(Math.pow(this.pos.getX()-playerPos.getX(),2)+Math.pow(this.pos.getY()-playerPos.getY(),2)+Math.pow(this.pos.getZ()-playerPos.getZ(),2));
		int pos2 = (int)Math.sqrt(Math.pow(o.pos.getX()-playerPos.getX(),2)+Math.pow(o.pos.getY()-playerPos.getY(),2)+Math.pow(o.pos.getZ()-playerPos.getZ(),2));
		return pos2-pos;
	}
}
