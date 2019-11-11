package Models;

import framework.Vector;
import javafx.geometry.Point3D;

public class Face implements Comparable<Face> {
	public int[] textures;
	public Vector[] normals;
	public Point3D[] vertices;
	public Point3D objectPos = new Point3D(0, 0, 0);
	public Point3D playerPos = new Point3D(0, 0, 0);

	Face(int[] VertexTexture, Vector[] VertexNormal, Point3D[] vertices) {
		textures = VertexTexture;
		normals = VertexNormal;
		this.vertices = vertices;
	}

	public void update(Point3D object, Point3D pp) {
		playerPos = pp;
		objectPos = object;
	}

	@Override
	public int compareTo(Face f) {
		// TODO Auto-generated method stub
		double x =  Math.abs(this.vertices[0].getX() + this.vertices[1].getX() + this.vertices[2].getX()+objectPos.getX());
		double y = Math.abs(this.vertices[0].getY() + this.vertices[1].getY() + this.vertices[2].getY()+objectPos.getY());
		double z = Math.abs(this.vertices[0].getZ() + this.vertices[1].getZ() + this.vertices[2].getZ()+objectPos.getZ());
		double x2 = Math.abs(f.vertices[0].getX() + f.vertices[1].getX() + f.vertices[2].getX()+objectPos.getX());
		double y2 = Math.abs(f.vertices[0].getY() + f.vertices[1].getY() + f.vertices[2].getY()+objectPos.getY());
		double z2 = Math.abs(f.vertices[0].getZ() + f.vertices[1].getZ() + f.vertices[2].getZ()+objectPos.getZ());

		int dist = (int) Math.sqrt(Math.pow(x - playerPos.getX(), 2) + Math.pow(y - playerPos.getY(), 2)
				+ Math.pow(z - playerPos.getZ(), 2));
		int dist2 = (int) Math.sqrt(Math.pow(x2 - playerPos.getX(), 2) + Math.pow(y2 - playerPos.getY(), 2)
				+ Math.pow(z2 - playerPos.getZ(), 2));
		;
		return dist2 - dist;
	}
}
