package Models;

import java.util.ArrayList;
import java.util.List;

import components.HitBox3D;
import framework.Vector;
import javafx.geometry.Point3D;

public class Model {
	public List<Point3D> vertices = new ArrayList<Point3D>();
	public List<Vector> normals = new ArrayList<Vector>();
	public List<Point3D> perSpaceVerts = new ArrayList<Point3D>();
	public List<Point3D> texture = new ArrayList<Point3D>();
	public List<Face> faces = new ArrayList<Face>();
	//public Point3D location = new Point3D(0,0,0);
	public double scale;
	public Vector direction;//forward
	public HitBox3D hitBox;
	public double highest, lowest, forwardest, backest, rightest, leftest;
}
