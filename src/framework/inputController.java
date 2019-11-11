package framework;

import java.awt.Point;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import components.Character;

public class inputController implements KeyListener, MouseListener, MouseMotionListener{
	
	boolean A = false;
	boolean D = false;
	boolean W = false;
	boolean S = false;
	boolean arrowUp = false;
	boolean arrowDown = false;
	boolean comma = false;
	boolean period = false;
	boolean L = false;
	boolean M = false;
	boolean space = false;
	boolean shift= false;
	Point mousePos;
	
	public void updateMousePos(Robot r, Character player, int width, int height){
		//moves the way you are looking relative to mouse position
		player.cameraHA += (mousePos.getX() - width/2) / 8;
		//prevent character from looking too high/low
		
		if ((player.cameraVA >= 90 && -(mousePos.getY() - height/2) / 12 < 0)
				|| (player.cameraVA <= -90 && -(mousePos.getY() - height/2) / 12 > 0)) {

		} else {
			player.cameraVA += (mousePos.getY() - height/2) / 12;
		}
		
		//reset mouse position to center
		r.mouseMove((int) width/2, (int) height/2);
		
	}
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		mousePos = new Point(e.getX(), e.getY());
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_A) {
			A = true;
		}
		if (key == KeyEvent.VK_D) {
			D = true;
		}
		if (key == KeyEvent.VK_S) {
			S = true;
		}
		if (key == KeyEvent.VK_W) {
			W = true;
		}
		if (key == KeyEvent.VK_UP) {
			arrowUp = true;
		}
		if (key == KeyEvent.VK_DOWN) {
			arrowDown = true;
		}
		if (key == KeyEvent.VK_COMMA) {
			comma = true;
		}
		if (key == KeyEvent.VK_L) {
			L = true;
		}
		if (key == KeyEvent.VK_M) {
			M = true;
		}
		if (key == KeyEvent.VK_PERIOD) {
			period = true;
		}
		if (key == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
		if (key == KeyEvent.VK_SPACE) {
			space = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_A) {
			A = false;
		}
		if (key == KeyEvent.VK_D) {
			D = false;
		}
		if (key == KeyEvent.VK_S) {
			S = false;
		}
		if (key == KeyEvent.VK_W) {
			W = false;
		}
		if (key == KeyEvent.VK_UP) {
			arrowUp = false;
		}
		if (key == KeyEvent.VK_DOWN) {
			arrowDown = false;
		}
		if (key == KeyEvent.VK_COMMA) {
			comma = false;
		}
		if (key == KeyEvent.VK_L) {
			L = false;
		}
		if (key == KeyEvent.VK_M) {
			M = false;
		}
		if (key == KeyEvent.VK_PERIOD) {
			period = false;
		}
		if (key == KeyEvent.VK_SPACE) {
			space = false;
		}
		if (key == KeyEvent.VK_SHIFT) {
			if(shift){
			shift = false;
			}else{
				shift = true;
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
