package framework;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import javax.swing.JPanel;

import Models.Face;
import Models.Image;
import Models.Model;
import Models.ModelLoader;
import components.Character;
import components.HitBox3D;
import components.LightSource;
import components.Object;
import components.World;
import components.WorldCreator;
import javafx.geometry.Point3D;

public class Main extends JPanel {

	int width, height;
	//ArrayList<Object> objects = new ArrayList<Object>();
	long start = System.nanoTime();
	Robot r;
	Point mousePos = new Point((int) width / 2, (int) height / 2);
	boolean paused = false;
	Image i = new Image("src/resources/brickTile.jpg");
	BufferedImage image;
	/*
	Character player;
	LightSource sun;
	ArrayList<HitBox3D> hitBoxes = new ArrayList<HitBox3D>();
	*/
	World world;
	double fps= System.nanoTime();

	Main(int width, int height) {
		this.width = width;
		this.height = height;

		inputController c = new inputController();
		addKeyListener(c);
		addMouseListener(c);
		addMouseMotionListener(c);
		setCursor();
		c.mousePos = new Point(width / 2, height / 2);
		
		Thread load = new Thread() {
			public void run() {
						
			}
		};
		load.start();
		
		world = new World();
		world = WorldCreator.create(2000,0,0,0.2,0);		
		world.player = new Character(0, 900, 0, 35, 8);
		world.sun = new LightSource(new Point3D(200, 200, 200), 255);

		try {
			r = new Robot();
		} catch (AWTException e) {

		}
		Thread main = new Thread() {
			public void run() {

				long last = System.nanoTime();

				long now;
				double ns = 1000000000.0;
				double idealFps = 60.0;
				double deltatime = 0;

				while (paused == false) {
					now = System.nanoTime();
					deltatime += ((now - last) / (ns / idealFps));
					last = now;
					repaint();
					if (deltatime >= 1) {
						
						c.updateMousePos(r, world.player, width, height);
						world.player.updateControls(c.A, c.D, c.W, c.S, c.arrowUp, c.arrowDown, c.comma, c.period, c.L, c.M,
								c.space);/*
						// give player access to each hitBox
						// sun.pos = player.Loc3D;
						for (Object o : world.objects) {
							hitBoxes.add(o.hitBox);
						}
						player.moveCamera(hitBoxes);
						if (c.shift) {
							player.moveSpeed = 10;
						} else {
							player.moveSpeed = 2;
							player.fall(hitBoxes);
						}
						
						for (int k=world.objects.size()/2 ;k<world.objects.size();k++) {
							world.hitBoxes.add(world.objects.get(k).hitBox);
						}*/
						
						if (world.objects.isEmpty() == false) {
							for (Object object : world.objects) {
								if (object.accessory)
									object.pos = new Point3D(world.player.Loc3D.getX() - 7, world.player.Loc3D.getY() + 1,
											world.player.Loc3D.getZ() - 15);
							}
						}
			
						deltatime--;
						
					}

				}
			}
		};
		
		Thread main2 = new Thread() {
			public void run() {

				long last = System.nanoTime();

				long now;
				double ns = 1000000000.0;
				double idealFps = 60.0;
				double deltatime = 0;

				while (paused == false) {
					now = System.nanoTime();
					deltatime += ((now - last) / (ns / idealFps));
					last = now;
					//repaint();
					if (deltatime >= 1) {

						//c.updateMousePos(r, player, width, height);
						//player.updateControls(c.A, c.D, c.W, c.S, c.arrowUp, c.arrowDown, c.comma, c.period, c.L, c.M,
						//		c.space);
						// give player access to each hitBox
						// sun.pos = player.Loc3D;
						for (Object o : world.objects) {
							world.hitBoxes.add(o.hitBox);
						}
						
						//for (int k=0 ;k<world.objects.size()/2;k++) {
						//	world.hitBoxes.add(world.objects.get(k).hitBox);
						//}
						world.player.moveCamera(world.hitBoxes);
						if (c.shift) {
							world.player.moveSpeed = 10;
						} else {
							world.player.moveSpeed = 2;
							world.player.fall(world.hitBoxes);
						}
						/*
						if (world.objects.isEmpty() == false) {
							for (Object object : world.objects) {
								if (object.accessory)
									object.pos = new Point3D(player.Loc3D.getX() - 7, player.Loc3D.getY() + 1,
											player.Loc3D.getZ() - 15);
							}
						}
						*/
						 
							deltatime--;

					}

				}
			}
		};
		
		
		main.start();
		main2.start();
	}

	public void paintComponent(Graphics g) {
		if (image == null) {
			image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		}
		
		
		Graphics2D g3 = image.createGraphics();
		// g3.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		// RenderingHints.VALUE_ANTIALIAS_ON);
		g3.setColor(Color.cyan);
		g3.fillRect(0, 0, width, height);
		ArrayList<Object> viewobj = new ArrayList<Object>(world.objects);
		Collections.sort(viewobj);
		for (Object object : viewobj) {
			if (object != null) {
				
				try {
					Collections.sort(object.model.faces);
					
				} catch (java.lang.IllegalArgumentException e) {
				}
				object.update(world.player.Loc3D);
				if(object.model.faces.size()>5)
					
				
				
				
				for (Face f : object.model.faces) {
					
					if(f== object.model.faces.get(0)==false){
						
					
					f.update(object.pos, world.player.Loc3D);
					
					LinkedList<Point3D> points = new LinkedList<Point3D>();

					Point3D val1 = f.vertices[0];
					Point3D val2 = f.vertices[1];
					Point3D val3 = f.vertices[2];

					if (object.accessory) {
						val1 = rotate(new Point3D(0, 0, 0), val1, -world.player.getCameraHA(), world.player.getCameraVA(), true);
						val2 = rotate(new Point3D(0, 0, 0), val2, -world.player.getCameraHA(), world.player.getCameraVA(), true);
						val3 = rotate(new Point3D(0, 0, 0), val3, -world.player.getCameraHA(), world.player.getCameraVA(), true);
					}

					Vector norm1 = f.normals[0];
					Vector norm2 = f.normals[1];
					Vector norm3 = f.normals[2];

					points.add(val1);
					points.add(val2);
					points.add(val3);
					draw3d(points, object.pos, world.player.getCameraVA(),
							world.player.getCameraHA() + 180, new Vector((norm1.x + norm2.x + norm3.x) / 3,
									(norm1.y + norm2.y + norm3.y) / 3, (norm1.z + norm2.z + norm3.z) / 3),
							g3, Color.black);
				}}

			}
		}
		//viewobj.clear();
		g3.setColor(Color.black);
		g3.drawString("Fps: " + String.valueOf((int) (1 / ((System.nanoTime() - start) / 1000000000.0))), 20, 20);
		start = System.nanoTime();
		g3.drawString(String.valueOf((int) world.player.Loc3D.getX() + ", " + (int) world.player.Loc3D.getY()) + ", "
				+ (int) world.player.Loc3D.getZ(), 20, 35);
		g3.drawString("VSpeed: " + String.valueOf(world.player.vSpeed), 20, 50);
		g3.drawString("Grounded: " + String.valueOf(world.player.grounded), 20, 65);
		g.drawImage(image, 0, 0, null);
		
	}

	public void draw3d(LinkedList<Point3D> points, Point3D loc, double vAngle, double hAngle, Vector normal, Graphics g,
			Color c) {
		double shadow = 255;

		Point3D Dist;
		boolean draw = true;

		int size = points.size();
		int[] screenX = new int[size];
		int[] screenY = new int[size];

		int co = 0;
		ArrayList<Point3D> newPoints = new ArrayList<Point3D>();

		for (Point3D pt : points) {
			// getting the distance from the character

			double horzAngle, vertAngle;
			pt = pt.add(loc);
			newPoints.add(pt);

			Dist = pt.subtract(new Point3D(world.player.Loc3D.getX(),
					world.player.Loc3D.getY() + 35 / 33.1 * world.player.hitBox.height - world.player.hitBox.height / 2,
					world.player.Loc3D.getZ()));

			Point3D newPoint = rotate(new Point3D(0, 0, 0), Dist, hAngle, vAngle, false);
			double xDist = newPoint.getX();
			double yDist = newPoint.getY();
			double zDist = newPoint.getZ();

			vertAngle = Math.toDegrees(Math.atan2(yDist, zDist));
			horzAngle = Math.toDegrees(Math.atan2(xDist, zDist));

			if (horzAngle > 90 || horzAngle <= -90 || vertAngle > 90 || vertAngle < -90)
				draw = false;
			if (Math.sqrt(Math.pow(zDist, 2) + Math.pow(yDist, 2)) > 3000)
				draw = false;

			screenY[co] = height / 2 - (int) (750
					* ((Math.tan(Math.toRadians(vertAngle))) / (Math.tan(Math.toRadians(world.player.vertFOV)))));
			screenX[co] = width / 2 + (int) ((width / 2)
					* ((Math.tan(Math.toRadians(horzAngle))) / (Math.tan(Math.toRadians(world.player.horFOV)))));

			co++;

		}
		Point3D average = new Point3D((newPoints.get(0).getX() + newPoints.get(1).getX() + newPoints.get(2).getX()) / 3,
				(newPoints.get(0).getY() + newPoints.get(1).getY() + newPoints.get(2).getY()) / 3,
				(newPoints.get(0).getZ() + newPoints.get(1).getZ() + newPoints.get(2).getZ()) / 3);
		double diffuse = (direction(average, world.sun.pos).standardify().dot(normal) + 1) * world.sun.intensity / 2;
		g.setColor(new Color((int) diffuse, (int) diffuse, (int) diffuse));
		// g.setColor(c);
		// show lines
		if (draw) {
			// g.drawLine(screenX[0], screenY[0], screenX[1], screenY[1]);
			// g.drawLine(screenX[1], screenY[1], screenX[2], screenY[2]);
			// g.drawLine(screenX[2], screenY[2], screenX[0], screenY[0]);
			g.fillPolygon(screenX, screenY, points.size());
		}
	}

	public void addNotify() {
		super.addNotify();
		requestFocus();
	}

	public Vector direction(Point3D p1, Point3D p2) {
		return (new Vector(p2.getX() - p1.getX(), p2.getY() - p1.getY(), p2.getZ() - p1.getZ()));
	}

	public Point3D rotate(Point3D origin, Point3D point, double hAngle, double vAngle, boolean vOverh) {

		double cost = Math.cos(Math.toRadians(hAngle));
		double sint = Math.sin(Math.toRadians(hAngle));

		double newX = point.getX();
		double newY = point.getY();
		double newZ = point.getZ();

		double x = point.getX();
		double y = point.getY();
		double z = point.getZ();

		if (vOverh) {
			y = (Math.cos(Math.toRadians(-vAngle)) * newY - Math.sin(Math.toRadians(-vAngle)) * newZ);
			z = (Math.sin(Math.toRadians(-vAngle)) * newY + Math.cos(Math.toRadians(-vAngle)) * newZ);
		} else {
			x = (cost * newX - sint * newZ + origin.getX());
			z = (sint * newX + cost * newZ + origin.getZ());
		}
		newZ = z;

		if (vOverh) {
			x = (cost * newX - sint * newZ + origin.getX());
			z = (sint * newX + cost * newZ + origin.getZ());

		} else {
			y = (Math.cos(Math.toRadians(-vAngle)) * newY - Math.sin(Math.toRadians(-vAngle)) * newZ);
			z = (Math.sin(Math.toRadians(-vAngle)) * newY + Math.cos(Math.toRadians(-vAngle)) * newZ);
		}
		return new Point3D(x, y, z);
	}

	public void setCursor() {

		int Xpnts[] = { 0, 14 * 4, 20 * 4, 24 * 4 };
		int Ypnts[] = { 0, 24 * 4, 20 * 4, 14 * 4 };
		int Xpnts2[] = { 4 * 4, 16 * 4, 18 * 4, 22 * 4 };
		int Ypnts2[] = { 4 * 4, 22 * 4, 18 * 4, 16 * 4 };

		BufferedImage image = new BufferedImage(24 * 4, 24 * 4, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();
		// g2.rotate(Math.toRadians(180), 12*4, 12*4);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);// soft
																								// borders
		g2.setColor(new Color(80, 80, 80));
		// g2.fillPolygon(Xpnts, Ypnts, 4);
		g2.setColor(new Color(110, 110, 110));
		// g2.fillPolygon(Xpnts2, Ypnts2, 4);
		g2.dispose();
		Point hotSpot = new Point(0, 0);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Cursor cursor = tk.createCustomCursor(image, hotSpot, "custom pointer");
		setCursor(cursor);

	}
	public double[] getFPS(double lastTime){
		return new double[]{System.nanoTime(),1/((System.nanoTime()-lastTime)/1000000000.0)};
		
	}
}
